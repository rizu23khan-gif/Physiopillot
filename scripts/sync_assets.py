#!/usr/bin/env python3
import os
import sys
import json
import shutil

def check_report_for_errors(report_path, error_keys):
    """
    Checks a validation JSON report for any error indications.
    Returns (has_errors, list_of_errors)
    """
    if not os.path.exists(report_path):
        return False, []
        
    try:
        with open(report_path, 'r', encoding='utf-8') as f:
            data = json.load(f)
            
        # If the report itself is a list (like yaml_validation_report.json),
        # any entry means there is an error
        if isinstance(data, list):
            return len(data) > 0, data
            
        # For dictionaries, check specific key-lengths
        errors = []
        for key in error_keys:
            val = data.get(key, [])
            if isinstance(val, list) and len(val) > 0:
                errors.extend(val)
            elif isinstance(val, dict) and len(val) > 0:
                errors.append(val)
                
        return len(errors) > 0, errors
    except Exception as e:
        return True, [f"Could not read report {report_path}: {str(e)}"]

def main(generated_dir="generated_json", app_assets_dir="app/src/main/assets"):
    # Check all validation reports
    reports_to_check = [
        ("/validation/yaml_validation_report.json", []),
        ("/validation/citation_report.json", ["missingCitations"]),
        ("/validation/relationship_report.json", ["brokenLinks", "duplicates", "circularDependencies"]),
        ("/validation/asset_report.json", ["missingMedia", "invalidExtensions"])
    ]
    
    any_failures = False
    for path, err_keys in reports_to_check:
        failed, errs = check_report_for_errors(path, err_keys)
        if failed:
            print(f"  [BLOCKED] Cannot sync assets: Validation report '{path}' contains active errors:", file=sys.stderr)
            for e in errs[:5]:
                print(f"    - {str(e)}", file=sys.stderr)
            if len(errs) > 5:
                print(f"    ... and {len(errs) - 5} more errors.", file=sys.stderr)
            any_failures = True
            
    if any_failures:
        print("Sync aborted due to validation failures. Run validation and resolve all issues first.", file=sys.stderr)
        return 1
        
    # Proceed to Sync
    print("Zero errors in reports. Proceeding with synchronized copy of validated assets...")
    
    src_content = os.path.join(generated_dir, "content")
    dst_content = os.path.join(app_assets_dir, "content")
    
    if os.path.exists(src_content):
        # We recursively copy subjects from generated content to app assets content
        for subject_dir in os.listdir(src_content):
            src_sub = os.path.join(src_content, subject_dir)
            
            # Map subject directories to what the Android app expects
            target_dirs = [subject_dir]
            if subject_dir == "biomechanics_kinesiology":
                target_dirs.append("biomechanics")
            elif subject_dir == "exercise_therapy_i":
                target_dirs.append("exercise_therapy_1")
            elif subject_dir == "electrotherapy_i":
                target_dirs.append("electrotherapy_1")
                
            for t_dir in target_dirs:
                dst_sub = os.path.join(dst_content, t_dir)
                if os.path.isdir(src_sub):
                    os.makedirs(dst_sub, exist_ok=True)
                    for f_name in os.listdir(src_sub):
                        shutil.copy2(os.path.join(src_sub, f_name), os.path.join(dst_sub, f_name))
                    print(f"  Synced content subject: '{t_dir}' (from '{subject_dir}')")
                
    # Copy aggregate files into app assets
    aggregates = ["search_index.json", "revision_notes.json"]
    for agg in aggregates:
        src_agg = os.path.join(generated_dir, agg)
        dst_agg = os.path.join(app_assets_dir, agg)
        if os.path.exists(src_agg):
            shutil.copy2(src_agg, dst_agg)
            print(f"  Synced aggregate asset: '{agg}'")
            
    print("Synchronization completed successfully!")
    return 0

if __name__ == "__main__":
    sys.exit(main())
