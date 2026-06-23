import 'package:flutter/material.dart';
import 'package:flutter/services.dart' show rootBundle;
import 'assessment_models.dart';

/// Primary List Screen showing all available clinical assessment templates.
class AssessmentTemplateScreen extends StatefulWidget {
  const AssessmentTemplateScreen({super.key});

  @override
  State<AssessmentTemplateScreen> createState() => _AssessmentTemplateScreenState();
}

class _AssessmentTemplateScreenState extends State<AssessmentTemplateScreen> {
  List<AssessmentTemplate> _templates = [];
  List<AssessmentTemplate> _filteredTemplates = [];
  bool _isLoading = true;
  String _errorMessage = '';
  final TextEditingController _searchController = TextEditingController();

  @override
  void initState() {
    super.initState();
    _loadTemplates();
    _searchController.addListener(_onSearchChanged);
  }

  @override
  void dispose() {
    _searchController.dispose();
    super.dispose();
  }

  /// Reads and parses the local assessment templates JSON asset.
  Future<void> _loadTemplates() async {
    try {
      setState(() {
        _isLoading = true;
        _errorMessage = '';
      });

      // Simulates reading from assets/data/assessment_templates.json
      final String jsonString = await rootBundle.loadString('assets/data/assessment_templates.json');
      final templates = parseAssessmentTemplates(jsonString);

      setState(() {
        _templates = templates;
        _filteredTemplates = templates;
        _isLoading = false;
      });
    } catch (e) {
      setState(() {
        _errorMessage = 'Failed to load assessment templates. '
            'Please verify if assets/data/assessment_templates.json is declared in pubspec.yaml.\n\nError details: $e';
        _isLoading = false;
      });
    }
  }

  void _onSearchChanged() {
    final query = _searchController.text.toLowerCase();
    setState(() {
      _filteredTemplates = _templates.where((template) {
        return template.templateName.toLowerCase().contains(query) ||
            template.steps.any((step) =>
                step.title.toLowerCase().contains(query) ||
                step.description.toLowerCase().contains(query));
      }).toList();
    });
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);

