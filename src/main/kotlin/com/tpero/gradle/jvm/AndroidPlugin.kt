package com.tpero.gradle.jvm

import org.gradle.api.Plugin
import org.gradle.api.Project

class AndroidPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.repositories.add(project.repositories.google())

        project.plugins.apply {
            apply(JvmPlugin::class.java)
            apply("com.android.application")
        }
    }
}
