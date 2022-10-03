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
    implementation("com.google.inject:guice:5.1.0")
    implementation("com.datadoghq:java-dogstatsd-client:4.0.0")
    implementation("org.antlr:antlr4-runtime:4.10.1")
    implementation("org.graalvm.js:js:22.2.0")
    implementation("com.rollbar:rollbar-java:1.8.1")

    implementation("org.springframework.boot:spring-boot-starter-web:2.7.3")
    implementation("org.springframework.guice:spring-guice:2.0.1")
    implementation("org.graalvm.js:js-scriptengine:22.2.0")


    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.3")
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