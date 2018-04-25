package com.claassen.reactPlayground

import com.claassen.reactPlayground.components.misc.{ReactCollapseExample, ReactCounterProtocol, TicTacToe}
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
      new ReactCollapseExample.Handler[RootModel](zoomTo(_.app.misc.collapse))
    )
}

case class RootModel(app: AppState = AppState())
case class AppState(misc: MiscState = MiscState())

case class MiscState(ticTacToe: TicTacToe.GameState = TicTacToe.GameState(),
                     collapse: ReactCollapseExample.Props = ReactCollapseExample.Props(),
                     counter: ReactCounterProtocol.CounterModel = ReactCounterProtocol.CounterModel(0))