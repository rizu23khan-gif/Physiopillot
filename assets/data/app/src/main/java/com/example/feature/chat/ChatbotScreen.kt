package com.example.feature.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gemini.GeminiClient
import com.example.gemini.Content
import com.example.gemini.Part
import kotlinx.coroutines.launch

data class ChatMessage(val text: String, val isUser: Boolean)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatbotScreen(navController: NavController) {
    var messages by remember { mutableStateOf(listOf<ChatMessage>()) }
    var inputText by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var selectedModel by remember { mutableStateOf("gemini-3.5-flash") }
    val models = listOf(
        "gemini-3.5-flash" to "General",
        "gemini-3.1-pro-preview" to "Pro (High Thinking)",
        "gemini-3.1-flash-lite-preview" to "Fast"
    )
    
    val coroutineScope = rememberCoroutineScope()
    val listState = androidx.compose.foundation.lazy.rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Physio Assistant") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                    navigationIconContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            // Model Selector Header
            ScrollableTabRow(
                selectedTabIndex = models.indexOfFirst { it.first == selectedModel },
                edgePadding = 8.dp,
                modifier = Modifier.fillMaxWidth()
            ) {
                models.forEach { (modelId, label) ->
                    Tab(
                        selected = selectedModel == modelId,
                        onClick = { selectedModel = modelId },
                        text = { Text(label) }
                    )
                }
            }

            LazyColumn(
                state = listState,
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                contentPadding = PaddingValues(vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                if (messages.isEmpty()) {
                    item {
                        Text(
                            "Hi, I'm your Physio Pilot AI tutor. Ask me any clinical or anatomical questions!",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center
                        )
                    }
                }
                items(messages) { msg ->
                    ChatBubble(msg)
                }
                if (isLoading) {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .align(Alignment.CenterHorizontally),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Ask a question...") },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                    keyboardActions = KeyboardActions(onSend = {
                        if (inputText.isNotBlank()) {
                            val userText = inputText
                            messages = messages + ChatMessage(userText, true)
                            inputText = ""
                            isLoading = true
                            coroutineScope.launch {
                                // Build conversation history
                                val history = messages.dropLast(1).map {
                                    Content(role = if (it.isUser) "user" else "model", parts = listOf(Part(text = it.text)))
                                }
                                val response = GeminiClient.generateText(
                                    prompt = userText,
                                    modelName = selectedModel,
                                    systemInstruction = "You are PhysioPilot, an expert AI tutor for Physiotherapy (BPT) students. Be helpful, accurate, and pedagogical.",
                                    useHighThinking = selectedModel == "gemini-3.1-pro-preview",
                                    conversationHistory = history
                                )
                                messages = messages + ChatMessage(response, false)
                                isLoading = false
                                listState.animateScrollToItem(messages.size)
                            }
                        }
                    }),
                    shape = RoundedCornerShape(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                IconButton(
                    onClick = {
                        if (inputText.isNotBlank()) {
                            val userText = inputText
                            messages = messages + ChatMessage(userText, true)
                            inputText = ""
                            isLoading = true
                            coroutineScope.launch {
                                val history = messages.dropLast(1).map {
                                    Content(role = if (it.isUser) "user" else "model", parts = listOf(Part(text = it.text)))
                                }
                                val response = GeminiClient.generateText(
                                    prompt = userText,
                                    modelName = selectedModel,
                                    systemInstruction = "You are PhysioPilot, an expert AI tutor for Physiotherapy (BPT) students. Be helpful, accurate, and pedagogical.",
                                    useHighThinking = selectedModel == "gemini-3.1-pro-preview",
                                    conversationHistory = history
                                )
                                messages = messages + ChatMessage(response, false)
                                isLoading = false
                                listState.animateScrollToItem(messages.size)
                            }
                        }
                    },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(50))
                ) {
                    Icon(Icons.AutoMirrored.Filled.Send, contentDescription = "Send", tint = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
    }
}

@Composable
fun ChatBubble(message: ChatMessage) {
    val alignment = if (message.isUser) Alignment.CenterEnd else Alignment.CenterStart
    val color = if (message.isUser) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (message.isUser) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        contentAlignment = alignment
    ) {
        Surface(
            color = color,
            shape = RoundedCornerShape(
                topStart = 16.dp,
                topEnd = 16.dp,
                bottomStart = if (message.isUser) 16.dp else 4.dp,
                bottomEnd = if (message.isUser) 4.dp else 16.dp
            ),
            shadowElevation = 1.dp,
            modifier = Modifier.widthIn(max = 280.dp)
        ) {
            Text(
                text = message.text,
                modifier = Modifier.padding(12.dp),
                color = textColor,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}
