plugins {
    id("java")
    id("fabric-loom") version("1.13.6") apply(false)
    id("net.neoforged.moddev") version("2.0.139") apply(false)
}

val MINECRAFT_VERSION by extra {"1.21.11"}
val NEOFORGE_VERSION by extra {"21.11.27-beta"}
val FABRIC_LOADER_VERSION by extra {"0.18.4"}
val MOD_VERSION by extra {"1.0"}

allprojects {
    apply(plugin = "java")
    apply(plugin = "maven-publish")

    group = "com.fuzjajadrowa"
    version = "$MOD_VERSION+MC$MINECRAFT_VERSION"
}

subprojects {
    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    val loader = if (project.name.contains("fabric")) "fabric" else "neoforge"

    base {
        archivesName.set("DontHideDays-$loader")
    }

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(21)
    }

    tasks.processResources {
        val replaceProperties = mapOf(
            "version" to MOD_VERSION
        )
        inputs.properties(replaceProperties)
        filesMatching(listOf("META-INF/neoforge.mods.toml", "fabric.mod.json")) {
            expand(replaceProperties)
        }
    }
}

tasks.jar { enabled = false }