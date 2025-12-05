package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.R

data class Message(
    val text: String,
    val isUser: Boolean
)

@Composable
fun ChatScreen(
    onBack: () -> Unit
) {
    var messages by remember {
        mutableStateOf(
            mutableListOf(
                Message(
                    "Hello! I'm your offline expert assistant. How can I help you today?",
                    false
                )
            )
        )
    }

    var inputText by remember { mutableStateOf(TextFieldValue("")) }

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

                Column {
                    Text(
                        "Expert Chat",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_wifi_off),
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("Offline", color = Color.White.copy(0.9f))
                    }
                }
            }
        }

        // 🔥 MESSAGES LIST
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(messages) { message ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (message.isUser) Arrangement.End else Arrangement.Start
                ) {

                    Box(
                        modifier = Modifier
                            .background(
                                if (message.isUser) {
                                    Brush.linearGradient(
                                        listOf(
                                            Color(0xFF4CAF50),
                                            Color(0xFF2ECC71)
                                        )
                                    )
                                } else Color.White,
                                shape = RoundedCornerShape(20.dp)
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

            // 🔥 SUGGESTIONS when only 1 message
            if (messages.size <= 1) {
                item {
                    Spacer(Modifier.height(20.dp))
                    Text(
                        "Quick suggestions:",
                        color = Color.Gray,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
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
                                            messages.add(Message(suggestion, true))
                                            messages.add(
                                                Message(
                                                    "Great question! Let me provide expert guidance...",
                                                    false
                                                )
                                            )
                                        },
                                        modifier = Modifier.weight(1f),
                                        colors = ButtonDefaults.buttonColors(
                                            containerColor = Color.White
                                        )
                                    ) {
                                        Text(suggestion, color = Color.Black)
                                    }
                                }
                            }
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
                        messages.add(Message(inputText.text, true))
                        messages.add(
                            Message(
                                "I understand your question! Here's the expert advice...",
                                false
                            )
                        )
                        inputText = TextFieldValue("")
                    }
                },
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(
                                Color(0xFF4CAF50),
                                Color(0xFF2ECC71)
                            )
                        )
                    )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_send),
                    contentDescription = "Send",
                    tint = Color.White
                )
            }
        }
    }
}
