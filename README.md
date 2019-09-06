# chiwava
CWS common libraries for Java

## Installation

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
        url 'https://maven.pkg.github.com/nsftx/cws-flow-specification'
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
- core-commons:0.1.2
- core-error:0.1.0
- core-persistence:0.1.0
- debezium-commons:0.1.1
- spring-pagination-core:0.1.0
- spring-pagination-resolver:0.1.0