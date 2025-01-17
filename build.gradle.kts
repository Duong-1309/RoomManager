plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jfree:jfreechart:1.5.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

// Thêm cấu hình cho Java application
application {
    mainClass.set("com.hotelmanager.Main")  // Điều chỉnh theo package của bạn
}

// Cấu hình Java version
java {
    sourceCompatibility = JavaVersion.VERSION_17  // Hoặc version Java bạn đang dùng
    targetCompatibility = JavaVersion.VERSION_17
}