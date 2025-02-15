plugins {
    id("java")
}

group = "com.pet"
version = "1.0.0"
java.sourceCompatibility = JavaVersion.VERSION_21

repositories {
    mavenCentral()
}

dependencies {
    /*postgresql*/
    implementation("org.postgresql:postgresql:42.7.5")

    implementation("org.slf4j:slf4j-api:2.0.16")

    /* log4j */
    implementation("log4j:log4j:1.2.17")

    /* slf4j */
    testImplementation("org.slf4j:slf4j-log4j12:2.0.16")

    implementation("org.apache.commons:commons-dbcp2:2.13.0")

    /* test */
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.0")

    /* lombok */
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
    testCompileOnly("org.projectlombok:lombok:1.18.36")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.36")

}

tasks.test {
    useJUnitPlatform()
}