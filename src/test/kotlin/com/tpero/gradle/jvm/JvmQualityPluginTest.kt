package com.tpero.gradle.jvm

import info.solidsoft.gradle.pitest.PitestPlugin
import info.solidsoft.gradle.pitest.PitestPluginExtension
import org.gradle.api.plugins.GroovyPlugin
import org.gradle.testfixtures.ProjectBuilder
import org.sonarqube.gradle.SonarQubePlugin
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue

class JvmQualityPluginTest {
    private val project = ProjectBuilder.builder().build()

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
    fun `Sonar conventions are established`() {
        assertTrue {
            this.project.plugins.hasPlugin(SonarQubePlugin::class.java)
        }
    }
}
