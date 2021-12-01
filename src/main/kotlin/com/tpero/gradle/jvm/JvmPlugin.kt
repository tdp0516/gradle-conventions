package com.tpero.gradle.jvm

import com.tpero.gradle.addDependency
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

/**
 * A gradle plugin that provides conventions to support developing JVM-based applications
 *
 * The following underlying plugins are applied:
 * 1. [kotlin](TODO)
 * 1. [java](TODO)
 *
 * The following `implementation` dependencies are added:
 * 1. [commons-lang](TODO)
 */
class JvmPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            when (project.extensions.create("jvm", JvmExtension::class.java).language) {
                JvmLanguage.KOTLIN -> apply(KotlinPluginWrapper::class.java)
                JvmLanguage.JAVA -> apply(JavaPlugin::class.java)
            }
        }

        addDependency(project, "commons-lang")
    }
}
