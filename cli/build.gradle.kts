plugins {
    id("java")
    id("application")
    // Disabled for now, see https://github.com/graalvm/graal-js-jdk11-gradle-demo
    // id("org.graalvm.plugin.compiler") version "0.1.0-alpha2"
    id("io.freefair.lombok") version "6.5.1"
}

group = "io.codiga"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
    mavenLocal()
    maven("https://jitpack.io")
}


application {
    mainClass.set("io.codiga.cli.Main")

}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.codiga.cli.Main"
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
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-core:2.14.2")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.14.2")
    implementation("com.google.inject:guice:5.1.0")
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("ch.qos.logback:logback-classic:1.4.6")

    implementation("org.yaml:snakeyaml:2.0")

    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.slf4j:slf4j-simple:2.0.7")

    testImplementation("net.jimblackler.jsonschemafriend:core:0.11.4")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.9.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.2")
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
