package com.tpero.gradle.jvm

import com.tpero.gradle.addDependency
import com.tpero.gradle.jvm.java.JavaPlugin
import com.tpero.gradle.jvm.kotlin.KotlinPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project
import java.net.URI

/**
 * A gradle plugin that provides conventions to support developing JVM-based applications
 *
 * The following underlying plugins are applied:
 * 1. [kotlin](TODO)
 * 1. [java](TODO)
 *
 * The following `implementation` dependencies are added:
 * 1. [commons-lang](TODO)
 * 1. [kotlin-logging](TODO) - Conditional on [JvmExtension.language] being set to [JvmLanguage.KOTLIN]
 */
class JvmPlugin : Plugin<Project> {
    override fun apply(project: Project) {
        project.repositories.apply {
            add(mavenLocal())
            add(mavenCentral())
            add(maven {}.apply {
                url = URI(System.getProperties()["artifactoryUrl"] as String? ?: "http:localhost:8080")

                credentials.apply {
                    username = System.getProperties()["artifactoryUser"] as String?
                    password = System.getProperties()["artifactoryApiKey"] as String?
                }
            })
        }

        project.plugins.apply {
            when (project.extensions.create("jvm", JvmExtension::class.java).language) {
                JvmLanguage.KOTLIN -> apply(KotlinPlugin::class.java)
                JvmLanguage.JAVA -> apply(JavaPlugin::class.java)
            }
        }

        addDependency(project, "commons-lang")
    }
}
