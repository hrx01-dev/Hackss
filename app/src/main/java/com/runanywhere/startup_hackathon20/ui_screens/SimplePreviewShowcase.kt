package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme
import java.text.SimpleDateFormat
import java.util.*

/**
 * Simple Preview Showcase - No ViewModels Required!
 * 
 * This file contains standalone previews that work perfectly in Android Studio.
 * Open this file and click "Design" or "Split" mode to see all previews.
 */

// ==================== HOME SCREEN ====================

@Preview(
    name = "1. Home Screen",
    showBackground = true,
    showSystemUi = true,
    group = "Main Screens"
)
@Composable
fun Preview1_HomeScreen() {
    Startup_hackathon20Theme {
        HomeScreenPreviewContent()
    }
}

@Composable
fun HomeScreenPreviewContent() {
    val categories = listOf(
        Triple(Icons.Default.Add, "Add Medicine", listOf(Color(0xFF4CAF50), Color(0xFF2ECC71))),
        Triple(Icons.Default.Insights, "Insights", listOf(Color(0xFF2ECC71), Color(0xFF4CAF50)))
    )

    val recentInsights = listOf(
        Insight("Productivity Boost", "Learn 5 tips to increase daily productivity", "2 hours ago"),
        Insight("Better Sleep", "Expert advice for improving sleep quality", "5 hours ago"),
        Insight("Healthy Habits", "Building sustainable daily routines", "1 day ago")
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF6F6F6))
    ) {
        item {
            // HEADER
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF4CAF50), Color(0xFF2ECC71))
                        )
                    )
                    .padding(top = 60.dp, bottom = 30.dp, start = 24.dp, end = 24.dp)
            ) {
                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                "Hello, User",
                                color = Color.White,
                                fontWeight = FontWeight.Bold,
                                style = MaterialTheme.typography.titleLarge
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.WifiOff,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    "Offline Mode Active",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = 14.sp
                                )
                            }
                        }

                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Notifications,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }

                            IconButton(
                                onClick = {},
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                                    .background(Color.White.copy(alpha = 0.2f))
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(25.dp))

                    Button(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Chat,
                            contentDescription = "",
                            tint = Color(0xFF2ECC71)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Ask Expert", color = Color(0xFF2ECC71))
                    }
                }
            }
        }

        item {
            Text(
                "Categories",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }

        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                categories.forEach { (icon, title, gradient) ->
                    Button(
                        onClick = {},
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                        shape = RoundedCornerShape(20.dp)
                    ) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(RoundedCornerShape(14.dp))
                                    .background(Brush.linearGradient(gradient)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = icon,
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                            Spacer(Modifier.height(8.dp))
                            Text(title, color = Color.Black, fontSize = 12.sp)
                        }
                    }
                }
            }
        }

        item {
            Text(
                "Recent Insights",
                modifier = Modifier.padding(24.dp),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.titleMedium
            )
        }

        items(recentInsights) { insight ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 6.dp),
                shape = RoundedCornerShape(18.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(insight.title, fontWeight = FontWeight.Bold)
                    Text(insight.description, color = Color.Gray, fontSize = 14.sp)
                    Spacer(Modifier.height(4.dp))
                    Text(insight.time, color = Color.LightGray, fontSize = 12.sp)
                }
            }
        }
    }
}

// ==================== CHAT SCREEN ====================

@Preview(
    name = "2. Chat Screen",
    showBackground = true,
    showSystemUi = true,
    group = "Main Screens"
)
@Composable
fun Preview2_ChatScreen() {
    Startup_hackathon20Theme {
        ChatScreenPreviewContent()
    }
}

@Composable
fun ChatScreenPreviewContent() {
    val messages = listOf(
        Pair("Hello! I'm your offline expert assistant. How can I help you today?", false),
        Pair("What are the best practices for taking medicine?", true),
        Pair("Always follow your doctor's instructions and take medicine at the prescribed times. Keep them in a cool, dry place and never share your medication with others.", false)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF7F7F7))
    ) {
        // TOP BAR
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF4CAF50), Color(0xFF2ecc71))
                    )
                )
                .padding(16.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                    onClick = {},
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(Color.White.copy(alpha = 0.2f))
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }

                Spacer(Modifier.width(12.dp))

                Column {
                    Text(
                        "Expert Chat",
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.WifiOff,
                            contentDescription = "",
                            tint = Color.White,
                            modifier = Modifier.size(14.dp)
                        )
                        Spacer(Modifier.width(4.dp))
                        Text("Offline", color = Color.White.copy(0.9f), fontSize = 12.sp)
                    }
                }
            }
        }

        // MESSAGES
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(messages) { (text, isUser) ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = if (isUser) Arrangement.End else Arrangement.Start
                ) {
                    Box(
                        modifier = Modifier
                            .then(
                                if (isUser) {
                                    Modifier.background(
                                        brush = Brush.linearGradient(
                                            listOf(Color(0xFF4CAF50), Color(0xFF2ECC71))
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
                            text,
                            color = if (isUser) Color.White else Color.Black,
                            fontSize = 14.sp
                        )
                    }
                }
            }
        }

        // INPUT FIELD
        Row(
            modifier = Modifier
                .background(Color.White)
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                placeholder = { Text("Type your question...") },
                modifier = Modifier
                    .weight(1f)
                    .border(1.dp, Color.LightGray, RoundedCornerShape(12.dp)),
                shape = RoundedCornerShape(12.dp)
            )

            Spacer(Modifier.width(10.dp))

            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.linearGradient(
                            listOf(Color(0xFF4CAF50), Color(0xFF2ECC71))
                        )
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send",
                    tint = Color.White
                )
            }
        }
    }
}

