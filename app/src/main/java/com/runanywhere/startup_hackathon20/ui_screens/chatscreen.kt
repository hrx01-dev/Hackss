package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.runanywhere.startup_hackathon20.ChatViewModel
import com.runanywhere.startup_hackathon20.ChatMessage
import com.runanywhere.startup_hackathon20.R
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

data class Message(
    val text: String,
    val isUser: Boolean
)

@Composable
fun ChatScreen(
    onBack: () -> Unit,
    viewModel: ChatViewModel? = viewModel()
) {
    var inputText by remember { mutableStateOf(TextFieldValue("")) }

    // Collect messages from ViewModel
    val messages by (viewModel?.messages ?: MutableStateFlow(emptyList())).collectAsState()
    val isLoading by (viewModel?.isLoading ?: MutableStateFlow(false)).collectAsState()
    val currentModelId by (viewModel?.currentModelId
        ?: MutableStateFlow<String?>(null)).collectAsState()
    val statusMessage by (viewModel?.statusMessage ?: MutableStateFlow("Initializing...")).collectAsState()

    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    // Auto-scroll to bottom when new messages arrive
    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            coroutineScope.launch {
                listState.animateScrollToItem(messages.size - 1)
            }
        }
    }

    val suggestions = listOf(
        "Give me a tip",
        "Explain this",
        "Best practices",
        "How to improve"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {

        // 🔥 TOP BAR WITH GRADIENT
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(
                            Color(0xFF4CAF50),
                            Color(0xFF2ecc71)
                        )
                    )
                )
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {

                // BACK BUTTON
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_arrow_left),
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        "Expert Chat",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(
                                id = if (currentModelId != null) R.drawable.ic_wifi_off else R.drawable.ic_wifi_off
                            ),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text(
                            if (currentModelId != null) "Model Loaded" else "No Model",
                            color = Color.White.copy(0.9f),
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }

                // Clear Chat Button
                if (messages.isNotEmpty()) {
                    var showDialog by remember { mutableStateOf(false) }

                    IconButton(
                        onClick = { showDialog = true },
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f))
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_delete),
                            contentDescription = "Clear Chat",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }

                    if (showDialog) {
                        AlertDialog(
                            onDismissRequest = { showDialog = false },
                            title = { Text("Clear Chat History") },
                            text = { Text("Are you sure you want to delete all chat messages? This action cannot be undone.") },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        viewModel?.clearAllMessages()
                                        showDialog = false
                                    }
                                ) {
                                    Text("Clear")
                                }
                            },
                            dismissButton = {
                                TextButton(onClick = { showDialog = false }) {
                                    Text("Cancel")
                                }
                            }
                        )
                    }
                }
            }
        }

        // 🔥 MESSAGES LIST
        LazyColumn(
            state = listState,
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            // Show welcome message if no messages
            if (messages.isEmpty()) {
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Text(
                                "Hello! I'm your offline expert assistant.",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Gray
                            )
                            Spacer(Modifier.height(8.dp))
                            Text(
                                statusMessage,
                                style = MaterialTheme.typography.bodyMedium,
                                color = Color.Gray
                            )
                        }
                    }
                }
            }

            items(messages) { message ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
                ) {

                    Box(
                        modifier = Modifier
                            .then(
                                if (message.isUser) {
                                    Modifier.background(
                                        brush = Brush.linearGradient(
                                            listOf(
                                                Color(0xFF4CAF50),
                                                Color(0xFF2ECC71)
                                            )
                                        ),
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                } else {
                                    Modifier.background(
                                        color = Color.White,
                                        shape = RoundedCornerShape(20.dp)
                                    )
                                }
                            )
                            .padding(14.dp)
                            .widthIn(max = 260.dp)
                    ) {
                        Text(
                            message.text,
                            color = if (message.isUser) Color.White else Color.Black
                        )
                    }
                }
            }

            // 🔥 SUGGESTIONS when no messages
            if (messages.isEmpty()) {
                item {
                    Spacer(Modifier.height(20.dp))
                    Text(
                        "Quick suggestions:",
                        color = Color.Gray,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(Modifier.height(10.dp))

                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        suggestions.chunked(2).forEach { rowItems ->
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                rowItems.forEach { suggestion ->
                                    Button(
                                        onClick = {
                                            viewModel?.sendMessage(suggestion)
                                        },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.White
                                        ),
                                        enabled = currentModelId != null && !isLoading
                                    ) {
                                        Text(suggestion, color = Color.Black)
                                    }
                                }
                            }
                        }
                    }
                }
            }

            // Show loading indicator
            if (isLoading) {
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = Color.White,
                                    shape = RoundedCornerShape(20.dp)
                                )
                                .padding(14.dp)
                        ) {
                            CircularProgressIndicator(
                                modifier = Modifier.size(20.dp),
                                color = Color(0xFF4CAF50),
                                strokeWidth = 2.dp
                            )
                        }
                    }
                }
            }
        }

        // 🔥 INPUT FIELD + SEND BUTTON
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = inputText,
                onValueChange = { inputText = it },
                placeholder = { Text("Type your question...") },
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.width(10.dp))

            IconButton(
                onClick = {
                    if (inputText.text.isNotBlank()) {
                        viewModel?.sendMessage(inputText.text)
                        inputText = TextFieldValue("")
                    }
                },
                enabled = currentModelId != null && !isLoading && inputText.text.isNotBlank(),
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        if (currentModelId != null && !isLoading && inputText.text.isNotBlank()) {
                            Brush.linearGradient(
                                listOf(
                                    Color(0xFF4CAF50),
                                    Color(0xFF2ECC71)
                                )
                            )
                        } else {
                            Brush.linearGradient(
                                listOf(
                                    Color.Gray.copy(alpha = 0.5f),
                                    Color.Gray.copy(alpha = 0.5f)
                                )
                            )
                        }
                    )
            ) {
                if (isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_send),
                        contentDescription = "Send",
                        tint = Color.White
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun ChatScreenPreview() {
    Startup_hackathon20Theme {
        ChatScreen(onBack = {}, viewModel = null)
    }
}
