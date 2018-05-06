package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Label extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Label", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(bsStyle: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object LabelExample {

  val component = ScalaComponent.static("WellExample")(
    <.div(
      <.div(^.paddingBottom := "10px",
        <.h2("Labels"),
        Panel()(
          Panel.Body()(
            <.h1("Label", Label()("New")),
            <.h2("Label", Label()("New")),
            <.h3("Label", Label()("New")),
            <.h4("Label", Label()("New")),
          )
        )
      ),
      <.div(^.paddingBottom := "10px",
        <.h2("Labels"),
        Panel()(
          Panel.Body()(
            <.div(
              Label(bsStyle = "default")("Default"), " ",
              Label(bsStyle = "primary")("Primary"), " ",
              Label(bsStyle = "success")("Success"), " ",
              Label(bsStyle = "info")("Info"), " ",
              Label(bsStyle = "warning")("Warning"), " ",
              Label(bsStyle = "danger")("Danger"), " ",
            )
          )
        )
      )
    )
  )

  def apply() = component().vdomElement
}
