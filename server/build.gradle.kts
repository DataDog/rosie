plugins {
    id("java")
    id("application")
    id("org.springframework.boot") version "2.7.3"
}

group = "io.codiga"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()

    // For tree-sitter
    maven {
        url = uri("https://jitpack.io")
    }
}


java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17

val ddTracerAgent by configurations.creating

configurations {
    ddTracerAgent
}

dependencies {
    ddTracerAgent("com.datadoghq:dd-java-agent:1.8.3")
}


dependencies {
    implementation(project(":core"))
    testImplementation(project(":core"))
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.rollbar:rollbar-java:1.8.1")

    implementation("org.springframework.boot:spring-boot-starter-web:2.7.3")
    implementation("org.springframework.guice:spring-guice:2.0.1")

    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.3")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
}

val ddTraceAgentAsPath: String = ddTracerAgent.asPath

if (project.hasProperty("dd-civisibility")) {
    dependencies {
        implementation("com.datadoghq:dd-javac-plugin-client:0.1.1")
        annotationProcessor("com.datadoghq:dd-javac-plugin:0.1.1")
        testAnnotationProcessor("com.datadoghq:dd-javac-plugin:0.1.1")
    }
    tasks.withType<JavaCompile> {
        options.compilerArgs.add("-Xplugin:DatadogCompilerPlugin")
    }

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
    testLogging {
        showStandardStreams = true
        exceptionFormat = org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL
        showExceptions = true
        showCauses = true
        showStackTraces = true
    }

    jvmArgs = listOf(
        "-javaagent:$ddTraceAgentAsPath",
        "-Ddd.service=rosie",
        "-Ddd.civisibility.enabled=true"
    )
}