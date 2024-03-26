import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.9.23"

	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
	kotlin("plugin.noarg") version "1.9.23"
	kotlin("kapt") version "1.9.23"
}

val querydslVersion = "5.0.0"

allOpen {
	annotation("com.my.Annotation")
	// annotations("com.another.Annotation", "com.third.Annotation")
}

noArg {
	annotation("com.my.Annotation")
}

group = "com.showdown"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
//	kapt("$group:$:$version")
//	kapt("$group:artifactId:$version")

	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

//	implementation("com.querydsl:querydsl-jpa:$querydslVersion:jakarta")
//	kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa:jakarta")
	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("org.springframework.boot:spring-boot-configuration-processor")



	// log
	implementation("ch.qos.logback:logback-classic:1.5.3")
	implementation("ch.qos.logback:logback-core:1.5.3")

	// db
	runtimeOnly("com.mysql:mysql-connector-j")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}