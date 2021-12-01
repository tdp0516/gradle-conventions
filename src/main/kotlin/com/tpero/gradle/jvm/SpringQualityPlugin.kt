package com.tpero.gradle.jvm

import com.tpero.gradle.addTestDependency
import org.gradle.api.Plugin
import org.gradle.api.Project

/**
 * 1. [jvm-quality-plugin](JvmQualityPlugin)
 * 1. [Spring Boot](https://docs.spring.io/spring-boot/docs/current/gradle-plugin/reference/htmlsingle/)
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
