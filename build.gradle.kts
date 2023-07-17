plugins {
	java
	id("org.springframework.boot") version "3.1.1"
	id("io.spring.dependency-management") version "1.1.0"
}

group = "ru.gt2.learn.sa"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_20
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}

// Так вот эта штука позволяет больше информации при тестировании выдавать
/*
// This section causes useful test output to go to the terminal.
test {
	testLogging {
		events "passed", "skipped", "failed" //, "standardOut", "standardError"

		showExceptions true
		exceptionFormat "full"
		showCauses true
		showStackTraces true

		// Change to `true` for more verbose test output
		showStandardStreams = false
	}
}
*/
