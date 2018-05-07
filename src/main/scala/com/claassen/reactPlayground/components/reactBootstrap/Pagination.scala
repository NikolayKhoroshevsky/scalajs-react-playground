package com.claassen.reactPlayground.components.reactBootstrap

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

  case class Props(pagination1: PaginationProps,
                   pagination2: PaginationProps)

  case class PaginationProps(total: Int, selected: Int)

  case class Select(pager: Int, page: Int) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case Select(1,page) => updated(value.copy(pagination1 = value.pagination1.copy(selected = page)))
      case Select(2,page) => updated(value.copy(pagination2 = value.pagination2.copy(selected = page)))
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def render(P: ModelProxy[Props]) = {
      def makeProps(id: Int, p: PaginationProps, visible: Int, size: String = "medium") =
        FinitePagination.Props(p.total,
          visible,
          p.selected,
          selected => {
            P.dispatchCB(Select(id, selected))
          },
          size = size
        )

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
                FinitePagination(makeProps(1, P().pagination1, 10, size = "large")),
                FinitePagination(makeProps(1, P().pagination1, 10)),
                FinitePagination(makeProps(1, P().pagination1, 10, size = "small")),
              )
            )
          ),
          <.div(^.paddingBottom := "10px",
            <.h2("More Options"),
            Panel()(
              Panel.Body()(
                FinitePagination(makeProps(2, P().pagination2, 7))
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

  case class Props(total: Int,
                   visible: Int,
                   selected: Int,
                   onSelect: Int => {},
                   includeFirstLast: Boolean = true,
                   size: String = "medium")

  class Backend($: BackendScope[Props, Unit]) {

    def handleClick(page: Int)(e: ReactMouseEvent) = dispatch(_ => page)

    def handleFirst(e: ReactMouseEvent) = dispatch(_ => 1)

    def handlePrevious(e: ReactMouseEvent) = dispatch(p => Math.max(1, p.selected - 1))

    def handleNext(e: ReactMouseEvent) = dispatch(p => Math.min(p.total, p.selected + 1))

    def handleLast(e: ReactMouseEvent) = dispatch(p => p.total)

    def dispatch(f: Props => Int) = Callback {
      $.props.map(p => p.onSelect(f(p)))
    }

    def buildItems(p: Props) = {
      if (p.visible >= p.total) {
        (1 to p.total).map { i =>
          Pagination.Item(active = i == p.selected,
            onClick = handleClick(i) _)(s"$i")
        }
      } else {

        val center = p.visible / 2 + 1
        val minOffset = p.selected - center + 1
        val maxOffset = p.selected + center - 1
        val (leftEllipsis, min, max, rightEllipsis) =
          if (minOffset <= 1) {
            (false, 1, p.visible - 1, true)
          } else if (maxOffset >= p.total) {
            (true, p.total - p.visible + 2, p.total, false)
          } else {
            (true, minOffset + 1, maxOffset - 1, true)
          }
        List(
          if (p.includeFirstLast) List(Pagination.First(
            disabled = p.selected == 1,
            onClick = handleFirst _
          )()) else Nil,
          List(Pagination.Prev(
            disabled = p.selected == 1,
            onClick = handlePrevious _
          )()),
          if (leftEllipsis) List(Pagination.Ellipsis(disabled = true)()) else Nil,
          (min to max).map { i =>
            Pagination.Item(
              active = i == p.selected,
              onClick = handleClick(i) _
            )(s"$i")
          }.toList,
          if (rightEllipsis) List(Pagination.Ellipsis(disabled = true)()) else Nil,
          List(Pagination.Next(
            disabled = p.selected == p.total,
            onClick = handleNext _
          )()),
          if (p.includeFirstLast) List(Pagination.Last(
            disabled = p.selected == p.total,
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
