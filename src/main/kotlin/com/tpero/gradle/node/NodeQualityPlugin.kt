package com.tpero.gradle.node

import com.github.gradle.node.NodePlugin
import com.tpero.gradle.jvm.SpringQualityPlugin
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A gradle plugin to provide conventions for developing quality node applications
 */
class NodeQualityPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(NodePlugin::class.java)
            apply(SpringQualityPlugin::class.java)
        }
    }
}
