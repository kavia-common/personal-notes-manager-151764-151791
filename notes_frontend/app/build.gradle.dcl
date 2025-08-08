androidApplication {
    namespace = "org.example.app"

    dependencies {
        implementation("org.apache.commons:commons-text:1.11.0")
        implementation(project(":utilities"))
        // Compose - ALL VERSIONS ALIGNED! See gradle.properties for Compose Compiler version.
        implementation(platform("androidx.compose:compose-bom:2024.04.01"))
        implementation("androidx.activity:activity-compose:1.8.2")
        implementation("androidx.compose.ui:ui")
        implementation("androidx.compose.material:material")
        implementation("androidx.compose.material3:material3")
        implementation("androidx.navigation:navigation-compose:2.7.7")
        implementation("androidx.compose.material:material-icons-extended")
        implementation("androidx.compose.foundation:foundation")
        implementation("androidx.compose.foundation:foundation-layout")
        // Room
        implementation("androidx.room:room-runtime:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")
        // Lifecycle, coroutines
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        // For XML style Theme.MaterialComponents.DayNight bridge theme
        implementation("com.google.android.material:material:1.11.0")
    }
}
