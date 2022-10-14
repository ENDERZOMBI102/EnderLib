enableFeaturePreview("VERSION_CATALOGS")
pluginManagement {
	repositories {
		gradlePluginPortal()
		maven( url="https://maven.fabricmc.net" )
		maven( url="https://maven.quiltmc.org/repository/release" )
		maven( url="https://maven.quiltmc.org/repository/snapshot" )
	}
}

include( "jvm8" )
include( "minecraft", /*"minecraft-jvm8"*/ )
include( "manifolds", "manifolds-jvm8" )
