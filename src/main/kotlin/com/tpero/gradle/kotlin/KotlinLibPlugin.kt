package com.tpero.gradle.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin

/**
 * A gradle plugin that provides conventions to support developing Kotlin libraries.
 *
 * The following underlying plugins are applied:
 * 1. [kotlin-quality](KotlinQualityPlugin)
 * 1. [java-library](https://docs.gradle.org/current/userguide/java_library_plugin.html)
 */
class KotlinLibPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(KotlinQualityPlugin::class.java)
            apply(JavaLibraryPlugin::class.java)
        }
    }
}