    return Scaffold(
      appBar: AppBar(
        title: const Text(
          'Clinical Physio Templates',
          style: TextStyle(fontWeight: FontWeight.bold),
        ),
        elevation: 0,
        actions: [
          IconButton(
            icon: const Icon(Icons.refresh),
            tooltip: 'Reload templates',
            onPressed: _loadTemplates,
          ),
        ],
      ),
      body: Column(
        children: [
          _buildHeroHeader(theme),
          _buildSearchBar(theme),
          Expanded(
            child: _buildBody(theme),
          ),
        ],
      ),
    );
  }

  Widget _buildHeroHeader(ThemeData theme) {
    return Container(
      width: double.infinity,
      padding: const EdgeInsets.all(20),
      margin: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      decoration: BoxDecoration(
        gradient: LinearGradient(
          colors: [
            theme.colorScheme.primaryContainer,
            theme.colorScheme.secondaryContainer.withOpacity(0.5),
          ],
          begin: Alignment.topLeft,
          end: Alignment.bottomRight,
        ),
        borderRadius: BorderRadius.circular(20),
        boxShadow: [
          BoxShadow(
            color: theme.colorScheme.shadow.withOpacity(0.04),
            blurRadius: 10,
            offset: const Offset(0, 4),
          ),
        ],
      ),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          Row(
            children: [
              Icon(
                Icons.menu_book_rounded,
                color: theme.colorScheme.primary,
                size: 28,
              ),
              const SizedBox(width: 8),
              Text(
                'BPT OSCE & Clinical Guides',
                style: theme.textTheme.titleMedium?.copyWith(
                  fontWeight: FontWeight.bold,
                  color: theme.colorScheme.onPrimaryContainer,
                ),
              ),
            ],
          ),
          const SizedBox(height: 8),
          Text(
            'Syllabus-compliant practical physiotherapy assessment checklists. Use these templates to master subjective and objective clinical examinations.',
            style: theme.textTheme.bodyMedium?.copyWith(
              color: theme.colorScheme.onPrimaryContainer.withOpacity(0.85),
              height: 1.35,
            ),
          ),
        ],
      ),
    );
  }

  Widget _buildSearchBar(ThemeData theme) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: TextField(
        controller: _searchController,
        decoration: InputDecoration(
          hintText: 'Search templates, titles, or body parts...',
          prefixIcon: const Icon(Icons.search_rounded),
          suffixIcon: _searchController.text.isNotEmpty
              ? IconButton(
                  icon: const Icon(Icons.clear_rounded),
                  onPressed: () => _searchController.clear(),
                )
              : null,
          filled: true,
          fillColor: theme.colorScheme.surfaceVariant.withOpacity(0.3),
          contentPadding: const EdgeInsets.symmetric(vertical: 0, horizontal: 16),
          border: OutlineInputBorder(
            borderRadius: BorderRadius.circular(16),
            borderSide: BorderSide.none,
          ),
          enabledBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(16),
            borderSide: BorderSide.none,
          ),
          focusedBorder: OutlineInputBorder(
            borderRadius: BorderRadius.circular(16),
            borderSide: BorderSide(
              color: theme.colorScheme.primary.withOpacity(0.5),
              width: 1.5,
            ),
          ),
        ),
      ),
    );
  }

  Widget _buildBody(ThemeData theme) {
    if (_isLoading) {
      return const Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            CircularProgressIndicator(),
            SizedBox(height: 16),
            Text('Parsing clinical database...'),
          ],
        ),
      );
    }

    if (_errorMessage.isNotEmpty) {
      return Center(
        child: SingleChildScrollView(
          padding: const EdgeInsets.all(24),
          child: Column(
            mainAxisAlignment: MainAxisAlignment.center,
            children: [
              Icon(Icons.error_outline_rounded, size: 64, color: theme.colorScheme.error),
              const SizedBox(height: 16),
              Text(
                'Initialization Fail',
                style: theme.textTheme.titleMedium?.copyWith(fontWeight: FontWeight.bold),
              ),
              const SizedBox(height: 8),
              Container(
                padding: const EdgeInsets.all(16),
                decoration: BoxDecoration(
                  color: theme.colorScheme.errorContainer.withOpacity(0.3),
                  borderRadius: BorderRadius.circular(12),
                  border: Border.all(color: theme.colorScheme.error.withOpacity(0.2)),
                ),
                child: Text(
                  _errorMessage,
                  textAlign: TextAlign.center,
                  style: TextStyle(color: theme.colorScheme.onErrorContainer, fontSize: 13),
                ),
              ),
              const SizedBox(height: 20),
              ElevatedButton.icon(
                onPressed: _loadTemplates,
                icon: const Icon(Icons.refresh_rounded),
                label: const Text('Retry Loading String'),
              ),
            ],
          ),
        ),
      );
    }

    if (_filteredTemplates.isEmpty) {
      return Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            Icon(Icons.search_off_rounded, size: 64, color: theme.colorScheme.outline),
            const SizedBox(height: 16),
            const Text(
              'No assessment templates found',
              style: TextStyle(fontWeight: FontWeight.bold, fontSize: 16),
            ),
            const SizedBox(height: 4),
            Text(
              'Try a different keyword search.',
              style: TextStyle(color: theme.colorScheme.onSurfaceVariant),
            ),
          ],
        ),
      );
    }

    return ListView.builder(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      itemCount: _filteredTemplates.length,
      itemBuilder: (context, index) {
        final template = _filteredTemplates[index];
        return _buildTemplateCard(context, template, theme);
      },
    );
  }

  Widget _buildTemplateCard(BuildContext context, AssessmentTemplate template, ThemeData theme) {
    final stepsCount = template.steps.length;
    final isOaKnee = template.templateName.contains('Osteoarthritis') || template.templateId == 'tpl_oa_knee';

    return Card(
      margin: const EdgeInsets.only(bottom: 16),
      elevation: isOaKnee ? 2 : 0,
      shape: RoundedCornerShape(
        isOaKnee 
          ? BorderRadius.circular(20) 
          : BorderRadius.circular(16)
      ),
      clipBehavior: Clip.antiAlias,
      color: isOaKnee 
          ? theme.colorScheme.surface
          : theme.colorScheme.surfaceVariant.withOpacity(0.2),
      borderOnForeground: true,
      side: isOaKnee 
          ? BorderSide(color: theme.colorScheme.primary.withOpacity(0.3), width: 1.5)
          : BorderSide(color: theme.colorScheme.outlineVariant.withOpacity(0.5)),
      child: InkWell(
        onTap: () {
          Navigator.push(
            context,
            MaterialPageRoute(
              builder: (context) => AssessmentTemplateDetailScreen(template: template),
            ),
          );
        },
        child: Padding(
          padding: const EdgeInsets.all(16),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: [
              Row(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Expanded(
                    child: Column(
                      crossAxisAlignment: CrossAxisAlignment.start,
                      children: [
                        if (isOaKnee)
                          Container(
                            padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
                            margin: const EdgeInsets.only(bottom: 6),
                            decoration: BoxDecoration(
                              color: theme.colorScheme.primary,
                              borderRadius: BorderRadius.circular(6),
                            ),
                            child: const Text(
                              'CORE LESSON',
                              style: TextStyle(
                                color: Colors.white,
                                fontWeight: FontWeight.bold,
                                fontSize: 9,
                                letterSpacing: 0.8,
                              ),
                            ),
                          ),
                        Text(
                          template.templateName,
                          style: theme.textTheme.titleMedium?.copyWith(
                            fontWeight: FontWeight.bold,
                            color: isOaKnee ? theme.colorScheme.primary : theme.colorScheme.onSurface,
                          ),
                        ),
                      ],
                    ),
                  ),
                  Icon(
                    Icons.arrow_forward_ios_rounded,
                    size: 16,
                    color: isOaKnee ? theme.colorScheme.primary : theme.colorScheme.outline,
                  ),
                ],
              ),
              const SizedBox(height: 12),
              Row(
                children: [
                  Icon(Icons.format_list_numbered_rounded, size: 16, color: theme.colorScheme.secondary),
                  const SizedBox(width: 6),
                  Text(
                    '$stepsCount OSCE Assessment Steps',
                    style: TextStyle(
                      color: theme.colorScheme.onSurfaceVariant,
                      fontSize: 13,
                    ),
                  ),
                ],
              ),
              const SizedBox(height: 16),
              Row(
                mainAxisAlignment: MainAxisAlignment.spaceBetween,
                children: [
                  Text(
                    'Clinical Assessment Template',
                    style: TextStyle(
                      fontSize: 11,
                      color: theme.colorScheme.outline,
                      fontStyle: FontStyle.italic,
                    ),
                  ),
                  TextButton(
                    onPressed: () {
                      Navigator.push(
                        context,
                        MaterialPageRoute(
                          builder: (context) => AssessmentTemplateDetailScreen(template: template),
                        ),
                      );
                    },
                    style: TextButton.styleFrom(
                      padding: EdgeInsets.zero,
                      minimumSize: const Size(50, 30),
                      tapTargetSize: MaterialTapTargetSize.shrinkWrap,
                    ),
                    child: Row(
                      mainAxisSize: MainAxisSize.min,
                      children: [
                        Text(isOaKnee ? 'Start Guide' : 'View Details'),
                        const SizedBox(width: 4),
                        const Icon(Icons.arrow_forward_rounded, size: 14),
                      ],
                    ),
                  ),
                ],
              ),
            ],
          ),
        ),
      ),
    );
  }
}

