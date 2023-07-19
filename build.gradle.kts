import org.gradle.api.tasks.testing.logging.TestLogEvent
import org.gradle.api.tasks.testing.logging.TestExceptionFormat

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

	implementation("org.springframework.data:spring-data-jdbc")
	testImplementation("com.h2database:h2")
}

// Так вот эта штука позволяет больше информации при тестировании выдавать
tasks.withType<Test> {
	useJUnitPlatform()
	testLogging {
		events(TestLogEvent.PASSED, TestLogEvent.SKIPPED, TestLogEvent.FAILED/*, TestLogEvent.STANDARD_OUT, TestLogEvent.STANDARD_ERROR*/)

		showExceptions = true
		exceptionFormat = TestExceptionFormat.FULL
		showCauses = true
		showStackTraces = true

		showStandardStreams = false
	}
}
