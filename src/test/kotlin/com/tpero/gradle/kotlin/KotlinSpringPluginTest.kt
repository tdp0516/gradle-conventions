package com.tpero.gradle.kotlin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertFalse

class KotlinSpringPluginTest {
    private val project: Project = ProjectBuilder.builder().build()

    @BeforeTest
    fun `Initialize project`() {
        this.project.plugins.apply(KotlinSpringPlugin::class.java)
    }

    @Test
    @Ignore
    fun `Jar conventions are established`() {
        assertFalse {
            this.project.tasks.findByName("jar")!!.enabled
        }
    }
}
