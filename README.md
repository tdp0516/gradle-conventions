# Gradle Conventions

This repository holds components to streamline my software development using Gradle.

This may include, but is not limited to:

* `Convention Plugins`
* `Version Catalogs`

## Technologies

* [Kotlin](https://kotlinlang.org/) - Source Language
* [Gradle](https://gradle.org/) - Build Framework
* [Detekt](https://detekt.github.io/detekt/) - Static Analysis
* [Pitest](https://pitest.org/) - Mutation Testing Tool

## How to build

`<gradlew.bat|./gradlew> build`

## How to publish locally for testing

`<gradlew.bat|.gradlew publishToMavenLocal`

## Documentation

KDocs can be viewed, up-to-date with main, [here](https://tdp0516.github.io/gradle-conventions/html/index.html)

## What plugins are available?

1. [jvm-quality](https://tdp0516.github.io/gradle-conventions/html/gradle-conventions/com.tpero.gradle.jvm/-jvm-quality-plugin/index.html)

1. [android](https://tdp0516.github.io/gradle-conventions/html/gradle-conventions/com.tpero.gradle.kotlin/-android-plugin/index.html)

1. [jvm-lib](https://tdp0516.github.io/gradle-conventions/html/gradle-conventions/com.tpero.gradle.kotlin/-jvm-lib-plugin/index.html)

1. [jvm-quality](https://tdp0516.github.io/gradle-conventions/html/gradle-conventions/com.tpero.gradle.kotlin/-jvm-quality-plugin/index.html)

1. [spring](https://tdp0516.github.io/gradle-conventions/html/gradle-conventions/com.tpero.gradle.kotlin/-spring-plugin/index.html)

1. [spring-quality](https://tdp0516.github.io/gradle-conventions/html/gradle-conventions/com.tpero.gradle.kotlin/-spring-quality-plugin/index.html)

## How to reference the version catalog from other projects

Add the following to the `settings.gradle`

```

dependencyResolutionManagement {
    repositories {
        // Locally, this will be available in the local maven repo.
        // When executed from CI it will use artifactory, which will be resolved
        // by system properties set via github secrets. The reason for the hard-coded default dummy
        // values is to allow the gradle scripts to properly resolve locally during evaluation.
        // TODO Refactor this so the dummy defaults aren't necessary
        mavenLocal()
        maven {
            url = System.properties['artifactoryUrl'] + '/default-maven-local' ?: 'https://localhost:8080'

            credentials {
                username = System.properties['artifactoryUser'] ?: ''
                password = System.properties['artifactoryApiKey'] ?: ''
            }
        }
    }

    versionCatalogs {
        libs {
            from("com.tpero.gradle:gradle-conventions:0.0")
        }
    }
}

```
