package com.tpero.gradle.jvm

import com.softeq.gradle.itest.ItestPlugin
import com.tpero.gradle.addIntegrationTestBundle
import com.tpero.gradle.addTestDependency
import com.tpero.gradle.kotlin.KotlinQualityPlugin
import info.solidsoft.gradle.pitest.PitestPlugin
import info.solidsoft.gradle.pitest.PitestPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.plugins.scala.ScalaPlugin
import org.sonarqube.gradle.SonarQubePlugin

/**
 * A gradle plugin that provides conventions to support developing quality JVM-based applications.
 *
 * The following plugins are applied with the documented conventions:
 * 1. [jvm-plugin](JvmPlugin)
 * 1. [Groovy](https://docs.gradle.org/current/userguide/groovy_plugin.html) - To support Spock testing
 *    * Adds a `testImplementation` dependency on spock-core
 * 1. [PiTest](https://gradle-pitest-plugin.solidsoft.info/)
 *     * Target Classes default to `com.tpero.*` but can be overridden using the `-PpitestTarget` command line argument
 *     * Produces HTMl and XML output files
 * 1. [SonarQube](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-gradle/)
 * 1. [Scala](https://docs.gradle.org/current/userguide/scala_plugin.html) - To support Gatling testing
 * 1. [itest]() - To eventually be replaced with gradle's built-in JVM test suite solution
 *     * Adds an `itestImplementation` dependency on several cucumber libraries
 * 1. [kotlin-quality-plugin](KotlinQualityPlugin) - Only applied if `jvm.language` is set to [JvmLanguage.KOTLIN]
 *
 * A [jvm](JvmExtension) extension is provided to configure plugin functionality
 */
class JvmQualityPlugin : Plugin<Project> {
    companion object {
        const val DEFAULT_PACKAGE = "com.tpero.*"
    }

    override fun apply(project: Project) {
        project.plugins.apply {
            apply(JvmPlugin::class.java)
            apply(GroovyPlugin::class.java)
            apply(PitestPlugin::class.java)
            apply(SonarQubePlugin::class.java)
            apply(ScalaPlugin::class.java)
            apply(ItestPlugin::class.java)
        }

        // Pitest conventions
        project.extensions.apply {
            findByType(PitestPluginExtension::class.java)!!.apply {
                targetClasses.set(mutableSetOf(project.properties["pitestTarget"] as String? ?: DEFAULT_PACKAGE))
                outputFormats.set(mutableSetOf("HTML", "XML"))
            }
        }

        addTestDependency(project, "spock")
        addIntegrationTestBundle(project, "cucumber")

        if (project.extensions.findByType(JvmExtension::class.java)!!.language == JvmLanguage.KOTLIN) {
            project.plugins.apply(KotlinQualityPlugin::class.java)
        }

        // Use JUnit 5
//      project.extensions.findByType(TestingExtension::class.java)?.apply {
//          suites.findByName("test")?.apply {
//              targets.findByName("all").apply {
//
//              }
//          }
//      }
    }
}
