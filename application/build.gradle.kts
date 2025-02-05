version = "1.0.0"
group = "com.pet"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

plugins {
    java
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.7"
}

dependencies {
    /* project */
    implementation(project(":sort_strategies"))

    /* spring */
    implementation("org.springframework.boot:spring-boot-starter")

    /* test */
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
}

tasks.test {
    useJUnitPlatform()
}