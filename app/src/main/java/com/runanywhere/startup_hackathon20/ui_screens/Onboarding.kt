package com.runanywhere.startup_hackathon20.ui_screens
import com.runanywhere.startup_hackathon20.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Wifi
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Shield
import androidx.compose.material.icons.filled.ChevronRight

// Data class representing a single onboarding screen
data class OnboardingScreen(
    val icon: ImageVector,
    val title: String,
    val description: String,
    val gradientColors: List<Color>
)

@Composable
fun OnboardingScreens(onComplete: () -> Unit) {
    var currentScreen by remember { mutableStateOf(0) }

    val screens = listOf(
        OnboardingScreen(
            icon = Icons.Default.Wifi,
            title = "Works 100% Offline",
            description = "Access expert guidance anytime, anywhere. No internet connection required.",
            gradientColors = listOf(Color(0xFF34D399), Color(0xFF059669)) // green gradient
        ),
        OnboardingScreen(
            icon = Icons.Default.Bolt,
            title = "Instant Expert Guidance",
            description = "Get immediate answers and insights powered by advanced AI technology.",
            gradientColors = listOf(Color(0xFF14B8A6), Color(0xFF06B6D4)) // teal to cyan
        ),
        OnboardingScreen(
            icon = Icons.Default.Shield,
            title = "Safe, Private, Always Available",
            description = "Your data stays on your device. Complete privacy and security guaranteed.",
            gradientColors = listOf(Color(0xFF059669), Color(0xFF14B8A6)) // green to teal
        )
    )

    val screen = screens[currentScreen]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Main content
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp, vertical = 48.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(128.dp)
                    .background(
                        brush = Brush.linearGradient(screen.gradientColors),
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = screen.icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(64.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = screen.title,
                fontSize = 24.sp,
                color = Color.Black,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = screen.description,
                fontSize = 16.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }

        // Pagination dots and buttons
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                screens.forEachIndexed { index, _ ->
                    val width = if (index == currentScreen) 32.dp else 8.dp
                    val color = if (index == currentScreen) Color(0xFF10B981) else Color(0xFFD1D5DB)
                    Box(
                        modifier = Modifier
                            .height(8.dp)
                            .width(width)
                            .background(color, shape = CircleShape)
                            .padding(horizontal = 2.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    if (currentScreen < screens.lastIndex) {
                        currentScreen++
                    } else {
                        onComplete()
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF10B981)
                ),
                shape = RoundedCornerShape(28.dp)
            ) {
                Text(
                    text = if (currentScreen == screens.lastIndex) "Get Started" else "Next",
                    color = Color.White,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.width(8.dp))
                Icon(Icons.Default.ChevronRight, contentDescription = null, tint = Color.White)
            }

            if (currentScreen < screens.lastIndex) {
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = "Skip",
                    color = Color.Gray,
                    modifier = Modifier
                        .clickable { onComplete() }
                        .padding(16.dp)
                )
            }
        }
    }
}