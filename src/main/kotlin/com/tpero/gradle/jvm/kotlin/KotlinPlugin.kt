package com.tpero.gradle.jvm.kotlin

import com.tpero.gradle.addDependency
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

class KotlinPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(KotlinPluginWrapper::class.java)
        }

        addDependency(project, "kotlin-logging")
    }
}
