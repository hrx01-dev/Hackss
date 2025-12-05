# 🎨 UI Preview Guide

## Overview

All screens and components now have preview functions that allow you to view them in Android Studio
without running the app on a device!

## 📱 Screens with Previews

### 1. **HomeScreen** (`homescreen.kt`)

- ✅ Full screen preview
- ✅ Category card component preview

### 2. **AddMedicineScreen** (`AddMedicineScreen.kt`)

- ✅ Full form preview with all input fields

### 3. **InsightsScreen** (`insightscreen.kt`)

- ✅ Empty state preview
- ✅ Medicine card component preview

### 4. **ChatScreen** (`chatscreen.kt`)

- ✅ Full chat interface preview

### 5. **NotificationsScreen** (`notificationscreen.kt`)

- ✅ Notification list preview

### 6. **PreviewShowcase** (`PreviewShowcase.kt`) - NEW!

- ✅ All screens in one place
- ✅ Individual component previews
- ✅ Color palette showcase
- ✅ Chat message bubbles

---

## 🚀 How to View Previews

### Method 1: In Android Studio (Recommended)

#### Step 1: Open a Screen File

Open any of these files:

- `homescreen.kt`
- `AddMedicineScreen.kt`
- `insightscreen.kt`
- `chatscreen.kt`
- `notificationscreen.kt`
- **`PreviewShowcase.kt`** (Best - shows all screens!)

#### Step 2: Enable Preview Mode

1. Look at the top-right corner of the editor
2. Click on **"Split"** or **"Design"** button
3. Or use keyboard shortcut: **`Ctrl + Shift + P`** (Windows/Linux) or **`Cmd + Shift + P`** (Mac)

#### Step 3: View the Preview

- The preview will render on the right side
- You can scroll through multiple previews if available
- Click the **↻ Refresh** button if preview doesn't load

### Method 2: Interactive Preview

1. Open any file with `@Preview` annotation
2. Click on the **⚡ Split** button at the top right
3. Click the **▶️ Play** button in the preview panel
4. Select a device to run the preview interactively

---

## 📂 Preview Files Structure

```
app/src/main/java/com/runanywhere/startup_hackathon20/ui_screens/
├── homescreen.kt                    ← 2 previews
├── AddMedicineScreen.kt             ← 1 preview
├── insightscreen.kt                 ← 2 previews
├── chatscreen.kt                    ← 1 preview
├── notificationscreen.kt            ← 1 preview
└── PreviewShowcase.kt               ← 9 previews! 🎉
```

---

## 🎯 Available Previews

### Screen Previews

| Preview Name | File | Shows |
|-------------|------|-------|
| `HomeScreenPreview` | homescreen.kt | Full home screen with categories |
| `CategoryCardPreview` | homescreen.kt | Individual category button |
| `AddMedicineScreenPreview` | AddMedicineScreen.kt | Medicine input form |
| `InsightsScreenEmptyPreview` | insightscreen.kt | Empty state message |
| `MedicineCardPreview` | insightscreen.kt | Medicine card with details |
| `ChatScreenPreview` | chatscreen.kt | Chat interface |
| `NotificationsScreenPreview` | notificationscreen.kt | Notification list |

### Showcase Previews (PreviewShowcase.kt)

| Preview Name | Shows |
|-------------|-------|
| `PreviewHomeScreen` | Complete home screen |
| `PreviewAddMedicineScreen` | Add medicine form |
| `PreviewInsightsScreenEmpty` | Insights empty state |
| `PreviewChatScreen` | Chat interface |
| `PreviewNotificationsScreen` | Notifications |
| `PreviewMedicineCard` | Medicine card component |
| `PreviewNotificationCard` | Notification card |
| `PreviewChatMessageUser` | User chat bubble |
| `PreviewChatMessageBot` | Bot chat bubble |
| `PreviewColorPalette` | App color scheme |

---

## 🎨 Preview Annotations

### Full Screen Preview

```kotlin
@Preview(
    showBackground = true,      // Show white background
    showSystemUi = true,        // Show status bar and nav bar
    name = "Screen Name"        // Name in preview list
)
@Composable
fun MyScreenPreview() {
    Startup_hackathon20Theme {
        MyScreen()
    }
}
```

### Component Preview

