import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

repositories {
    mavenCentral()
}

dependencies {
}

kotlin {
    macosArm64()
    linuxArm64()
    linuxX64()
    mingwX64()

    targets.withType<KotlinNativeTarget>().configureEach {
        binaries {
            executable {
                entryPoint = "main"
            }
        }
    }

    sourceSets {
        nativeMain.dependencies {

        }
    }
}

tasks.withType<Wrapper> {
    gradleVersion = "9.5.0"
    distributionType = Wrapper.DistributionType.BIN
}