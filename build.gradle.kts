plugins {
    java
    application
}

repositories {
    mavenCentral()
}

val javafxVersion = "11.0.2"

fun getOs(): String {
    val os = System.getProperty("os.name").lowercase()
    return when {
        os.contains("win") -> "win"
        os.contains("mac") -> "mac"
        os.contains("linux") -> "linux"
        else -> throw GradleException("Unknown OS: $os")
    }
}

dependencies {
    implementation("org.openjfx:javafx-base:$javafxVersion:${getOs()}")
    implementation("org.openjfx:javafx-controls:$javafxVersion:${getOs()}")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:${getOs()}")
    implementation("org.openjfx:javafx-graphics:$javafxVersion:${getOs()}")
    implementation("org.openjfx:javafx-media:$javafxVersion:${getOs()}")
    implementation("org.openjfx:javafx-web:$javafxVersion:${getOs()}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.10.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.10.2")
}

application {
    mainModule.set("com.example.demo1")
    mainClass.set("com.example.demo1.controls.CalcsApp")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(11))
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.test {
    useJUnitPlatform()
}