package com.tpero.gradle.jvm.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.kotlin.allopen.gradle.SpringGradleSubplugin

/**
 * A gradle plugin that provides conventions to support developing Kotlin Spring applications.
 *
 * The following underlying plugins are applied with the documented conventions:
 * 1. [kotlin-spring-quality](KotlinSpringQualityPlugin)
 *     * The `jar` task is disabled since the boot jar should typically suffice
 * 1. [kotlin-spring](https://kotlinlang.org/docs/all-open-plugin.html#spring-support)
 * 1. [Spring Dependency Management](https://docs.spring.io/dependency-management-plugin/docs/current/reference/html/)
 */
class KotlinSpringPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(SpringGradleSubplugin::class.java)
        }
    }
}
