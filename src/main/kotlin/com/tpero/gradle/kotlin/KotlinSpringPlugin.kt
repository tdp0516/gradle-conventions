package com.tpero.gradle.kotlin

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.allopen.gradle.SpringGradleSubplugin
import org.springframework.boot.gradle.plugin.SpringBootPlugin

/**
 * A gradle plugin that provides conventions to support developing Kotlin Spring applications.
 *
 * The following underlying plugins are applied with the documented conventions:
 * 1. [kotlin-quality](KotlinQualityPlugin)
 * 1. [kotlin-spring](https://kotlinlang.org/docs/all-open-plugin.html#spring-support)
 * 1. [Spring Boot](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
 *     * The `jar` task is disabled since the boot jar should typically suffice
 * 1. [Spring Dependency Management](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/)
 */
class KotlinSpringPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(KotlinQualityPlugin::class.java)
            apply(SpringBootPlugin::class.java)
            apply(SpringGradleSubplugin::class.java)
            apply(DependencyManagementPlugin::class.java)
        }

        project.tasks.findByName("jar")?.apply {
            enabled = false
        }
    }
}
