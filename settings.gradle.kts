rootProject.name = "example" // TODO: Change this to your addon id

enableFeaturePreview("VERSION_CATALOGS")
dependencyResolutionManagement {
    versionCatalogs {
        create("deps") {
            version("nova", "0.11-SNAPSHOT")
            version("spigot", "1.19.2-R0.1-SNAPSHOT")
            
            library("nova", "xyz.xenondevs.nova", "nova").versionRef("nova")
            library("spigot", "org.spigotmc", "spigot").versionRef("spigot")
        }
    }
}

pluginManagement {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://repo.xenondevs.xyz/releases")
    }
}