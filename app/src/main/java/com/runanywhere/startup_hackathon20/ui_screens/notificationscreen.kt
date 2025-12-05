package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme

data class NotificationInsight(
    val icon: androidx.compose.ui.graphics.vector.ImageVector,
    val title: String,
    val description: String,
    val time: String,
    val gradient: Brush
)

@Composable
fun NotificationsScreen(onBack: () -> Unit) {

    val insights = listOf(
        NotificationInsight(
            icon = Icons.Default.Lightbulb,
            title = "Daily Tip: Morning Routine",
            description = "Start your day with a 5-minute planning session to boost productivity by up to 30%.",
            time = "8:00 AM",
            gradient = Brush.linearGradient(listOf(Color(0xFF6EE7B7), Color(0xFF34D399)))
        ),
        NotificationInsight(
            icon = Icons.Default.MenuBook,
            title = "New Guide Available",
            description = "Check out our latest guide on effective time management strategies.",
            time = "2 hours ago",
            gradient = Brush.linearGradient(listOf(Color(0xFF5EEAD4), Color(0xFF22D3EE)))
        ),
        NotificationInsight(
            icon = Icons.Default.TrendingUp,
            title = "Weekly Progress Update",
            description = "You’ve completed 12 expert sessions this week. Keep up the great work!",
            time = "5 hours ago",
            gradient = Brush.linearGradient(listOf(Color(0xFF34D399), Color(0xFF14B8A6)))
        ),
        NotificationInsight(
            icon = Icons.Default.Notifications,
            title = "Reminder: Daily Check-in",
            description = "Don’t forget to log your daily achievements and reflections.",
            time = "Yesterday",
            gradient = Brush.linearGradient(listOf(Color(0xFF34D399), Color(0xFF10B981)))
        ),
        NotificationInsight(
            icon = Icons.Default.Lightbulb,
            title = "Expert Insight",
            description = "Taking regular breaks can improve focus and reduce mental fatigue.",
            time = "2 days ago",
            gradient = Brush.linearGradient(listOf(Color(0xFF6EE7B7), Color(0xFF34D399)))
        )
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
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                "Daily Insights",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }

        Column(
            Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            insights.forEach { insight ->
                InsightCard(insight)
                Spacer(Modifier.height(10.dp))
            }
        }
    }
}

@Composable
fun InsightCard(insight: NotificationInsight) {
    Box(
        Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(18.dp))
            .background(Color.White, RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.Top) {

            // ICON GRADIENT BOX
            Box(
                Modifier
                    .size(55.dp)
                    .background(insight.gradient, RoundedCornerShape(14.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(insight.icon, contentDescription = null, tint = Color.White, modifier = Modifier.size(28.dp))
            }

            Spacer(Modifier.width(14.dp))

            Column(Modifier.weight(1f)) {
                Text(insight.title, style = MaterialTheme.typography.titleMedium)
                Spacer(Modifier.height(4.dp))
                Text(insight.description, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
                Spacer(Modifier.height(6.dp))
                Text(insight.time, style = MaterialTheme.typography.labelSmall, color = Color.Gray)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun NotificationsScreenPreview() {
    Startup_hackathon20Theme {
        NotificationsScreen(onBack = {})
    }
}
