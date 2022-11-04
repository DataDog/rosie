plugins {
    id("java")
    // Disabled for now, see https://github.com/graalvm/graal-js-jdk11-gradle-demo
    // id("org.graalvm.plugin.compiler") version "0.1.0-alpha2"
}

group = "io.codiga"
version = "1.0-SNAPSHOT"

//graal {
//    version = "22.2.0"
//}

repositories {
    mavenCentral()
    mavenLocal()
}


java.sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
java.targetCompatibility = org.gradle.api.JavaVersion.VERSION_17


dependencies {

    implementation("com.google.inject:guice:5.1.0")
    implementation("com.datadoghq:java-dogstatsd-client:4.1.0")
    implementation("org.antlr:antlr4-runtime:4.11.1")
    implementation("com.rollbar:rollbar-java:1.8.1")

    implementation("org.graalvm.js:js:22.2.0")
    implementation("org.graalvm.js:js-scriptengine:22.2.0")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
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
}