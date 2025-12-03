package com.runanywhere.startup_hackathon20.ui_screens
import com.runanywhere.startup_hackathon20.R
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image

@Composable
fun SplashScreen(onComplete: () -> Unit) {
    // Fade-in animation state
    var visible by remember { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 800)
    )

    // Trigger animation and auto-complete
    LaunchedEffect(Unit) {
        visible = true
        delay(2000L) // 2 seconds delay
        onComplete()
    }

    // Full-screen gradient background
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFFE6F4EA), // green-50
                        Color(0xFFD1F0E0), // emerald-50
                        Color(0xFFBCE7DF)  // teal-50
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.alpha(alpha),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Card with lightbulb
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color(0xFF22C55E), // green-400
                                Color(0xFF10B981)  // emerald-500
                            )
                        ),
                        shape = RoundedCornerShape(24.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_lightbulb), // replace with your drawable
                    contentDescription = "Lightbulb",
                    modifier = Modifier.size(48.dp)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Title & subtitle
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "MediInsight",
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontSize = 24.sp,
                        color = Color(0xFF111827) // gray-900
                    )
                )
                Text(
                    text = "Your Offline Expert Companion",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 16.sp,
                        color = Color(0xFF4B5563) // gray-600
                    )
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun splashscreenPreview(){
    SplashScreen(onComplete={})
}

