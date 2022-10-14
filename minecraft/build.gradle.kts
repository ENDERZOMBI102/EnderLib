@file:Suppress("PropertyName", "UnstableApiUsage")

plugins {
	id( "org.quiltmc.loom" ) version "0.12.+"
}

version = "$version+${project.ext["main_version"]}"

sourceSets {
	create("testmod") {
		compileClasspath += sourceSets.main.get().compileClasspath
		runtimeClasspath += sourceSets.main.get().runtimeClasspath
	}
}

repositories {
	maven( url="https://jitpack.io" )
	maven( url="https://maven.terraformersmc.com" )
	maven( url="https://maven.quiltmc.org/repository/release" )
	maven( url="https://maven.quiltmc.org/repository/shanpshot" )
}

val mappings: String by project
val minecraft_version = rootProject.libs.versions.minecraft.get()
val testmodImplementation by configurations
dependencies {
	minecraft( "com.mojang:minecraft:$minecraft_version" )
	mappings( loom.layered {
		addLayer( quiltMappings.mappings( "org.quiltmc:quilt-mappings:$minecraft_version+build.$mappings:v2" ) )
	})
	modImplementation( rootProject.libs.bundles.minecraft.modImplementation )

	include( rootProject.libs.mextras )
	implementation( rootProject.libs.mextras )
	annotationProcessor( rootProject.libs.mextras )

	implementation( include( rootProject )!! )
	implementation( include( project( ":manifolds" ) )!! )

	testmodImplementation( sourceSets.main.get().output )
}

loom {
	shareRemapCaches.set(true)

	runConfigs {
		get("client").runDir = "../run"
		get("server").runDir = "../run"

		create("testmodClient") {
			client()
			runDir = "../run"
			source(sourceSets["testmod"])
		}
	}
}

tasks.withType<Jar> {
	inputs.property( "repo", project.ext["repo"] )
	inputs.property( "version", archiveVersion )
	inputs.property( "description", project.description )

	filesMatching( "*.mod.json" ) {
		expand(
			Pair( "repo", project.ext["repo"] ),
			Pair( "version", archiveVersion ),
			Pair( "description", project.description )
		)
	}
}
