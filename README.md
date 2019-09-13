# CHIWAVA

CHIWAVA is a set of libraries used to reduce the amount of boilerplate code used in Java projects.

## Installation

**Note**: CHIWAVA is migrating to Maven Central, use the guide below for versions below v1.0.0

CHIWAVA uses GitHub's package registry as a deployment solution. If your project is using Maven, please follow the this [guide](https://help.github.com/en/articles/configuring-apache-maven-for-use-with-github-package-registry#installing-a-package).

If your project is using Gradle, please follow the steps below:

1. Create a system gradle.properties file in ~/.gradle/ and add the following properties:
```
mavenUser=GITHUB_USERNAME
mavenPassword=PERSONAL_ACCESS_TOKEN
```

2. Add CHIWAVA's Maven repository
```
repositories {
    maven {
        credentials {
            username "$mavenUser"
            password "$mavenPassword"
        }
        url 'https://maven.pkg.github.com/nsftx/chiwava'
    }
}
```

3. Install the needed CHIWAVA modules
```
dependencies {
    compile("com.nsoft.chiwava:chiwava-MODULE_NAME:VERSION")
}
```

## Available modules
- core-commons:1.0.0
- core-error:1.0.0
- core-persistence:1.0.0
- debezium-commons:1.0.0
- spring-pagination-core:1.0.0
- spring-pagination-resolver:1.0.0
