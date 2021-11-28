package com.tpero.gradle.kotlin

import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * WORK IN PROGRESS
 */
class KotlinAndroidPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply("com.android.application")
            apply(KotlinAndroidPlugin::class.java)
            apply(KotlinQualityPlugin::class.java)
        }
    }
}
