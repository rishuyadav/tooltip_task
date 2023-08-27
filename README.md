# Plotline Internship Task
Mobile App Assignment | Plotline

A user-friendly Android application that allows users to customize and render tooltips based on their specifications.

## Table of Contents

- [About](#about)
- [Key Features](#key-features)
- [Screenshots](#screenshots)
- [Code Description](#code-description)
- [Tech Stack](#tech-stack)
- [Dependencies](#dependencies)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Folder Structure](#folder-structure)
- [Contact](#contact)

## About

This Android application allows users to create and display custom tooltips with various customization options such as text, text color, background color, padding, and more. Users can choose the position of the tooltip relative to a selected button, as well as associate an image with the tooltip.

## Key Features

- **Custom Tooltip Rendering:** Create and render tooltips with personalized text and styling options.
- **Positioning Options:** Choose from four different tooltip positions: top, right, bottom, and left, to ensure optimal display.
- **Text Formatting:** Adjust the text size for tooltips to fit your design requirements.
- **Color Customization:** Select custom text and background colors to match the app's aesthetics.
- **Padding Control:** Define padding to control the space between the tooltip content and its border.
- **Database Integration:** Save tooltip configurations to a database for future use or reference.
- **Easy Navigation:** Seamless navigation between tooltip rendering and preview activities.
- **Image Integration:** Associate images with tooltips for enhanced visual representation.



## Screenshots


<div style="display: flex; justify-content: center;">
    <div style="display: flex; flex-direction: column;">
        <img src="https://github.com/rishuyadav/tooltip_task/assets/72988817/d992f913-2aa5-451b-b42d-a58aaea9658a  alt="Screenshot 1"  alt="Screenshot 1" width="300"/>
        <img src="https://github.com/rishuyadav/tooltip_task/assets/72988817/35339776-8bc4-4545-9809-520e3fb63d51  alt="Screenshot 2" alt="Screenshot 2" width="300"/>
    </div>
    <div style="display: flex; flex-direction: column;">
        <img src="https://github.com/rishuyadav/tooltip_task/assets/72988817/8af30c50-7f87-4aa1-ac1d-887089631475" alt="Screenshot 3" width="300"/>
        <img src="https://github.com/rishuyadav/tooltip_task/assets/72988817/bdc6a08a-ff1e-4b4f-9baa-2b6ff126ea3d" alt="Screenshot 1" alt="Screenshot 4" width="300"/>
    </div>
</div>





https://github.com/rishuyadav/tooltip_task/assets/72988817/7bd57187-4270-47ef-8f35-7d17124978bd





## Code Description

#### `TooltipConfiguration.kt`

Data class that represents tooltip configurations for database storage, including properties like button index, tooltip text, position, colors, padding, and text size.

#### `TooltipDao.kt`
Room DAO interface for tooltip configurations. Defines database operations for insertion and retrieval of tooltip configurations.

#### `TooltipDatabase.kt`

Room Database class responsible for creating and managing the database to store tooltip configurations. Provides access to the DAO and handles migrations.

### UI Files

#### `MainActivity.kt`

Main activity that renders custom tooltips based on user input. Handles UI setup, color picking, tooltip rendering, and database insertion of configurations.

#### `PreviewActivity.kt`

Activity displaying a preview of saved tooltips. Retrieves configurations from the database, creates `CustomTooltip` instances, and displays them on corresponding buttons.

#### `CustomTooltip.kt`

Class for rendering and displaying custom tooltips using `PopupWindow`. Provides methods to set text, image, padding, width, and position of tooltips.

#### `SplashActivity.kt`

Activity for a splash screen displayed during app launch, enhancing user experience.

#### `Application.kt`

Application class for global app setup and initialization.


## Tech Stack

- Android
- Kotlin
- AndroidX
- Room Database
- Dagger (Dependency Injection)
- Glide (Image Loading)
- Timber (Logging)
- Material Design Components

## Dependencies

- androidx.core:core-ktx:1.7.0
- androidx.appcompat:appcompat:1.6.1
- com.google.android.material:material:1.9.0
- androidx.constraintlayout:constraintlayout:2.1.4
- com.github.Dhaval2404:ColorPicker:2.3
- androidx.room:room-runtime:2.5.2
- androidx.room:room-ktx:2.5.2
- androidx.room:room-compiler:2.5.2 (KSP)
- com.airbnb.android:lottie:4.0.0
- com.google.dagger:dagger:2.47
- com.jakewharton.timber:timber:5.0.0
- com.github.bumptech.glide:glide:4.12.0

## Getting Started
```shell
1. Clone the repository: `git clone https://github.com/your-usernam/tooltip-app.git`
2. Open the project in Android Studio.
3. Build and run the application on an emulator or a physical device.
```


## Usage

```shell
1. Launch the app.
2. Configure tooltip options using the provided UI elements.
3. Tap the "Render Tooltip" button to render and display the custom tooltip.
4. Preview the tooltip's appearance and position.
5. The app also supports saving tooltip configurations to a database for future use.
```

## Folder Structure

```
app/src/main/java/com/example/tooltip_task

├── data
│ ├── TooltipConfiguration.kt
│ ├── TooltipDao.kt
│ ├── TooltipDatabase.kt
│
├── ui
│ ├── MainActivity.kt
│ ├── PreviewActivity.kt
│ ├── CustomTooltip.kt
│ └── SplashActivity.kt
│
├── utils
│ ├── ... (Utility classes if needed)
│
├── Application.kt
├── MainActivityBinding.kt (Generated view binding)
├── PreviewActivityBinding.kt (Generated view binding)
|
└─ res
├── layout
│ ├── activity_main.xml
│ ├── activity_preview.xml
│ ├── layout_tooltip.xml
│ └── ... (Other XML layout files)
│
├── values
│ ├── strings.xml
│ ├── colors.xml
│ └── ... (Other resource XML files)

```
## Coding Practices followed

- Utilizes Kotlin coroutines for managing asynchronous and non-blocking tasks.
- Follows the single responsibility principle for cleaner and more modular code.
- Implements dependency injection for loosely-coupled components and better testability.
- Adheres to naming conventions and meaningful variable names for code readability.
- Uses Timber for multilevel logging to aid in debugging and monitoring.
- Follows Android's Material Design guidelines for consistent and user-friendly UI.

## Contact

For any inquiries, please contact [rishuss1212@gmail.com](mailto:rishuss1212@gmail.com).
