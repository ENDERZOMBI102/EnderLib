@file:Suppress("UnstableApiUsage", "LocalVariableName", "PropertyName", "DEPRECATION")
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

plugins {
	`maven-publish`
	java
	idea
}

allprojects {
	apply( plugin = "maven-publish" )
	apply( plugin = "java" )
	apply( plugin = "idea" )

	repositories {
		mavenCentral()
		maven( url="https://jitpack.io" )
	}

	// take the version referred by this project from the root one
	version = rootProject.property( property("version_prop") as String )!!
	description = property("description") as String

	tasks.withType<JavaCompile> {
		sourceCompatibility = if ( project.name.endsWith("jvm8") ) "8" else "17"
		targetCompatibility = if ( project.name.endsWith("jvm8") ) "8" else "17"
		options.encoding = "UTF-8"
	}

	tasks.withType<Jar> {
		from( "LICENSE" ) {
			rename { "${it}_${archiveBaseName.get()}" }
		}
		filteringCharset = "UTF-8"

		manifest.attributes.putAll(
			mapOf(
				Pair( "Implementation-Title"    , project.property("title")),
				Pair( "Implementation-Version"  , archiveVersion ),
				Pair( "Implementation-Vendor"   , "Aurora Inhabitants" ),
				Pair( "Implementation-Timestamp", LocalDateTime.now().format( DateTimeFormatter.ofPattern("dd-MM-yyyy'T'HH:mm:ss") ) ),
				Pair( "Implementation-Description", project.description )
			)
		)
	}

	java {
		withJavadocJar()
		withSourcesJar()
	}

	// configure the maven publication
	publishing {
		publications {
			create<MavenPublication>("mavenJava") {
				group = "com.enderzombi102${ if ( project == rootProject ) "" else ".EnderLib" }"
				from( components["java"] )
			}
		}

		repositories {
			maven {
				name = "Repsy"
				credentials(PasswordCredentials::class)
				url = uri("https://repsy.io/mvn/enderzombi102/mc")
			}
		}
	}
}

dependencies {
	implementation( libs.slf4j )
	implementation( libs.annotations )
}
