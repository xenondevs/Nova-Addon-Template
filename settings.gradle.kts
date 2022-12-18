rootProject.name = "example" // TODO: Change this to your addon id

dependencyResolutionManagement {
    versionCatalogs {
        create("libs") {
            version("nova", "0.12.7")
            version("spigot", "1.19.3-R0.1-SNAPSHOT")
            
            library("nova", "xyz.xenondevs.nova", "nova").versionRef("nova")
            library("spigot", "org.spigotmc", "spigot").versionRef("spigot")
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