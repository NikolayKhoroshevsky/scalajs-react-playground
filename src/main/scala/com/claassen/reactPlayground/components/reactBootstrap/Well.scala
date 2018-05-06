package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Well extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Well", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(bsSize: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object WellExample {

  val component = ScalaComponent.static("WellExample")(
    <.div(
      <.div(^.paddingBottom := "10px",
        <.h2("Wells"),
        Panel()(
          Panel.Body()(
            Well(bsSize = "large")("Look, I'm a large well!"),
            Well(bsSize = "small")("Look, I'm a small well!")
          )
        )
      )
    )
  )

  def apply() = component().vdomElement
}
