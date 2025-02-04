version = "1.0.0"
group = "com.pet"

repositories {
    mavenCentral()
}

plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.7"
}

dependencies {
    /* Spring */
    implementation("org.springframework.boot:spring-boot-starter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    /* Loombok */
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("-Ajava.version=11")
}