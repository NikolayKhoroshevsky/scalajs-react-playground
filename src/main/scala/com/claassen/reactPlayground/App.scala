package com.claassen.reactPlayground

import com.claassen.reactPlayground.components.samples.{ReactCounter, ReactCounterWithDiode}
import com.claassen.reactPlayground.css.AppCSS
import com.claassen.reactPlayground.routes.AppRouter
import org.scalajs.dom.document

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.Dynamic.{global => g}

object App {
//
//  @JSImport("moment", JSImport.Namespace)
//  @js.native
//  object moment extends js.Object
//
//  @JSImport("antd", JSImport.Namespace)
//  @js.native
//  object antd extends js.Object
//
//  @JSImport("react", JSImport.Namespace)
//  @js.native
//  object react extends js.Object
//
//  @JSImport("react-motion", JSImport.Namespace)
//  @js.native
//  object reactMotion extends js.Object
//
//  @JSImport("react-big-calendar", JSImport.Namespace)
//  @js.native
//  object reactBigCalendar extends js.Object
//
//  @JSImport("react-collapse", JSImport.Namespace)
//  @js.native
//  object reactCollapse extends js.Object
//
//  @JSImport("jquery", JSImport.Namespace)
//  @js.native
//  object jquery extends js.Object

  def main(args: Array[String]): Unit = {
//    g.console.log("started")
//    g.console.log("moment:")
//    g.console.log(moment)
//    g.console.log("antd:")
//    g.console.log(antd)
//    g.console.log("react:")
//    g.console.log(react)
//    g.console.log("react-motion:")
//    g.console.log(reactMotion)
//    g.console.log("react-big-calendar:")
//    g.console.log(reactBigCalendar)
//    g.console.log("react-collapse:")
//    g.console.log(reactCollapse)
//    g.console.log("jquery:")
//    g.console.log(jquery)
    val root = document.getElementById("root")
    AppCSS.load
    AppRouter.router().renderIntoDOM(root)
  }
}
