package com.claassen.reactPlayground

import com.claassen.reactPlayground.components.samples.{ReactCounter, ReactCounterWithDiode}
import com.claassen.reactPlayground.css.AppCSS
import com.claassen.reactPlayground.routes.AppRouter
import org.scalajs.dom.document

object App {

  def main(args: Array[String]): Unit = {
    println("loading playground")
    val root = document.getElementById("root")
    AppCSS.load
    //ReactCounterWithDiode().renderIntoDOM(root)
    AppRouter.router().renderIntoDOM(root)
  }
}
