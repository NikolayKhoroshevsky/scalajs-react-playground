package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^.{<, _}
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}

object Form extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Form", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(componentClass: String | Unit = {},
            inline: Boolean | Unit = {},
            horizontal: Boolean | Unit = {}): WithProps = auto
}


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
            multiple: Boolean | Unit = {},
            defaultValue: String | Unit = {},
            value: String | Unit = {},
            componentClass: String | Unit = {},
            placeholder: String | Unit = {},
            readOnly: Boolean | Unit = {},
            onChange: (ReactEventFromInput => Callback) | Unit = {},
            id: String | Unit = {},
            bsClass: String | Unit = {},
            bsSize: String | Unit = {}): WithProps = auto

  object Feedback extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/FormControlFeedback", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(bsClass: String | Unit = {}): WithProps = auto
  }

  object Static extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/FormControlStatic", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(bsClass: String | Unit = {},
              componentClass: String | Unit = {}): WithProps = auto
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

object Glyphicon extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Glyphicon", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(glyph: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object Checkbox extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Checkbox", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(inline: Boolean | Unit = {},
            disabled: Boolean | Unit = {},
            checked: Boolean | Unit = {},
            readOnly: Boolean | Unit = {},
            title: String | Unit = {},
            validationState: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object Radio extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Radio", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(name: String | Unit = {},
            inline: Boolean | Unit = {},
            disabled: Boolean | Unit = {},
            checked: Boolean | Unit = {},
            validationState: String | Unit = {},
            readOnly: Boolean | Unit = {}): WithProps = auto
}

object InputGroup extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/InputGroup", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(): WithProps = auto

  object Addon extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/InputGroupAddon", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(): WithProps = auto
  }

  object Button extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/InputGroupButton", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(): WithProps = auto
  }

}


object FormsExample {

  case class Props(controlledValue: String)

  class Backend($: BackendScope[Unit, String]) {

    def render(S: String) = {
      <.div(
        SimpleForm(),
        ControlledForm(
          ControlledForm.Props(S, s => $.setState(s))
        ),
        SupportedControls(),
        InlineForms(),
        HorizontalForms(),
        InputGroups(),
        ValidationStates()
      )
    }
  }

  val component = ScalaComponent.builder[Unit]("FormsExample")
    .initialState("")
    .renderBackend[Backend]
    .build

  def apply() = component().vdomElement
}

object SimpleForm {

  class Backend($: BackendScope[String, String]) {

    def handleChange(e: ReactEventFromInput) = $.setState(e.target.value)

