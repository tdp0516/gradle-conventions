package com.tpero.gradle.jvm

import com.tpero.gradle.addTestDependency
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * A gradle plugin that provides conventions for developing quality Spring applications
 *
 * The following underlying plugins are applied:
 * 1. [jvm-quality-plugin](JvmQualityPlugin)
 * 1. [Spring Boot](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
 *
 * The following `testImplementation` dependencies are added:
 * 1. [spring-boot-starter-test](TODO)
 * 1. [spock-spring](TODO)
 */
class SpringQualityPlugin: Plugin<Project> {
    override fun apply(project: Project) {
        project.plugins.apply {
            apply(JvmQualityPlugin::class.java)
            apply(SpringPlugin::class.java)
        }

        addTestDependency(project, "spring-boot-starter-test")
        addTestDependency(project, "spock-spring")
    }
}
