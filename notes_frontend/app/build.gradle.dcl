androidApplication {
    namespace = "org.example.app"

    dependencies {
        implementation("org.apache.commons:commons-text:1.11.0")
        implementation(project(":utilities"))
        // Compose
        implementation("androidx.activity:activity-compose:1.8.2")
        implementation("androidx.compose.ui:ui:1.6.4")
        implementation("androidx.compose.material3:material3:1.2.0")
        implementation("androidx.navigation:navigation-compose:2.7.7")
        // Room
        implementation("androidx.room:room-runtime:2.6.1")
        implementation("androidx.room:room-ktx:2.6.1")
        // Lifecycle, coroutines
        implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.7.0")
        implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")
        implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
        // Compose icons
        implementation("androidx.compose.material:material-icons-extended:1.6.4")
    }
}
