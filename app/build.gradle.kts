plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
}

android {
    namespace = "cybergammagroup.ndsmd"
    compileSdk = 34
    flavorDimensions += "version"

    defaultConfig {
        applicationId = "cybergammagroup.ndsmd"
        minSdk = 26
        targetSdk = 34
        versionCode = 11
        versionName = "1.1"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    productFlavors {
        create("lite") {
            dimension = "version"
            versionNameSuffix = "-lite"
            buildConfigField("Boolean", "FDROID_VARIANT", "true")
        }

        create("play-store") {
            dimension = "version"
            versionNameSuffix = "-full"
            buildConfigField("Boolean", "FDROID_VARIANT", "false")
        }
    }


    splits {
        abi {
            isEnable = false

        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )

            // required because of hardcoded f-droid values
            applicationVariants.all {
                val variant = this
                if (variant.flavorName == "lite") {
                    variant.outputs
                        .map { it as com.android.build.gradle.internal.api.BaseVariantOutputImpl }
                        .forEach { output ->
                            val outputFileName = "NDSMD-lite.apk"
                            println("OutputFileName: $outputFileName")
                            output.outputFileName = outputFileName
                        }
                }
            }
        }
        create("customDebugType"){
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }
}



dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // Shizuku dependecies
    implementation (libs.api)
    implementation (libs.provider)

    implementation(libs.gson)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.mpandroidchart)
    implementation(libs.timerangepicker)
    implementation(libs.fadingedgelayout)

}