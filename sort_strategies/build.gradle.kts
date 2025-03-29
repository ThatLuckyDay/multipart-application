version = "1.0.0"
group = "com.pet"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

plugins {
    java

    /* spring */
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.7"

    /* aspectj */
    id("io.freefair.aspectj.post-compile-weaving") version "8.12.1"
}

dependencies {
    /* spring */
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    /* aspectj */
    implementation("org.aspectj:aspectjweaver:1.9.22.1")
    implementation("org.aspectj:aspectjrt:1.9.22.1")
    aspect("org.aspectj:aspectjtools:1.9.22.1")

    /* test */
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")
    testImplementation("org.junit.jupiter:junit-jupiter-params:5.10.0")
    testImplementation("org.mockito:mockito-core:5.10.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.10.0")

    /* loombok */
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}