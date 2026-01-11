plugins {
    id("java")
    id("fabric-loom") version("1.13.6") apply(false)
    id("net.neoforged.moddev") version("2.0.139") apply(false)
}

val minecraft_version: String by project
val mod_version: String by project
val group_id: String by project

allprojects {
    apply(plugin = "java")
    group = group_id
    version = "$mod_version+mc$minecraft_version"

    repositories {
        mavenCentral()
    }
}

subprojects {
    java.toolchain.languageVersion = JavaLanguageVersion.of(21)

    tasks.withType<JavaCompile> {
        options.encoding = "UTF-8"
        options.release.set(21)
    }
}