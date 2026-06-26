# PhysioPilot Single Source of Truth (SSOT) Toolchain

Welcome to the **PhysioPilot SSOT Content Build System**! This toolchain acts as the definitive compilation and validation gateway for managing all educational, clinical, and exam content across every subject in the PhysioPilot application.

By establishing a **Single Source of Truth (SSOT)** in Master YAML files, we ensure that:
1. **No Data Redundancy**: Flashcards, MCQs, Viva, Revision Notes, and Search Indices are automatically compiled from a single master chapter file.
2. **Strict Verification**: Syntax correctness, schema integrity, citations traceability, and relationship graphs are fully validated before any asset goes into production.
3. **Traceable Citations**: Every clinical claim and reference links directly to standard textbooks listed in `bibliography.yaml`.

---

## 📂 Repository Layout & Folder Responsibilities

```text
├── build.py                   # Unified Command Line Interface (CLI) Entrypoint
├── requirements.txt           # Python library dependencies (PyYAML, jsonschema)
├── master_knowledge/          # Master YAML files containing single source of truth chapters
│   └── demo_chapter.yaml      # Sample master chapter YAML
├── citation_registry/         # Master textbook citations registry
│   └── bibliography.yaml      # Centralized bibliography with unique citationCodes
├── schemas/                   # JSON Schemas representing structured constraints
│   └── core_schema.json       # Base chapter structure and subject extensions
├── scripts/                   # Individual pipeline step executors
│   ├── validate_yaml.py       # Validates YAML chapters against core_schema.json
│   ├── verify_citations.py    # Ensures references match bibliography codes
│   ├── relationship_validator.py # Validates prerequisite, co-requisite, and parent links
│   ├── asset_validator.py     # Verifies size and existence of media links
│   ├── generate_assets.py     # Compiles YAML into optimized JSON and compiles sub-assets
│   ├── sync_assets.py         # Syncs successfully built assets to the Android app
│   └── build_report.py        # Compiles metrics into an interactive HTML report
├── generated_json/            # Local compilation output folder
│   ├── content/               # Compiled subject-specific chapter JSONs
│   ├── all_mcqs.json          # Compiled MCQs from all master chapters
│   ├── all_viva.json          # Compiled Viva cards from all master chapters
│   ├── search_index.json      # Unified application-wide search registry
│   └── revision_notes.json    # Summaries for offline revision
├── validation/                # Pipeline reports
│   └── yaml_validation_report.json
├── media/                     # Master assets directory (images, WEBP, SVG)
└── build/                     # Compiled build outputs
    └── build_report.html      # Visual dashboard reporting compilation status
```

---

## 🚀 Setup & Usage Guide

### Prerequisites
Make sure you have Python 3.8+ installed on your system.

### 1. Install Dependencies
Install required validation libraries:
```bash
pip install -r requirements.txt
```

### 2. Run the Unified CLI
Execute steps independently or run the entire pipeline in sequence:

* **Validate Chapter Content**:
  ```bash
  python build.py validate
  ```
  *Parses YAML files, checks schema compliance, traces citations, checks for cyclic relationships, and validates media assets.*

* **Compile Assets**:
  ```bash
  python build.py compile
  ```
  *Compiles chapters and generates individual files (MCQs, Viva cards, revision notes, and search index) inside `generated_json/`.*

* **Synchronize with Android Assets**:
  ```bash
  python build.py sync
  ```
  *Copies generated JSON files directly into the Android app's asset directories. Note: This command strictly aborts if any validation errors exist in reports.*

* **Generate HTML Build Report**:
  ```bash
  python build.py report
  ```
  *Builds an interactive compilation dashboard at `build/build_report.html`.*

* **Execute the Entire Pipeline (Recommended)**:
  ```bash
  python build.py all
  ```
  *Runs validation, compilation, report generation, and asset synchronization in a single transactional sequence.*

* **Clean Up Build Folders**:
  ```bash
  python build.py clean
  ```
  *Removes generated files, reports, and build artifacts to reset the compiler workspace.*

---

## 🔍 Validation Rules & Error Reporting

The toolchain provides strict, human-readable errors with **exact filename, field paths, line numbers, and actionable suggestions**. No confusing stack traces are shown for content formatting issues.

### 1. Schema Validation
Validates elements against `core_schema.json`, including:
* Minimum option count for MCQs (exactly 4 options).
* Correct value enums (e.g., `status` must be `draft`, `review`, or `published`).
* Correct structures for definitions, coreConcepts, and clinicalCases.

### 2. Citation Verification
* Extracts all cited keys from the chapter.
* Checks that every key (e.g., `KISNER_2017`) is defined in `citation_registry/bibliography.yaml`.
* Flags unused bibliography entries.

### 3. Relationship Verification
* Scans `prerequisites`, `coRequisites`, and `parentId`.
* Flags circular dependencies (e.g. `Chapter A` -> `Chapter B` -> `Chapter A`).
* Warns of orphans or duplicate chapter IDs across files.

### 4. Media Validation
* Verifies that all paths in `media` blocks point to files that actually exist in `/media` or `/app/src/main/assets`.
* Ensures extensions are supported (PNG, JPG, WEBP, SVG).
* Flags oversized assets (> 1.5MB) that would bloat the mobile binary.

---

## 🛠️ Extending Subject Schemas

PhysioPilot supports a highly modular subject extension architecture. Core parameters (such as metadata, learning objectives, definitions, and MCQs) are shared by all chapters. Subject-specific parameters are stored under the `"subjectExtensions"` field in `core_schema.json`:

1. **Anatomy**: Muscle origin, insertion, nerve supply, action, palpation.
2. **Physiology**: Organ systems, regulatory loops, normal values.
3. **Exercise Therapy**: Planes of motion, mechanical forces, goniometry values, FITT dosage (Frequency, Intensity, Time, Type).
4. **Electrotherapy**: Waveform parameters, frequency ranges, pulse duration, electrode setups.
