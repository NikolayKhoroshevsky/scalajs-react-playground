package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object DropdownButton extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/DropdownButton", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(title: String,
            bsStyle: String | Unit = {},
            bsSize: String | Unit = {},
            noCaret: Boolean | Unit = {},
            dropup: Boolean = false,
            key: String | Unit = {},
            id: String | Unit = {}): WithProps = auto
}

object SplitButton extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/SplitButton", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(title: String,
            bsStyle: String | Unit = {},
            bsSize: String | Unit = {},
            noCaret: Boolean | Unit = {},
            dropup: Boolean = false,
            key: String | Unit = {},
            id: String | Unit = {}): WithProps = auto
}


object MenuItem extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/MenuItem", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(divider: Boolean = false,
            active: Boolean = false,
            disabled: Boolean = false,
            header: Boolean = false,
            href: js.UndefOr[String] = js.undefined,
            onClick: js.UndefOr[ReactMouseEvent => Callback] = js.undefined,
            onSelect: js.UndefOr[String => Callback] = js.undefined,
            eventKey: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}


object DropdownExample {

  val buttons = List("Default", "Primary", "Success", "Info", "Warning", "Danger")
  val component = ScalaComponent.static("DropdownExample")(
    <.div(
      <.div(^.paddingBottom := "10px",
        <.h2("Dropdown Buttons"),
        ButtonToolbar()(buttons.zipWithIndex.map { case (title, i) =>
          DropdownButton(
            bsStyle = title.toLowerCase,
            title = title,
            key = i.toString,
            id = s"dropdown-button-$title")(
            MenuItem(eventKey = "1")("Action"),
            MenuItem(eventKey = "2", active = true)("AnotherAction"),
            MenuItem(divider = true)(),
            MenuItem(eventKey = "3")("Separated Action")
          )

        }: _*)
      ),
      <.div(^.paddingBottom := "10px",
        <.h2("Split Buttons"),
        ButtonToolbar()(buttons.zipWithIndex.map { case (title, i) =>
          SplitButton(
            bsStyle = title.toLowerCase,
            title = title,
            key = i.toString,
            id = s"split-button-$title")(
            MenuItem(eventKey = "1")("Action"),
            MenuItem(eventKey = "2", active = true)("AnotherAction"),
            MenuItem(divider = true)(),
            MenuItem(eventKey = "3")("Separated Action")
          )

        }: _*)
      ),
      <.div(^.paddingBottom := "10px",
        <.h2("No Caret"),
        DropdownButton(title = "No Caret", noCaret = true, id="dropdown-nocaret")(
          MenuItem()("Action 1"),
          MenuItem()("Action 2"),
          MenuItem()("Action3 ")
        )
      )
    )
  )

  def apply() = component().vdomElement
}
