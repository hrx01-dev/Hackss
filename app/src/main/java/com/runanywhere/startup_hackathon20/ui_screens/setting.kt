package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(
    theme: String,
    onThemeChange: (String) -> Unit,
    onBack: () -> Unit
) {
    var showThemeSelector by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
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
            Text("Settings", color = Color.White, style = MaterialTheme.typography.titleLarge)
        }

        Column(Modifier.padding(16.dp)) {

            // THEME SELECTOR CARD
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .shadow(2.dp, RoundedCornerShape(20.dp))
            ) {

                // Toggle Row
                Row(
                    Modifier
                        .fillMaxWidth()
                        .clickable { showThemeSelector = !showThemeSelector }
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // ICON BOX
                    Box(
                        Modifier
                            .size(45.dp)
                            .background(
                                if (theme == "light") Color(0xFFFBBF24) else Color(0xFF4F46E5),
                                RoundedCornerShape(12.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            if (theme == "light") Icons.Default.WbSunny else Icons.Default.DarkMode,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }

                    Spacer(Modifier.width(14.dp))

                    Column(Modifier.weight(1f)) {
                        Text("Theme", style = MaterialTheme.typography.titleMedium)
                        Text(
                            if (theme == "light") "Light Mode" else "Dark Mode",
                            style = MaterialTheme.typography.bodySmall,
                            color = Color.Gray
                        )
                    }

                    Icon(
                        Icons.Default.ChevronRight,
                        contentDescription = null,
                        tint = Color.Gray,
                        modifier = Modifier.rotate(if (showThemeSelector) 90f else 0f)
                    )
                }

                // EXPANDED OPTIONS
                AnimatedVisibility(showThemeSelector) {

                    Column(Modifier.padding(16.dp)) {

                        // LIGHT THEME OPTION
                        ThemeOption(
                            selected = theme == "light",
                            title = "Light Mode",
                            subtitle = "Green & White",
                            gradient = Brush.horizontalGradient(
                                listOf(Color(0xFFE8FDEB), Color(0xFFD1FADF))
                            ),
                            icon = Icons.Default.WbSunny
                        ) { onThemeChange("light") }

                        Spacer(Modifier.height(14.dp))

                        // DARK THEME OPTION
                        ThemeOption(
                            selected = theme == "dark",
                            title = "Dark Mode",
                            subtitle = "Slate & Indigo",
                            gradient = Brush.horizontalGradient(
                                listOf(Color(0xFF1E293B), Color(0xFF312E81))
                            ),
                            icon = Icons.Default.DarkMode
                        ) { onThemeChange("dark") }
                    }
                }
            }

            Spacer(Modifier.height(22.dp))

            // ABOUT SECTION
            Column(
                Modifier
                    .fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(20.dp))
                    .shadow(2.dp, RoundedCornerShape(20.dp))
            ) {

                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Box(
                        Modifier
                            .size(45.dp)
                            .background(Color.Gray, RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(Icons.Default.Info, contentDescription = null, tint = Color.White)
                    }

                    Spacer(Modifier.width(14.dp))

                    Column(Modifier.weight(1f)) {
                        Text("About App", style = MaterialTheme.typography.titleMedium)
                        Text("Version 1.0.0", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    }

                    Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.Gray)
                }
            }

            Spacer(Modifier.height(32.dp))

            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text("MediInsight", color = Color.Gray)
                Text("All data stored securely on your device", color = Color(0xFF9CA3AF), fontSize = MaterialTheme.typography.labelSmall.fontSize)
            }
        }
    }
}

@Composable
fun ThemeOption(
    selected: Boolean,
    title: String,
    subtitle: String,
    gradient: Brush,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    onClick: () -> Unit
) {
    Column(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .border(
                2.dp,
                if (selected) Color(0xFF10B981) else Color(0xFFDDDDDD),
                RoundedCornerShape(16.dp)
            )
            .background(Color.White, RoundedCornerShape(16.dp))
            .padding(16.dp)
    ) {

        Row(verticalAlignment = Alignment.CenterVertically) {

            // GRADIENT ICON BOX
            Box(
                Modifier
                    .size(45.dp)
                    .background(gradient, RoundedCornerShape(10.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = Color.White)
            }

            Spacer(Modifier.width(12.dp))

            Column(Modifier.weight(1f)) {
                Text(title, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
            }

            if (selected) {
                Box(
                    Modifier
                        .size(24.dp)
                        .background(Color(0xFF10B981), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}
