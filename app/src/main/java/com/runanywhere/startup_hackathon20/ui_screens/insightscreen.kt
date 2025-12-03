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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class Medicine(
    val name: String,
    val dosage: String,
    val frequency: String,
    val time: String,
    val duration: String,
    val quantity: String,
    val instructions: String
)

data class Prescription(
    val id: String,
    val patientName: String,
    val date: String,
    val medicines: List<Medicine>
)

@Composable
fun InsightsScreen(onBack: () -> Unit) {

    val prescriptions = listOf(
        Prescription(
            id = "RX001",
            patientName = "John Doe",
            date = "Nov 30, 2025",
            medicines = listOf(
                Medicine(
                    name = "Amoxicillin",
                    dosage = "500mg",
                    frequency = "Three times a day",
                    time = "After meals",
                    duration = "7 days",
                    quantity = "21 tablets",
                    instructions = "Take with plenty of water. Complete the full course."
                ),
                Medicine(
                    name = "Paracetamol",
                    dosage = "650mg",
                    frequency = "As needed",
                    time = "When required for fever",
                    duration = "5 days",
                    quantity = "10 tablets",
                    instructions = "Do not exceed 4 doses in 24 hours. Take with food if stomach upset occurs."
                )
            )
        )
    )

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

        Column(
            Modifier
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            prescriptions.forEach { prescription ->
                PrescriptionCard(prescription)
            }

            Text(
                "🔒 All prescriptions stored securely offline",
                Modifier.fillMaxWidth(),
                color = Color.Gray,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun PrescriptionCard(prescription: Prescription) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
            .shadow(4.dp, RoundedCornerShape(22.dp))
            .background(Color.White, RoundedCornerShape(22.dp))
            .padding(20.dp)
    ) {

        // HEADER
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text("MediInsight", fontWeight = FontWeight.Bold, color = Color.Black)
                Text("Digital Prescription", color = Color.Gray)
            }

            Box(
                Modifier
                    .size(60.dp)
                    .background(Color(0xFF34D399), RoundedCornerShape(16.dp)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Default.Description, contentDescription = null, tint = Color.White)
            }
        }

        Spacer(Modifier.height(16.dp))

        // INFO GRID
        InfoItem(Icons.Default.Person, "Patient Name", prescription.patientName)
        InfoItem(Icons.Default.CalendarToday, "Date", prescription.date)
        InfoItem(Icons.Default.Tag, "Prescription ID", prescription.id)

        Spacer(Modifier.height(16.dp))

        Text(
            "Prescribed Medications",
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(10.dp))

        prescription.medicines.forEachIndexed { index, medicine ->
            MedicineCard(medicine, index)
        }

        Spacer(Modifier.height(16.dp))

        Divider()
        Text(
            "This is a digital prescription stored securely on your device.",
            Modifier.padding(top = 12.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.labelSmall
        )
        Text(
            "Generated by MediInsight Offline Expert System",
            Modifier.padding(top = 4.dp),
            color = Color.Gray,
            style = MaterialTheme.typography.labelSmall
        )
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
fun MedicineCard(medicine: Medicine, index: Int) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(Color(0xFFEFFDF4), RoundedCornerShape(18.dp))
            .border(1.dp, Color(0xFFBBF7D0), RoundedCornerShape(18.dp))
            .padding(16.dp)
    ) {

        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(medicine.name, fontWeight = FontWeight.Bold)
                Text(medicine.dosage, color = Color(0xFF059669))
            }

            Box(
                Modifier
                    .background(Color.White, RoundedCornerShape(50))
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {
                Text("#${index + 1}", color = Color.Black)
            }
        }

        Spacer(Modifier.height(12.dp))

        InfoGrid("Frequency", medicine.frequency)
        InfoGrid("Time", medicine.time)
        InfoGrid("Duration", medicine.duration)
        InfoGrid("Quantity", medicine.quantity)

        Spacer(Modifier.height(10.dp))

        Column(
            Modifier
                .background(Color.White, RoundedCornerShape(12.dp))
                .padding(12.dp)
        ) {
            Text("Instructions", color = Color.Gray, fontSize = MaterialTheme.typography.labelSmall.fontSize)
            Spacer(Modifier.height(6.dp))
            Text(medicine.instructions)
        }
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

