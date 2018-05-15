package com.claassen.reactPlayground.components.reactBootstrap

import com.claassen.reactPlayground.components.reactBootstrap.FinitePagination.{Props, Select}
import com.payalabs.scalajs.react.bridge._
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}

object Pager extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Pager", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  object Item extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PagerItem", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(disabled: Boolean = false,
              previous: Boolean = false,
              next: Boolean = false,
              onSelect: js.UndefOr[String => Callback] = js.undefined,
              onClick: (ReactMouseEvent => Callback) | Unit = {},
              eventKey: String | Unit = {},
              href: String | Unit = {}): WithProps = auto
  }

  def apply(onSelect: js.UndefOr[String => Callback] = js.undefined,
            bsClass: String | Unit = {}): WithProps = auto
}

object Pagination extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Pagination", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  object Item extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PaginationItem", JSImport.Default)
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(disabled: Boolean = false,
              active: Boolean = false,
              onClick: (ReactMouseEvent => Callback) | Unit = {},
              href: String | Unit = {}): WithProps = auto
  }

  object First extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PaginationItem", "First")
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(disabled: Boolean = false,
              onClick: (ReactMouseEvent => Callback) | Unit = {},
              href: String | Unit = {}): WithProps = auto
  }

  object Prev extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PaginationItem", "Prev")
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(disabled: Boolean = false,
              onClick: (ReactMouseEvent => Callback) | Unit = {},
              href: String | Unit = {}): WithProps = auto
  }

  object Ellipsis extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PaginationItem", "Ellipsis")
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(disabled: Boolean = false,
              onClick: (ReactMouseEvent => Callback) | Unit = {},
              href: String | Unit = {}): WithProps = auto
  }

  object Next extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PaginationItem", "Next")
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(disabled: Boolean = false,
              onClick: (ReactMouseEvent => Callback) | Unit = {},
              href: String | Unit = {}): WithProps = auto
  }

  object Last extends ReactBridgeComponent {

    @JSImport("react-bootstrap/lib/PaginationItem", "Last")
    @js.native
    object RawComponent extends js.Object

    override lazy val componentValue = RawComponent

    def apply(disabled: Boolean = false,
              onClick: (ReactMouseEvent => Callback) | Unit = {},
              href: String | Unit = {}): WithProps = auto
  }

  def apply(onSelect: js.UndefOr[String => Callback] = js.undefined,
            bsSize: String | Unit = {}): WithProps = auto
}

object PaginationExample {

  case class Props(pager1Selected: Int,
                   pager2Selected: Int)

  case class Select(pager: Int, page: Int) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      {
        case Select(1, page) => updated(value.copy(pager1Selected = page))
        case Select(2, page) =>
          g.console.log(s"pager 2: $page")
          updated(value.copy(pager2Selected = page))
      }
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def handleSelect(id: Int)(selected: Int): Callback = $.props.flatMap(_.dispatchCB(Select(id, selected)))

