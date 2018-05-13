
enablePlugins(ScalaJSPlugin)

enablePlugins(WorkbenchPlugin)

enablePlugins(ScalaJSBundlerPlugin)

name := "scalajs-react-playground"

version := "0.1"

scalaVersion := "2.12.5"

scalaJSUseMainModuleInitializer := true

scalacOptions += "-P:scalajs:sjsDefinedByDefault"

emitSourceMaps := true

val scalaJSReactVersion = "1.2.0"
val scalaCssVersion = "0.5.5"
val reactJSVersion = "16.2.0"
val diodeVersion = "1.1.3"

libraryDependencies ++= Seq(
  "com.github.japgolly.scalajs-react" %%% "core"                  % scalaJSReactVersion,
  "com.github.japgolly.scalajs-react" %%% "extra"                 % scalaJSReactVersion,
  "com.github.japgolly.scalacss"      %%% "core"                  % scalaCssVersion,
  "com.github.japgolly.scalacss"      %%% "ext-react"             % scalaCssVersion,
  "io.suzaku"                         %%% "diode"                 % diodeVersion,
  "io.suzaku"                         %%% "diode-devtools"        % diodeVersion,
  "io.suzaku"                         %%% "diode-react"           % (diodeVersion + ".120"),
  "org.querki"                        %%% "jquery-facade"         % "1.2",
  "com.payalabs"                      %%% "scalajs-react-bridge"  % "0.6.0",
  "ru.pavkin"                         %%% "scala-js-momentjs"     % "0.9.1"
)

npmDependencies in Compile ++= Seq(
  "bootstrap"           -> "3.3.7",
  "react"               -> reactJSVersion,
  "react-dom"           -> reactJSVersion,
  "jquery"              -> "3.2.1",
  "moment"              -> "2.19.2",
  "moment-timezone"     -> "0.5.14",
  "react-bootstrap"     -> "0.32.1",
  "react-big-calendar"  -> "0.19.0",
  "react-motion"        -> "0.5.2",
  "react-collapse"      -> "4.0.3",
  "antd"                -> "3.4.1"
)

webpackBundlingMode := scalajsbundler.BundlingMode.LibraryOnly()

webpackConfigFile := Some(baseDirectory.value / "webpack.config.js")

workbenchDefaultRootObject := Some(("target/scala-2.12/classes/index.html", "target/scala-2.12"))

workbenchStartMode := WorkbenchStartModes.OnCompile