
package com.tpero.gradle.jvm.kotlin

import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

/**
 * A simple unit test for the `kotlin-quality` plugin.
 */
class KotlinQualityPluginTest {
    private val project = ProjectBuilder.builder().build()

    @BeforeTest
    fun `Initialize project`() {
        this.project.plugins.apply(KotlinQualityPlugin::class.java)
    }

    @Test
    fun `Plugin doesn't break simple build`() {
        assertTrue {
            this.project.plugins.hasPlugin(KotlinQualityPlugin::class.java)
        }
    }
}