    def render(p: String, s: String) = {

      def getValidationState(): String | Unit = {
        if (s.length > 10) "success"
        else if (s.length > 5) "warning"
        else if (s.length > 0) "error"
        else ()
      }

      <.div(^.paddingBottom := "10px",
        <.h2("Simple Form with Validation"),
        Panel()(
          Panel.Body()(
            <.form(
              FormGroup(controlId = "formBasicText", validationState = getValidationState())(
                ControlLabel()("Working example with validation"),
                FormControl(`type` = "text",
                  value = s,
                  placeholder = "Enter Text",
                  onChange = handleChange _)(),
                FormControl.Feedback()(),
                HelpBlock()("Validation is based on string length")
              )
            )
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

object ControlledForm {

  case class Props(value: String, onChange: String => Callback)

  class Backend($: BackendScope[Props, Unit]) {

    private val target = Ref[dom.html.Element]
    private val dataSource = Ref[dom.html.Input]

    def handleChange(e: ReactEventFromInput) = Callback {
      g.console.log(s"onChange: ${e.target.value}")
    }

    def updateInput: Callback = for {
      input <- dataSource.get
      props <- $.props
      _ <- props.onChange(input.value)
    } yield ()

    def clearInput(e: ReactMouseEvent): Callback = for {
      props <- $.props
      _ <- props.onChange("")
    } yield ()

    def render(p: Props) = {
      val value = p.value

      <.div(^.paddingBottom := "10px",
        <.h2("Controlled Form"),
        Panel()(
          Panel.Body()(
            FormGroup()(
              <.div(
                InputGroup()(
                  ^.className := "tp-input",
                  FormControl(`type` = "text", placeholder = "Select time", value = value, onChange = handleChange _)(
                    ^.onClick ==> clearInput
                  ),
                  InputGroup.Addon()(Glyphicon(glyph = "time")())
                )
              ).withRef(target)
            ),
            <.input(^.`type` := "text", ^.id := "data-source").withRef(dataSource),
            <.input(^.`type` := "button", ^.onClick --> updateInput, ^.value := "Update")
          )
        )
      )

    }
  }

  val component = ScalaComponent.builder[Props]("ControlledForm")
    .renderBackend[Backend]
    .build

  def apply(props: Props) = component(props).vdomElement
}

object FieldGroup {

  case class Props(id: String | Unit = {},
                   label: String = "",
                   help: Option[String] = None,
                   `type`: String | Unit = {},
                   placeholder: String | Unit = {})

  val component = ScalaComponent.builder[Props]("FieldGroup")
    .render_P(P =>
      FormGroup(controlId = P.id)(
        ControlLabel()(P.label),
        FormControl(`type` = P.`type`, placeholder = P.placeholder),
        P.help.map { help => HelpBlock()(help) }.getOrElse(TagMod.empty)
      )
    ).build

  def apply(id: String | Unit = {},
            label: String = "",
            help: Option[String] = None,
            `type`: String | Unit = {},
            placeholder: String | Unit = {}) = component(Props(id, label, help, `type`, placeholder)).vdomElement
}

object SupportedControls {

  val component = ScalaComponent.static("SupportedControls")(
    <.div(^.paddingBottom := "10px",
      <.h2("Supported Controls"),
      Panel()(
        Panel.Body()(
          <.form(
            FieldGroup(id = "formControlsText", `type` = "text", label = "Text", placeholder = "Enter text")(),
            FieldGroup(id = "formControlsEmail", `type` = "email", label = "Email address", placeholder = "Enter email")(),
            FieldGroup(id = "formControlsPassword", `type` = "password", label = "Password")(),
            FieldGroup(id = "formControlsFile", `type` = "file", label = "File", help = Some("Example block-level help text here."))(),
            Checkbox(checked = true, readOnly = true)(
              "Checkbox"
            ),
            Radio(checked = true, readOnly = true)(
              "Radio"
            ),
            FormGroup()(
              Checkbox(inline = true)("1"), Checkbox(inline = true)("2"), Checkbox(inline = true)("3")
            ),
            FormGroup()(
              Radio(name = "radioGroup", inline = true)("1"),
              Radio(name = "radioGroup", inline = true)("2"),
              Radio(name = "radioGroup", inline = true)("3")
            ),
            FormGroup(controlId = "formControlsSelect")(
              ControlLabel()("Select"),
              FormControl(componentClass = "select", placeholder = "select")(
                <.option(^.value := "select", "select"),
                <.option(^.value := "other", "...")
              )
            ),
            FormGroup(controlId = "formControlsSelectMultiple")(
              ControlLabel()("Multiple select"),
              FormControl(componentClass = "select", multiple = true)(
                <.option(^.value := "select", "select"),
                <.option(^.value := "other", "...")
              )
            ),
            FormGroup(controlId = "formControlsTextarea")(
              ControlLabel()("Textarea"),
              FormControl(componentClass = "textarea", placeholder = "textarea")
            ),
            FormGroup()(
              ControlLabel()("Static text"),
              FormControl.Static()("email@example.com")
            ),
            Button(`type` = "submit")("Submit")
          )
        )
      )
    )
  )

  def apply() = component().vdomElement

}

object InlineForms {

  val component = ScalaComponent.static("InlineForms")(
    <.div(^.paddingBottom := "10px",
      <.h2("Inline forms"),
      Panel()(
        Panel.Body()(
          Form(inline = true)(
            FormGroup(controlId = "formInlineName")(
              ControlLabel()("Name"),
              " ",
              FormControl(`type` = "text", placeholder = "Jane Doe")()
            ),
            FormGroup(controlId = "formInlineEmail")(
              ControlLabel()("Email"),
              " ",
              FormControl(`type` = "email", placeholder = "jane.doe@example.com")()
            ),
            Button(`type` = "submit")("Send invitation")
          )
        )
      )
    )
  )

  def apply() = component().vdomElement

}

object HorizontalForms {

  val component = ScalaComponent.static("HorizontalForms")(
    <.div(^.paddingBottom := "10px",
      <.h2("Horizontal Forms"),
      Panel()(
        Panel.Body()(
          Form(horizontal = true)(
            FormGroup(controlId = "formHorizontalEmail")(
              Col(componentClass = "label", sm = 2)("Email"), // ?? componentClass="ControlLabel" ??
              Col(sm = 10)(
                FormControl(`type` = "email", placeholder = "Email")()
              )
            ),
            FormGroup(controlId = "formHorizontalPassword")(
              Col(componentClass = "label", sm = 2)("Password"), // ?? componentClass="ControlLabel" ??
              Col(sm = 10)(
                FormControl(`type` = "password", placeholder = "Passwors")()
              )
            ),
            FormGroup()(
              Col(smOffset = 2, sm = 10)(
                Checkbox()("Remember me")
              )
            ),
            FormGroup()(
              Col(smOffset = 2, sm = 10)(
                Button(`type` = "submit")("Sign in")
              )
            )
          )
        )
      )
    )
  )


  def apply() = component().vdomElement

}

object InputGroups {

  val component = ScalaComponent.static("InputGroups")(
    <.div(^.paddingBottom := "10px",
      <.h2("Input Groups"),
      Panel()(
        Panel.Body()(
          <.form()(
            FormGroup()(
              InputGroup()(
                InputGroup.Addon()("@"),
                FormControl(`type` = "text")()
              )
            ),
            FormGroup()(
              InputGroup()(
                FormControl(`type` = "text")(),
                InputGroup.Addon()(".00")
              )
            ),
            FormGroup()(
              InputGroup()(
                InputGroup.Addon()("$"),
                FormControl(`type` = "text")(),
                InputGroup.Addon()(".00")
              )
            ),
            FormGroup()(
              InputGroup()(
                FormControl(`type` = "text")(),
                InputGroup.Addon()(
                  Glyphicon(glyph = "music")())
              )
            ),
            FormGroup()(
              InputGroup()(
                InputGroup.Button()(
                  Button()("Before")
                ),
                FormControl(`type` = "text")(),
              )
            ),
            FormGroup()(
              InputGroup()(
                FormControl(`type` = "text")(),
                InputGroup.Button()(
                  DropdownButton(id = "input-dropdown-addon", title = "Action")(
                    MenuItem(eventKey = "1")("Item")
                  )
                )
              )
            ),
            FormGroup()(
              InputGroup()(
                InputGroup.Addon()(
                  <.input(^.`type` := "radio")
                ),
                FormControl(`type` = "text")()
              )
            ),
            FormGroup()(
              InputGroup()(
                InputGroup.Addon()(
                  <.input(^.`type` := "checkbox")
                ),
                FormControl(`type` = "text")()
              )
            )
          )
        )
      )
    )
  )

  def apply() = component().vdomElement

}

object ValidationStates {

  val component = ScalaComponent.static("ValidationStates")(
    <.div(^.paddingBottom := "10px",
      <.h2("Validation States"),
      Panel()(
        Panel.Body()(
          <.form()(
            FormGroup(controlId = "formValidationSuccess1", validationState = "success")(
              ControlLabel()("Input with success"),
              FormControl(`type` = "text")(),
              HelpBlock()("Help text with validation state")
            ),

            FormGroup(controlId = "formValidationWarning1", validationState = "warning")(
              ControlLabel()("Input with warning"),
              FormControl(`type` = "text")(),
            ),

            FormGroup(controlId = "formValidationError1", validationState = "error")(
              ControlLabel()("Input with error"),
              FormControl(`type` = "text")(),
            ),

            FormGroup(controlId = "formValidationNull", validationState = {})(
              ControlLabel()("Input with no validation state"),
              FormControl(`type` = "text")(),
            ),

            FormGroup(controlId = "formValidationSuccess2", validationState = "success")(
              ControlLabel()("Input with success and feedback icon"),
              FormControl(`type` = "text")(),
              FormControl.Feedback()()
            ),

            FormGroup(controlId = "formValidationWarning2", validationState = "warning")(
              ControlLabel()("Input with warning and feedback icon"),
              FormControl(`type` = "text")(),
              FormControl.Feedback()()
            ),

            FormGroup(controlId = "formValidationError2", validationState = "error")(
              ControlLabel()("Input with error and feedback icon"),
              FormControl(`type` = "text")(),
              FormControl.Feedback()()
            ),

            FormGroup(controlId = "formValidationSuccess3", validationState = "success")(
              ControlLabel()("Input with success and custom feedback icon"),
              FormControl(`type` = "text")(),
              FormControl.Feedback()(
                Glyphicon(glyph = "music")()
              )
            ),

            FormGroup(controlId = "formValidationWarning3", validationState = "warning")(
              ControlLabel()("Input group with warning"),
              InputGroup()(
                InputGroup.Addon()("@"),
                FormControl(`type` = "text")(),
              ),
              FormControl.Feedback()()
            ),

            Form(componentClass = "fieldset", horizontal = true)(
              FormGroup(controlId = "formValidationError3", validationState = "error")(
                Col(xs = 3)(
                  ControlLabel()("Input with error"),
                ),
                Col(xs = 9)(
                  FormControl(`type` = "text")(),
                  FormControl.Feedback()()
                )
              ),

              FormGroup(controlId = "formValidationSuccess4", validationState = "success")(
                Col(xs = 3)(
                  ControlLabel()("Input group with success")
                ),
                Col(xs = 9)(
                  InputGroup()(
                    InputGroup.Addon()("@"),
                    FormControl(`type` = "text")(),
                  ),
                  FormControl.Feedback()()
                )
              ),
            ),

            Form(componentClass = "fieldset", inline = true)(
              FormGroup(controlId = "formValidationWarning4", validationState = "warning")(
                ControlLabel()("Input with warning"),
                " ",
                FormControl(`type` = "text")(),
                FormControl.Feedback()()
              ),
              " ",
              FormGroup(controlId = "formValidationError4", validationState = "error")(
                ControlLabel()("Input group with error"),
                " ",
                InputGroup()(
                  InputGroup.Addon()("@"),
                  FormControl(`type` = "text")(),
                ),
                FormControl.Feedback()()
              )
            ),
            Checkbox(validationState = "success")("Checkbox with success"),
            Radio(validationState = "warning")("Radio with warning"),
            Checkbox(validationState = "error")("Checkbox with error"),

            FormGroup(validationState = "success")(
              Checkbox(inline = true)("Checkbox"), Checkbox(inline = true)("with"), " ", Checkbox(inline = true)("success")
            )
          )
        )
      )
    )
  )

  def apply() = component().vdomElement
}