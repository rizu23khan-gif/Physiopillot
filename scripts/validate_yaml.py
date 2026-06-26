#!/usr/bin/env python3
import os
import sys
import json
import yaml
from jsonschema import Draft7Validator, exceptions

class LineLoader(yaml.SafeLoader):
    """
    Custom SafeLoader that attaches line numbers to parsed dictionary keys and values
    for precise error tracing.
    """
    def construct_mapping(self, node, deep=False):
        mapping = super().construct_mapping(node, deep=deep)
        # Store line numbers for keys
        line_info = {}
        for k, v in node.value:
            if isinstance(k, yaml.ScalarNode):
                line_info[k.value] = k.start_mark.line + 1
        mapping['__line_info__'] = line_info
        mapping['__line__'] = node.start_mark.line + 1
        return mapping

def find_line_number(data, path):
    """
    Helper to trace the exact line number of a schema violation using path nodes.
    """
    current = data
    line = 1
    for part in path:
        if isinstance(current, dict):
            # Try to get key-specific line info
            line_info = current.get('__line_info__', {})
            if part in line_info:
                line = line_info[part]
            elif '__line__' in current:
                line = current['__line__']
            
            # Navigate deeper
            current = current.get(part)
        elif isinstance(current, list):
            # Lists don't map keys directly, fall back to list's line
            if hasattr(current, '__line__'):
                line = current.__line__
            try:
                current = current[int(part)]
            except (ValueError, IndexError):
                break
        else:
            break
    return line

def clean_annotated_dict(data):
    """
    Recursively strip out the __line__ and __line_info__ metadata before validation/saving.
    """
    if isinstance(data, dict):
        return {k: clean_annotated_dict(v) for k, v in data.items() if k not in ('__line__', '__line_info__')}
    elif isinstance(data, list):
        return [clean_annotated_dict(item) for item in data]
    return data

def validate_file(file_path, schema):
    errors = []
    filename = os.path.basename(file_path)
    
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            annotated_data = yaml.load(f, Loader=LineLoader)
            
        if not annotated_data:
            return [{"filename": filename, "line": 1, "field": "root", "message": "File is empty or invalid YAML."}]
            
        # Strip metadata for validation
        clean_data = clean_annotated_dict(annotated_data)
        
        # Validate
        validator = Draft7Validator(schema)
        schema_errors = sorted(validator.iter_errors(clean_data), key=lambda e: e.path)
        
        for err in schema_errors:
            path_str = ".".join(map(str, err.path)) or "root"
            line_num = find_line_number(annotated_data, err.path)
            
            # Human-friendly suggestions based on paths
            suggestion = ""
            if "status" in path_str:
                suggestion = " (Allowed values: draft, review, published)"
            elif "difficulty" in path_str:
                suggestion = " (Allowed values: Easy, Medium, Hard)"
            elif "mcqs" in path_str and "options" in path_str:
                suggestion = " (Must have exactly 4 items)"
            
            errors.append({
                "filename": filename,
                "line": line_num,
                "field": path_str,
                "message": f"{err.message}{suggestion}"
            })
            
    except yaml.YAMLError as ye:
        line = 1
        if hasattr(ye, 'problem_mark'):
            line = ye.problem_mark.line + 1
        errors.append({
            "filename": filename,
            "line": line,
            "field": "yaml_syntax",
            "message": f"YAML Syntax Error: {str(ye)}"
        })
    except Exception as e:
        errors.append({
            "filename": filename,
            "line": 1,
            "field": "system",
            "message": f"Unexpected error reading file: {str(e)}"
        })
        
    return errors

def main(target_dir="/master_knowledge", schema_path="/schemas/core_schema.json"):
    # Ensure directories exist
    os.makedirs(target_dir, exist_ok=True)
    
    if not os.path.exists(schema_path):
        print(f"Schema file not found at {schema_path}", file=sys.stderr)
        return 1
        
    with open(schema_path, 'r', encoding='utf-8') as sf:
        schema = json.load(sf)
        
    yaml_files = [os.path.join(target_dir, f) for f in os.listdir(target_dir) if f.endswith(('.yaml', '.yml'))]
    
    if not yaml_files:
        print(f"No YAML files found in {target_dir}")
        return 0
        
    all_errors = []
    for yf in yaml_files:
        file_errors = validate_file(yf, schema)
        all_errors.extend(file_errors)
        
    # Write report to validation folder
    os.makedirs("/validation", exist_ok=True)
    report_path = "/validation/yaml_validation_report.json"
    with open(report_path, 'w', encoding='utf-8') as rf:
        json.dump(all_errors, rf, indent=2)
        
    if all_errors:
        print(f"YAML Validation failed with {len(all_errors)} issues:")
        for err in all_errors:
            print(f"  [ERROR] File: {err['filename']} | Line: {err['line']} | Field: {err['field']} | Message: {err['message']}")
        return 1
    else:
        print("All YAML files validated successfully!")
        return 0

if __name__ == "__main__":
    sys.exit(main())
