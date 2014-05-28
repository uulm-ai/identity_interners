import de.johoop.jacoco4sbt._
import JacocoPlugin._

name := "identity_interners"

organization := "de.uni-ulm"

version := "1.0"

scalaVersion := "2.11.0"

libraryDependencies += "com.google.guava" % "guava" % "16.0.1"

libraryDependencies += "com.google.code.findbugs" % "jsr305" % "1.3.9"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.1.7" % "test"

jacoco.settings
