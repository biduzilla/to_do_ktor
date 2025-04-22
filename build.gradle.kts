
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.ktor)
    alias(libs.plugins.kotlin.plugin.serialization)
}

group = "com.ricky"
version = "0.0.1"

application {
    mainClass = "io.ktor.server.netty.EngineMain"

    val isDevelopment: Boolean = project.ext.has("development")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=$isDevelopment")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.server.content.negotiation)
    implementation(libs.postgresql)
    implementation(libs.h2)
    implementation ("org.jetbrains.exposed:exposed-core:0.61.0")
    implementation ("org.jetbrains.exposed:exposed-crypt:0.61.0")
    implementation ("org.jetbrains.exposed:exposed-dao:0.61.0")
    implementation ("org.jetbrains.exposed:exposed-jdbc:0.61.0")

    //Koin
//    implementation("io.insert-koin:koin-ktor:4.0.3")
//    implementation("io.insert-koin:koin-core:4.0.3")
//    implementation("io.insert-koin:koin-logger-slf4j:4.0.3")
//    implementation("io.insert-koin:koin-bom:4.0.3")
    implementation("io.insert-koin:koin-ktor:4.0.3")
    implementation("io.insert-koin:koin-logger-slf4j:4.0.3")

    implementation("io.ktor:ktor-server-status-pages:3.1.2")
    implementation("io.ktor:ktor-server-request-validation:3.1.2")

    implementation(libs.exposed.jdbc)
    implementation(libs.ktor.server.host.common)
    implementation(libs.ktor.server.netty)
    implementation(libs.logback.classic)
    implementation(libs.ktor.server.config.yaml)
    testImplementation(libs.ktor.server.test.host)
    testImplementation(libs.kotlin.test.junit)
}
