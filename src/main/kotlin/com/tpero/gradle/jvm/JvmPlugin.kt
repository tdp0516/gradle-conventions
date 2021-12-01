package com.tpero.gradle.jvm

import com.tpero.gradle.addDependency
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

class JvmPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        val extension = project.extensions.create("jvm", JvmExtension::class.java)

        project.plugins.apply {
            when (extension.language) {
                JvmLanguage.KOTLIN -> apply(KotlinPluginWrapper::class.java)
                JvmLanguage.JAVA -> apply(JavaPlugin::class.java)
            }
        }

        addDependency(project, "commons-lang")
    }
}
