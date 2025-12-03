package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.yourpackage.R

data class Insight(
    val title: String,
    val description: String,
    val time: String
)

@Composable
fun HomeScreen(
    onNavigate: (String) -> Unit
) {
    val categories = listOf(
        Triple(R.drawable.ic_pill, "Add Medicine", listOf(Color(0xFF4CAF50), Color(0xFF2ECC71))),
        Triple(R.drawable.ic_trending_up, "Insights", listOf(Color(0xFF2ECC71), Color(0xFF4CAF50)))
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
            // 🌈 HEADER
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
                                fontWeight = FontWeight.Bold
                            )

                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_wifi_off),
                                    contentDescription = "",
                                    tint = Color.White,
                                    modifier = Modifier.size(18.dp)
                                )
                                Spacer(Modifier.width(6.dp))
                                Text(
                                    "Offline Mode Active",
                                    color = Color.White.copy(alpha = 0.9f),
                                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                                )
                            }
                        }

                        // Top buttons
                        Row(horizontalArrangement = Arrangement.spacedBy(14.dp)) {
                            CircleIconButton(
                                icon = R.drawable.ic_bell
                            ) { onNavigate("notifications") }

                            CircleIconButton(
                                icon = R.drawable.ic_settings
                            ) { onNavigate("settings") }
                        }
                    }

                    Spacer(Modifier.height(25.dp))

                    // 💬 Ask Expert Button
                    Button(
                        onClick = { onNavigate("chat") },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_message_circle),
                            contentDescription = "",
                            tint = Color(0xFF2ECC71)
                        )
                        Spacer(Modifier.width(10.dp))
                        Text("Ask Expert", color = Color(0xFF2ECC71))
                    }
                }
            }
        }

        // 🟢 Categories Grid
        item {
            Text(
                "Categories",
                modifier = Modifier.padding(horizontal = 24.dp, vertical = 12.dp),
                fontWeight = FontWeight.Bold
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
                    CategoryCard(
                        icon = icon,
                        label = title,
                        gradient = gradient
                    ) {
                        onNavigate(
                            when (title) {
                                "Add Medicine" -> "addMedicine"
                                "Insights" -> "insights"
                                else -> ""
                            }
                        )
                    }
                }
            }
        }

        // 📝 Recent Insights
        item {
            Text(
                "Recent Insights",
                modifier = Modifier.padding(24.dp),
                fontWeight = FontWeight.Bold
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
                    Text(insight.description, color = Color.Gray)
                    Spacer(Modifier.height(4.dp))
                    Text(insight.time, color = Color.LightGray, fontSize = 12.sp)
                }
            }
        }
    }
}

@Composable
fun CircleIconButton(icon: Int, onClick: () -> Unit) {
    IconButton(
        onClick = onClick,
        modifier = Modifier
            .size(46.dp)
            .clip(CircleShape)
            .background(Color.White.copy(alpha = 0.2f))
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
fun CategoryCard(icon: Int, label: String, gradient: List<Color>, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.weight(1f),
        colors = ButtonDefaults.buttonColors(containerColor = Color.White),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(14.dp))
                    .background(
                        Brush.linearGradient(gradient)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(id = icon),
                    contentDescription = "",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(Modifier.height(8.dp))

            Text(label, color = Color.Black)
        }
    }
}
