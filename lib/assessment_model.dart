import 'dart:convert';

/// Represents a single physical assessment step in a clinical template.
class AssessmentStep {
  final String stepId;
  final int stepNumber;
  final String category;
  final String title;
  final String description;
  final List<String> expectedFindings;
  final List<String> relatedTests;
  final List<String> relatedOutcomes;

  AssessmentStep({
    required this.stepId,
    required this.stepNumber,
    required this.category,
    required this.title,
    required this.description,
    required this.expectedFindings,
    required this.relatedTests,
    required this.relatedOutcomes,
  });

  factory AssessmentStep.fromJson(Map<String, dynamic> json) {
    return AssessmentStep(
      stepId: json['step_id'] as String? ?? '',
      stepNumber: json['step_number'] as int? ?? 0,
      category: json['category'] as String? ?? 'general',
      title: json['title'] as String? ?? '',
      description: json['description'] as String? ?? '',
      expectedFindings: (json['expected_findings'] as List<dynamic>?)
              ?.map((e) => e as String)
              .toList() ??
          [],
      relatedTests: (json['related_tests'] as List<dynamic>?)
              ?.map((e) => e as String)
              .toList() ??
          [],
      relatedOutcomes: (json['related_outcomes'] as List<dynamic>?)
              ?.map((e) => e as String)
              .toList() ??
          [],
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'step_id': stepId,
      'step_number': stepNumber,
      'category': category,
      'title': title,
      'description': description,
      'expected_findings': expectedFindings,
      'related_tests': relatedTests,
      'related_outcomes': relatedOutcomes,
    };
  }
}

class ReferenceItem {
  final String bookName;
  final String author;
  final String edition;
  final String chapterName;
  final String? pageRange;
  final String type;

  ReferenceItem({
    required this.bookName,
    required this.author,
    required this.edition,
    required this.chapterName,
    this.pageRange,
    this.type = 'textbook',
  });

  factory ReferenceItem.fromJson(Map<String, dynamic> json) {
    return ReferenceItem(
      bookName: json['book_name'] as String? ?? '',
      author: json['author'] as String? ?? '',
      edition: json['edition'] as String? ?? '',
      chapterName: json['chapter_name'] as String? ?? '',
      pageRange: json['page_range'] as String?,
      type: json['type'] as String? ?? 'textbook',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'book_name': bookName,
      'author': author,
      'edition': edition,
      'chapter_name': chapterName,
      if (pageRange != null) 'page_range': pageRange,
      'type': type,
    };
  }
}

class AssessmentScale {
  final String scaleName;
  final String originalAuthor;
  final String publicationYear;
  final String purpose;

  AssessmentScale({
    required this.scaleName,
    required this.originalAuthor,
    required this.publicationYear,
    required this.purpose,
  });

  factory AssessmentScale.fromJson(Map<String, dynamic> json) {
    return AssessmentScale(
      scaleName: json['scale_name'] as String? ?? '',
      originalAuthor: json['original_author'] as String? ?? '',
      publicationYear: json['publication_year'] as String? ?? '',
      purpose: json['purpose'] as String? ?? '',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'scale_name': scaleName,
      'original_author': originalAuthor,
      'publication_year': publicationYear,
      'purpose': purpose,
    };
  }
}

class EvidenceMetadata {
  final bool textbookBased;
  final bool clinicalGuidelineBased;
  final bool researchSupported;
  final bool wbuhsBptAligned;

  EvidenceMetadata({
    required this.textbookBased,
    required this.clinicalGuidelineBased,
    required this.researchSupported,
    required this.wbuhsBptAligned,
  });

  factory EvidenceMetadata.fromJson(Map<String, dynamic> json) {
    return EvidenceMetadata(
      textbookBased: json['textbook_based'] as bool? ?? false,
      clinicalGuidelineBased: json['clinical_guideline_based'] as bool? ?? false,
      researchSupported: json['research_supported'] as bool? ?? false,
      wbuhsBptAligned: json['wbuhs_bpt_aligned'] as bool? ?? false,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'textbook_based': textbookBased,
      'clinical_guideline_based': clinicalGuidelineBased,
      'research_supported': researchSupported,
      'wbuhs_bpt_aligned': wbuhsBptAligned,
    };
  }
}

/// Represents a complete clinical assessment template for a clinical condition.
class AssessmentTemplate {
  final String templateId;
  final String templateName;
  final List<AssessmentStep> steps;
  final List<ReferenceItem> references;
  final List<AssessmentScale> scales;
  final EvidenceMetadata evidenceMetadata;

  AssessmentTemplate({
    required this.templateId,
    required this.templateName,
    required this.steps,
    this.references = const [],
    this.scales = const [],
    EvidenceMetadata? evidenceMetadata,
  }) : evidenceMetadata = evidenceMetadata ??
            EvidenceMetadata(
              textbookBased: false,
              clinicalGuidelineBased: false,
              researchSupported: false,
              wbuhsBptAligned: false,
            );

  factory AssessmentTemplate.fromJson(Map<String, dynamic> json) {
    return AssessmentTemplate(
      templateId: json['template_id'] as String? ?? '',
      templateName: json['template_name'] as String? ?? 'Untitled Template',
      steps: (json['steps'] as List<dynamic>?)
              ?.map((e) => AssessmentStep.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      references: (json['references'] as List<dynamic>?)
              ?.map((e) => ReferenceItem.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      scales: (json['scales'] as List<dynamic>?)
              ?.map((e) => AssessmentScale.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
      evidenceMetadata: json['evidence_metadata'] != null
          ? EvidenceMetadata.fromJson(json['evidence_metadata'] as Map<String, dynamic>)
          : null,
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'template_id': templateId,
      'template_name': templateName,
      'steps': steps.map((s) => s.toJson()).toList(),
      'references': references.map((r) => r.toJson()).toList(),
      'scales': scales.map((s) => s.toJson()).toList(),
      'evidence_metadata': evidenceMetadata.toJson(),
    };
  }
}

/// Top level JSON parsing utility.
List<AssessmentTemplate> parseAssessmentTemplates(String rawJson) {
  final List<dynamic> decoded = json.decode(rawJson) as List<dynamic>;
  return decoded
      .map((item) => AssessmentTemplate.fromJson(item as Map<String, dynamic>))
      .toList();
}
