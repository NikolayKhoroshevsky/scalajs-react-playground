package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object ButtonToolbar extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/ButtonToolbar", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(): WithProps = auto
}

object Button extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Button", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(active: Boolean = false,
            disabled: Boolean = false,
            block: Boolean = false,
            onClick: (ReactMouseEvent => Callback) | Unit = {},
            href: String | Unit = {},
            `type`: String | Unit = {},
            bsStyle: String | Unit = {},
            bsSize: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object ButtonExample {

  val component = ScalaComponent.static("ButtonExample")(
    <.div(
      ButtonToolbar()(
        Button()("Default"),
        Button(bsStyle = "primary")("Primary"),
        Button(bsStyle = "success")("Success"),
        Button(bsStyle = "info")("Info"),
        Button(bsStyle = "warning")("Warning"),
        Button(bsStyle = "danger")("Danger"),
        Button(bsStyle = "link")("Link"),
        Button(disabled = true)("Disabled")
      ),
      Well()(
        Button(bsStyle = "primary", block = true)("Block level button 1"),
        Button(block = true)("Block level button 1"),

      )
    )
  )

  def apply() = component().vdomElement
}
