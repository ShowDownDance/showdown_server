import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.2.3"
	id("io.spring.dependency-management") version "1.1.4"
	id("org.jetbrains.kotlin.plugin.allopen") version "1.9.23"
	id("org.asciidoctor.jvm.convert") version "3.3.2"

	kotlin("jvm") version "1.9.22"
	kotlin("plugin.spring") version "1.9.22"
	kotlin("plugin.jpa") version "1.9.22"
	kotlin("plugin.noarg") version "1.9.23"
	kotlin("kapt") version "1.9.23"
}

//val querydslVersion = "5.0.0"
val kotestVersion = "5.7.2"
val restDocVersion = "3.0.1"

// extend Asciidoctor
val asciidoctorExt = configurations.create("asciidoctorExt") {
	extendsFrom(configurations["testImplementation"])
}

// 생성된 generated snippets 저장 위치 설정
val snippetsDir by extra { file("build/generated-snippets") }

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
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")

	implementation("org.springframework.boot:spring-boot-starter-validation")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")

	implementation("com.querydsl:querydsl-jpa:5.0.0:jakarta")
	kapt("com.querydsl:querydsl-apt:5.0.0:jakarta")
	kapt("org.springframework.boot:spring-boot-configuration-processor")



	// log
	implementation("ch.qos.logback:logback-classic:1.5.3")
	implementation("ch.qos.logback:logback-core:1.5.3")

	// db
	runtimeOnly("com.mysql:mysql-connector-j")
	runtimeOnly("com.h2database:h2")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.mockito")
		exclude(group = "org.assertj")
		exclude(group = "org.hamcrest")
	}

	asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor:$restDocVersion")
	testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc:$restDocVersion")
	testImplementation("io.kotest:kotest-runner-junit5:$kotestVersion")
	testImplementation("io.kotest:kotest-assertions-core:$kotestVersion")
	testImplementation("io.kotest:kotest-property:$kotestVersion")
	implementation("io.kotest.extensions:kotest-extensions-spring:1.1.3")

	testImplementation("io.mockk:mockk:1.13.7")
	testImplementation("com.ninja-squad:springmockk:4.0.2")

	testImplementation("io.github.serpro69:kotlin-faker:1.15.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}



tasks.withType<Test> {
	useJUnitPlatform()
	jvmArgs = listOf("-Xshare:off") // JVM 아규먼트 설정
}


// test task output 결과를 snippetsDir에 작성하도록
tasks.test {
	outputs.dir(snippetsDir)
}

tasks.asciidoctor {
	inputs.dir(snippetsDir)
	configurations("asciidoctorExt")
	dependsOn(tasks.test)
}

tasks.asciidoctor {
	doFirst {
		println("=====start asciidoctor")
		delete("src/main/resources/static/docs")
	}

	doLast {
		println("=====finish asciidoctor")
	}
}

tasks.bootJar {
	dependsOn(tasks.asciidoctor)
	finalizedBy("copyDocument")
}

tasks.register<Copy>("copyDocument") {
	dependsOn(tasks.asciidoctor)

	destinationDir = file(".")
	from(tasks.asciidoctor.get().outputDir) {
		into("src/main/resources/static/docs")
	}
}