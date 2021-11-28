package com.tpero.gradle

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

/**
 * Helper class that simplifies manipulating a gradle project for testing purposes
 */
class ProjectWrapper {
    val project: Project = ProjectBuilder.builder().build()

    init {
        this.project.repositories.apply {
            add(mavenLocal())
            add(mavenCentral())
        }
    }
}
