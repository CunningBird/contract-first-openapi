import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("java")
    id("maven-publish")
    id("org.openapi.generator") version "6.2.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // Web
    compileOnly("org.springframework.boot:spring-boot-starter-web:2.7.4")
    compileOnly("org.springframework.data:spring-data-commons:2.7.3")

    // SpringDoc dependencies
    compileOnly("org.springdoc:springdoc-openapi-webmvc-core:1.6.11")

    // @Nullable annotation
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.4")
    compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")
    compileOnly("org.openapitools:jackson-databind-nullable:0.2.3")

    // Bean Validation API support
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
}

val schemaPath = "$rootDir/src/main/openapi/contract-v1.0.yaml"

openApiValidate {
    inputSpec.set(schemaPath)
}

openApiGenerate {
    inputSpec.set(schemaPath)
    skipValidateSpec.set(false)

    outputDir.set("$buildDir/generated")

    generatorName.set("spring")
    configOptions.set(
        mutableMapOf(
            "dataLibrary" to "java8",
            "skipDefaultInterface" to "true",
            "interfaceOnly" to "true",
            "useSwaggerUI" to "false"
        )
    )

    apiPackage.set("com.cunningbird.templates.contractfirstrest.api")
    invokerPackage.set("com.cunningbird.templates.contractfirstrest.invoker")
    modelPackage.set("com.cunningbird.templates.contractfirstrest.model")
}

sourceSets {
    main {
        java {
            srcDir("${buildDir}/generated/src/main/java")
        }
    }
}

tasks.withType<JavaCompile> {
    dependsOn(tasks.withType<GenerateTask>())
}

tasks.test {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/cunningbird-lab/contract-frist-rest")
            credentials {
                username = System.getProperty("publishRegistryUsername")
                password = System.getProperty("publishRegistryPassword")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cunningbird.templates"
            artifactId = "contract-first-rest"
            version = "1.0.0"
            from(components["java"])
        }
    }
}