plugins {
    id("org.springframework.boot") version "3.0.0-M5"
    id("io.spring.dependency-management") version "1.0.14.RELEASE"
	java
}

group = "com.github.ixre"
version = "0.0.1-SNAPSHOT"

repositories {
	mavenCentral()
	maven( "https://repo.spring.io/milestone")
}

dependencies {
	implementation("javax.inject:javax.inject:1")
	implementation("com.sun.xml.bind:jaxb-core:2.3.0.1")
	implementation("com.sun.xml.bind:jaxb-impl:2.3.1")
	implementation("com.google.code.gson:gson:2.9.1")
	implementation("javax.xml.bind:jaxb-api:2.3.1")
	//implementation "javax.persistence:javax.persistence-api:2.2"
	implementation("mysql:mysql-connector-java:8.0.30")
	implementation("org.springframework.boot:spring-boot-starter")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation(files("src/lib/fze-commons-0.3.9.jar"))
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}
configure<JavaPluginExtension> {
	sourceCompatibility = JavaVersion.VERSION_17
}
tasks.withType<Test> {
	useJUnitPlatform()
}