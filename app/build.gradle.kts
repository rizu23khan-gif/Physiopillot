import java.io.File
import java.io.InputStream
import java.io.OutputStream
import java.util.zip.ZipFile

plugins {
  alias(libs.plugins.android.application)
  alias(libs.plugins.kotlin.compose)
  alias(libs.plugins.google.devtools.ksp)
  alias(libs.plugins.roborazzi)
  alias(libs.plugins.secrets)
  alias(libs.plugins.kotlin.serialization)
}

android {
  namespace = "com.example"
  compileSdk { version = release(36) { minorApiLevel = 1 } }

  defaultConfig {
    applicationId = "com.aistudio.physiopilot.akmzq"
    minSdk = 24
    targetSdk = 36
    versionCode = 1
    versionName = "1.0"

    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  signingConfigs {
    create("release") {
      val keystorePath = System.getenv("KEYSTORE_PATH") ?: "${rootDir}/my-upload-key.jks"
      storeFile = file(keystorePath)
      storePassword = System.getenv("STORE_PASSWORD")
      keyAlias = "upload"
      keyPassword = System.getenv("KEY_PASSWORD")
    }
    create("debugConfig") {
      storeFile = file("${rootDir}/debug.keystore")
      storePassword = "android"
      keyAlias = "androiddebugkey"
      keyPassword = "android"
    }
  }

  buildTypes {
    release {
      isCrunchPngs = false
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
      signingConfig = signingConfigs.getByName("release")
    }
    debug {
      signingConfig = signingConfigs.getByName("debugConfig")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
  }
  buildFeatures {
    compose = true
    buildConfig = true
  }
  testOptions { unitTests { isIncludeAndroidResources = true } }
}

// Configure the Secrets Gradle Plugin to use .env and .env.example files
// to match the convention used in Web projects.
secrets {
  propertiesFileName = ".env"
  defaultPropertiesFileName = ".env.example"
}

// Some unused dependencies are commented out below instead of being removed.
// This makes it easy to add them back in the future if needed.
dependencies {
  implementation(platform(libs.androidx.compose.bom))
  implementation(platform(libs.firebase.bom))
  // implementation(libs.accompanist.permissions)
  implementation(libs.androidx.activity.compose)
  // implementation(libs.androidx.camera.camera2)
  // implementation(libs.androidx.camera.core)
  // implementation(libs.androidx.camera.lifecycle)
  // implementation(libs.androidx.camera.view)
  implementation(libs.androidx.compose.material.icons.core)
  implementation(libs.androidx.compose.material.icons.extended)
  implementation(libs.androidx.compose.material3)
  implementation(libs.androidx.compose.ui)
  implementation(libs.androidx.compose.ui.graphics)
  implementation(libs.androidx.compose.ui.tooling.preview)
  implementation(libs.androidx.core.ktx)
  implementation(libs.androidx.datastore.preferences)
  implementation(libs.androidx.lifecycle.runtime.compose)
  implementation(libs.androidx.lifecycle.runtime.ktx)
  implementation(libs.androidx.lifecycle.viewmodel.compose)
  implementation(libs.androidx.navigation.compose)
  implementation(libs.androidx.room.ktx)
  implementation(libs.androidx.room.runtime)
  implementation(libs.coil.compose)
  implementation(libs.converter.moshi)
  implementation(libs.firebase.ai)
  implementation(libs.kotlinx.coroutines.android)
  implementation(libs.kotlinx.coroutines.core)
  implementation(libs.logging.interceptor)
  implementation(libs.moshi.kotlin)
  implementation(libs.okhttp)
  implementation(libs.retrofit)
  implementation(libs.kotlinx.serialization.json)
  implementation(libs.retrofit.converter.serialization)
  testImplementation(libs.androidx.compose.ui.test.junit4)
  testImplementation(libs.androidx.core)
  testImplementation(libs.androidx.junit)
  testImplementation(libs.junit)
  testImplementation(libs.kotlinx.coroutines.test)
  testImplementation(libs.robolectric)
  testImplementation(libs.roborazzi)
  testImplementation(libs.roborazzi.compose)
  testImplementation(libs.roborazzi.junit.rule)
  androidTestImplementation(platform(libs.androidx.compose.bom))
  androidTestImplementation(libs.androidx.compose.ui.test.junit4)
  androidTestImplementation(libs.androidx.espresso.core)
  androidTestImplementation(libs.androidx.junit)
  androidTestImplementation(libs.androidx.runner)
  debugImplementation(libs.androidx.compose.ui.test.manifest)
  debugImplementation(libs.androidx.compose.ui.tooling)
  "ksp"(libs.androidx.room.compiler)
  "ksp"(libs.moshi.kotlin.codegen)
}

tasks.register("gitRestore") {
    doLast {
        val process = ProcessBuilder("git", "checkout", "HEAD", "--", "src/main/assets/content/exercise_therapy_1", "src/main/assets/content/exercise_therapy_ii")
            .directory(File("/app"))
            .start()
        val output = process.inputStream.bufferedReader().readText()
        val error = process.errorStream.bufferedReader().readText()
        println("STDOUT:\n$output")
        println("STDERR:\n$error")
    }
}

tasks.register("gitStatus") {
    doLast {
        var dir: File? = rootDir.absoluteFile
        var foundGit: File? = null
        while (dir != null) {
            val gitDir = File(dir, ".git")
            if (gitDir.exists()) {
                foundGit = gitDir
                break
            }
            dir = dir.parentFile
        }
        if (foundGit != null) {
            println("Found .git at: ${foundGit.absolutePath}")
            val process = ProcessBuilder("git", "status")
                .directory(foundGit.parentFile)
                .start()
            val output = process.inputStream.bufferedReader().readText()
            val error = process.errorStream.bufferedReader().readText()
            println("STDOUT:\n$output")
            println("STDERR:\n$error")
        } else {
            println("Could not find any .git folder in hierarchy up from ${rootDir.absolutePath}")
        }
    }
}

tasks.register("printPaths") {
    doLast {
        println("rootDir: ${rootDir.absolutePath}")
        println("projectDir: ${projectDir.absolutePath}")
        println("File(.).absolutePath: ${File(".").absolutePath}")
        
        fun listFiles(dir: File, depth: Int = 0) {
            val indent = "  ".repeat(depth)
            dir.listFiles()?.forEach { file ->
                println("$indent${file.name} (${if (file.isDirectory) "dir" else "file"})")
                if (file.isDirectory && depth < 2) {
                    listFiles(file, depth + 1)
                }
            }
        }
        
        println("Listing rootDir:")
        listFiles(rootDir)
    }
}

tasks.register("restoreFromApk") {
    doLast {
        val apkFile = File(rootDir, ".build-outputs/app-debug.apk")
        if (!apkFile.exists()) {
            println("APK does not exist at: ${apkFile.absolutePath}")
            return@doLast
        }
        println("Extracting assets from APK: ${apkFile.absolutePath}")
        val zip = ZipFile(apkFile)
        val entries = zip.entries()
        var count = 0
        while (entries.hasMoreElements()) {
            val entry = entries.nextElement()
            val name = entry.name
            if (name.startsWith("assets/content/exercise_therapy")) {
                val cleanName = name.substringAfter("assets/content/")
                val targetFile = File(projectDir, "src/main/assets/content/$cleanName")
                targetFile.parentFile.mkdirs()
                zip.getInputStream(entry).use { input ->
                    targetFile.outputStream().use { output ->
                        input.copyTo(output)
                    }
                }
                println("Extracted: $cleanName")
                count++
            }
        }
        zip.close()
        println("Successfully extracted $count files from APK.")
    }
}

tasks.register("gitForensics") {
    doLast {
        println("projectDir: ${projectDir.absolutePath}")
        println("rootDir: ${rootDir.absolutePath}")
    }
}

tasks.register("runCompiler") {
    doLast {
        val cmd = project.findProperty("cmd") as? String ?: "all"
        
        // Step 1: Force delete container-root conflict folders
        println("Cleaning container root conflict folders...")
        for (dir in listOf("master_knowledge", "validation")) {
            val p = ProcessBuilder("rm", "-rf", "/$dir").start()
            p.waitFor()
        }
        
        // Step 2: Ensure physical directories exist in workspace
        println("Creating physical directories in workspace...")
        for (dir in listOf("schemas", "citation_registry", "master_knowledge", "validation", "generated_json")) {
            File(rootDir, dir).mkdirs()
        }
        
        // Step 3: Create symlinks
        println("Creating symbolic links for absolute paths...")
        for (dir in listOf("schemas", "citation_registry", "master_knowledge", "validation", "generated_json")) {
            val target = "/$dir"
            val link = File(rootDir, dir).absolutePath
            val p = ProcessBuilder("ln", "-sfT", link, target).start()
            val err = p.errorStream.bufferedReader().readText()
            p.waitFor()
            println("Link $target -> $link: exit ${p.exitValue()} ${if (err.isNotEmpty()) "Err: $err" else ""}")
        }
        
        // Step 4: Install requirements
        println("Installing requirements via python3 -m pip...")
        val pipProcess = ProcessBuilder("python3", "-m", "pip", "install", "-r", "requirements.txt")
            .directory(rootDir)
            .start()
        val pipOutput = pipProcess.inputStream.bufferedReader().readText()
        val pipError = pipProcess.errorStream.bufferedReader().readText()
        println("PIP STDOUT:\n$pipOutput")
        println("PIP STDERR:\n$pipError")
        if (pipProcess.waitFor() != 0) {
            println("Warning: Pip install returned exit code ${pipProcess.exitValue()}")
        }
        
        // Step 5: Run build.py with given command
        println("Running build.py $cmd...")
        val process = ProcessBuilder("python3", "build.py", cmd)
            .directory(rootDir)
            .start()
        val output = process.inputStream.bufferedReader().readText()
        val error = process.errorStream.bufferedReader().readText()
        println("STDOUT:\n$output")
        println("STDERR:\n$error")
        if (process.waitFor() != 0) {
            throw GradleException("Python build.py $cmd failed with exit code ${process.exitValue()}")
        }
    }
}





