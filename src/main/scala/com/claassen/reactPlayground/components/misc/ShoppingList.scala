package com.claassen.reactPlayground.components.misc

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object ShoppingList {

  val component = ScalaComponent.builder[String]("ShoppingList")
    .render( $ =>
      <.div(^.className := "shopping-list",
        <.h1(s"Shopping List for ${$.props}"),
        <.ul(
          <.li("Instagram"),
          <.li("WhatsApp"),
          <.li("Oculus")
        )
      )
    ).build

  def apply() = component("Mark").vdomElement
}
