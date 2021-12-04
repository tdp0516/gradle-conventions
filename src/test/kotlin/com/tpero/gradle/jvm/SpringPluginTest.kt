package com.tpero.gradle.jvm

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SpringPluginTest {
    private val project: Project = ProjectBuilder.builder().build()

    @BeforeTest
    fun `Initialize project`() {
        this.project.plugins.apply(SpringPlugin::class.java)
    }

    @Test
    fun `Spring conventions are established`() {
        this.project.plugins.apply {
            assertTrue {
                hasPlugin(JvmPlugin::class.java)
            }

            assertTrue {
                hasPlugin(SpringBootPlugin::class.java)
            }

            assertTrue {
                hasPlugin(DependencyManagementPlugin::class.java)
            }
        }
    }
}
