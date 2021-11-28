
package com.tpero.gradle.kotlin

import com.tpero.gradle.jvm.JvmQualityPlugin
import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.dokka.gradle.DokkaPlugin
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper

/**
 * A gradle plugin that provides conventions to support developing quality Kotlin applications.
 *
 * A `kotlinQuality` task is added and the following underlying plugins are applied:
 * 1. [Kotlin JVM](https://kotlinlang.org/docs/gradle.html)
 * 1. [Detekt](https://detekt.github.io/detekt/)
 *     * The `detekt` task depends on `kotlinQuality` so that any `detekt` conventions are set appropriately
 * 1. [Dokka](https://kotlin.github.io/dokka/)
 * 1. [Pitest](https://gradle-pitest-plugin.solidsoft.info/)
 *     * The `pitest` task depends on `kotlinQuality` so that any `pitest` conventions are set appropriately
 */
class KotlinQualityPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(KotlinPluginWrapper::class.java)
            apply(JvmQualityPlugin::class.java)
            apply(DetektPlugin::class.java)
            apply(DokkaPlugin::class.java)
        }

        val qualityTask = project.task("kotlinQuality") {
            // Detekt conventions
            project.extensions.findByType(DetektExtension::class.java)!!.apply {
                buildUponDefaultConfig = true
                // TODO Make this handle the null case better
//                config = project.files(Thread.currentThread().contextClassLoader.getResource("detekt.yml")?.path)
            }
        }

        project.tasks.findByName("detekt")?.dependsOn(qualityTask)
        project.tasks.findByName("pitest")?.dependsOn(qualityTask)
    }
}