    def render(P: ModelProxy[Props]) = {
      val pager1Props = FinitePagination.Props(10, 10, P().pager1Selected, handleSelect(1))
      val pager2Props = FinitePagination.Props(100, 10, P().pager2Selected, handleSelect(2))

      <.div(
        <.div(^.paddingBottom := "10px",
          <.h1("Pager"),
          <.div(^.paddingBottom := "10px",
            <.h2("Centers by default"),
            Panel()(
              Panel.Body()(
                Pager()(
                  Pager.Item(href = "#")("Previous"),
                  " ",
                  Pager.Item(href = "#")("Next")
                )
              )
            )
          ),
          <.div(^.paddingBottom := "10px",
            <.h2("Aligned"),
            Panel()(
              Panel.Body()(
                Pager()(
                  Pager.Item(previous = true, href = "#")("\u2190 Previous Page"),
                  Pager.Item(next = true, href = "#")("Next Page \u2192")
                )
              )
            )
          ),
          <.div(^.paddingBottom := "10px",
            <.h2("Disabled"),
            Panel()(
              Panel.Body()(
                Pager()(
                  Pager.Item(previous = true, href = "#")("\u2190 Previous Page"),
                  Pager.Item(next = true, disabled = true, href = "#")("Next Page \u2192")
                )
              )
            )
          ),
        ),
        <.div(^.paddingBottom := "10px",
          <.h1("Pagination"),
          <.div(^.paddingBottom := "10px",
            <.h2("Sizes"),
            Panel()(
              Panel.Body()(
                FinitePagination(pager1Props.copy(size = "large")),
                FinitePagination(pager1Props),
                FinitePagination(pager1Props.copy(size = "small")),
              )
            )
          ),
          <.div(^.paddingBottom := "10px",
            <.h2("More Options"),
            Panel()(
              Panel.Body()(
                FinitePagination(pager2Props),
              )
            )
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("PaginationExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}

object FinitePagination {

  case class Select(id: Int, page: Int) extends Action

  case class Props(total: Int,
                   visible: Int,
                   selected: Int,
                   onSelect: Int => Callback,
                   includeFirstLast: Boolean = true,
                   size: String = "medium")

  class Backend($: BackendScope[Props, Unit]) {

    def handleClick(page: Int)(e: ReactMouseEvent) = dispatch(_ => page)

    def handleFirst(e: ReactMouseEvent) = dispatch(_ => 0)

    def handlePrevious(e: ReactMouseEvent) = dispatch(p => Math.max(0, p.selected - 1))

    def handleNext(e: ReactMouseEvent) = dispatch(p => Math.min(p.total - 1, p.selected + 1))

    def handleLast(e: ReactMouseEvent) = dispatch(p => p.total - 1)

    def dispatch(f: Props => Int): Callback = $.props.flatMap(p => p.onSelect(f(p)))


    def buildItems(p: Props) = {
      if (p.visible >= p.total) {
        (0 until p.total).map { i =>
          Pagination.Item(active = i == p.selected,
            onClick = handleClick(i) _)(s"${i + 1}")
        }
      } else {
        val center = p.visible / 2
        val leftOffset = center
        val rightOffset = p.visible - center - 1
        val (leftEllipsis, min, max, rightEllipsis) =
          if (p.selected - leftOffset <= 0) {
            (false, 0, p.visible - 1, true)
          } else if (p.selected + rightOffset >= p.total -1) {
            (true, p.total - p.visible + 1, p.total, false)
          } else {
            (true, p.selected - leftOffset + 1, p.selected + rightOffset, true)
          }
        List(
          if (p.includeFirstLast) List(Pagination.First(
            disabled = p.selected == 0,
            onClick = handleFirst _
          )()) else Nil,
          List(Pagination.Prev(
            disabled = p.selected == 0,
            onClick = handlePrevious _
          )()),
          if (leftEllipsis) List(Pagination.Ellipsis(disabled = true)()) else Nil,
          (min until max).map { i =>
            Pagination.Item(
              active = i == p.selected,
              onClick = handleClick(i) _
            )(s"${i + 1}")
          }.toList,
          if (rightEllipsis) List(Pagination.Ellipsis(disabled = true)()) else Nil,
          List(Pagination.Next(
            disabled = p.selected == p.total - 1,
            onClick = handleNext _
          )()),
          if (p.includeFirstLast) List(Pagination.Last(
            disabled = p.selected == p.total - 1,
            onClick = handleLast _
          )()) else Nil
        ).flatten
      }
    }

    def render(p: Props) = {
      <.div(
        Pagination(
          bsSize = p.size
        )(
          buildItems(p): _*
        )
      )
    }
  }

  val component = ScalaComponent.builder[Props]("SimplePagination")
    .renderBackend[Backend]
    .build

  def apply(props: Props) = component(props).vdomElement
}
