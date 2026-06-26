#!/usr/bin/env python3
import os
import sys
import json
import yaml

def load_content_registry(registry_path="/app/src/main/assets/content_registry.json"):
    if os.path.exists(registry_path):
        try:
            with open(registry_path, 'r', encoding='utf-8') as f:
                return json.load(f)
        except Exception as e:
            print(f"Warning: Could not load content registry from absolute path: {str(e)}")
            
    # Dynamic fallback: search up the parent directory tree
    cur = os.path.abspath(os.path.dirname(__file__))
    for _ in range(5):
        # Check standard app workspace structure
        cand = os.path.join(cur, "app/src/main/assets/content_registry.json")
        if os.path.exists(cand):
            try:
                with open(cand, 'r', encoding='utf-8') as f:
                    return json.load(f)
            except Exception as e:
                print(f"Warning: Could not load content registry from {cand}: {str(e)}")
                
        # Check outer root workspace structure
        cand_outer = os.path.join(cur, "src/main/assets/content_registry.json")
        if os.path.exists(cand_outer):
            try:
                with open(cand_outer, 'r', encoding='utf-8') as f:
                    return json.load(f)
            except Exception as e:
                print(f"Warning: Could not load content registry from {cand_outer}: {str(e)}")
                
        parent = os.path.dirname(cur)
        if parent == cur:
            break
        cur = parent
        
    return []

def get_target_filename(chapter_id, registry_list):
    for entry in registry_list:
        if entry.get("chapterId") == chapter_id:
            return entry.get("contentFile")
    return f"{chapter_id}.json"

def clean_for_app(data):
    """
    Strips compilation-only or metadata keys that shouldn't go to the client app
    if needed, but keeps the standard JSON representation expected.
    """
    # Simply clone and clean
    if isinstance(data, dict):
        return {k: clean_for_app(v) for k, v in data.items() if not k.startswith('__') and k not in ('versioning',)}
    elif isinstance(data, list):
        return [clean_for_app(item) for item in data]
    return data

def main(knowledge_dir="master_knowledge", output_dir="generated_json"):
    os.makedirs(output_dir, exist_ok=True)
    
    if not os.path.exists(knowledge_dir):
        print(f"Knowledge directory not found at {knowledge_dir}", file=sys.stderr)
        return 1
        
    yaml_files = [f for f in os.listdir(knowledge_dir) if f.endswith(('.yaml', '.yml'))]
    
    if not yaml_files:
        print("No master YAML files found to compile.")
        return 0
        
    registry_list = load_content_registry()
    print(f"  [DIAGNOSTIC] Loaded registry list of size: {len(registry_list)}")
    
    # Storage for aggregates
    all_mcqs = []
    all_viva = []
    all_pyqs = []
    all_flashcards = []
    search_index = []
    revision_notes = {}
    
    # Create content output directory
    content_out_base = os.path.join(output_dir, "content")
    os.makedirs(content_out_base, exist_ok=True)
    
    compiled_count = 0
    for yf in yaml_files:
        file_path = os.path.join(knowledge_dir, yf)
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                chapter_data = yaml.safe_load(f)
                
            if not chapter_data:
                continue
                
            metadata = chapter_data.get('metadata', {})
            chapter_id = metadata.get('chapterId')
            subject_id = metadata.get('subjectId', 'general')
            chapter_name = metadata.get('title', '')
            
            if not chapter_id:
                print(f"Skipping {yf} - missing chapterId")
                continue
                
            # Clean data for application distribution
            app_chapter_data = clean_for_app(chapter_data)
            
            # Map correct filename
            target_file_name = get_target_filename(chapter_id, registry_list)
            
            # Save individual chapter JSON
            if "/" in target_file_name or "\\" in target_file_name:
                chapter_out_path = os.path.join(content_out_base, target_file_name)
            else:
                chapter_out_path = os.path.join(content_out_base, subject_id, target_file_name)
                
            os.makedirs(os.path.dirname(chapter_out_path), exist_ok=True)
            with open(chapter_out_path, 'w', encoding='utf-8') as out_f:
                json.dump(app_chapter_data, out_f, indent=2)
                
            # Process Aggregates
            # 1. MCQs
            mcqs = app_chapter_data.get('mcqs', []) or []
            for m in mcqs:
                m['chapterId'] = chapter_id
                m['subjectId'] = subject_id
                all_mcqs.append(m)
                
            # 2. Viva
            viva = app_chapter_data.get('viva', []) or []
            for v in viva:
                v['chapterId'] = chapter_id
                v['subjectId'] = subject_id
                all_viva.append(v)
                
            # 3. PYQs
            pyqs = app_chapter_data.get('pyqs', []) or []
            for p in pyqs:
                p['chapterId'] = chapter_id
                p['subjectId'] = subject_id
                all_pyqs.append(p)
                
            # 4. Flashcards
            flashcards = app_chapter_data.get('flashcards', []) or []
            for f in flashcards:
                f['chapterId'] = chapter_id
                f['subjectId'] = subject_id
                all_flashcards.append(f)
                
            # 5. Search Index
            search_index.append({
                "chapterId": chapter_id,
                "title": chapter_name,
                "subject": metadata.get('subject', ''),
                "subjectId": subject_id,
                "keywords": metadata.get('keywords', []) or [],
                "definitions": [d[:100] + '...' for d in (app_chapter_data.get('theory', {}).get('definitions', []) or [])]
            })
            
            # 6. Revision Notes
            revision_notes[chapter_id] = {
                "title": chapter_name,
                "subject": metadata.get('subject', ''),
                "definitions": app_chapter_data.get('theory', {}).get('definitions', []) or [],
                "coreConcepts": app_chapter_data.get('theory', {}).get('coreConcepts', []) or [],
                "pearls": app_chapter_data.get('clinicalCorrelations', {}).get('pearls', []) or []
            }
            
            compiled_count += 1
            
        except Exception as e:
            print(f"Error compiling chapter {yf}: {str(e)}", file=sys.stderr)
            return 1
            
    # Save all compiled aggregates
    with open(os.path.join(output_dir, "all_mcqs.json"), 'w', encoding='utf-8') as f:
        json.dump(all_mcqs, f, indent=2)
        
    with open(os.path.join(output_dir, "all_viva.json"), 'w', encoding='utf-8') as f:
        json.dump(all_viva, f, indent=2)
        
    with open(os.path.join(output_dir, "all_pyqs.json"), 'w', encoding='utf-8') as f:
        json.dump(all_pyqs, f, indent=2)
        
    with open(os.path.join(output_dir, "all_flashcards.json"), 'w', encoding='utf-8') as f:
        json.dump(all_flashcards, f, indent=2)
        
    with open(os.path.join(output_dir, "search_index.json"), 'w', encoding='utf-8') as f:
        json.dump(search_index, f, indent=2)
        
    with open(os.path.join(output_dir, "revision_notes.json"), 'w', encoding='utf-8') as f:
        json.dump(revision_notes, f, indent=2)
        
    print(f"Successfully compiled {compiled_count} chapters to production assets.")
    return 0

if __name__ == "__main__":
    sys.exit(main())
