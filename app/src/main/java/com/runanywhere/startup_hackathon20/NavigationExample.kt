package com.runanywhere.startup_hackathon20

import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.runanywhere.startup_hackathon20.ui_screens.*
import com.runanywhere.startup_hackathon20.viewmodel.MedicineViewModel

/**
 * Example of how to integrate the medicine database screens with navigation
 *
 * To use this in your MainActivity, you can replace the current content with:
 *
 * setContent {
 *     Startup_hackathon20Theme {
 *         MedicineApp()
 *     }
 * }
 */
@Composable
fun MedicineApp(viewModel: MedicineViewModel = viewModel()) {
    var currentScreen by remember { mutableStateOf("home") }

    when (currentScreen) {
        "home" -> {
            HomeScreen(
                onNavigate = { route ->
                    currentScreen = route
                }
            )
        }

        "addMedicine" -> {
            AddMedicineScreen(
                onBack = { currentScreen = "home" },
                onMedicineAdded = {
                    // Navigate to insights after adding medicine
                    currentScreen = "insights"
                },
                viewModel = viewModel
            )
        }

        "insights" -> {
            InsightsScreen(
                onBack = { currentScreen = "home" },
                viewModel = viewModel
            )
        }

        "chat" -> {
            // Your existing chat screen
            currentScreen = "home" // For now, go back to home
        }

        "notifications" -> {
            // Your notifications screen
            currentScreen = "home" // For now, go back to home
        }

        "settings" -> {
            // Your settings screen
            currentScreen = "home" // For now, go back to home
        }

        else -> {
            HomeScreen(
                onNavigate = { route ->
                    currentScreen = route
                }
            )
        }
    }
}
