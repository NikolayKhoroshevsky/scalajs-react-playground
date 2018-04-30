package com.claassen.reactPlayground.components.reactBootstrap

import com.claassen.reactPlayground.components.reactBootstrap.Panel.Collapse.auto
import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Panel extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Panel", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(defaultExpanded: Boolean | Unit = {},
            expanded: Boolean | Unit = {},
            eventKey: String | Unit = {},
            id: String | Unit = {},
            bsStyle: String | Unit = {}): WithProps = auto

  object Body extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelBody", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(collapsible: Boolean = false,
              bsClass: String | Unit = {}): WithProps = auto
  }

  object Heading extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelHeading", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(componentClass: String | Unit = {},
              bsClass: String | Unit = {}): WithProps = auto
  }

  object Footer extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelFooter", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(bsClass: String | Unit = {}): WithProps = auto
  }

  object Title extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelTitle", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(componentClass: String | Unit = {},
              bsClass: String | Unit = {},
              toggle: Boolean = false): WithProps = auto
  }

  object Toggle extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelToggle", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(componentClass: String | Unit = {}): WithProps = auto
  }

  object Collapse extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PanelCollapse", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(bsClass: String | Unit = {}): WithProps = auto
  }

}

object PanelGroup extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/PanelGroup", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(accordion: Boolean | Unit = {},
            defaultActiveKey: String | Unit = {},
            activeKey: String | Unit = {},
            onSelect: (String => Callback) | Unit = {},
            role: String | Unit = {},
            id: String | Unit = {}): WithProps = auto
}


object PanelExample {

  val component = ScalaComponent.static("DropdownExample")(
    <.div(
      <.h2("Basic panel"),
      Panel()(
        Panel.Body()("Basic panel example")
      ),
      <.h2("Basic panel with header and Footer"),
      Panel()(
        Panel.Heading()(
          Panel.Title(componentClass = "h3")("Panel heading with H3 title")
        ),
        Panel.Body()("Panel content"),
        Panel.Footer()("Panel Footer")
      ),
      <.h2("Panels with contextual styling"),
      Panel(bsStyle = "primary")(
        Panel.Heading()(
          Panel.Title(componentClass = "h3")("Panel heading")
        ),
        Panel.Body()("Panel content"),
      ),
      Panel(bsStyle = "info")(
        Panel.Heading()(
          Panel.Title(componentClass = "h3")("Panel heading")
        ),
        Panel.Body()("Panel content"),
      ),
      Panel(bsStyle = "danger")(
        Panel.Heading()(
          Panel.Title(componentClass = "h3")("Panel heading")
        ),
        Panel.Body()("Panel content"),
      ),
      <.h2("Collapsible Panel"),
      Panel(id = "collapsible-panel-example-2", defaultExpanded = true)(
        Panel.Heading()(
          Panel.Title(toggle = true)(
            "Title that functions as a collapse toggle"
          )
        ),
        Panel.Collapse()(
          Panel.Body()(
            """Anim pariatur cliche reprehenderit, enim eiusmod high life
            accusamus terry richardson ad squid. Nihil anim keffiyeh
            helvetica, craft beer labore wes anderson cred nesciunt sapiente
            ea proident."""
          )
        )
      ),
      <.p("or use a manual toggle"),
      Panel(id = "collapsible-panel-example-3", defaultExpanded = true)(
        Panel.Heading()(
          Panel.Title()(
            "Title that functions as a collapse toggle"
          ),
          Panel.Toggle(componentClass = "a")("My own toggle")
        ),
        Panel.Collapse()(
          Panel.Body()(
            """Anim pariatur cliche reprehenderit, enim eiusmod high life
            accusamus terry richardson ad squid. Nihil anim keffiyeh
            helvetica, craft beer labore wes anderson cred nesciunt sapiente
            ea proident."""
          )
        )
      ),
      <.h2("Panel groups"),
      PanelGroup(id="uncontrolled-example", defaultActiveKey="2")(
        Panel(eventKey="1")(
          Panel.Heading()(
            Panel.Title(toggle=true)("Panel heading 1")
          ),
          Panel.Body( collapsible=true)("Panel content 1")
        ),
        Panel(eventKey="2")(
          Panel.Heading()(
            Panel.Title(toggle=true)("Panel heading 2")
          ),
          Panel.Body( collapsible=true)("Panel content 2")
        )
      ),
      <.h2("Accordions"),
      PanelGroup(accordion=true, id="accordion-uncontrolled-example", defaultActiveKey="2")(
        Panel(eventKey="1")(
          Panel.Heading()(
            Panel.Title(toggle=true)("Panel heading 1")
          ),
          Panel.Body( collapsible=true)("Panel content 1")
        ),
        Panel(eventKey="2")(
          Panel.Heading()(
            Panel.Title(toggle=true)("Panel heading 2")
          ),
          Panel.Body( collapsible=true)("Panel content 2")
        )
      ),

    )
  )

  def apply() = component().vdomElement
}