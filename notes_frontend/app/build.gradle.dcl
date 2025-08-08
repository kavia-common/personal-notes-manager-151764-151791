androidApplication {
    namespace = "org.example.app"

    dependencies {
        implementation("org.apache.commons:commons-text:1.11.0")
        implementation(project(":utilities"))
        // Compose: explicit versions instead of BOM due to dependency resolution failures in this DSL.
        // Remove/comment the BOM as this setup doesn't support it.
        // implementation(platform("androidx.compose:compose-bom:2024.04.01"))
        implementation("androidx.activity:activity-compose:1.8.2")
        implementation("androidx.compose.ui:ui:1.6.4")
        implementation("androidx.compose.material:material:1.6.4")
        implementation("androidx.compose.material3:material3:1.2.0-beta01") // must match Compose 1.6.4; 1.2.1 is not compatible!
        implementation("androidx.compose.material:material-icons-extended:1.6.4")
        implementation("androidx.compose.foundation:foundation:1.6.4")
        implementation("androidx.compose.foundation:foundation-layout:1.6.4")
        implementation("androidx.navigation:navigation-compose:2.7.7")
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
