package com.claassen.reactPlayground

import com.claassen.reactPlayground.components.misc.TimePicker.Time
import com.claassen.reactPlayground.components.misc._
import com.claassen.reactPlayground.components.reactBootstrap.{ListGroupExample, NavbarExample, PaginationExample}
import com.claassen.reactPlayground.css.AppCSS
import com.claassen.reactPlayground.routes.AppRouter
import diode.react.ReactConnector
import diode.{ActionHandler, Circuit}
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
      new MasterDetailExample.Handler[RootModel](zoomTo(_.app.misc.masterDetail)),
      new DataTableExample.Handler[RootModel](zoomTo(_.app.misc.dataTable)),
      new TimerPickerExample.Handler[RootModel](zoomTo(_.app.misc.timePicker)),
      new NavbarExample.Handler[RootModel](zoomTo(_.app.bootstrap.navbar)),
      new ListGroupExample.Handler[RootModel](zoomTo(_.app.bootstrap.listgroup)),
      new PaginationExample.Handler[RootModel](zoomTo(_.app.bootstrap.pagination))
    )

}

case class RootModel(app: AppState = AppState())

case class AppState(misc: MiscState = MiscState(),
                    bootstrap: Bootstrap = Bootstrap())

case class MiscState(ticTacToe: TicTacToe.GameState = TicTacToe.GameState(),
                     collapse: ReactCollapseExample.Props = ReactCollapseExample.Props(),
                     counter: ReactCounterProtocol.CounterModel = ReactCounterProtocol.CounterModel(0),
                     moment: MomentInterop.Props = MomentInterop.Props(),
                     masterDetail: MasterDetailExample.Props = MasterDetailExample.Props(None),
                     dataTable: DataTableExample.Props = DataTableExample.Props(),
                     timePicker: TimerPickerExample.Props = TimerPickerExample.Props(time = Some(Time())))

case class Bootstrap(navbar: NavbarExample.Props = NavbarExample.Props(),
                     listgroup: ListGroupExample.Props= ListGroupExample.defaultProps,
                     pagination: PaginationExample.Props = PaginationExample.Props(4,4))
