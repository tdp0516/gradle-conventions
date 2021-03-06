package com.tpero.gradle.jvm

import com.tpero.gradle.jvm.kotlin.KotlinSpringPlugin
import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.springframework.boot.gradle.plugin.SpringBootPlugin

/**
 * A gradle plugin that provides conventions for developing Spring applications
 *
 * The following underlying plugins are applied:
 * 1. [spring-boot](TODO)
 * 1. [spring-dependency-management](TODO)
 * 1. [spring-quality-plugin](TODO)
 */
class SpringPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(JvmPlugin::class.java)
            apply(SpringBootPlugin::class.java)
            apply(DependencyManagementPlugin::class.java)
        }

        project.tasks.findByName("jar")!!.apply {
            enabled = false
        }

        if (project.extensions.findByType(JvmExtension::class.java)!!.language == JvmLanguage.KOTLIN) {
            project.plugins.apply(KotlinSpringPlugin::class.java)
        }
    }
}
