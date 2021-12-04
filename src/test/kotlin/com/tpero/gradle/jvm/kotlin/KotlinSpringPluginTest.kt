package com.tpero.gradle.jvm.kotlin

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.BeforeTest

class KotlinSpringPluginTest {
    private val project: Project = ProjectBuilder.builder().build()

    @BeforeTest
    fun `Initialize project`() {
        this.project.plugins.apply(KotlinSpringPlugin::class.java)
    }
}
