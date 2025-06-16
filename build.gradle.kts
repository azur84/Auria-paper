import io.papermc.paperweight.userdev.ReobfArtifactConfiguration

plugins {
    kotlin("jvm") version "2.1.20-Beta1"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.papermc.paperweight.userdev") version "2.0.0-beta.17"
    id("xyz.jpenilla.run-paper") version "2.3.1"
}

group = "fr.azur"
version = "1.0-SNAPSHOT"
val minecraftVersion = "1.21.5"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://repo.papermc.io/repository/maven-public/") {
        name = "papermc-repo"
    }
    maven("https://oss.sonatype.org/content/groups/public/") {
        name = "sonatype-repo"
    }
    maven("https://maven.citizensnpcs.co/repo") {
        name = "citizens-repo"
    }
    maven("https://jitpack.io/") {
        name = "jitpack-repo"
    }
    maven("https://raw.githubusercontent.com/tom5454/maven/main") {
        name = "tom5454 maven"
    }
}

dependencies {
    paperweight.paperDevBundle("${minecraftVersion}-R0.1-SNAPSHOT")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

    compileOnly("net.citizensnpcs:citizens-main:2.0.38-SNAPSHOT") {
        exclude("*","*")
    }
    compileOnly("com.tom5454.cpm:CustomPlayerModels-API:0.6.22")
}

val targetJavaVersion = 21
kotlin {
    jvmToolchain(targetJavaVersion)
}
java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

tasks.build {
    dependsOn("shadowJar")
}

tasks.processResources {
    val props = mapOf("version" to version)
    inputs.properties(props)
    filteringCharset = "UTF-8"
    filesMatching("paper-plugin.yml") {
        expand(props)
    }
}

tasks.runServer {
    minecraftVersion(minecraftVersion)
}

paperweight {
    injectPaperRepository = true
    reobfArtifactConfiguration = ReobfArtifactConfiguration.MOJANG_PRODUCTION
}