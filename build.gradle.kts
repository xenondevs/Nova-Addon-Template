group = "com.example" // TODO: Change this to your group
version = "1.0-SNAPSHOT" // TODO: Change this to your addon version

plugins {
    alias(libs.plugins.nova)
    alias(libs.plugins.kotlinx.serialization)
}

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
    maven("https://repo.xenondevs.xyz/releases/")
}

dependencies {
    implementation(libs.nova)
}

kotlin {
    compilerOptions {
        optIn.addAll(
            "xyz.xenondevs.invui.ExperimentalReactiveApi",
            "xyz.xenondevs.invui.dsl.ExperimentalDslApi"
        )
    }
}

addon {
    name = project.name.replaceFirstChar(Char::uppercase)
    version = project.version.toString()
    main = "com.example.ExampleAddon" // TODO: Change this to your main class
    
    // output directory for the generated addon jar is read from the "outDir" project property (-PoutDir="...")
    val outDir = project.findProperty("outDir")
    if (outDir is String)
        destination = File(outDir)
}

origami {
    runServer {
        // working directory is read from the "serverDir" project property (-PserverDir="...")
        workingDirectory.set(layout.dir(providers.gradleProperty("serverDir").map(::File)))
        
        // you can add additional plugins with `plugins.from(...)` (all types: normal plugins, origami plugins, nova addons)
        // or just put them in the plugins/ folder of your server (only normal plugins! origami plugins and nova addons MUST be specified in `plugins.from(...)`)
    }
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(25))
    }
}