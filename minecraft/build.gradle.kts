@file:Suppress("PropertyName", "UnstableApiUsage", "DEPRECATION", "LocalVariableName")
plugins {
	id( "org.quiltmc.loom" ) version "1.0.+"
}

version = buildString {
	val parts = ( version as String ).split("-")
	append( parts[0] )
	append( "+" )
	val main_version: String by project.ext
	append( main_version.removeSuffix("-SNAPSHOT") )
	if ( parts.size == 2 )
		append( "-SNAPSHOT" )
}

sourceSets {
	create("testmod") {
		compileClasspath += sourceSets.main.get().compileClasspath
		runtimeClasspath += sourceSets.main.get().runtimeClasspath
	}
}

repositories {
	maven( url="https://maven.terraformersmc.com" )
	maven( url="https://maven.quiltmc.org/repository/release" )
	maven( url="https://maven.quiltmc.org/repository/shanpshot" )
}

val mappings: String by project
val minecraft_version: String by project
val testmodImplementation by configurations
dependencies {
	minecraft( "com.mojang:minecraft:$minecraft_version" )
	mappings( "org.quiltmc:quilt-mappings:$minecraft_version+build.$mappings:intermediary-v2" )
	modImplementation( rootProject.libs.bundles.minecraft.modImplementation )

	include( rootProject.libs.mextras )
	implementation( rootProject.libs.mextras )
	annotationProcessor( rootProject.libs.mextras )

	implementation( include( rootProject )!! )
//	implementation( include( project( ":manifolds" ) )!! )

	testmodImplementation( sourceSets.main.get().output )
}

loom.runConfigs {
	get("client").runDir = "../run"
	get("server").runDir = "../run"

	create("testmodClient") {
		client()
		runDir = "../run"
		source(sourceSets["testmod"])
	}
	create("testmodServer") {
		server()
		runDir = "../run"
		source(sourceSets["testmod"])
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
