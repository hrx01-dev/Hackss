package com.runanywhere.startup_hackathon20.ui_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.runanywhere.startup_hackathon20.database.MedicineEntity
import com.runanywhere.startup_hackathon20.ui.theme.Startup_hackathon20Theme
import com.runanywhere.startup_hackathon20.viewmodel.MedicineViewModel
import java.text.SimpleDateFormat
import java.util.*

data class Medicine(
    val id: Long = 0,
    val name: String,
    val dosage: String,
    val frequency: String,
    val time: String,
    val duration: String,
    val quantity: String,
    val instructions: String,
    val createdAt: Long = System.currentTimeMillis()
)

fun MedicineEntity.toMedicine() = Medicine(
    id = id,
    name = name,
    dosage = dosage,
    frequency = frequency,
    time = time,
    duration = duration,
    quantity = quantity,
    instructions = instructions,
    createdAt = createdAt
)

@Composable
fun InsightsScreen(
    onBack: () -> Unit,
    viewModel: MedicineViewModel = viewModel()
) {
    val medicines by viewModel.allMedicines.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()

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
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                "Medical Insights",
                color = Color.White,
                style = MaterialTheme.typography.titleLarge
            )
        }

        // Error message
        error?.let { errorMessage ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFEE2E2))
            ) {
                Text(
                    text = errorMessage,
                    modifier = Modifier.padding(16.dp),
                    color = Color(0xFFDC2626)
                )
            }
        }

        Column(
            Modifier
                .weight(1f)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            if (isLoading) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(color = Color(0xFF34D399))
                }
            } else if (medicines.isEmpty()) {
                // Empty state
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(32.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
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
                }
            } else {
                medicines.forEach { medicineEntity ->
                    MedicineCard(
                        medicine = medicineEntity.toMedicine(),
                        onDelete = { viewModel.deleteMedicine(medicineEntity) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "🔒 All medicines stored securely offline",
                Modifier.fillMaxWidth(),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}



@Composable
fun InfoItem(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String, value: String) {
    Row(
        Modifier
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(icon, contentDescription = null, tint = Color(0xFF34D399))
        Spacer(Modifier.width(8.dp))
        Column {
            Text(label, color = Color.Gray, fontSize = MaterialTheme.typography.labelSmall.fontSize)
            Text(value, fontWeight = FontWeight.Medium)
        }
    }
}

@Composable
fun MedicineCard(medicine: Medicine, onDelete: () -> Unit) {
    val dateFormat = SimpleDateFormat("MMM dd, yyyy 'at' hh:mm a", Locale.getDefault())
    val formattedDate = dateFormat.format(Date(medicine.createdAt))

    Column(
        Modifier
            .fillMaxWidth()
            .shadow(4.dp, RoundedCornerShape(22.dp))
            .background(Color.White, RoundedCornerShape(22.dp))
            .padding(20.dp)
    ) {
        // Header with medicine name and delete button
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    medicine.name,
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

            IconButton(onClick = onDelete) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete medicine",
                    tint = Color(0xFFEF4444)
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Medicine icon with dosage
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
                Text("Dosage", color = Color.Gray, style = MaterialTheme.typography.labelSmall)
                Text(
                    medicine.dosage,
                    color = Color(0xFF059669),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleMedium
                )
            }
        }

        Spacer(Modifier.height(16.dp))

        // Medicine details
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color(0xFFEFFDF4), RoundedCornerShape(18.dp))
                .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            InfoGrid("Frequency", medicine.frequency)
            InfoGrid("Time", medicine.time)
            InfoGrid("Duration", medicine.duration)
            InfoGrid("Quantity", medicine.quantity)
        }

        Spacer(Modifier.height(12.dp))

        // Instructions section
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
                    style = MaterialTheme.typography.titleSmall
                )
            }
            Spacer(Modifier.height(8.dp))
            Text(
                medicine.instructions,
                style = MaterialTheme.typography.bodyMedium,
                color = Color(0xFF374151)
            )
        }

        Spacer(Modifier.height(12.dp))

        Divider()

        Text(
            "🔒 Stored securely on your device",
            Modifier.padding(top = 8.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.labelSmall
        )
    }
}

@Composable
fun InfoGrid(label: String, value: String) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .background(Color.White, RoundedCornerShape(12.dp))
            .padding(12.dp)
    ) {
        Text(label, color = Color.Gray, fontSize = MaterialTheme.typography.labelSmall.fontSize)
        Spacer(Modifier.height(4.dp))
        Text(value)
    }
}

@Preview(showBackground = true, showSystemUi = true, name = "Insights Screen - Empty")
@Composable
fun InsightsScreenEmptyPreview() {
    Startup_hackathon20Theme {
        InsightsScreen(onBack = {})
    }
}

@Preview(showBackground = true, name = "Medicine Card", widthDp = 400)
@Composable
fun MedicineCardPreview() {
    Startup_hackathon20Theme {
        Column(modifier = Modifier.padding(16.dp)) {
            MedicineCard(
                medicine = Medicine(
                    id = 1,
                    name = "Aspirin",
                    dosage = "500mg",
                    frequency = "Twice daily",
                    time = "After meals",
                    duration = "7 days",
                    quantity = "14 tablets",
                    instructions = "Take with plenty of water. Do not exceed recommended dosage.",
                    createdAt = System.currentTimeMillis()
                ),
                onDelete = {}
            )
        }
    }
}
