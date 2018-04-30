package com.claassen.reactPlayground.components.reactBootstrap

import com.claassen.reactPlayground.components.reactBootstrap.Navbar.Brand.auto
import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}


object ListGroup extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/ListGroup", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(): WithProps = auto
}

object ListGroupItem extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/ListGroupItem", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(active: Boolean = false,
            disabled: Boolean = false,
            listItem: Boolean = false,
            header: String | Unit = (),
            href: String | Unit = (),
            onClick: (ReactMouseEvent => Callback) | Unit = {}): WithProps = auto
}

object ListGroupExample {

  case class Props(items: List[Item], selectedItem: Item)

  case class Item(content: String)

  case class Click(item: Item) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case Click(item) => updated(value.copy(selectedItem = item))
    }
  }
  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def handleClick(item: Item)(e: ReactMouseEvent): Callback =
      $.props.flatMap(_.dispatchCB(Click(item)))

    def render(p: ModelProxy[Props]) = {
      ListGroup()(
        p().items.map { item =>
          ListGroupItem(
            active = item == p().selectedItem,
            onClick = handleClick(item) _)(item.content)
        }: _*
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("ListGroupExample")
    .renderBackend[Backend]
    .build

  val defaultProps = {
    val selected = Item("Item 2")

    Props(
      List(
        Item("Item 1"),
        selected,
        Item("Item 3"),
        Item("Item 4")
      ),
      selected
    )
  }

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}