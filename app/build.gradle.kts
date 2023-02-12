import java.nio.file.Files

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.10"
    application
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17))
    }
}

repositories {
    mavenCentral()
}

application {
    mainClass.set("me.alllex.ocwe.AppKt")
}

fun File.ownSubdirs() = Files.walk(this.toPath(), 1).filter { it.toFile().isDirectory }.toList()

val extensionSources = project.layout.projectDirectory.dir("../enterprise-extensions").asFile
if (extensionSources.isDirectory) {
    sourceSets {
        main {
            for (sourceRoot in extensionSources.ownSubdirs()) {
                when (sourceRoot.fileName.toString()) {
                    "java" -> java.srcDir(sourceRoot)
                    "resources" -> resources.srcDir(sourceRoot)
                }
            }
        }
    }
}


