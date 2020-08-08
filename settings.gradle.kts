pluginManagement {
    plugins {
        kotlin("jvm") version "1.3.72"
    }
}
rootProject.name = "skipmo"
include(
        "port",
        "game-adapter-mvc",
        "core"
)
