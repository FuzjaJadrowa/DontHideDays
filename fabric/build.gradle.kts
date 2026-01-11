plugins {
    id("fabric-loom") version("1.13.6")
}

val MINECRAFT_VERSION: String by rootProject.extra
val FABRIC_LOADER_VERSION: String by rootProject.extra

dependencies {
    minecraft("com.mojang:minecraft:${MINECRAFT_VERSION}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${FABRIC_LOADER_VERSION}")
}