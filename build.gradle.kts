
plugins {
    `java-gradle-plugin`
    `java-test-fixtures`
    `maven-publish`
    alias(libs.plugins.jfrog)
    alias(libs.plugins.detekt)
    alias(libs.plugins.dokka)
    alias(libs.plugins.kotlin)
    alias(libs.plugins.pitest)
}

group = "com.tpero.gradle"

tasks.publishToMavenLocal {
    dependsOn("build")
}

repositories {
    mavenCentral()
    google()
    maven(url = "https://plugins.gradle.org/m2/")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

dependencies {
    implementation(libs.commons.lang)
    implementation(libs.bundles.spring)

    // Dependencies needed for applying other plugins
    implementation(libs.detekt)
    implementation(libs.dokka)
    implementation(libs.kotlin.allopen)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.pitest)
    implementation(libs.spring.boot.plugin)
    implementation(libs.spring.dependency.mgmt)
    implementation(libs.android.gradle)
    implementation(libs.sonar)
    implementation(libs.gatling)

    testImplementation(libs.bundles.test)
}

gradlePlugin {
    plugins {
        register("jvm-quality") {
            id = "jvm-quality"
            version = 0.0
            implementationClass = "com.tpero.gradle.jvm.JvmQualityPlugin"
        }

        register("kotlin-android") {
            id = "kotlin-android"
            version = 0.0
            implementationClass = "com.tpero.gradle.kotlin.KotlinAndroidPlugin"
        }

        register("kotlin-lib") {
            id = "kotlin-lib"
            version = 0.0
            implementationClass = "com.tpero.gradle.kotlin.KotlinLibPlugin"
        }

        register("kotlin-quality") {
            id = "kotlin-quality"
            version = 0.0
            implementationClass = "com.tpero.gradle.kotlin.KotlinQualityPlugin"
        }

        register("kotlin-spring") {
            id = "kotlin-spring"
            version = 0.0
            implementationClass = "com.tpero.gradle.kotlin.KotlinSpringPlugin"
        }
    }
}

pitest {
    testPlugin.set("KotlinTest")
    targetClasses.set(setOf("com.tpero.gradle.*"))
    outputFormats.set(setOf("HTML", "XML"))
    timestampedReports.set(false)
    enableDefaultIncrementalAnalysis.set(true)
    useClasspathFile.set(true)
}

artifactory {
    setContextUrl(System.getProperties()["artifactoryUrl"])
    publish(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.PublisherConfig> {
        repository(delegateClosureOf<org.jfrog.gradle.plugin.artifactory.dsl.DoubleDelegateWrapper> {
            setProperty("repoKey", "default-maven-local")
            setProperty("username", System.getProperties()["artifactoryUser"])
            setProperty("password", System.getProperties()["artifactoryApiKey"])
            setProperty("maven", true)
        })

        defaults(delegateClosureOf<groovy.lang.GroovyObject> {
            invokeMethod("publications", publishing.publications.names.toTypedArray())
        })
    })
}
