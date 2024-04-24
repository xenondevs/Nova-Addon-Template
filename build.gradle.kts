import org.gradle.configurationcache.extensions.capitalized
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.example" // TODO: Change this to your group
version = "1.0-SNAPSHOT" // TODO: Change this to your addon version

val mojangMapped = project.hasProperty("mojang-mapped")

plugins {
    alias(libs.plugins.kotlin)
    alias(libs.plugins.paperweight)
    alias(libs.plugins.nova)
}

repositories {
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.xenondevs.xyz/releases")
}

dependencies {
    paperweight.paperDevBundle(libs.versions.paper)
    implementation(libs.nova)
}

addon {
    id.set(project.name)
    name.set(project.name.capitalized())
    version.set(project.version.toString())
    novaVersion.set(libs.versions.nova)
    main.set("com.example.ExampleAddon") // TODO: Change this to your main class
    authors.add("ExampleAuthor") // TODO: Set your list of authors
}

tasks {
    register<Copy>("addonJar") {
        group = "build"
        if (mojangMapped) {
            dependsOn("jar")
            from(File(buildDir, "libs/${project.name}-${project.version}-dev.jar"))
        } else {
            dependsOn("reobfJar")
            from(File(buildDir, "libs/${project.name}-${project.version}.jar"))
        }
        
        from(File(project.buildDir, "libs/${project.name}-${project.version}.jar"))
        into((project.findProperty("outDir") as? String)?.let(::File) ?: project.buildDir)
        rename { "${addonMetadata.get().addonName.get()}-${project.version}.jar" }
    }
    
    withType<KotlinCompile> {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}