/// Detailed Checklist Screen for performing specific assessment templates.
class AssessmentTemplateDetailScreen extends StatefulWidget {
  final AssessmentTemplate template;

  const AssessmentTemplateDetailScreen({
    super.key,
    required this.template,
  });

  @override
  State<AssessmentTemplateDetailScreen> createState() => _AssessmentTemplateDetailScreenState();
}

class _AssessmentTemplateDetailScreenState extends State<AssessmentTemplateDetailScreen> {
  // Local state to track checked indices of steps
  final Map<String, bool> _completedSteps = {};

  @override
  void initState() {
    super.initState();
    // Initialize state mapping
    for (var step in widget.template.steps) {
      _completedSteps[step.stepId] = false;
    }
  }

  double get _progressRate {
    if (widget.template.steps.isEmpty) return 0.0;
    final int completedCount = _completedSteps.values.where((v) => v).length;
    return completedCount / widget.template.steps.length;
  }

  int get _completedCount => _completedSteps.values.where((v) => v).length;

  void _toggleStep(String stepId, bool? value) {
    setState(() {
      _completedSteps[stepId] = value ?? false;
    });
  }

  void _resetProgress() {
    setState(() {
      _completedSteps.updateAll((key, value) => false);
    });
  }

  Color _getCategoryColor(String category, ThemeData theme) {
    switch (category.toLowerCase()) {
      case 'subjective':
        return Colors.teal;
      case 'observation':
        return Colors.indigo;
      case 'palpation':
        return Colors.deepOrange;
      case 'rom':
      case 'rom_assessment':
        return Colors.green;
      case 'mmt':
      case 'mmt_assessment':
        return Colors.purple;
      case 'special_test':
      case 'special_tests':
        return Colors.blue;
      default:
        return theme.colorScheme.primary;
    }
  }

