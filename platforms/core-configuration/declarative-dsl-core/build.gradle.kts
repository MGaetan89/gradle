import org.jetbrains.kotlin.gradle.dsl.KotlinVersion
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("gradlebuild.distribution.implementation-kotlin")
    id("gradlebuild.publish-public-libraries")
    id("gradlebuild.kotlin-dsl-plugin-bundle-integ-tests")

    embeddedKotlin("plugin.serialization")
}

description = "Common shared classes used by the Declarative DSL"

tasks.withType<KotlinCompile>().configureEach {
    compilerOptions {
        apiVersion.set(KotlinVersion.KOTLIN_1_9)
        languageVersion.set(KotlinVersion.KOTLIN_1_9)
    }
}

dependencies {
    api(projects.declarativeDslToolingModels)

    api(oldLibs.kotlinStdlib)
    api(oldLibs.kotlinCompilerEmbeddable)

    implementation(projects.declarativeDslApi)
    implementation(oldLibs.kotlinReflect)
    implementation(oldLibs.kotlinxSerializationCore)
    implementation(oldLibs.kotlinxSerializationJson)

    testImplementation(projects.coreApi)
    testImplementation("org.jetbrains:annotations:24.0.1")

    testFixturesImplementation(oldLibs.kotlinReflect)

    integTestImplementation(testFixtures(projects.kotlinDsl))

    integTestDistributionRuntimeOnly(projects.distributionsFull)
}
tasks.isolatedProjectsIntegTest {
    enabled = false
}
