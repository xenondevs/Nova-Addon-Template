# AGENTS.md

## Project

This is a Kotlin addon for Nova. The Nova version is selected through the version catalog in `settings.gradle.kts`.

## References

Before implementing addon features, obtain these repositories in the gitignored `.references/` directory. Prefer cloning so you can inspect branches, tags, and history:

```sh
git clone https://github.com/xenondevs/Docs.git .references/Docs
git clone https://github.com/xenondevs/Nova-Addons.git .references/Nova-Addons
```

- Reuse existing checkouts. Fetch updates when needed; preserve any local changes.
- If cloning is unavailable, download GitHub source archives and extract them into the same directories.
- Select reference branches or revisions compatible with this project's Nova version. Do not assume the default branch matches.
- Consult these references before inventing API usage. Keep reference files out of commits and addon artifacts.

## Common mistakes

- Use `ItemType` and `BlockType` instead of `Material`. The vanilla enum cannot represent Nova's custom items and blocks.
- Compare registry entries with entries, or their resolved values with values. A `RegistryEntry<T>` wrapper is not the `T` it refers to.
- Use `key.asString()` instead of `toString()` or implicit string interpolation when you need a namespaced identifier. `toString()` may return an implementation-specific representation.
- Use `typedKey.key()` when a plain `Key` is required. A `TypedKey` also identifies its registry, so passing the wrapper is not equivalent to extracting its entry key.

## Development server

Use `runOrigamiServer` to build the addon and launch a local Paper server with Origami, Nova, and this addon configured automatically:

```sh
./gradlew runOrigamiServer -PserverDir="/path/to/dev-server"
```

- Use this for in-game checks such as GUI behavior, resource-pack rendering, and save/reload compatibility. Compilation alone cannot verify those.
- Configure additional addons and Origami plugins through `origami.runServer.plugins.from(...)` in `build.gradle.kts`, so Origami can include their transformations at startup. Ordinary Paper plugins can also go in the server's `plugins/` directory.
- `runOrigamiServerAot` uses Java's AOT cache to speed up subsequent starts; its first run takes longer. Use `runOrigamiServer` when debugging.
