# PHYSIOPILOT PHASE 1 — REPOSITORY & DATA INTEGRITY REPORT

## CRITICAL RISKS
- Invalid JSON or syntax error in app/src/main/assets/content/anatomy/anat_ch22.json: Expected ',' or '}' after property value in JSON at position 7824 (line 76 column 29)
- Invalid JSON or syntax error in app/src/main/assets/content/anatomy/anat_ch23.json: Expected ',' or ']' after array element in JSON at position 15342 (line 182 column 99)
- Invalid JSON or syntax error in app/src/main/assets/content/anatomy/anat_ch33.json: Expected ',' or '}' after property value in JSON at position 2315 (line 47 column 3)
- Invalid JSON or syntax error in app/src/main/assets/content/anatomy/anat_ch34.json: Expected ',' or '}' after property value in JSON at position 2242 (line 45 column 3)

## HIGH RISKS
- SubjectChapterRepository: chapterId 'med_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'med_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'med_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'surg_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'surg_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'surg_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'neu3_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'neu3_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'neu3_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'ast3_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'ast3_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'ast3_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'res_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'res_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'res_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'ort4_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'ort4_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'ort4_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'obs4_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'obs4_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'obs4_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'pmd4_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'pmd4_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'pmd4_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'neu4_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'neu4_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'neu4_ch3' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'eth_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'eth_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'pos_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'pos_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'cas_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'cas_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'doc_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'doc_ch2' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'hsp_ch1' is not registered in ChapterContentRepository
- SubjectChapterRepository: chapterId 'hsp_ch2' is not registered in ChapterContentRepository

## MEDIUM RISKS
- None

## LOW RISKS
- None

## VERIFICATION SUMMARY
- **Total JSON files scanned:** 119
- **Total Chapters in Content Registry:** 119
- **Total Chapters in Subject Modules:** 156
- **Serialization Compatibility:** Verified required fields for kotlinx.serialization.
