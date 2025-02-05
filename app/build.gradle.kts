plugins {
	// Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
	alias(libs.plugins.kotlin.jvm)

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
java { toolchain { languageVersion = JavaLanguageVersion.of(21) } }

application {
	// Define the main class for the application.
	mainClass = "org.example.AppKt"
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

kotlin {
	compilerOptions {
		freeCompilerArgs.addAll("-Xjsr305=strict")
	}
}