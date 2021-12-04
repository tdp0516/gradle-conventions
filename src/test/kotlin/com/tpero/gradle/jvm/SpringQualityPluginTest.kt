package com.tpero.gradle.jvm

import io.gatling.gradle.GatlingPlugin
import org.gradle.api.plugins.scala.ScalaPlugin
import org.gradle.testfixtures.ProjectBuilder
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class SpringQualityPluginTest {
    private val project = ProjectBuilder.builder().build()

    @BeforeTest
    fun `Initialize project`() {
        this.project.plugins.apply(SpringQualityPlugin::class.java)
    }

    @Test
    fun `Gatling conventions are established`() {
        this.project.plugins.apply {
            assertTrue {
                hasPlugin(ScalaPlugin::class.java)
            }

            assertTrue {
                hasPlugin(GatlingPlugin::class.java)
            }
        }
    }
}