// ==================== INSIGHTS - EMPTY STATE ====================

@Preview(
    name = "3. Insights Screen - Empty",
    showBackground = true,
    showSystemUi = true,
    group = "Main Screens"
)
@Composable
fun Preview3_InsightsEmpty() {
    Startup_hackathon20Theme {
        InsightsEmptyPreviewContent()
    }
}

@Composable
fun InsightsEmptyPreviewContent() {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF8FAF9))
    ) {
        // TOP BAR
        Row(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFF34D399))
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                "Medical Insights",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Column(
            Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.MedicalServices,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "No Medicines Added Yet",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Add medicines from the home screen to see them here",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.LightGray,
                textAlign = TextAlign.Center
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            Text(
                "🔒 All medicines stored securely offline",
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}

// ==================== NOTIFICATIONS SCREEN ====================

@Preview(
    name = "4. Notifications Screen",
    showBackground = true,
    showSystemUi = true,
    group = "Main Screens"
)
@Composable
fun Preview4_Notifications() {
    Startup_hackathon20Theme {
        NotificationsPreviewContent()
    }
}

@Composable
fun NotificationsPreviewContent() {
    val insights = listOf(
        Triple(Icons.Default.Lightbulb, "Daily Tip: Morning Routine", "8:00 AM"),
        Triple(Icons.Default.MenuBook, "New Guide Available", "2 hours ago"),
        Triple(Icons.Default.TrendingUp, "Weekly Progress Update", "5 hours ago")
    )

    Column(
        Modifier
            .fillMaxSize()
            .background(Color(0xFFF3F4F6))
    ) {
        // TOP BAR
        Row(
            Modifier
                .fillMaxWidth()
                .background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFF34D399), Color(0xFF10B981))
                    )
                )
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {}) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                "Daily Insights",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }

        LazyColumn(
            Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(insights) { (icon, title, time) ->
                Box(
                    Modifier
                        .fillMaxWidth()
                        .shadow(4.dp, RoundedCornerShape(18.dp))
                        .background(Color.White, RoundedCornerShape(18.dp))
                        .padding(16.dp)
                ) {
                    Row(verticalAlignment = Alignment.Top) {
                        Box(
                            Modifier
                                .size(55.dp)
                                .background(
                                    Brush.linearGradient(
                                        listOf(Color(0xFF6EE7B7), Color(0xFF34D399))
                                    ),
                                    RoundedCornerShape(14.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
                        }

                        Spacer(Modifier.width(14.dp))

                        Column(Modifier.weight(1f)) {
                            Text(title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Spacer(Modifier.height(4.dp))
                            Text("Important health tip for today", style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                            Spacer(Modifier.height(6.dp))
                            Text(time, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
                        }
                    }
                }
            }
        }
    }
}

// ==================== MEDICINE CARD ====================

@Preview(
    name = "5. Medicine Card",
    showBackground = true,
    widthDp = 400,
    heightDp = 600,
    group = "Components"
)
@Composable
fun Preview5_MedicineCard() {
    Startup_hackathon20Theme {
        Surface(color = Color(0xFFF8FAF9)) {
            Column(modifier = Modifier.padding(16.dp)) {
                MedicineCardPreviewContent()
            }
        }
    }
}

@Composable
fun MedicineCardPreviewContent() {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(Date())

    Column(
        Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(22.dp))
            .background(Color.White, RoundedCornerShape(22.dp))
            .padding(20.dp)
    ) {
        // Header
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    "Aspirin",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.Black
                )
                Text(
                    "Added: $formattedDate",
                    color = Color.Gray,
                    style = MaterialTheme.typography.bodySmall
                )
            }

            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete",
                    tint = Color(0xFFEF4444)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Dosage
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                Modifier
                    .size(60.dp)
                    .background(Color(0xFF34D399), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.MedicalServices,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(32.dp)
                )
            }

            Spacer(Modifier.width(12.dp))

            Column {
                Text("Dosage", color = Color.Gray, fontSize = 12.sp)
                Text(
                    "500mg",
                    color = Color(0xFF059669),
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Details
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFFDF4), RoundedCornerShape(18.dp))
                .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(18.dp))
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            DetailRow("Frequency", "Twice daily")
            DetailRow("Time", "After meals")
            DetailRow("Duration", "7 days")
            DetailRow("Quantity", "14 tablets")
        }

        Spacer(Modifier.height(12.dp))

        // Instructions
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFFF3F4F6), RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = null,
                    tint = Color(0xFF34D399),
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(8.dp))
                Text(
                    "Instructions",
                    color = Color(0xFF059669),
                    fontWeight = FontWeight.Bold,
                    fontSize = 14.sp
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                "Take with plenty of water. Do not exceed recommended dosage.",
                fontSize = 14.sp,
                color = Color(0xFF374151)
            )
        }
    }
}

@Composable
fun DetailRow(label: String, value: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(label, color = Color.Gray, fontSize = 12.sp)
        Spacer(Modifier.height(4.dp))
        Text(value, fontSize = 14.sp)
    }
}
