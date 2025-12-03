package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.*
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.R

@Composable
fun AuthScreen(onComplete: () -> Unit) {

    var activeTab by remember { mutableStateOf("login") }
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.linearGradient(
                    colors = listOf(Color(0xFFE8FBEA), Color(0xFFD2F6E4))
                )
            )
    ) {

        FloatingBubbles()

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text("Welcome Back", fontSize = MaterialTheme.typography.headlineMedium.fontSize)
            Text("Sign in to continue", color = Color.Gray, modifier = Modifier.padding(bottom = 20.dp))

            Card(
                shape = RoundedCornerShape(28.dp),
                elevation = CardDefaults.cardElevation(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    // Toggle Buttons Row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFFEFEFEF), RoundedCornerShape(20.dp))
                            .padding(4.dp)
                    ) {

                        TabButton("login", activeTab) { activeTab = "login" }
                        Spacer(Modifier.width(8.dp))
                        TabButton("signup", activeTab) { activeTab = "signup" }
                    }

                    Spacer(Modifier.height(16.dp))

                    if (activeTab == "signup") {
                        InputField(
                            label = "Name",
                            value = name,
                            onValueChange = { name = it },
                            icon = R.drawable.ic_user
                        )
                    }

                    InputField(
                        label = "Username",
                        value = username,
                        onValueChange = { username = it },
                        icon = R.drawable.ic_mail
                    )

                    InputField(
                        label = "Password",
                        value = password,
                        onValueChange = { password = it },
                        icon = R.drawable.ic_lock,
                        isPassword = true
                    )

                    if (activeTab == "signup") {
                        InputField(
                            label = "Confirm Password",
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            icon = R.drawable.ic_lock,
                            isPassword = true
                        )
                    }

                    Spacer(Modifier.height(18.dp))

                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = { onComplete() },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Box(
                            modifier = Modifier
                                .background(
                                    Brush.horizontalGradient(
                                        listOf(Color(0xFF4CAF50), Color(0xFF2EAD73))
                                    ),
                                    RoundedCornerShape(16.dp)
                                )
                                .padding(14.dp)
                        ) {
                            Text(
                                text = if (activeTab == "login") "Login" else "Create Account",
                                color = Color.White
                            )
                        }
                    }

                    Text(
                        "🔒 Data stored securely offline",
                        color = Color.Gray,
                        fontSize = MaterialTheme.typography.bodySmall.fontSize,
                        modifier = Modifier.padding(top = 16.dp).align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}
