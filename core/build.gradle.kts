plugins {
    id("java")
    // Disabled for now, see https://github.com/graalvm/graal-js-jdk11-gradle-demo
    // id("org.graalvm.plugin.compiler") version "0.1.0-alpha2"
    id("io.freefair.lombok") version "6.5.1"
}

group = "io.codiga"
version = "1.0-SNAPSHOT"

//graal {
//    version = "22.2.0"
//}

val ddTracerAgent by configurations.creating

configurations {
    ddTracerAgent
}

dependencies {
    ddTracerAgent("com.datadoghq:dd-java-agent:1.8.3")
}

repositories {
    mavenCentral()
    mavenLocal()

    // tree-sitter
    maven {
        url = uri("https://jitpack.io")
    }
}

java.sourceCompatibility = JavaVersion.VERSION_17
java.targetCompatibility = JavaVersion.VERSION_17


dependencies {

    implementation("com.google.inject:guice:5.1.0")
    implementation("com.datadoghq:java-dogstatsd-client:4.1.0")
    implementation("com.datadoghq:dd-trace-api:1.3.0")
    implementation("org.antlr:antlr4-runtime:4.11.1")
    implementation("com.rollbar:rollbar-java:1.9.0")
    implementation("com.github.serenadeai:java-tree-sitter:1.1.2")

    implementation("org.graalvm.js:js:22.2.0")
    implementation("org.graalvm.js:js-scriptengine:22.2.0")

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