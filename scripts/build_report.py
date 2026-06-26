#!/usr/bin/env python3
import os
import sys
import json
import time

def get_file_size_formatted(path):
    if not os.path.exists(path):
        return "0 B"
    sz = os.path.getsize(path)
    for unit in ['B', 'KB', 'MB']:
        if sz < 1024:
            return f"{sz:.1f} {unit}"
        sz /= 1024
    return f"{sz:.1f} GB"

def main(start_time=None):
    os.makedirs("/build", exist_ok=True)
    report_out = "/build/build_report.html"
    
    # Load reports
    yaml_report = []
    if os.path.exists("/validation/yaml_validation_report.json"):
        with open("/validation/yaml_validation_report.json", 'r', encoding='utf-8') as f:
            yaml_report = json.load(f)
            
    citation_report = {}
    if os.path.exists("/validation/citation_report.json"):
        with open("/validation/citation_report.json", 'r', encoding='utf-8') as f:
            citation_report = json.load(f)
            
    relationship_report = {}
    if os.path.exists("/validation/relationship_report.json"):
        with open("/validation/relationship_report.json", 'r', encoding='utf-8') as f:
            relationship_report = json.load(f)
            
    asset_report = {}
    if os.path.exists("/validation/asset_report.json"):
        with open("/validation/asset_report.json", 'r', encoding='utf-8') as f:
            asset_report = json.load(f)
            
    # Calculate sizes
    mcq_size = get_file_size_formatted("/generated_json/all_mcqs.json")
    viva_size = get_file_size_formatted("/generated_json/all_viva.json")
    search_size = get_file_size_formatted("/generated_json/search_index.json")
    
    # Counts
    total_chapters = relationship_report.get("totalChapters", 0)
    total_mcqs = 0
    total_vivas = 0
    total_pyqs = 0
    total_references = citation_report.get("totalBibliographyEntries", 0)
    
    try:
        if os.path.exists("/generated_json/all_mcqs.json"):
            with open("/generated_json/all_mcqs.json", 'r', encoding='utf-8') as f:
                total_mcqs = len(json.load(f))
        if os.path.exists("/generated_json/all_viva.json"):
            with open("/generated_json/all_viva.json", 'r', encoding='utf-8') as f:
                total_vivas = len(json.load(f))
        if os.path.exists("/generated_json/all_pyqs.json"):
            with open("/generated_json/all_pyqs.json", 'r', encoding='utf-8') as f:
                total_pyqs = len(json.load(f))
    except Exception:
        pass
        
    compilation_time = "N/A"
    if start_time:
        compilation_time = f"{time.time() - start_time:.2f} seconds"
        
    # Validation status
    failed_validations = len(yaml_report) + \
                         len(citation_report.get("missingCitations", [])) + \
                         len(relationship_report.get("brokenLinks", [])) + \
                         len(relationship_report.get("circularDependencies", [])) + \
                         len(asset_report.get("missingMedia", []))
                         
    status_class = "success" if failed_validations == 0 else "danger"
    status_text = "PASSING" if failed_validations == 0 else "FAILING"
    
    html_content = f"""<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>PhysioPilot SSOT Build Report</title>
    <style>
        body {{
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            line-height: 1.6;
            color: #333;
            background-color: #f8f9fa;
            margin: 0;
            padding: 20px;
        }}
        .container {{
            max-width: 1100px;
            margin: 0 auto;
            background: #fff;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0,0,0,0.1);
        }}
        h1, h2, h3 {{
            color: #2c3e50;
        }}
        .header {{
            display: flex;
            justify-content: space-between;
            align-items: center;
            border-bottom: 2px solid #ecf0f1;
            padding-bottom: 15px;
            margin-bottom: 30px;
        }}
        .status-badge {{
            font-weight: bold;
            padding: 8px 16px;
            border-radius: 20px;
            text-transform: uppercase;
        }}
        .status-badge.success {{
            background-color: #d4edda;
            color: #155724;
        }}
        .status-badge.danger {{
            background-color: #f8d7da;
            color: #721c24;
        }}
        .grid {{
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
            gap: 20px;
            margin-bottom: 30px;
        }}
        .card {{
            background: #fdfdfd;
            border: 1px solid #e2e8f0;
            border-radius: 6px;
            padding: 20px;
            text-align: center;
        }}
        .card .value {{
            font-size: 2rem;
            font-weight: bold;
            color: #2c3e50;
            margin-top: 5px;
        }}
        .card .label {{
            font-size: 0.9rem;
            color: #7f8c8d;
            text-transform: uppercase;
        }}
        .section {{
            margin-bottom: 40px;
        }}
        table {{
            width: 100%;
            border-collapse: collapse;
            margin-top: 15px;
        }}
        th, td {{
            text-align: left;
            padding: 12px;
            border-bottom: 1px solid #e2e8f0;
        }}
        th {{
            background-color: #f1f5f9;
            color: #475569;
        }}
        .text-danger {{
            color: #e74c3c;
            font-weight: bold;
        }}
        .text-warning {{
            color: #f39c12;
            font-weight: bold;
        }}
    </style>
</head>
<body>
    <div class="container">
        <div class="header">
            <div>
                <h1>PhysioPilot SSOT Build Report</h1>
                <p style="margin: 0; color: #7f8c8d;">Generated on {time.strftime('%Y-%m-%d %H:%M:%S')}</p>
            </div>
            <span class="status-badge {status_class}">{status_text}</span>
        </div>

        <div class="grid">
            <div class="card">
                <div class="label">Total Chapters</div>
                <div class="value">{total_chapters}</div>
            </div>
            <div class="card">
                <div class="label">Total MCQs</div>
                <div class="value">{total_mcqs}</div>
            </div>
            <div class="card">
                <div class="label">Total Viva Cards</div>
                <div class="value">{total_vivas}</div>
            </div>
            <div class="card">
                <div class="label">Bibliography Entries</div>
                <div class="value">{total_references}</div>
            </div>
        </div>

        <div class="section">
            <h2>Build Performance & Artifacts</h2>
            <table>
                <tr>
                    <th>Metric</th>
                    <th>Value</th>
                </tr>
                <tr>
                    <td>Compilation Duration</td>
                    <td>{compilation_time}</td>
                </tr>
                <tr>
                    <td>MCQ Output Size (all_mcqs.json)</td>
                    <td>{mcq_size}</td>
                </tr>
                <tr>
                    <td>Viva Output Size (all_viva.json)</td>
                    <td>{viva_size}</td>
                </tr>
                <tr>
                    <td>Search Index Size (search_index.json)</td>
                    <td>{search_size}</td>
                </tr>
                <tr>
                    <td>Validation Issues Count</td>
                    <td class="{"text-danger" if failed_validations > 0 else ""}"><strong>{failed_validations} issues</strong></td>
                </tr>
            </table>
        </div>

        <div class="section">
            <h2>Validation Failures ({failed_validations})</h2>
            {f"<p class='text-success'>✓ All validations passed successfully!</p>" if failed_validations == 0 else ""}
            
            {f"<h3>YAML Schema Failures ({len(yaml_report)})</h3>" if yaml_report else ""}
            {f"<table><tr><th>File</th><th>Line</th><th>Field</th><th>Error</th></tr>" if yaml_report else ""}
            {"".join(f"<tr><td>{err['filename']}</td><td>{err['line']}</td><td><code>{err['field']}</code></td><td class='text-danger'>{err['message']}</td></tr>" for err in yaml_report)}
            {f"</table>" if yaml_report else ""}

            {f"<h3>Citation Failures ({len(citation_report.get('missingCitations', []))})</h3>" if citation_report.get('missingCitations') else ""}
            {f"<table><tr><th>Chapter</th><th>Invalid Citation Code</th><th>Suggested Fix</th></tr>" if citation_report.get('missingCitations') else ""}
            {"".join(f"<tr><td>{err['chapterId']}</td><td class='text-danger'>{err['invalidCode']}</td><td>{err['suggestedFix']}</td></tr>" for err in citation_report.get('missingCitations', []))}
            {f"</table>" if citation_report.get('missingCitations') else ""}

            {f"<h3>Broken Relationships ({len(relationship_report.get('brokenLinks', []))})</h3>" if relationship_report.get('brokenLinks') else ""}
            {f"<table><tr><th>Chapter</th><th>Broken ID</th><th>Type</th></tr>" if relationship_report.get('brokenLinks') else ""}
            {"".join(f"<tr><td>{err['fromChapter']}</td><td class='text-danger'>{err['brokenLink']}</td><td>{err['linkType']}</td></tr>" for err in relationship_report.get('brokenLinks', []))}
            {f"</table>" if relationship_report.get('brokenLinks') else ""}
        </div>
    </div>
</body>
</html>
"""
    with open(report_out, 'w', encoding='utf-8') as out_f:
        out_f.write(html_content)
        
    print(f"HTML build report created at: {report_out}")
    return 0

if __name__ == "__main__":
    sys.exit(main())
