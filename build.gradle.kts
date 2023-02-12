import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.21"
    `maven-publish`
    application
}

group = "br.com.guiodes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val sourcesJar by tasks.registering(Jar::class) {
    archiveClassifier.set("sources")
    from(sourceSets.getByName("main").allSource)
}

publishing {
    repositories {
        maven("https://oss.sonatype.org/service/local/staging/deploy/maven2") {
            credentials {
                username = ""
                password = ""
            }
        }
    }

    publications {
        register("mavenJava", MavenPublication::class) {
            from(components["java"])
            artifact(sourcesJar.get())

            pom {
                name.set("SimpleRegex")
                description.set("Simple RegEx Creator")
                url.set("https://www.github.com/GuiOdes/simple-regex")


                scm {
                    connection.set("scm:git:http://www.github.com/GuiOdes/simple-regex/")
                    developerConnection.set("scm:git:http://github.com/GuiOdes/")
                    url.set("https://www.github.com/GuiOdes/simple-regex")
                }

                licenses {
                    license {
                        name.set("The Apache 2.0 License")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }

                developers {
                    developer {
                        id.set("GuiOdes")
                        name.set("Guilherme Monteiro Marques Menezes")
                        email.set("guilherme.montnezes@gmail.com")
                    }
                }
            }
        }
    }
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}