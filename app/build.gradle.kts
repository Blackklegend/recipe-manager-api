plugins {
	// Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
	alias(libs.plugins.kotlin.jvm)
	alias(libs.plugins.kotlin.spring)

	id("org.springframework.boot") version "3.2.2"
	id("io.spring.dependency-management") version "1.1.4"

	// Apply the application plugin to add support for building a CLI application in Java.
	application
}

repositories {
	// Use Maven Central for resolving dependencies.
	mavenCentral()
}

dependencies {
	// This dependency is used by the application.
	implementation(libs.guava)

	// Spring Boot dependencies
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

	// Kotlin dependencies
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

	// Dev tools
	developmentOnly("org.springframework.boot:spring-boot-devtools")

	// Test dependencies
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

testing {
	suites {
		// Configure the built-in test suite
		val test by
						getting(JvmTestSuite::class) {
							// Use Kotlin Test test framework
							useKotlinTest("2.0.21")
						}
	}
}

// Apply a specific Java toolchain to ease working on different environments.
java {
		toolchain {
				languageVersion = JavaLanguageVersion.of(21)
		}
}

application {
		// Define the main class for the application.
		mainClass = "org.recipes.AppKt"
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	archiveFileName.set("recipe-manager-api.jar")
}

tasks.named<Jar>("jar") {
	archiveFileName.set("recipe-manager-api-plain.jar")
}

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}