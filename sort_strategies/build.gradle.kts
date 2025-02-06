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
}

dependencies {
    /* spring */
    implementation("org.springframework.boot:spring-boot-starter")

    /* aop */
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("org.aspectj:aspectjweaver")

    /* test */
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    /* loombok */
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}