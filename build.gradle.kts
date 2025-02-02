plugins {
    id("java")
    id("application")
}
application {
    mainClass = "clase.Ejemplos"
}
group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("mysql:mysql-connector-java:8.0.28")
}

tasks.test {
    useJUnitPlatform()
}
tasks.named<JavaExec>("run"){
    standardInput = System.`in`
}