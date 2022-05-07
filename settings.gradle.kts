rootProject.name = "kotching"

val projectMap = listOf(
    "main",
    "test"
).associateWith {
    project(":kotching-$it".also { include(it) })
}