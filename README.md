# CHIWAVA [![Maven metadata URL](https://img.shields.io/maven-metadata/v?metadataUrl=https%3A%2F%2Frepo1.maven.org%2Fmaven2%2Fcom%2Fnsoft%2Fchiwava%2Fchiwava-parent%2Fmaven-metadata.xml&style=for-the-badge)](https://search.maven.org/search?q=g:com.nsoft.chiwava) [![GitHub](https://img.shields.io/github/license/nsftx/nsoft-chiwava?style=for-the-badge)](https://www.apache.org/licenses/LICENSE-2.0)

CHIWAVA is a set of libraries used to reduce the amount of boilerplate code used in Spring based Java projects.

## Installation

### Maven
```xml
<dependencies>
    ...
    <dependency>
        <groupId>com.nsoft.chiwava</groupId>
        <artifactId>${chiwava-module-name}</artifactId>
        <version>2.0.0.RC3</version>
    </dependency>
    ...
</dependencies>
```

### Gradle
```groovy
dependencies {
    ...
    compile group: "com.nsoft.chiwava", name: "${chiwava-module-name}", version: "2.0.0.RC3"
    ...
}
```

### Available modules
- chiwava-core-commons:2.0.0.RC3
- chiwava-core-error:2.0.0.RC3
- chiwava-core-persistence:2.0.0.RC3
- chiwava-debezium-commons:2.0.0.RC3
- chiwava-spring-pagination-core:2.0.0.RC3
- chiwava-spring-pagination-resolver:2.0.0.RC3

## License
This project is released under version 2.0 of the [Apache License](https://www.apache.org/licenses/LICENSE-2.0)
