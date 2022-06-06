# EnderLib
Common code used by my JVM projects

This repo contains all the modules of EnderLib, my JVM library, all commonly used code is put here to be shared with every JVM project i make

Module map
-
| Name                                  | Artifact Coordinates                               | J8-Compatible | Description                                                         |
|---------------------------------------|----------------------------------------------------|---------------|---------------------------------------------------------------------|
| [EnderLib](src)                       | com.enderzombi102:EnderLib:$version                | no            | Common utilities                                                    |
| [EnderLib](jvm8)                      | com.enderzombi102.EnderLib:jvm8:$version           | yes           | Common utilities, for Java 8                                        |
| [EnderLib: Manifolds](manifolds)      | com.enderzombi102.EnderLib:manifolds:$version      | no            | Extension methods based off EnderLib's common utilities             |
| [EnderLib: Manifolds](manifolds-jvm8) | com.enderzombi102.EnderLib:manifolds-jvm8:$version | yes           | Extension methods based off EnderLib's common utilities, for Java 8 |
| [EnderLib: Fabric](fabric)            | com.enderzombi102.EnderLib:fabric:$version         | no            | Common Fabric utilities, ex. events                                 |
| [EnderLib: Quilt](quilt)              | com.enderzombi102.EnderLib:quilt:$version          | no            | Common Quilt utilities, ex. events                                  |
