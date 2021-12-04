package com.tpero.gradle.jvm.java

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPlugin
import org.gradle.api.plugins.quality.PmdPlugin

class JavaQualityPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(JavaPlugin::class.java)
            apply(PmdPlugin::class.java)
        }
    }
}
