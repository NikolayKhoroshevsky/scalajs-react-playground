package com.claassen.reactPlayground

import com.claassen.reactPlayground.components.antDesign.{BadgeExample, DatePickerExample}
import com.claassen.reactPlayground.components.misc.{MomentInterop, ReactCollapseExample, ReactCounterProtocol, TicTacToe}
import com.claassen.reactPlayground.components.reactBootstrap.{FinitePagination, ListGroupExample, NavbarExample, PaginationExample}
import com.claassen.reactPlayground.css.AppCSS
import com.claassen.reactPlayground.routes.AppRouter
import diode.react.{ModelProxy, ReactConnector}
import diode.{ActionHandler, Circuit}
import japgolly.scalajs.react.vdom.VdomElement
import org.scalajs.dom.document

object App {

  def main(args: Array[String]): Unit = {
    val root = document.getElementById("root")
    AppCSS.load
    AppRouter.router().renderIntoDOM(root)
  }
}

object AppCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
  // define initial value for the application model
  def initialModel = RootModel()

  val counterHandler = new ActionHandler(zoomTo(_.app.misc.counter.counter)) {

    import ReactCounterProtocol._

    override def handle = {
      case Increase => updated(value + 1)
      case Decrease => updated(value - 1)
      case Reset => updated(0)
    }
  }

  override protected def actionHandler: AppCircuit.HandlerFunction =
    composeHandlers(
      counterHandler,
      new TicTacToe.GameHandler[RootModel](zoomTo(_.app.misc.ticTacToe.moves)),
      new ReactCollapseExample.Handler[RootModel](zoomTo(_.app.misc.collapse)),
      new MomentInterop.Handler[RootModel](zoomTo(_.app.misc.moment)),
      new BadgeExample.Handler[RootModel](zoomTo(_.app.antDesign.badge)),
      new DatePickerExample.Handler[RootModel](zoomTo(_.app.antDesign.datePicker)),
      new NavbarExample.Handler[RootModel](zoomTo(_.app.bootstrap.navbar)),
      new ListGroupExample.Handler[RootModel](zoomTo(_.app.bootstrap.listgroup)),
      new FinitePagination.Handler[RootModel](1, zoomTo(_.app.bootstrap.pagination.pagination1)),
      new FinitePagination.Handler[RootModel](2, zoomTo(_.app.bootstrap.pagination.pagination2)),
    )

}

case class RootModel(app: AppState = AppState())

case class AppState(misc: MiscState = MiscState(), antDesign: AntDesign = AntDesign(), bootstrap: Bootstrap = Bootstrap())

case class AntDesign(badge: BadgeExample.Props = BadgeExample.Props(),
                     datePicker: DatePickerExample.Props = DatePickerExample.Props())

case class MiscState(ticTacToe: TicTacToe.GameState = TicTacToe.GameState(),
                     collapse: ReactCollapseExample.Props = ReactCollapseExample.Props(),
                     counter: ReactCounterProtocol.CounterModel = ReactCounterProtocol.CounterModel(0),
                     moment: MomentInterop.Props = MomentInterop.Props())

case class Bootstrap(navbar: NavbarExample.Props = NavbarExample.Props(),
                     listgroup: ListGroupExample.Props= ListGroupExample.defaultProps,
                     pagination: PaginationExample.Props = PaginationExample.Props(
                       FinitePagination.Props(1, 10,10,4),
                       FinitePagination.Props(2, 100,7,4)
                     ))