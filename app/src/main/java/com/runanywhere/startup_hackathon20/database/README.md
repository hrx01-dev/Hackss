# Medicine Room Database

This module provides a complete Room database implementation for storing medicine information
offline.

## Components

### 1. **MedicineEntity** (`MedicineEntity.kt`)

The database entity that represents a medicine record.

**Fields:**

- `id`: Auto-generated unique identifier
- `name`: Medicine name
- `dosage`: Dosage information (e.g., "500mg")
- `frequency`: How often to take (e.g., "3 times a day")
- `time`: When to take (e.g., "After meals")
- `duration`: Treatment duration (e.g., "7 days")
- `quantity`: Total quantity (e.g., "21 tablets")
- `instructions`: Additional instructions
- `createdAt`: Timestamp when added

### 2. **MedicineDao** (`MedicineDao.kt`)

Data Access Object for database operations.

**Methods:**

- `getAllMedicines()`: Returns a Flow of all medicines (auto-updates UI)
- `getMedicineById(id)`: Get a specific medicine
- `insertMedicine(medicine)`: Add a new medicine
- `insertMedicines(medicines)`: Add multiple medicines
- `updateMedicine(medicine)`: Update existing medicine
- `deleteMedicine(medicine)`: Delete a medicine
- `deleteAllMedicines()`: Clear all medicines
- `getMedicineCount()`: Get total count

### 3. **MedicineDatabase** (`MedicineDatabase.kt`)

The Room database singleton.

**Usage:**

```kotlin
val database = MedicineDatabase.getDatabase(context)
val dao = database.medicineDao()
```

### 4. **MedicineRepository** (`MedicineRepository.kt`)

Repository pattern for clean architecture.

**Usage:**

```kotlin
val repository = MedicineRepository(dao)
val medicines = repository.allMedicines.collect { medicines ->
    // Update UI
}
```

### 5. **MedicineViewModel** (`../viewmodel/MedicineViewModel.kt`)

ViewModel for managing UI state and database operations.

**Usage in Composables:**

```kotlin
@Composable
fun MyScreen(viewModel: MedicineViewModel = viewModel()) {
    val medicines by viewModel.allMedicines.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
    
    // Add a medicine
    viewModel.insertMedicine(
        name = "Aspirin",
        dosage = "500mg",
        frequency = "Twice daily",
        time = "After meals",
        duration = "5 days",
        quantity = "10 tablets",
        instructions = "Take with water"
    )
}
```

## UI Screens

### 1. **AddMedicineScreen** (`../ui_screens/AddMedicineScreen.kt`)

Form for adding new medicines with validation.

**Features:**

- Input fields for all medicine properties
- Real-time validation
- Success dialog
- Error handling
- Loading states

### 2. **InsightsScreen** (`../ui_screens/insightscreen.kt`)

Displays all stored medicines from the database.

**Features:**

- List of all medicines
- Delete functionality
- Empty state handling
- Beautiful card layouts
- Auto-updates when data changes

## Integration Example

### Option 1: Update MainActivity

```kotlin
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Startup_hackathon20Theme {
                MedicineApp() // Use the navigation example
            }
        }
    }
}
```

### Option 2: Manual Navigation

```kotlin
@Composable
fun MyApp() {
    val viewModel: MedicineViewModel = viewModel()
    var currentScreen by remember { mutableStateOf("home") }
    
    when (currentScreen) {
        "home" -> HomeScreen(onNavigate = { currentScreen = it })
        "addMedicine" -> AddMedicineScreen(
            onBack = { currentScreen = "home" },
            viewModel = viewModel
        )
        "insights" -> InsightsScreen(
            onBack = { currentScreen = "home" },
            viewModel = viewModel
        )
    }
}
```

## Database Operations

### Insert Medicine

```kotlin
viewModel.insertMedicine(
    name = "Medicine Name",
    dosage = "500mg",
    frequency = "3 times daily",
    time = "After meals",
    duration = "7 days",
    quantity = "21 tablets",
    instructions = "Take with plenty of water"
)
```

### Observe All Medicines

```kotlin
val medicines by viewModel.allMedicines.collectAsState()
```

### Delete Medicine

```kotlin
viewModel.deleteMedicine(medicineEntity)
```

### Delete All Medicines

```kotlin
viewModel.deleteAllMedicines()
```

## Features

✅ **Offline First**: All data stored locally using Room  
✅ **Reactive UI**: Flow-based updates automatically refresh UI  
✅ **Type Safe**: Kotlin coroutines and type-safe database queries  
✅ **MVVM Architecture**: Clean separation of concerns  
✅ **Error Handling**: Comprehensive error states and messages  
✅ **Beautiful UI**: Modern Material 3 design with animations  
✅ **Validation**: Input validation before saving  
✅ **CRUD Operations**: Complete Create, Read, Update, Delete support

## Database Schema

```sql
CREATE TABLE medicines (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    dosage TEXT NOT NULL,
    frequency TEXT NOT NULL,
    time TEXT NOT NULL,
    duration TEXT NOT NULL,
    quantity TEXT NOT NULL,
    instructions TEXT NOT NULL,
    createdAt INTEGER NOT NULL
);
```

## Dependencies

Already included in `app/build.gradle.kts`:

```kotlin
implementation("androidx.room:room-runtime:2.6.1")
implementation("androidx.room:room-ktx:2.6.1")
kapt("androidx.room:room-compiler:2.6.1")
```

## Notes

- Database is automatically created on first launch
- Data persists across app restarts
- Thread-safe operations using Kotlin coroutines
- All database operations run on background threads
- UI automatically updates when data changes using Flow