  @override
  Widget build(BuildContext context) {
    final theme = Theme.of(context);
    final progress = _progressRate;
    final totalSteps = widget.template.steps.length;

    return Scaffold(
      appBar: AppBar(
        title: Text(widget.template.templateName),
        elevation: 0,
        backgroundColor: theme.colorScheme.surface,
        foregroundColor: theme.colorScheme.onSurface,
        actions: [
          IconButton(
            icon: const Icon(Icons.restart_alt_rounded),
            tooltip: 'Reset checklist',
            onPressed: () {
              showDialog(
                context: context,
                builder: (context) => AlertDialog(
                  title: const Text('Reset Checklist?'),
                  content: const Text('This will clear your local checked progress for this template.'),
                  actions: [
                    TextButton(
                      onPressed: () => Navigator.pop(context),
                      child: const Text('Cancel'),
                    ),
                    TextButton(
                      onPressed: () {
                        _resetProgress();
                        Navigator.pop(context);
                      },
                      child: const Text('Reset'),
                    ),
                  ],
                ),
              );
            },
          ),
        ],
      ),
      body: Column(
        children: [
          _buildProgressBar(theme, progress, _completedCount, totalSteps),
          Expanded(
            child: widget.template.steps.isEmpty
                ? const Center(child: Text('No steps configured for this template'))
                : ListView.builder(
                    padding: const EdgeInsets.fromLTRB(16, 8, 16, 24),
                    itemCount: widget.template.steps.length,
                    itemBuilder: (context, index) {
                      final step = widget.template.steps[index];
                      return _buildStepTile(step, theme);
                    },
                  ),
          ),
        ],
      ),
    );
  }

  Widget _buildProgressBar(ThemeData theme, double progress, int completed, int total) {
    return Container(
      color: theme.colorScheme.surface,
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
      child: Column(
        children: [
          Row(
            mainAxisAlignment: MainAxisAlignment.spaceBetween,
            children: [
              Text(
                'OSCE Progress Checklist',
                style: theme.textTheme.bodyMedium?.copyWith(
                  fontWeight: FontWeight.bold,
                  color: theme.colorScheme.onSurface,
                ),
              ),
              Text(
                '$completed / $total Steps Done (${(progress * 100).toInt()}%)',
                style: theme.textTheme.bodySmall?.copyWith(
                  fontWeight: FontWeight.bold,
                  color: theme.colorScheme.primary,
                ),
              ),
            ],
          ),
          const SizedBox(height: 8),
          ClipRRect(
            borderRadius: BorderRadius.circular(8),
            child: LinearProgressIndicator(
              value: progress,
              minHeight: 12,
              backgroundColor: theme.colorScheme.primaryContainer.withOpacity(0.3),
              valueColor: AlwaysStoppedAnimation<Color>(theme.colorScheme.primary),
            ),
          ),
          const SizedBox(height: 4),
        ],
      ),
    );
  }

