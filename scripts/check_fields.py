import json
import os

files = [
    ("/app/src/main/assets/content/anatomy/anat_ch9.json", "Anatomy"),
    ("/app/src/main/assets/content/physiology/phys_ch4.json", "Physiology"),
    ("/app/src/main/assets/content/electrotherapy_1/et_el1_ch1.json", "Electrotherapy I"),
    ("/app/src/main/assets/content/electrotherapy_ii/et_el2_ch2.json", "Electrotherapy II"),
    ("/app/src/main/assets/content/biomechanics/bio_ch1.json", "Biomechanics"),
    ("/app/src/main/assets/content/pathology/path_ch1.json", "Pathology"),
    ("/app/src/main/assets/content/pharmacology/pharm_ch1.json", "Pharmacology"),
    ("/app/src/main/assets/content/microbiology/micro_ch1.json", "Microbiology")
]

# Note: /app/src/main/assets/... is absolute container path. Let's resolve it relative to the workspace as well if needed.
# Since /app/src/main/assets exists, let's use it.

for fpath, subject in files:
    # Let's check both /app/src/main/assets/... and relative app/src/main/assets/...
    resolved_path = fpath
    if not os.path.exists(resolved_path):
        resolved_path = fpath.lstrip("/")
    if os.path.exists(resolved_path):
        with open(resolved_path, 'r', encoding='utf-8') as f:
            data = json.load(f)
        print(f"=== {subject} ({os.path.basename(resolved_path)}) ===")
        print(f"  Keys: {list(data.keys())}")
        if "contraindications" in data:
            print(f"  Contraindications: {type(data['contraindications'])} - {repr(data['contraindications'])[:150]}")
        if "physiologicalEffects" in data:
            print(f"  PhysiologicalEffects: {type(data['physiologicalEffects'])} - len {len(data['physiologicalEffects'])}")
            if len(data['physiologicalEffects']) > 0:
                print(f"    First: {repr(data['physiologicalEffects'][0])[:150]}")
    else:
        print(f"=== {subject} - FILE NOT FOUND: {fpath} (tried {resolved_path}) ===")
