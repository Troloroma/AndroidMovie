# AndroidMovie

## Description
Android Movie is a mobile application designed to explore popular movies and information about them. Users can view popular, access detailed descriptions and ratings.

## Architecture
The application follows the MVI (Model-View-Intent) architecture and Clean Architecture to ensure a clean and maintainable codebase. Key components include:
- **Feature/presentation:** XML-based layouts and ViewModels
- **Feature/domain:** Interactor and Repository interfaces
- **Domain module:** Implementations of interactors
- **Data module:** Implementations of repositories
- **Network module:** Retrofit for network requests, error handlers
- **Common module:** Entity class for passing data between layers
- **Core/navigation:** Navigation
- **App module:** Build and Dagger2 setup

### Programming languages, used libraries and android specifics
- Kotlin
- XML
- Dagger2
- Retrofit2
- Picasso
- MVIKotlin
- Coroutines, Flow
- ViewBindings
- Pagination3
- Single Activity

## API Documentation
The app uses [The Movie Database (TMDb) API](https://developer.themoviedb.org/docs) to fetch movie data. The API follows RESTful principles and returns JSON responses.

## How to Deploy a New Build
- Used this versions to develop the app: Java 17, Kotlin 1.8.20, Gradle Plugin 8.0.2, Gradle 8.12
1. Run the following Gradle commands to generate .apk:
   ```bash
   ./gradlew assembleDebug
   ```
2. Apk file will be in the directory AndroidMovie/app/build/outputs/apk/debug