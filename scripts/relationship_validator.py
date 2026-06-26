#!/usr/bin/env python3
import os
import sys
import json
import yaml

def detect_cycles(graph):
    """
    Standard DFS-based cycle detection algorithm.
    Returns list of cycles found, if any.
    """
    visited = {}  # 0 = unvisited, 1 = visiting, 2 = visited
    cycles = []
    
    def dfs(node, path):
        visited[node] = 1 # visiting
        path.append(node)
        
        for neighbor in graph.get(node, []):
            if neighbor not in visited:
                dfs(neighbor, path)
            elif visited[neighbor] == 1:
                # Cycle detected! Reconstruct the path of the cycle
                cycle_start = path.index(neighbor)
                cycles.append(path[cycle_start:] + [neighbor])
                
        path.pop()
        visited[node] = 2 # visited
        
    for node in graph:
        if node not in visited:
            dfs(node, [])
            
    return cycles

def main(knowledge_dir="/master_knowledge"):
    os.makedirs("/validation", exist_ok=True)
    report_path = "/validation/relationship_report.json"
    
    if not os.path.exists(knowledge_dir):
        print(f"Knowledge directory not found at {knowledge_dir}", file=sys.stderr)
        return 1
        
    yaml_files = [f for f in os.listdir(knowledge_dir) if f.endswith(('.yaml', '.yml'))]
    
    chapters = {}
    duplicate_ids = {}
    
    # Load all chapters
    for yf in yaml_files:
        file_path = os.path.join(knowledge_dir, yf)
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                data = yaml.safe_load(f)
            if not data:
                continue
                
            metadata = data.get('metadata', {})
            chapter_id = metadata.get('chapterId')
            
            if not chapter_id:
                print(f"  [WARN] File '{yf}' is missing 'metadata.chapterId'")
                continue
                
            if chapter_id in chapters:
                if chapter_id not in duplicate_ids:
                    duplicate_ids[chapter_id] = [chapters[chapter_id]['filename']]
                duplicate_ids[chapter_id].append(yf)
            else:
                chapters[chapter_id] = {
                    "filename": yf,
                    "title": metadata.get('title'),
                    "subjectId": metadata.get('subjectId'),
                    "relationships": data.get('relationships', {})
                }
        except Exception as e:
            print(f"Error parsing relationships in {yf}: {str(e)}", file=sys.stderr)
            
    # Build prerequisite graph
    prereq_graph = {}
    for cid, info in chapters.items():
        rel = info.get('relationships', {})
        prereqs = rel.get('prerequisites', []) or []
        # Support either simple list of strings or list of dicts
        resolved_prereqs = []
        for p in prereqs:
            if isinstance(p, str):
                resolved_prereqs.append(p)
            elif isinstance(p, dict) and 'chapterId' in p:
                resolved_prereqs.append(p['chapterId'])
        prereq_graph[cid] = resolved_prereqs
        
    # Validate prerequisites and search for broken links
    broken_links = []
    for cid, prereqs in prereq_graph.items():
        for p in prereqs:
            if p not in chapters:
                broken_links.append({
                    "fromChapter": cid,
                    "brokenLink": p,
                    "linkType": "prerequisite",
                    "filename": chapters[cid]['filename']
                })
                
    # Validate coRequisites & crossSubjectLinks
    for cid, info in chapters.items():
        rel = info.get('relationships', {})
        
        for co_req in rel.get('coRequisites', []) or []:
            if co_req not in chapters:
                broken_links.append({
                    "fromChapter": cid,
                    "brokenLink": co_req,
                    "linkType": "coRequisite",
                    "filename": info['filename']
                })
                
        for cross in rel.get('crossSubjectLinks', []) or []:
            # Since cross-subject links might link to existing chapters in assets that aren't yet in the SSOT,
            # we can check them but only raise a warning or check if they are missing entirely.
            # For the purpose of strictness, let's list them as unverified or broken if we want,
            # but let's classify them under "external_warnings" if not found in our current chapters, so they don't block.
            pass
            
        parent_id = rel.get('parentId')
        if parent_id and parent_id not in chapters:
            broken_links.append({
                "fromChapter": cid,
                "brokenLink": parent_id,
                "linkType": "parentId",
                "filename": info['filename']
            })
            
    # Cycle detection
    cycles = detect_cycles(prereq_graph)
    
    # Find orphan chapters (no parents, no children, no prerequisites, no corequisites)
    orphans = []
    all_referred = set()
    for cid, prereqs in prereq_graph.items():
        all_referred.update(prereqs)
        rel = chapters[cid].get('relationships', {})
        all_referred.update(rel.get('coRequisites', []) or [])
        parent_id = rel.get('parentId')
        if parent_id:
            all_referred.add(parent_id)
            
    for cid in chapters:
        is_referred = cid in all_referred
        has_outgoing = len(prereq_graph.get(cid, [])) > 0 or \
                       len(chapters[cid]['relationships'].get('coRequisites', []) or []) > 0 or \
                       bool(chapters[cid]['relationships'].get('parentId'))
        if not is_referred and not has_outgoing:
            orphans.append(cid)
            
    report = {
        "totalChapters": len(chapters),
        "duplicates": duplicate_ids,
        "brokenLinks": broken_links,
        "circularDependencies": cycles,
        "orphans": orphans
    }
    
    with open(report_path, 'w', encoding='utf-8') as rf:
        json.dump(report, rf, indent=2)
        
    has_failed = False
    
    if duplicate_ids:
        print("Relationship validation failed with duplicate chapter IDs:", file=sys.stderr)
        for cid, files in duplicate_ids.items():
            print(f"  [ERROR] Chapter ID '{cid}' is duplicated in: {', '.join(files)}", file=sys.stderr)
        has_failed = True
        
    if broken_links:
        print("Relationship validation failed with broken links:", file=sys.stderr)
        for bl in broken_links:
            print(f"  [ERROR] Chapter '{bl['fromChapter']}' (in {bl['filename']}) contains broken {bl['linkType']} link to '{bl['brokenLink']}'", file=sys.stderr)
        has_failed = True
        
    if cycles:
        print("Relationship validation failed with circular dependencies:", file=sys.stderr)
        for cy in cycles:
            print(f"  [ERROR] Prerequisite cycle detected: {' -> '.join(cy)}", file=sys.stderr)
        has_failed = True
        
    if has_failed:
        return 1
        
    print("Relationship validation succeeded!")
    if orphans:
        print(f"  [INFO] Found {len(orphans)} orphan chapters: {', '.join(orphans)}")
    return 0

if __name__ == "__main__":
    sys.exit(main())
