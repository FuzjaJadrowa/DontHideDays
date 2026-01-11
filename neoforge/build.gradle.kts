plugins {
    id("net.neoforged.moddev")
}

val neoforge_version: String by project

base {
    archivesName.set("DontHideDays-neoforge")
}

neoForge {
    version = neoforge_version

    runs {
        create("client") {
            client()
            systemProperty("neoforge.enabledGameTestNamespaces", "donthidedays")
        }
    }

    mods {
        create("donthidedays") {
            sourceSet(sourceSets.main.get())
        }
    }
}

tasks.processResources {
    val replaceProperties = mapOf(
        "version" to project.version,
        "minecraft_version" to rootProject.extra["minecraft_version"]
    )

    inputs.properties(replaceProperties)

    filesMatching("META-INF/neoforge.mods.toml") {
        expand(replaceProperties)
    }
}