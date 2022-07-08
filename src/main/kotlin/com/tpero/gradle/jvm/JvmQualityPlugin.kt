package com.tpero.gradle.jvm

import com.tpero.gradle.addIntegrationTestBundle
import com.tpero.gradle.addTestDependency
import com.tpero.gradle.jvm.java.JavaQualityPlugin
import com.tpero.gradle.jvm.kotlin.KotlinQualityPlugin
import info.solidsoft.gradle.pitest.PitestPlugin
import info.solidsoft.gradle.pitest.PitestPluginExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.testing.base.TestingExtension
import org.sonarqube.gradle.SonarQubePlugin

/**
 * A gradle plugin that provides conventions to support developing quality JVM-based applications.
 *
 * The following plugins are applied with the documented conventions:
 * 1. [jvm-plugin](JvmPlugin)
 *     * `test` is set to run JUnit Jupiter tests by default
 * 1. [Groovy](https://docs.gradle.org/current/userguide/groovy_plugin.html) - To support Spock testing
 *    * Adds a `testImplementation` dependency on spock-core
 * 1. [PiTest](https://gradle-pitest-plugin.solidsoft.info/)
 *     * Target Classes default to `com.tpero.*` but can be overridden using the `-PpitestTarget` command line argument
 *     * Produces HTMl and XML output files
 * 1. [SonarQube](https://docs.sonarqube.org/latest/analysis/scan/sonarscanner-for-gradle/)
 * 1. [itest]() - To eventually be replaced with gradle's built-in JVM test suite solution
 *     * Adds an `itestImplementation` dependency on several cucumber libraries
 *     * `itestImplementation` inherits all dependencies from `testImplementation`
 *     * `integrationTest` is set to run JUnit Jupiter tests by default
 * 1. [kotlin-quality-plugin](KotlinQualityPlugin) -
 * Only applied if [JvmExtension.language] is set to [JvmLanguage.KOTLIN]
 *
 * A [jvm](JvmExtension) extension is provided to configure plugin functionality
 */
class JvmQualityPlugin : Plugin<Project> {
    companion object {
        val DEFAULT_PACKAGES = setOf("com.tpero.*", "com.pero.*")
    }

    override fun apply(project: Project) {
        project.plugins.apply {
            apply(JvmPlugin::class.java)
            apply(GroovyPlugin::class.java)
            apply(PitestPlugin::class.java)
            apply(SonarQubePlugin::class.java)
        }

        // Pitest conventions
        project.extensions.apply {
            findByType(PitestPluginExtension::class.java)!!.apply {
                junit5PluginVersion.set(findByType(VersionCatalogsExtension::class.java)
                        ?.named("libs")
                        ?.findVersion("pitest-junit")
                        ?.get()
                        ?.requiredVersion)
                targetClasses.set(getTargetClasses(project))
                outputFormats.set(mutableSetOf("HTML", "XML"))
            }
        }

        project.extensions.getByType(TestingExtension::class.java).suites.apply {
            (this.getByName("test") as JvmTestSuite).apply {
                this.useSpock()
                addTestDependency(project, "spock")
            }

            this.create("itest", JvmTestSuite::class.java).apply {
                this.useJUnitJupiter()
                addIntegrationTestBundle(project, "cucumber")
            }
        }

        project.plugins.apply {
            when (project.extensions.findByType(JvmExtension::class.java)!!.language) {
                JvmLanguage.KOTLIN -> apply(KotlinQualityPlugin::class.java)
                JvmLanguage.JAVA -> apply(JavaQualityPlugin::class.java)
            }
        }
    }

    private fun getTargetClasses(project: Project): Set<String> {
        (project.properties["pitestTarget"] as String?)?.apply {
            return split(",").toSet()
        }

        return DEFAULT_PACKAGES
    }
}
