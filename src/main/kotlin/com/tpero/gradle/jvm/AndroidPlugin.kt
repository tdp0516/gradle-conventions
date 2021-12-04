package com.tpero.gradle.jvm

import com.android.build.gradle.AppPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A gradle plugin that provides conventions to develop android applications
 *
 * This plugin automatically adds the `google` repository to the applied project.
 *
 * The following underlying plugins are applied:
 * 1. [jvm-plugin](JvmPlugin)
 * 1. [com.android.application](TODO)
 */
class AndroidPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.repositories.apply {
            add(google())
        }

        project.plugins.apply {
            apply(JvmPlugin::class.java)
            apply(AppPlugin::class.java)
        }
    }
}
