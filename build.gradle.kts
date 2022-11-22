import org.openapitools.generator.gradle.plugin.tasks.GenerateTask

plugins {
    id("java")
    id("maven-publish")
    id("org.openapi.generator") version "5.3.1"
}

repositories {
    mavenCentral()
}

dependencies {
    // Build Context
    compileOnly("javax.annotation:javax.annotation-api:1.3.2")
    compileOnly("org.springframework:spring-web:5.3.23")
    compileOnly("org.springframework:spring-context:5.3.23")
    compileOnly("javax.validation:validation-api:2.0.1.Final")
    compileOnly("javax.servlet:javax.servlet-api:4.0.1")
    compileOnly("io.swagger:swagger-annotations:1.6.8")
    compileOnly("com.google.code.findbugs:jsr305:3.0.2")
    compileOnly("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.4")
    compileOnly("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")
    compileOnly("org.openapitools:jackson-databind-nullable:0.2.4")

    // Test context
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.9.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.0")
    testImplementation("org.springframework.boot:spring-boot-starter-test:2.7.5")
    testImplementation("org.springframework.boot:spring-boot-starter-web:2.7.5")
    testImplementation("org.springframework.cloud:spring-cloud-starter-openfeign:3.1.5")
    testImplementation("javax.annotation:javax.annotation-api:1.3.2")
    testImplementation("javax.validation:validation-api:2.0.1.Final")
    testImplementation("javax.servlet:javax.servlet-api:4.0.1")
    testImplementation("io.swagger:swagger-annotations:1.6.8")
    testImplementation("com.google.code.findbugs:jsr305:3.0.2")
    testImplementation("com.fasterxml.jackson.dataformat:jackson-dataformat-yaml:2.13.4")
    testImplementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.13.4")
    testImplementation("org.openapitools:jackson-databind-nullable:0.2.4")
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
            "useSwaggerUI" to "false",
            "interfaceOnly" to "true",
            "useFeignClientUrl" to "false",
        )

    )

    apiPackage.set("com.cunningbird.contractfirst.openapi.contract.api")
    invokerPackage.set("com.cunningbird.contractfirst.openapi.contract.invoker")
    modelPackage.set("com.cunningbird.contractfirst.openapi.contract.model")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }
}

sourceSets {
    main {
        java {
            srcDir("${buildDir}/generated/src/main/java")
        }
    }
    test {
        java {
            srcDir("${buildDir}/generated/src/main/java")
        }
    }
}

tasks.withType<JavaCompile> {
    dependsOn(tasks.withType<GenerateTask>())
}

tasks.withType<Test> {
    useJUnitPlatform()
}

publishing {
    repositories {
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/cunningbird/contract-first-openapi")
            credentials {
                username = System.getProperty("publishRegistryUsername")
                password = System.getProperty("publishRegistryPassword")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.cunningbird.contractfirst.openapi"
            artifactId = "contract"
            version = "1.1.1"
            from(components["java"])
        }
    }
}