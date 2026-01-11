plugins {
    id("net.neoforged.moddev") version("2.0.139")
}

neoForge {
    version = rootProject.extra["NEOFORGE_VERSION"].toString()

    mods {
        create("donthidedays") {
            sourceSet(sourceSets.main.get())
        }
    }
}

dependencies {
}