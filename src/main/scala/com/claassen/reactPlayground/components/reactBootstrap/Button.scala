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

  def apply(bsClass: String | Unit = {}): WithProps = auto
}

object ButtonGroup extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/ButtonGroup", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(vertical: Boolean = false,
            justified: Boolean = false,
            block: Boolean = false,
            bsClass: String | Unit = {}): WithProps = auto
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

object ToggleButtonGroup extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/ToggleButtonGroup", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(name: String | Unit = {},
            defaultValue: js.Any = js.undefined,
            onChange: (js.Any => Callback) | Unit = {},
            `type`: String = "radio"): WithProps = auto
}

object ToggleButton extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/ToggleButton", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(value: js.Any,
            name: String | Unit = {},
            checked: Boolean = false,
            disabled: Boolean = false,
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
      <.h3("Buttons"),
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
      <.h3("Block Buttons"),
      Well()(
        Button(bsStyle = "primary", block = true)("Block level button 1"),
        Button(block = true)("Block level button 1"),
      ),
      <.h3("Button Group"),
      ButtonGroup()(
        Button()("Left"),
        Button()("Middle"),
        Button()("Right")
      ),
      <.h3("Vertical Button Group"),
      ButtonGroup(vertical = true)(
        Button()("Top"),
        Button()("Middle"),
        Button()("Bottom")
      ),
      <.h3("Checkbox Toggle Buttons"),
      ButtonToolbar()(
        ToggleButtonGroup(`type` = "checkbox", defaultValue = List(1, 3))(
          ToggleButton(value = 1)("Checkbox 1"),
          ToggleButton(value = 2)("Checkbox 2"),
          ToggleButton(value = 3)("Checkbox 3"),
        )
      ),
      <.h3("Radio Toggle Buttons"),
      ButtonToolbar()(
        ToggleButtonGroup(`type` = "radio", defaultValue = 2, name="options")(
          ToggleButton(value = 1)("Radio 1"),
          ToggleButton(value = 2)("Radio 2"),
          ToggleButton(value = 3)("Radio 3"),
        )
      ),
    )
  )

  def apply() = component().vdomElement
}
