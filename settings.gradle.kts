rootProject.name = "example" // TODO: Change this to your addon id

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("nova", "0.13")
            version("spigot", "1.19.4-R0.1-SNAPSHOT")
            version("kotlin", "1.8.20")
            
            plugin("kotlin", "org.jetbrains.kotlin.jvm").versionRef("kotlin")
            plugin("nova", "xyz.xenondevs.nova.nova-gradle-plugin").versionRef("nova")
            plugin("stringremapper", "xyz.xenondevs.string-remapper-gradle-plugin").version("1.3")
            plugin("specialsource", "xyz.xenondevs.specialsource-gradle-plugin").version("1.1")
            
            library("nova", "xyz.xenondevs.nova", "nova").versionRef("nova")
        }
    }
}

pluginManagement {
    repositories {
        mavenCentral()
        maven("https://repo.xenondevs.xyz/releases")
        mavenLocal { content { includeGroup("org.spigotmc") } }
    }
}