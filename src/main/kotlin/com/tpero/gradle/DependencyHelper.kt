package com.tpero.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension

/**
 * Adds an `implementation` dependency referenced by the given alias from the `libs` version catalog
 */
fun addDependency(project: Project, dependency: String) {
    addDependency(project, "libs", dependency)
}

/**
 * Adds an `implementation` dependency referenced by the given alias from the given version catalog
 */
fun addDependency(project: Project, catalog: String, dependency: String) {
    project.extensions.findByType(VersionCatalogsExtension::class.java)?.apply {
        named(catalog).apply {
            addDependency(project, this, dependency)
        }
    }
}

private fun addDependency(project: Project, catalog: VersionCatalog, dependency: String) {
    project.dependencies.addProvider("implementation", catalog.findDependency(dependency).get())
}

/**
 * Adds a `testImplementation` dependency referenced by the given alias from the `libs` version catalog
 */
fun addTestDependency(project: Project, dependency: String) {
    addTestDependency(project, "libs", dependency)
}

/**
 * Adds a `testImplementation` dependency referenced by the given alias from the given version catalog
 */
fun addTestDependency(project: Project, catalog: String, dependency: String) {
    project.extensions.findByType(VersionCatalogsExtension::class.java)?.apply {
        named(catalog).apply {
            addTestDependency(project, this, dependency)
        }
    }
}

private fun addTestDependency(project: Project, catalog: VersionCatalog, dependency: String) {
    project.dependencies.addProvider("testImplementation", catalog.findDependency(dependency).get())
}

/**
 * Adds an `itestImplementation` bundle referenced by the given alias from the `libs` version catalog
 */
fun addIntegrationTestBundle(project: Project, bundle: String) {
    addIntegrationTestBundle(project, "libs", bundle)
}

/**
 * Adds an `itestImplementation` bundle referenced by the given alias from the given version catalog
 */
fun addIntegrationTestBundle(project: Project, catalog: VersionCatalog, bundle: String) {
    project.dependencies.addProvider("itestImplementation", catalog.findBundle(bundle).get())
}

private fun addIntegrationTestBundle(project: Project, catalog: String, bundle: String) {
    project.extensions.findByType(VersionCatalogsExtension::class.java)?.apply {
        named(catalog).apply {
            addIntegrationTestBundle(project, this, bundle)
        }
    }
}
