# declarative-samples-android-app
A sample Android application written in the Declarative Gradle DSL, using the prototype Declarative Gradle `androidApplication` Software Type defined in the `org.gradle.experimental.android-ecosystem` ecosystem plugin.

## Building and Running

This sample shows the definition of a multiproject Android application implemented using Kotlin 2.0.21 source code.
The project is the result of reproducing the project produced by the `gradle init` command in Gradle 8.9 as an Android project.

To build the project without running, use:

```shell
  ./gradlew build
```

To run the application (on a device or emulator), use:

```shell
  ./gradlew :app:installDebug
```

Then launch the "Notes" app from your device's app drawer.

---

## Troubleshooting build or startup hangs

If the build or startup hangs for a long time, check:

- Ensure you are using JDK 17 (see JAVA_HOME); run `java -version` and verify.
- Emulator or Android device must be running and unlocked before `installDebug`.
- Gradle may need to download dependencies; the first build can be slow (check internet connection).
- For memory issues (e.g., OutOfMemoryError), increase the JVM args in gradle.properties (`org.gradle.jvmargs=-Xmx2048m ...`).
- **IMPORTANT:** If you get Compose/Kotlin compiler or "couldn't find inline method ... ComposablesKt.remember" errors, ensure:
    - Kotlin version is exactly 1.9.23 (set in `gradle.properties` as `kotlin.version=1.9.23`)
    - Compose Compiler version is 1.5.11 (`kotlin.compose.compiler.version=1.5.11`)
    - All Compose libraries are 1.5.x or 1.6.x line.
- Run `./gradlew --stop && ./gradlew clean` to clear stuck daemons and clean up state if the build is repeatedly stuck.
- If you are still having problems, delete the `.gradle` and `build` folders in the project and try building again.
- For long dependency resolution, check for connectivity to mavenCentral, google(), and Gradle plugin portal.
