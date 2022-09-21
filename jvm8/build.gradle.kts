@file:Suppress("UnstableApiUsage")

dependencies {
	implementation( rootProject.libs.annotations )
	compileOnly( rootProject.libs.slf4j )
}

tasks.withType<Javadoc> {
	options.jFlags( "--add-exports", "java.management/sun.management=ALL-UNNAMED" )

	isFailOnError = false
}
