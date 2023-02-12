# Open-core with optional enterprise sources Gradle build

This build demonstrates how to have a project that has an open-core (public sources) and optional enterprise extensions (private sources).

The key is that during Gradle configuration time we look up `enterprise-extensions` directory on disk
and include its contents into the main source set, but only if it exists.
If it does exist, then not only will it participate in the build executed via command line, 
but also an IDE would pick it up and allow to use all IDE features transparently between open-source and enterprise sources.

The functionality of enterprise services is provided via the `ServiceLoader` mechanism,
meaning that is enough for the services to be present on runtime classpath.
However, including enterprise extensions as sources instead of dependencies allow to avoid the need of publishing the artifacts of the open-core part.

One of the pitfalls in this setup is the ability of open-source sources depend on enterprise sources.
In this case the project will successfully build when enterprise sources are present, but the build will fail when they are not provided.

For demonstration purposes in this project the enterprise sources are in the `enterprise-extensions-not-here` directory,
which means they will not participate in the build initially.
If we run the application now, only the open source services will be loaded:

```
$ ./gradlew :app:run                                     

> Task :app:run
Loaded 1 services
- Open Source service
```

However, if we rename the directory:

```
$ mv enterprise-extensions-not-here enterprise-extensions
```

and reload Gradle in the IDE or rerun the same build on the command line, we'll see that enterprise services have been picked up.

```
$ ./gradlew :app:run 

> Task :app:run
Loaded 3 services
- Open Source service
- Enterprise Service 1
- Enterprise Service 2
```