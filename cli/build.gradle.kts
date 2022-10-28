plugins {
    id("java")
    id("application")

}

group = "io.codiga"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
}

application {
    mainClass.set("io.codiga.cli.Main")

}

tasks.withType<Jar> {
    manifest {
        attributes["Main-Class"] = "io.codiga.cli.Main"
    }
}

java.sourceCompatibility = org.gradle.api.JavaVersion.VERSION_17
java.targetCompatibility = org.gradle.api.JavaVersion.VERSION_17


dependencies {
    implementation(project(":core"))
    testImplementation(project(":core"))
    implementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    implementation("com.google.inject:guice:5.1.0")
    implementation("commons-cli:commons-cli:1.5.0")
    implementation("com.rollbar:rollbar-java:1.8.1")
    implementation("ch.qos.logback:logback-classic:1.3.2")

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