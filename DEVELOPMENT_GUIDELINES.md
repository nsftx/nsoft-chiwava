# Table of contents

* [Overview](#overview)
* [Project structure](#project-structure)
  * [Module naming](#module-naming)
  * [Module versioning](#module-versioning)
  * [Open-sourcing](#open-sourcing)
* [Writing utility classes](#writing-utility-classes)
* [Library documentation](#library-documentation)    

## Overview

_CWS CHIWAVA_ is a multipurpose Java library designed to aid in development of the CWS ecosystem. The motivation behind the development of such component is to avoid repetitiveness and redundancy throughout our codebase, as well as speed up the development process of newly created components.

_CWS CHIWAVA_ is designed with CWS microservices in mind, but its usage is not limited to said microservices.

## Project structure

Since _CWS CHIWAVA_ covers a wide spectrum of functionality, the library should be split up into multiple modules, allowing end-users to only use the parts of the library that are necessary for their project.

Said modules should be uploaded to _Nexus_ or any other artifact management system.

Optionally, a convenience module should exist which would include all other project modules.

### Module naming

Module naming should follow the gradle `group:artifact:version` format, where the group is `com.nsoft.cws.chiwava`, the artifact is `chiwava-[MODULE_NAME]`, and the version is described in the SemVer format described below.

### Module versioning

Module versioning should be done as described in the [Semantic Versioning](https://semver.org/) system.

### Open-sourcing

One of the goals this library aims to achieve is to be available for general use. With this in mind, modules should be written in such a way that they are usable outside of the CWS ecosystem.

## Implementing features

Every feature implementation must be a part of a release milestone. For every milestone, a set of issues is opened in order to track the development progress.

Milestones should follow SemVer mentioned above.

Every release milestone must have a dedicated branch with a 'release/' prefix, eg. 'release/0.1.0'

Feature development must always follow the same branching order: 'feature/id/name' -> 'release/milestone' -> 'master'. 

Every feature must be fully developed before being merged into the release branch. Every release branch must be fully developed before being merged into master.

Before merging into master, the last release commit must be tagged with the milestone version.

## Writing utility classes

Every utility class **must _cover one and only one topic_**. [This](https://github.com/nsftx/cws-storage-static-files/blob/master/src/application/src/main/java/com/nsoft/cws/storage/esef/application/common/util/RideUtil.java) is a bad example of a utility class - it covers multiple topics (JSON (de)serialization, ID generation, Date formatting).

The linked class is also a bad example of utility class naming, as we don't learn much about the functionality of the class from the name alone. If, for instance, a class deals with String manipulation, a good practice would be to name the class `Strings.java`, `StringUtil.java`, `StringUtils.java` or even `StringUtilities.java`.

Utility classes **must _not hold any state_**, meaning they should be written statically.

Utility classes **must _be final_ and _non constructable_**, meaning they cannot be extended upon nor have instances.

Utility classes and their methods **should _be documented using Javadoc_**.

```java
/**
 * Provides utility methods for working with ids.
 *
 * @author mislav
 * @since 1.0
 */
public final class Ids {
    
    private Ids() {
    }

     /**
     * Generates a UUID in {@link String} form
     *
     * @return the newly generated id
     */
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
```

## Library documentation

Javadoc is an easy and convenient way of documenting Java code and getting immediate results in a form of generated documentation pages. But Javadoc has a downside - its generated pages are ugly to say the least.

[Orchid](https://github.com/JavaEden/Orchid) solves this issue - it's customizable, it's extendable and most importantly, it's easy to use.

Orchid provides us with a plugin that automatically generates documentation from our Javadocs. Unlike plain old Javadocs, Orchid's generated pages are highly customizable and can be changed to our needs.