```kotlin
@Preview(
    showBackground = true,
    widthDp = 400,             // Custom width
    heightDp = 200,            // Custom height
    name = "Component Name"
)
@Composable
fun MyComponentPreview() {
    Startup_hackathon20Theme {
        MyComponent()
    }
}
```

### Multiple Previews

```kotlin
@Preview(name = "Light Mode", showBackground = true)
@Preview(name = "Dark Mode", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MyPreview() {
    // Preview content
}
```

---

## 💡 Tips & Tricks

### 1. Quick Preview Navigation

- Use the preview selector dropdown to switch between previews
- Group previews using `group = "Screens"` parameter

### 2. Preview Parameters

```kotlin
@Preview(
    showBackground = true,      // White background
    showSystemUi = true,        // System UI (status bar, etc.)
    widthDp = 360,             // Device width
    heightDp = 640,            // Device height
    name = "My Preview",       // Preview name
    group = "Screens"          // Group name
)
```

### 3. Interactive Preview

- Click the **▶️ Play** button to run preview on emulator
- Test interactions without full app launch

### 4. Multiple Device Previews

```kotlin
@Preview(name = "Phone", device = Devices.PIXEL_4)
@Preview(name = "Tablet", device = Devices.PIXEL_C)
@Composable
fun MultiDevicePreview() {
    // Your composable
}
```

---

## 🔧 Troubleshooting

### Preview Not Loading?

1. **Build the project first**
   ```bash
   ./gradlew :app:assembleDebug
   ```

2. **Refresh preview**
    - Click the ↻ Refresh button in preview panel
    - Or use: **Build → Refresh Preview**

3. **Invalidate caches**
    - File → Invalidate Caches → Invalidate and Restart

### Preview Shows Error?

1. **Check imports**
    - Make sure all required imports are present
    - Preview functions need `@Preview` annotation

2. **Check theme**
    - Wrap preview in `Startup_hackathon20Theme { }`

3. **Check parameters**
    - Preview functions should have no required parameters
    - Use default values or mock data

### Build Taking Too Long?

1. **Use Compose Preview only**
    - No need to build entire app
    - Preview renders faster than full build

2. **Disable unused previews**
    - Comment out `@Preview` annotation
    - Reduces preview rendering time

---

## 📱 Preview Showcase Features

The new `PreviewShowcase.kt` file includes:

### ✨ All Screens

- View all 5 main screens in one place
- Switch between previews easily
- See complete layouts

### 🎯 Individual Components

- Medicine cards
- Notification cards
- Chat message bubbles
- Form inputs

### 🎨 Color Palette

- All app colors displayed
- Hex codes shown
- Easy reference for design

### 💬 Chat Bubbles

- User messages (green gradient)
- Bot messages (white)
- Proper alignment and styling

---

## 🚀 Quick Start

### Best Way to View All Previews

1. Open `PreviewShowcase.kt`
2. Click **Split** mode (top-right)
3. Scroll through all previews
4. Click dropdown to select specific preview

### View Individual Screen

1. Open the screen file (e.g., `homescreen.kt`)
2. Enable **Design** mode
3. See the screen instantly!

### Interactive Testing

1. Open any preview
2. Click **▶️ Play** in preview panel
3. Select device
4. Interact with the preview

---

## 🎯 Benefits

### ⚡ Fast Development

- No need to run app on device
- Instant visual feedback
- Quick iterations

### 🎨 Design Verification

- See UI before running
- Compare variations side-by-side
- Check all screen states

### 🐛 Easy Debugging

- Spot layout issues immediately
- Test different data states
- Verify responsive design

### 📱 Multiple Devices

- Preview on different screen sizes
- Test tablet layouts
- Check phone variations

---

## 📚 Additional Resources

### Android Documentation

- [Jetpack Compose Preview](https://developer.android.com/jetpack/compose/tooling/previews)
- [Interactive Preview](https://developer.android.com/jetpack/compose/tooling#interactive-preview)

### Preview Best Practices

1. Add previews to all `@Composable` functions
2. Use descriptive names
3. Group related previews
4. Include edge cases (empty, loading, error states)

---

## ✅ Summary

All screens now have complete preview support:

- ✅ 5 main screens with full previews
- ✅ 10+ component previews
- ✅ Color palette showcase
- ✅ Interactive preview support
- ✅ Multiple preview variations
- ✅ `PreviewShowcase.kt` for everything in one place

**Happy Previewing! 🎨**
