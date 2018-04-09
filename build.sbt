
enablePlugins(ScalaJSPlugin)

enablePlugins(WorkbenchPlugin)

enablePlugins(ScalaJSBundlerPlugin)

name := "scalajs-react-playground"

version := "0.1"

scalaVersion := "2.12.5"

scalaJSUseMainModuleInitializer := true

val scalaJSReactVersion = "1.2.0"
val scalaCssVersion = "0.5.5"
val reactJSVersion = "16.2.0"

libraryDependencies ++= Seq(
  "com.github.japgolly.scalajs-react" %%% "core" % scalaJSReactVersion,
  "com.github.japgolly.scalajs-react" %%% "extra" % scalaJSReactVersion,
  "com.github.japgolly.scalacss" %%% "core" % scalaCssVersion,
  "com.github.japgolly.scalacss" %%% "ext-react" % scalaCssVersion
)

npmDependencies in Compile ++= Seq(
  "react" -> reactJSVersion,
  "react-dom" -> reactJSVersion)

webpackBundlingMode := scalajsbundler.BundlingMode.LibraryOnly()

workbenchDefaultRootObject := Some(("target/scala-2.12/classes/index.html", "target/scala-2.12"))

workbenchStartMode := WorkbenchStartModes.OnCompile