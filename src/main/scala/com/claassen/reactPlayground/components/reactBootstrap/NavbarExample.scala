package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}

object Navbar extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Navbar", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(): WithProps = auto


  object Header extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/NavbarHeader", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(): WithProps = auto
  }

  object Brand extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/NavbarBrand", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(): WithProps = auto
  }

}


object Nav extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Nav", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(): WithProps = auto
}

object NavItem extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/NavItem", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(active: Boolean = false,
            disabled: Boolean = false,
            href: js.UndefOr[String] = js.undefined,
            onClick: js.UndefOr[ReactMouseEvent => Callback] = js.undefined,
            onSelect: js.UndefOr[String => Callback] = js.undefined,
            eventKey: String | Unit = {}): WithProps = auto
}

object NavDropdown extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/NavDropdown", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(id: String,
            title: String,
            eventKey: String | Unit = {}): WithProps = auto
}

object MenuItem extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/MenuItem", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(divider: Boolean = false,
            active: Boolean = false,
            disabled: Boolean = false,
            href: js.UndefOr[String] = js.undefined,
            onClick: js.UndefOr[ReactMouseEvent => Callback] = js.undefined,
            onSelect: js.UndefOr[String => Callback] = js.undefined,
            eventKey: String | Unit = {}): WithProps = auto
}

object NavbarExample {

  case class Props(clickHistory: List[String] = Nil)

  case class Click(key: String) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case Click(key) => updated(value.copy(key :: value.clickHistory))
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def handleSelect(eventKey: String) = $.props.flatMap(_.dispatchCB(Click(eventKey)))

    def render(p: ModelProxy[Props]) = {

      def actions = p().clickHistory.map { x =>
        <.li(x)
      }

      <.div(
        Navbar()(
          Navbar.Header()(
            Navbar.Brand()(
              <.a(^.href := "#home", "React-Bootstrap")
            )
          ),
          Nav()(
            NavItem(eventKey = "Link1", onSelect = handleSelect _)(
              "Link 1"
            ),
            NavItem(eventKey = "Link2", onSelect = handleSelect _)(
              "Link 2"
            ),
            NavDropdown(title = "Dropdown", id = "basic-nav-dropdown")(
              MenuItem(eventKey = "Dropdown.Action1", onSelect = handleSelect _)("Action"),
              MenuItem(eventKey = "Dropdown.Action2", onSelect = handleSelect _)("Another action"),
              MenuItem(divider = true)(),
              MenuItem(eventKey = "Dropdown.Action3", onSelect = handleSelect _)("Separated link")
            )
          )
        ),
        <.ol(actions: _*)
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("NavbarExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}