  Widget _buildStepTile(AssessmentStep step, ThemeData theme) {
    final isChecked = _completedSteps[step.stepId] ?? false;
    final catColor = _getCategoryColor(step.category, theme);

    return Container(
      margin: const EdgeInsets.only(bottom: 12),
      decoration: BoxDecoration(
        color: isChecked ? theme.colorScheme.primaryContainer.withOpacity(0.06) : theme.colorScheme.surface,
        borderRadius: BorderRadius.circular(16),
        border: Border.all(
          color: isChecked 
              ? theme.colorScheme.primary.withOpacity(0.4) 
              : theme.colorScheme.outlineVariant.withOpacity(0.6),
          width: isChecked ? 1.5 : 1.0,
        ),
      ),
      clipBehavior: Clip.antiAlias,
      child: ExpansionTile(
        key: PageStorageKey<String>(step.stepId),
        maintainState: true,
        leading: SizedBox(
          width: 40,
          height: 40,
          child: Checkbox(
            value: isChecked,
            activeColor: theme.colorScheme.primary,
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(4)),
            onChanged: (val) => _toggleStep(step.stepId, val),
          ),
        ),
        title: Row(
          children: [
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 8, vertical: 4),
              decoration: BoxDecoration(
                color: catColor.withOpacity(0.12),
                borderRadius: BorderRadius.circular(6),
                border: Border.all(color: catColor.withOpacity(0.2)),
              ),
              child: Text(
                step.category.toUpperCase(),
                style: TextStyle(
                  color: catColor,
                  fontWeight: FontWeight.bold,
                  fontSize: 9,
                ),
              ),
            ),
            const SizedBox(width: 8),
            Expanded(
              child: Text(
                'Step ${step.stepNumber}: ${step.title}',
                style: TextStyle(
                  fontWeight: FontWeight.bold,
                  fontSize: 14,
                  decoration: isChecked ? TextDecoration.lineThrough : null,
                  color: isChecked ? theme.colorScheme.onSurface.withOpacity(0.6) : theme.colorScheme.onSurface,
                ),
              ),
            ),
          ],
        ),
        subtitle: Padding(
          padding: const EdgeInsets.only(top: 4.0),
          child: Text(
            step.description,
            maxLines: 1,
            overflow: TextOverflow.ellipsis,
            style: TextStyle(
              fontSize: 12,
              color: theme.colorScheme.onSurfaceVariant.withOpacity(0.8),
            ),
          ),
        ),
        iconColor: theme.colorScheme.primary,
        childrenPadding: const EdgeInsets.fromLTRB(16, 0, 16, 16),
        expandedCrossAxisAlignment: CrossAxisAlignment.start,
        children: [
          const Divider(),
          const SizedBox(height: 8),
          Text(
            'Clinical Action Description:',
            style: TextStyle(
              fontWeight: FontWeight.bold,
              fontSize: 12,
              color: theme.colorScheme.secondary,
            ),
          ),
          const SizedBox(height: 4),
          Text(
            step.description,
            style: theme.textTheme.bodyMedium?.copyWith(
              height: 1.4,
              color: theme.colorScheme.onSurface,
            ),
          ),
          const SizedBox(height: 16),
          Container(
            padding: const EdgeInsets.all(12),
            decoration: BoxDecoration(
              color: theme.colorScheme.surfaceVariant.withOpacity(0.4),
              borderRadius: BorderRadius.circular(12),
              border: Border.all(color: theme.colorScheme.outlineVariant.withOpacity(0.5)),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  children: [
                    Icon(Icons.check_circle_outline_rounded, size: 16, color: theme.colorScheme.primary),
                    const SizedBox(width: 6),
                    Text(
                      'Expected Clinical Findings:',
                      style: TextStyle(
                        fontWeight: FontWeight.bold,
                        fontSize: 12,
                        color: theme.colorScheme.primary,
                      ),
                    ),
                  ],
                ),
                const SizedBox(height: 6),
                if (step.expectedFindings.isEmpty)
                  const Text('No expected results specified.', style: TextStyle(fontSize: 12, fontStyle: FontStyle.italic))
                else
                  ...step.expectedFindings.map((finding) => Padding(
                        padding: const EdgeInsets.only(bottom: 6, left: 4),
                        child: Row(
                          crossAxisAlignment: CrossAxisAlignment.start,
                          children: [
                            const Text('• ', style: TextStyle(fontWeight: FontWeight.bold)),
                            Expanded(
                              child: Text(
                                finding,
                                style: TextStyle(
                                  fontSize: 12,
                                  color: theme.colorScheme.onSurface.withOpacity(0.85),
                                  height: 1.35,
                                ),
                              ),
                            ),
                          ],
                        ),
                      )),
              ],
            ),
          ),
          if (step.relatedTests.isNotEmpty || step.relatedOutcomes.isNotEmpty) ...[
            const SizedBox(height: 12),
            Wrap(
              spacing: 8,
              runSpacing: 4,
              children: [
                ...step.relatedTests.map((tId) => Chip(
                      avatar: const Icon(Icons.analytics_outlined, size: 12),
                      label: Text('Test: $tId'),
                      labelStyle: const TextStyle(fontSize: 10),
                      padding: EdgeInsets.zero,
                    )),
                ...step.relatedOutcomes.map((oId) => Chip(
                      avatar: const Icon(Icons.assignment_outlined, size: 12),
                      label: Text('Outcome: $oId'),
                      labelStyle: const TextStyle(fontSize: 10),
                      padding: EdgeInsets.zero,
                      backgroundColor: theme.colorScheme.secondaryContainer.withOpacity(0.3),
                    )),
              ],
            ),
          ],
        ],
      ),
    );
  }
}
