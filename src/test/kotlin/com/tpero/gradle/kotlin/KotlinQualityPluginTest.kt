
package com.tpero.gradle.kotlin

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * A simple unit test for the `kotlin-quality` plugin.
 */
class KotlinQualityPluginTest {
    @Test
    @Ignore
    fun `plugin doesn't break build`() {
        // Create a test project and apply the plugin
        val project = ProjectBuilder.builder().build()

        project.repositories.apply {
            add(mavenLocal())
            add(mavenCentral())
        }

        project.plugins.apply(KotlinQualityPlugin::class.java)

        assertTrue {
            project.plugins.hasPlugin(KotlinQualityPlugin::class.java)
        }
    }
}
