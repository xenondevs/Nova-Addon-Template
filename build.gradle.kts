import org.gradle.configurationcache.extensions.capitalized

group = "com.example" // TODO: Change this to your group
version = "1.0-SNAPSHOT" // TODO: Change this to your addon version

val mojangMapped = System.getProperty("mojang-mapped") != null

plugins {
    kotlin("jvm") version "1.7.10"
    id("xyz.xenondevs.specialsource-gradle-plugin") version "1.0.0"
    id("xyz.xenondevs.string-remapper-gradle-plugin") version "1.0.0"
    id("xyz.xenondevs.nova.nova-gradle-plugin") version "0.11-SNAPSHOT"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.xenondevs.xyz/releases")
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
}

dependencies {
    implementation(deps.nova)
    implementation(variantOf(deps.spigot) { classifier("remapped-mojang") })
}

addon {
    id.set(project.name)
    name.set(project.name.capitalized())
    version.set(project.version.toString())
    novaVersion.set(deps.versions.nova)
    main.set("com.example.ExampleAddon") // TODO: Change this to your main class
    
    // authors.add("ExampleAuthor") TODO: Set your list of authors
    // spigotResourceId.set(12345) TODO: Set your spigot resource id
}

spigotRemap {
    spigotVersion.set(deps.versions.spigot.get().substringBefore('-'))
    sourceJarTask.set(tasks.jar)
    spigotJarClassifier.set("")
}

remapStrings {
    remapGoal.set(if (mojangMapped) "mojang" else "spigot")
    spigotVersion.set(deps.versions.spigot.get())
    classes.set(listOf(
        // Put your classes to string-remap here
    ))
}

tasks {
    register<Copy>("addonJar") {
        group = "build"
        dependsOn("addon", if (mojangMapped) "jar" else "remapObfToSpigot")
        
        from(File(File(project.buildDir, "libs"), "${project.name}-${project.version}.jar"))
        into(System.getProperty("outDir")?.let(::File) ?: project.buildDir)
    }
}