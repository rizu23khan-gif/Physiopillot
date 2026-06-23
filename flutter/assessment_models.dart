import 'dart:convert';

/// Model representing a single detailed step in a physiotherapy clinical assessment template.
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

  /// Factory to construct an AssessmentStep from a decoded JSON Map.
  factory AssessmentStep.fromJson(Map<String, dynamic> json) {
    return AssessmentStep(
      stepId: json['step_id'] as String? ?? 'unk',
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

  /// Convert object back to a standard JSON map.
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

/// Model representing an entire syllabus-approved clinical assessment template (e.g., Osteoarthritis Knee).
class AssessmentTemplate {
  final String templateId;
  final String templateName;
  final List<AssessmentStep> steps;

  AssessmentTemplate({
    required this.templateId,
    required this.templateName,
    required this.steps,
  });

  /// Factory to construct an AssessmentTemplate from a decoded JSON Map.
  factory AssessmentTemplate.fromJson(Map<String, dynamic> json) {
    return AssessmentTemplate(
      templateId: json['template_id'] as String? ?? '',
      templateName: json['template_name'] as String? ?? 'Untitled Assessment',
      steps: (json['steps'] as List<dynamic>?)
              ?.map((e) => AssessmentStep.fromJson(e as Map<String, dynamic>))
              .toList() ??
          [],
    );
  }

  /// Convert object back to a standard JSON map.
  Map<String, dynamic> toJson() {
    return {
      'template_id': templateId,
      'template_name': templateName,
      'steps': steps.map((s) => s.toJson()).toList(),
    };
  }
}

/// Utility parsing helper to load and parse a list of templates from a raw string input.
List<AssessmentTemplate> parseAssessmentTemplates(String rawJsonString) {
  final decoded = json.decode(rawJsonString);
  if (decoded is List) {
    return decoded
        .map((item) => AssessmentTemplate.fromJson(item as Map<String, dynamic>))
        .toList();
  }
  return [];
}
