package com.tpero.gradle.jvm

import com.tpero.gradle.ProjectWrapper
import info.solidsoft.gradle.pitest.PitestPlugin
import info.solidsoft.gradle.pitest.PitestPluginExtension
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.api.plugins.scala.ScalaPlugin
import org.sonarqube.gradle.SonarQubePlugin
import kotlin.test.BeforeTest
import kotlin.test.Ignore
import kotlin.test.Test
import kotlin.test.assertTrue

@Ignore
class JvmQualityPluginTest {
    private var project = ProjectWrapper().project

    @BeforeTest
    fun `Initialize project`() {
        this.project.plugins.apply(JvmQualityPlugin::class.java)
    }

    @Test
    fun `Plugin doesn't break simple build`() {
        assertTrue {
            this.project.plugins.hasPlugin(JvmQualityPlugin::class.java)
        }
    }

    @Test
    fun `PiTest conventions are established`() {
        assertTrue {
            this.project.plugins.hasPlugin(PitestPlugin::class.java)
        }

        this.project.extensions.findByType(PitestPluginExtension::class.java)!!.apply {
            assertTrue {
                outputFormats.get().contains("HTML") && outputFormats.get().contains("XML")
            }

            assertTrue {
                targetClasses.get() == JvmQualityPlugin.DEFAULT_PACKAGES
            }
        }
    }

    @Test
    fun `PiTest allows property override of target classes`() {
        // TODO Need a way to add project property prior to applying plugin for this test only
    }

    @Test
    fun `Spock conventions are established`() {
        assertTrue {
            this.project.plugins.hasPlugin(GroovyPlugin::class.java)
        }
    }

    @Test
    fun `Gatling conventions are established`() {
        assertTrue {
            this.project.plugins.hasPlugin(ScalaPlugin::class.java)
        }
    }

    @Test
    fun `Sonar conventions are established`() {
        assertTrue {
            this.project.plugins.hasPlugin(SonarQubePlugin::class.java)
        }
    }
}
