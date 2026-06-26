#!/usr/bin/env python3
import os
import sys
import json
import yaml
import re

def extract_cited_codes(chapter_data):
    """
    Scans a chapter YAML for citation codes.
    Checks the 'references' array, but also scans text fields for [CODE] pattern.
    """
    cited = set()
    
    # Check the standard 'references' array
    references = chapter_data.get('references', [])
    for ref in references:
        if not isinstance(ref, str):
            continue
        # Check if reference starts with a code like "KISNER_2017: Chapter 1"
        match = re.match(r'^([A-Z0-9_]+)\s*:', ref)
        if match:
            cited.add(match.group(1))
        else:
            # Fallback: scan for any words matching uppercase code pattern
            for token in re.findall(r'\b([A-Z][A-Z0-9_]{3,})\b', ref):
                cited.add(token)
                
    # Proactive scanning: find any [CITATION_CODE] in definitions or coreConcepts
    def scan_recursive(item):
        if isinstance(item, str):
            # Find bracketed codes like [KISNER_2017]
            for code in re.findall(re.compile(r'\[([A-Z0-9_]{4,})\]'), item):
                cited.add(code)
        elif isinstance(item, dict):
            for v in item.values():
                scan_recursive(v)
        elif isinstance(item, list):
            for i in item:
                scan_recursive(i)
                
    scan_recursive(chapter_data)
    return cited

def main(bib_path="/citation_registry/bibliography.yaml", knowledge_dir="/master_knowledge"):
    os.makedirs("/validation", exist_ok=True)
    report_path = "/validation/citation_report.json"
    
    if not os.path.exists(bib_path):
        print(f"Bibliography file not found at {bib_path}", file=sys.stderr)
        return 1
        
    with open(bib_path, 'r', encoding='utf-8') as bf:
        bib_data = yaml.safe_load(bf) or {}
        
    bib_codes = set(bib_data.keys())
    
    # Track citation usage
    chapter_citations = {}
    all_used_codes = set()
    missing_citations = []
    
    if os.path.exists(knowledge_dir):
        yaml_files = [f for f in os.listdir(knowledge_dir) if f.endswith(('.yaml', '.yml'))]
    else:
        yaml_files = []
        
    for yf in yaml_files:
        file_path = os.path.join(knowledge_dir, yf)
        try:
            with open(file_path, 'r', encoding='utf-8') as f:
                chapter_data = yaml.safe_load(f)
                
            if not chapter_data:
                continue
                
            chapter_id = chapter_data.get('metadata', {}).get('chapterId', yf)
            cited_codes = extract_cited_codes(chapter_data)
            chapter_citations[chapter_id] = list(cited_codes)
            all_used_codes.update(cited_codes)
            
            # Check for missing citations
            for code in cited_codes:
                if code not in bib_codes:
                    missing_citations.append({
                        "chapterId": chapter_id,
                        "filename": yf,
                        "invalidCode": code,
                        "suggestedFix": f"Add '{code}' definition to bibliography.yaml or fix spelling in chapter references."
                    })
        except Exception as e:
            print(f"Error parsing {yf}: {str(e)}", file=sys.stderr)
            
    unused_bibliography = list(bib_codes - all_used_codes)
    
    report = {
        "totalBibliographyEntries": len(bib_codes),
        "totalCitedCodes": len(all_used_codes),
        "missingCitations": missing_citations,
        "unusedBibliographyEntries": unused_bibliography,
        "chapterCitationMapping": chapter_citations
    }
    
    with open(report_path, 'w', encoding='utf-8') as rf:
        json.dump(report, rf, indent=2)
        
    # Check if we should fail the build
    if missing_citations:
        print(f"Citation validation failed with {len(missing_citations)} invalid references:")
        for mc in missing_citations:
            print(f"  [ERROR] Chapter '{mc['chapterId']}' uses unknown citation code '{mc['invalidCode']}'")
            print(f"    Suggested Fix: {mc['suggestedFix']}")
        return 1
        
    print("Citation validation succeeded!")
    if unused_bibliography:
        print(f"  [INFO] {len(unused_bibliography)} bibliography entries are currently unused: {', '.join(unused_bibliography)}")
    return 0

if __name__ == "__main__":
    sys.exit(main())
