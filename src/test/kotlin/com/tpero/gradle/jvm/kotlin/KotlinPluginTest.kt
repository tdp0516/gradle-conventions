package com.tpero.gradle.jvm.kotlin

import org.gradle.testfixtures.ProjectBuilder
import org.jetbrains.kotlin.gradle.plugin.KotlinPluginWrapper
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class KotlinPluginTest {
    private val project = ProjectBuilder.builder().build()

    @BeforeTest
    fun `Initialize project`() {
        this.project.plugins.apply(KotlinPlugin::class.java)
    }

    @Test
    fun `Kotlin conventions are established`() {
        assertTrue {
            this.project.plugins.hasPlugin(KotlinPluginWrapper::class.java)
        }
    }
}
