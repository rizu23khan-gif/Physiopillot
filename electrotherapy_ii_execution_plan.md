# Execution Plan: Electrotherapy II Migration

## 1. Chapter List and Classification

1. **et2_ift** (Chapter 1 equivalent) - **Legacy JSON (requires migration)**
2. **el2_ch2** - **Already migrated PUCS (skip migration)** *(Note: Both `el2_ch2.json` and `et_el2_ch2.json` exist and are already PUCS formatted).*
3. **el2_ch3** - **Legacy JSON (requires migration)**
4. **el2_ch4** - **Legacy JSON (requires migration)**
5. **el2_ch5** - **Legacy JSON (requires migration)**
6. **el2_ch6** - **Legacy JSON (requires migration)**
7. **el2_ch7** - **Legacy JSON (requires migration)**
8. **el2_ch8** - **Legacy JSON (requires migration)**
9. **el2_ch9** - **Legacy JSON (requires migration)**
10. **el2_ch10** - **Legacy JSON (requires migration)**
11. **el2_ch11** - **Legacy JSON (requires migration)**

## 2. Processed Chapters Confirmation

Exactly 10 chapters will be processed through the legacy extraction pipeline to generate PUCS compliant YAMLs:
- `et2_ift`
- `el2_ch3`
- `el2_ch4`
- `el2_ch5`
- `el2_ch6`
- `el2_ch7`
- `el2_ch8`
- `el2_ch9`
- `el2_ch10`
- `el2_ch11`

## 3. Exclusion of "el2_ch2"
It is confirmed that `el2_ch2` (and its counterpart `et_el2_ch2`) will **NOT** be passed through the legacy migration pipeline. As it is already migrated to PUCS format, the migration script will either omit it from the execution list or map it with `is_pucs: True` to pass it through untouched, preserving its data safely.

## 4. Handling the "et2_ift" Filename Mismatch
The mismatch in the content registry mapping (`et_et2_ift.json` expected vs. `et2_ift.json` on disk) will be handled directly in the `batch_migrator.py` configuration mapping block for this subject. The migrator will simply be configured to read from the actual physical file `et2_ift.json` as the `original_file` source and set the `target_file` output name as `et2_ift.json`. Then, we can correct the filename pointer inside the `content_registry.json` mapping. This requires zero modifications to the frozen architecture (SSOT compiler, schemas, validators, or sync scripts).

## 5. Frozen Pipeline Confirmation
It is confirmed that **no** changes are required to:
- The Python compiler (`build.py`)
- JSON Schemas (`core_schema.json`)
- Validation scripts
- Asset sync scripts
- Kotlin source files (`InteractiveChapterContent.kt`, etc.)
- Android UI/Compose components

The pipeline natively supports the parameters structure used by Electrotherapy II.

**Decision:**
READY FOR EXECUTION
