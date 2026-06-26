#!/usr/bin/env python3
import os
import sys
import shutil
import time

# Ensure scripts can be imported cleanly
sys.path.insert(0, os.path.abspath(os.path.dirname(__file__)))
sys.path.insert(0, os.path.abspath(os.path.join(os.path.dirname(__file__), 'scripts')))

def run_validation():
    print("=== Step 1: YAML Schema Validation ===")
    import validate_yaml
    yaml_code = validate_yaml.main()
    print()

    print("=== Step 2: Citation Traceability Verification ===")
    import verify_citations
    citation_code = verify_citations.main()
    print()

    print("=== Step 3: Relationship Graph Verification ===")
    import relationship_validator
    rel_code = relationship_validator.main()
    print()

    print("=== Step 4: Asset Path & Size Verification ===")
    import asset_validator
    asset_code = asset_validator.main()
    print()

    # Aggregate exit code
    if yaml_code != 0 or citation_code != 0 or rel_code != 0 or asset_code != 0:
        return 1
    return 0

def run_compile():
    print("=== Step 5: Master Compilation & Asset Generation ===")
    import generate_assets
    return generate_assets.main()

def run_sync():
    print("=== Step 6: Asset Syncing (app/src/main/assets) ===")
    import sync_assets
    return sync_assets.main()

def run_report(start_time):
    print("=== Step 7: Generating Interactive Build Report ===")
    import build_report
    return build_report.main(start_time)

def run_clean():
    print("=== Cleaning up Generated Artifacts & Reports ===")
    dirs_to_clean = ["/generated_json", "/validation", "/build"]
    for d in dirs_to_clean:
        if os.path.exists(d):
            shutil.rmtree(d)
            print(f"  Removed: '{d}'")
    print("Cleanup completed.")
    return 0

def show_help():
    print("PhysioPilot SSOT Toolchain CLI")
    print("Usage:")
    print("  python build.py validate   : Validate syntax, schemas, citations, relationships, and assets")
    print("  python build.py compile    : Compile Master YAML chapters into app-compatible JSON files")
    print("  python build.py sync       : Sync successfully validated JSON outputs to Android assets")
    print("  python build.py report     : Generate the HTML summary build report")
    print("  python build.py clean      : Clean temporary build, validation, and generation directories")
    print("  python build.py all        : Execute entire validation, compilation, reporting, and syncing pipeline")
    print("  python build.py help       : Show this help menu")

def main():
    if len(sys.argv) < 2:
        show_help()
        return 1

    command = sys.argv[1].lower()
    start_time = time.time()

    if command == "validate":
        return run_validation()
    elif command == "compile":
        return run_compile()
    elif command == "sync":
        return run_sync()
    elif command == "report":
        return run_report(start_time)
    elif command == "clean":
        return run_clean()
    elif command == "all":
        # Run entire pipeline
        print("Executing full PhysioPilot SSOT compiler pipeline...")
        print()
        
        val_code = run_validation()
        if val_code != 0:
            print("Pipeline aborted: Validation step failed.", file=sys.stderr)
            run_report(start_time)
            return val_code
            
        comp_code = run_compile()
        if comp_code != 0:
            print("Pipeline aborted: Compilation step failed.", file=sys.stderr)
            run_report(start_time)
            return comp_code
            
        rep_code = run_report(start_time)
        if rep_code != 0:
            print("Warning: Build report generation returned an error.", file=sys.stderr)
            
        sync_code = run_sync()
        if sync_code != 0:
            print("Pipeline aborted: Asset synchronization failed.", file=sys.stderr)
            return sync_code
            
        print(f"Pipeline finished successfully in {time.time() - start_time:.2f} seconds!")
        return 0
    elif command == "check_fields":
        import check_fields
        return 0
    elif command == "batch_migrate":
        import batch_migrator
        return batch_migrator.run_stages()
    elif command in ("help", "--help", "-h"):
        show_help()
        return 0
    else:
        print(f"Unknown command: '{sys.argv[1]}'", file=sys.stderr)
        show_help()
        return 1

if __name__ == "__main__":
    sys.exit(main())
