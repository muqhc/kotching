plugins {
    kotlin("jvm") version "1.6.21"
}


allprojects {
    repositories {
        mavenCentral()
    }
}

subprojects {
    apply(plugin="org.jetbrains.kotlin.jvm")
    dependencies {
        implementation(kotlin("stdlib"))
        implementation(kotlin("reflect"))
    }
}