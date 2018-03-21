# Build

This project uses Maven with Eclipse Tycho. To perform a build open a command line and simple run 

```mvn``` 

By default, this will execute the Maven goals `clean verify`.

## Build Result

Besides all modules being build the main artifact that is produced by the build is a p2 repository. This can be used by Eclipse to install the DSL feature. 

The resulting repository is located in directory `repository/target/repository` and additionally as a zip archive in directory `repository/target`.


## Build Target Platform

Target Platform definitions are located in the `tp` submodule. There are platform definitions for Eclipse Oxygen and Photon as base, so that a multi-platform build is enabled. The platform definitions are separate Maven artifacts and attached with the [`build-helper-maven-plugin`](http://www.mojohaus.org/build-helper-maven-plugin/index.html).


### Switching Target Platforms

The target platform that is used to configure the [`target-platform-configuration`](https://wiki.eclipse.org/Tycho/Target_Platform#Target_files) Maven plugin is defined by the properties

* `targetplatform.groupId`
* `targetplatform.artifactId`
* `targetplatform.version`
* `targetplatform.classifier`

This will default to the `domainmodel-oxygen.target` definition.

To use another target definition override the properties. The `domainmodel-photon.target` can be used by overriding property `targetplatform.classifier` with value `domainmodel-photon`.

### Target Platform Aggregation

Tycho based builds are computing a target platform for each build module by using the target platform configuration. This configuration usually is pointing to several p2 repositories, and Tycho fetches their content description at the beginning of a build. This process is time consuming and slows down the initialization phase of a build.

To improve the target platform computation phase it is sometimes better to aggregate all elements of a target platform to a single local repository. Using such an aggregated repository performs way better.

This project provides definitions for the [CBI Aggregator](https://wiki.eclipse.org/CBI/aggregator/manual#Functional_Overview) that materialize such a repository to directory `tp/.repository`. To do so, run 

```mvn -Ptp-aggregate package```

This has to be performed only once.

This build will automatically detect the existence of the local repository and switch a local resolution.
