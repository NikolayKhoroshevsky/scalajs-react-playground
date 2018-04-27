package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

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

  def apply(): WithProps = auto
}

object NavDropdown extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/NavDropdown", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(id: String,
            title: String): WithProps = auto
}

object MenuItem extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/MenuItem", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(divider: Boolean = false): WithProps = auto
}

object NavbarExample {

  class Backend($: BackendScope[Unit, Unit]) {

    def render(p: Unit) = {
      <.div(
        Navbar()(
          Navbar.Header()(
            Navbar.Brand()(
              <.a(^.href := "#home", "React-Bootstrap")
            )
          ),
          Nav()(
            NavItem()(
              "Link"
            ),
            NavItem()(
              "Link"
            ),
            NavDropdown(title = "Dropdown", id = "basic-nav-dropdown")(
              MenuItem()("Action"),
              MenuItem()("Another action"),
              MenuItem()("Something else here"),
              MenuItem(divider = true)(),
              MenuItem()("Separated link")
            )
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[Unit]("NavbarExample")
    .renderBackend[Backend]
    .build

  def apply() = component().vdomElement
}
