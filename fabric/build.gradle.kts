plugins {
    id("fabric-loom")
}

val minecraft_version: String by project
val fabric_loader_version: String by project

base {
    archivesName.set("DontHideDays-fabric")
}

dependencies {
    minecraft("com.mojang:minecraft:${minecraft_version}")
    mappings(loom.officialMojangMappings())
    modImplementation("net.fabricmc:fabric-loader:${fabric_loader_version}")
}

tasks.processResources {
    val replaceProperties = mapOf(
        "version" to project.version,
        "minecraft_version" to rootProject.extra["minecraft_version"],
        "loader_version" to rootProject.extra["fabric_loader_version"]
    )

    inputs.properties(replaceProperties)

    filesMatching("fabric.mod.json") {
        expand(replaceProperties)
    }
}