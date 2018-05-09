package com.claassen.reactPlayground.components.reactBootstrap

import com.claassen.reactPlayground.components.reactBootstrap.Tabs.auto
import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Tabs extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Tabs", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(defaultActiveKey: String | Unit = {},
            activeKey: String | Unit = {},
            id: String | Unit = {},
            onSelect: js.UndefOr[String => Callback] = js.undefined,
            bsStyle: String | Unit = {}): WithProps = auto
}

object Tab extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Tab", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  object Container extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/TabContainer", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(defaultActiveKey: String | Unit = {},
              id: String | Unit = {},
              bsClass: String | Unit = {}): WithProps = auto
  }

  object Content extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/TabContent", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(animation: Boolean = true,
              bsClass: String | Unit = {}): WithProps = auto
  }

  object Pane extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/TabPane", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(eventKey: String | Unit = {},
              id: String | Unit = {},
              bsClass: String | Unit = {}): WithProps = auto
  }


  def apply(eventKey: String | Unit = {},
            title: String | Unit = {},
            disabled: Boolean = false,
            bsClass: String | Unit = {}): WithProps = auto
}

object TabExample {

  val component = ScalaComponent.static("WellExample")(
    <.div(
      <.div(^.paddingBottom := "10px",
        <.h2("Uncontrolled"),
        Panel()(
          Panel.Body()(
            Tabs(defaultActiveKey = "2", id = "uncontrolled-tab-example")(
              Tab(eventKey = "1", title = "Tab 1")("Tab 1 content"),
              Tab(eventKey = "2", title = "Tab 2")("Tab 2 content"),
              Tab(eventKey = "3", title = "Tab 3", disabled = true)("Tab 3 content"),
            )
          )
        )
      ),
      <.div(^.paddingBottom := "10px",
        <.h2("Controlled"),
        Panel()(
          Panel.Body()(
            ControlledTabs()
          )
        )
      ),
      <.div(^.paddingBottom := "10px",
        <.h2("Tabs with Dropdown"),
        Panel()(
          Panel.Body()(
            Tab.Container(id = "tabs-with-dropdown", defaultActiveKey = "first")(
              Row(className = "clearfix")(
                Col(sm = 12)(
                  Nav(bsStyle = "tabs")(
                    NavItem(eventKey = "first")("Tab 1"),
                    NavItem(eventKey = "second")("Tab 2"),
                    NavDropdown(eventKey = "3", title = "Dropdown", id = "nav-dropdown-within-tab")(
                      MenuItem(eventKey = "3.1")("Action"),
                      MenuItem(eventKey = "3.2")("Another action"),
                      MenuItem(eventKey = "3.3")("Something else here"),
                      MenuItem(divider = true)(),
                      MenuItem(eventKey = "3.4")("Separated link"),
                    )
                  )
                ),
                Col(sm = 12)(
                  Tab.Content()(
                    Tab.Pane(eventKey = "first")("Tab 1 content"),
                    Tab.Pane(eventKey = "second")("Tab 2 content"),
                    Tab.Pane(eventKey = "3.1")("Tab 3.1 content"),
                    Tab.Pane(eventKey = "3.2")("Tab 3.2 content"),
                    Tab.Pane(eventKey = "3.3")("Tab 3.3 content"),
                    Tab.Pane(eventKey = "3.4")("Tab 3.4 content"),
                  )
                )
              )
            )
          )
        )
      ),
    )
  )

  def apply() = component().vdomElement
}

object ControlledTabs {

  class Backend($: BackendScope[String, String]) {

    def handleSelect(key: String) = $.setState(key)

    def render(p: String, s: String) = {
      Tabs(activeKey = s,
        onSelect = handleSelect _,
        id = "controlled-tab-example"
      )(
        Tab(eventKey = "1", title = "Tab 1")("Tab 1 content"),
        Tab(eventKey = "2", title = "Tab 2")("Tab 2 content"),
        Tab(eventKey = "3", title = "Tab 3", disabled = true)("Tab 3 content"),
      )
    }
  }

  val component = ScalaComponent.builder[String]("ControlledTabs")
    .initialStateFromProps(p => p)
    .renderBackend[Backend]
    .build

  def apply() = component("2").vdomElement
}