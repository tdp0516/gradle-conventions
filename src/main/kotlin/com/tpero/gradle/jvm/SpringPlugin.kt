package com.tpero.gradle.jvm

import com.tpero.gradle.kotlin.KotlinSpringPlugin
import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.springframework.boot.gradle.plugin.SpringBootPlugin

class SpringPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(SpringBootPlugin::class.java)
            apply(DependencyManagementPlugin::class.java)
            apply(SpringQualityPlugin::class.java)
        }

        project.tasks.findByName("jar")?.apply {
            enabled = false
        }

        if (project.extensions.findByType(JvmExtension::class.java)!!.language == JvmLanguage.KOTLIN) {
            project.plugins.apply(KotlinSpringPlugin::class.java)
        }
    }
}
