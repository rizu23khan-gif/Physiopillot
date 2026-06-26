#!/usr/bin/env python3
import os
import sys
import json
import yaml

def check_media_file(path_str):
    """
    Search for file in /media, /app/src/main/assets, or absolute paths.
    Returns (exists, size_bytes, error_message).
    """
    candidate_paths = [
        path_str,
        os.path.join("/media", path_str),
        os.path.join("/app/src/main/assets", path_str),
        os.path.join("/app/src/main/assets/images", os.path.basename(path_str))
    ]
    
    for p in candidate_paths:
        if os.path.exists(p) and os.path.isfile(p):
            try:
                sz = os.path.getsize(p)
                return True, sz, None
            except Exception as e:
                return True, 0, f"Error getting size: {str(e)}"
                
    return False, 0, f"File not found in media search paths (checked {', '.join(candidate_paths[:3])})"

def main(knowledge_dir="/master_knowledge"):
    os.makedirs("/validation", exist_ok=True)
    report_path = "/validation/asset_report.json"
    
    if not os.path.exists(knowledge_dir):
        print(f"Knowledge directory not found at {knowledge_dir}", file=sys.stderr)
        return 1
        
    yaml_files = [f for f in os.listdir(knowledge_dir) if f.endswith(('.yaml', '.yml'))]
    
    missing_media = []
    oversized_media = []
    invalid_extensions = []
    total_assets = 0
    valid_extensions = {".png", ".jpg", ".jpeg", ".webp", ".svg", ".gif"}
    MAX_SIZE_BYTES = 1024 * 1024 * 1.5  # 1.5 MB limit
    
    for yf in yaml_files:
        file_path = os.path.join(knowledge_dir, yf)
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                data = yaml.safe_load(f)
            if not data:
                continue
                
            chapter_id = data.get('metadata', {}).get('chapterId', yf)
            media_list = data.get('media', []) or []
            
            for m in media_list:
                if not isinstance(m, dict):
                    continue
                path_str = m.get('path')
                if not path_str:
                    continue
                    
                total_assets += 1
                
                # Check extension
                _, ext = os.path.splitext(path_str.lower())
                if ext not in valid_extensions:
                    invalid_extensions.append({
                        "chapterId": chapter_id,
                        "path": path_str,
                        "extension": ext,
                        "message": f"Invalid extension '{ext}'. Only WebP, PNG, JPG, and SVG are allowed."
                    })
                    
                # Check file existence & size
                exists, size_bytes, err = check_media_file(path_str)
                if not exists:
                    missing_media.append({
                        "chapterId": chapter_id,
                        "path": path_str,
                        "error": err
                    })
                else:
                    if size_bytes > MAX_SIZE_BYTES:
                        oversized_media.append({
                            "chapterId": chapter_id,
                            "path": path_str,
                            "sizeBytes": size_bytes,
                            "sizeMB": round(size_bytes / (1024 * 1024), 2),
                            "message": "Asset is oversized (> 1.5MB). Please compress it for standard mobile builds."
                        })
                        
        except Exception as e:
            print(f"Error parsing media in {yf}: {str(e)}", file=sys.stderr)
            
    report = {
        "totalAssetsScanned": total_assets,
        "missingMediaCount": len(missing_media),
        "oversizedMediaCount": len(oversized_media),
        "invalidExtensionsCount": len(invalid_extensions),
        "missingMedia": missing_media,
        "oversizedMedia": oversized_media,
        "invalidExtensions": invalid_extensions
    }
    
    with open(report_path, 'w', encoding='utf-8') as rf:
        json.dump(report, rf, indent=2)
        
    has_failed = False
    
    # We fail only for missing media or invalid extensions to prevent broken app states,
    # but print warnings for oversized images.
    if missing_media:
        print("Asset validation failed with missing media assets:", file=sys.stderr)
        for mm in missing_media:
            print(f"  [ERROR] Chapter '{mm['chapterId']}' refers to non-existent file: '{mm['path']}'", file=sys.stderr)
        has_failed = True
        
    if invalid_extensions:
        print("Asset validation failed with invalid media extensions:", file=sys.stderr)
        for ie in invalid_extensions:
            print(f"  [ERROR] Chapter '{ie['chapterId']}' uses unsupported extension '{ie['extension']}' for asset '{ie['path']}'", file=sys.stderr)
        has_failed = True
        
    if oversized_media:
        print("Asset validation warnings:")
        for om in oversized_media:
            print(f"  [WARN] Chapter '{om['chapterId']}' asset '{om['path']}' is {om['sizeMB']} MB (Oversized)")
            
    if has_failed:
        return 1
        
    print("Asset validation completed successfully!")
    return 0

if __name__ == "__main__":
    sys.exit(main())
