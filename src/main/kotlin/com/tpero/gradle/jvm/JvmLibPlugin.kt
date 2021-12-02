package com.tpero.gradle.jvm

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaLibraryPlugin
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin

/**
 * A gradle plugin that provides conventions to support developing JVM-based libraries.
 *
 * The following underlying plugins are applied:
 * 1. [jvm-quality](JvmQualityPlugin)
 * 1. [java-library](https://docs.gradle.org/current/userguide/java_library_plugin.html)
 */
class JvmLibPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(JvmQualityPlugin::class.java)
            apply(JavaLibraryPlugin::class.java)
            apply(MavenPublishPlugin::class.java)
        }
    }
}
