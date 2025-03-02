plugins {
    id("java")
}

group = "org.practice"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    /* hibernate */
    implementation("org.hibernate:hibernate-core:6.6.9.Final")

    /* postgresql */
    implementation("org.postgresql:postgresql:42.7.5")

    /* jpa */
    implementation("jakarta.persistence:jakarta.persistence-api:3.2.0")

    /* logging */
    implementation("org.slf4j:slf4j-api:2.0.16")
    runtimeOnly("org.slf4j:slf4j-simple:2.0.16")

    /* loombok */
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

    /* test */
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}