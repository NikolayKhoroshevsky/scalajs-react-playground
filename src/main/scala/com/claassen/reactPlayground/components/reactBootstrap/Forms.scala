package com.claassen.reactPlayground.components.reactBootstrap

import com.claassen.reactPlayground.components.reactBootstrap
import com.claassen.reactPlayground.components.reactBootstrap.ControlLabel.auto
import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object FormGroup extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/FormGroup", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(controlId: String | Unit = {},
            validationState: String | Unit = {},
            bsClass: String | Unit = {},
            bsSize: String | Unit = {}): WithProps = auto
}

object FormControl extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/FormControl", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(`type`: String | Unit = {},
            value: String | Unit = {},
            placeholder:String | Unit = {},
            onChange: (ReactEventFromInput => Callback) | Unit = {},
            id: String | Unit = {},
            bsClass: String | Unit = {},
            bsSize: String | Unit = {}): WithProps = auto

  object Feedback extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/FormControlFeedback", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(): WithProps = auto
  }
}

object ControlLabel extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/ControlLabel", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(htmlFor: String | Unit = {},
            srOnly: Boolean = false,
            bsClass: String | Unit = {}): WithProps = auto
}
object HelpBlock extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/HelpBlock", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(): WithProps = auto
}


object FormsExample {

  val component = ScalaComponent.static("FormsExample")(
    SimpleForm()
  )

  def apply() = component().vdomElement
}

object SimpleForm {

  class Backend($: BackendScope[String, String]) {

    def handleChange(e: ReactEventFromInput) = $.setState(e.target.value)

    def render(p: String, s: String) = {

      def getValidationState(): String | Unit = {
        if(s.length > 10) "success"
        else if(s.length > 5) "warning"
        else if(s.length > 0) "error"
        else ()
      }

      <.div(^.paddingBottom := "10px",
        <.h2("Simple Form with Validation"),
        <.form(
          FormGroup(controlId = "formBasicText", validationState = getValidationState())(
            ControlLabel()("Working example with validation"),
            FormControl(`type`="text",
              value=s,
              placeholder = "Enter Text",
              onChange = handleChange _)(),
            FormControl.Feedback()(),
            HelpBlock()("Validation is based on string length")
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[String]("SimpleForm")
    .initialStateFromProps(x => x)
    .renderBackend[Backend]
    .build

  def apply() = component("").vdomElement
}