# Nova-Addon-Template

Visit [the Nova documentation](https://xenondevs.xyz/docs/nova/addon/) for a guide on how to create Nova addons.

## Development server

You can launch a local development server with Nova and your addon via the `runOrigamiServer` and `runOrigamiServerAot` tasks.
Use the `serverDir` gradle property to set a working directory for the server (this is where worlds, configs, etc. will be).

Run the development server normally:

```shell
./gradlew runOrigamiServer -PserverDir="/path/to/server"
```

Run the development server with Java's AOT cache to speed up startup. The first run will take longer, but subsequent runs will be faster. The AOT cache will be automatically invalidated when things like the paper version or anything that affects the server (like changes to access wideners or mixins) change. Not compatible with debuggers.

```shell
./gradlew runOrigamiServerAot -PserverDir="/path/to/server"
```

## Building

To build the final addon jar, use the `addonJar` gradle task. You can define an output directory via the `outDir` gradle property, or just leave it out, and it'll default to `build/libs`.

```shell
./gradlew addonJar -PoutDir="/path/to/output/directory"
```