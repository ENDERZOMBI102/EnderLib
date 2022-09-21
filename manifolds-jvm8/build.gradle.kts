@file:Suppress("UnstableApiUsage")

dependencies {
    implementation( rootProject.libs.annotations )

    implementation( project( ":jvm8" ) )

    // -- MANIFOLD --
    // manifold runtimes
    compileOnly( rootProject.libs.bundles.manifold.comp )
    // manifold processors
    annotationProcessor( rootProject.libs.bundles.manifold.proc )
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add( "-Xplugin:Manifold" )
}

tasks.withType<Jar> {
    manifest.attributes.put( "Contains-Sources", "java,class" )
}
