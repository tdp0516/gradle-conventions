package com.tpero.gradle

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.api.plugins.jvm.JvmTestSuite
import org.gradle.testing.base.TestingExtension

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
    project.dependencies.addProvider("implementation", catalog.findLibrary(dependency).get())
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
    (project.extensions.getByType(TestingExtension::class.java)
            .suites
            .getByName("test") as JvmTestSuite)
            .dependencies
            .implementation(catalog.findLibrary(dependency).get())
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
    findTestSuite(project, "test")
            .dependencies
            .implementation(catalog.findBundle(bundle).get())
}

private fun addIntegrationTestBundle(project: Project, catalog: String, bundle: String) {
    project.extensions.findByType(VersionCatalogsExtension::class.java)?.apply {
        named(catalog).apply {
            addIntegrationTestBundle(project, this, bundle)
        }
    }
}

private fun findTestSuite(project: Project, suiteName: String): JvmTestSuite {
    return (project.extensions.getByType(TestingExtension::class.java)
            .suites
            .getByName(suiteName) as JvmTestSuite)
}
