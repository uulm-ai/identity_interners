name := "identity_interners"

organization := "de.uni-ulm"

version := "1.2"

scalaVersion := "2.11.7"

// todo: uses modified Guava code -> check Licence!!

libraryDependencies += "com.google.guava" % "guava" % "18.0"

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "1.3.9"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.7" % "test"
