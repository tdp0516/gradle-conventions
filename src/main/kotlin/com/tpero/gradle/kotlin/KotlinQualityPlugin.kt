package com.tpero.gradle.kotlin

import io.gitlab.arturbosch.detekt.DetektPlugin
import io.gitlab.arturbosch.detekt.extensions.DetektExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.dokka.gradle.DokkaPlugin

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
class KotlinQualityPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(DetektPlugin::class.java)
            apply(DokkaPlugin::class.java)
        }

        project.extensions.findByType(DetektExtension::class.java)!!.apply {
            buildUponDefaultConfig = true
//                val copy = File("detekt-copy.yml").toPath()
//                Files.copy(getDetektYmlStream(), copy, StandardCopyOption.REPLACE_EXISTING)
//                config = project.files(project.zipTree(
//                        KotlinQualityPlugin::class.java
//                                .javaClass
//                                .protectionDomain
//                                .codeSource
//                                .location
//                                .path)
//                        .files.filter {
//                            it.name.contains("detekt.yml")
//                        }.map {
//                            it.absolutePath
//                        })
        }
    }

//    private fun getDetektYmlStream(): InputStream {
//        return Thread.currentThread()
//                .contextClassLoader
//                .getResourceAsStream("detekt.yml")
//                ?: throw IllegalStateException("Default detekt.yml is required and was unable to be located")
//    }
}
