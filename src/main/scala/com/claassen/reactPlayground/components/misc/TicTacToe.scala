package com.claassen.reactPlayground.components.misc


import diode.ActionResult.NoChange
import diode._
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.component.Scala.Unmounted
import japgolly.scalajs.react.vdom.html_<^._




object TicTacToe {

  class GameHandler[M](modelRW: ModelRW[M, List[BoardState]]) extends ActionHandler(modelRW) {

    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case Move(i) =>
        val current = value.head
        val move = current.move(i)
        if (move == current) NoChange else updated(move :: value)
      case Rewind(i) =>
        if(i == value.length-1) NoChange else updated(value.drop(value.length - 1 - i))
    }
  }

  def apply(model: ModelProxy[GameState]): Unmounted[ModelProxy[GameState], Unit, Game.Backend] = Game.component(model)

  case class Move(i: Int) extends Action
  case class Rewind(i: Int) extends Action

  val winningLines = List(
    List(0, 1, 2),
    List(3, 4, 5),
    List(6, 7, 8),
    List(0, 3, 6),
    List(1, 4, 7),
    List(2, 5, 8),
    List(0, 4, 8),
    List(2, 4, 6)
  )

  case class GameState(moves: List[BoardState] = BoardState()::Nil)

  case class BoardState(xIsNext: Boolean = true, squares: Vector[SquareState] = Vector.fill(9)(SquareState.None)) {
    def move(i: Int): BoardState = if (!hasWinner && squares(i) == SquareState.None) {
      val square = if (xIsNext) SquareState.X else SquareState.O
      BoardState(!xIsNext, squares.patch(i, Seq(square), 1))
    } else {
      this
    }

    def hasWinner: Boolean = winningLines.exists {
      case List(a, b, c) =>
        squares(a) != SquareState.None && squares(a) == squares(b) && squares(a) == squares(c)
    }

    def winner: Option[String] = if (hasWinner) Some(if (xIsNext) "O" else "X") else None
  }

  sealed trait SquareState

  object SquareState {

    case object None extends SquareState {
      override def toString: String = ""
    }

    case object X extends SquareState {
      override def toString: String = "X"
    }

    case object O extends SquareState {
      override def toString: String = "O"
    }

  }

  object Square {

    case class Props(value: SquareState, onClick: Callback)

    val component = ScalaComponent.builder[Props]("Square")
      .render($ =>
        <.button(
          ^.className := "square",
          ^.onClick --> $.props.onClick,
          $.props.value.toString
        )
      ).build

    def apply(value: SquareState, onClick: Callback) = component(Props(value, onClick)).vdomElement
  }

  object Board {


    case class Props(value: BoardState, onClick: Int => Callback)

    class Backend($: BackendScope[Props, Unit]) {

      def render(p: Props) = {
        def renderSquare(i: Int) = {
          Square(value = p.value.squares(i), onClick = p.onClick(i))
        }

        <.div(
          <.div(^.className := "board-row",
            renderSquare(0),
            renderSquare(1),
            renderSquare(2)
          ),
          <.div(^.className := "board-row",
            renderSquare(3),
            renderSquare(4),
            renderSquare(5)
          ),
          <.div(^.className := "board-row",
            renderSquare(6),
            renderSquare(7),
            renderSquare(8)
          )
        )
      }
    }

    val component = ScalaComponent.builder[Props]("Board")
      .renderBackend[Backend]
      .build

    def apply(value: BoardState, onClick: Int => Callback) = component(Props(value, onClick)).vdomElement
  }

  object Game {

    class Backend($: BackendScope[ModelProxy[GameState], Unit]) {

      def render(p: ModelProxy[GameState]) = {

        def status = p().moves.head.winner match {
          case Some(winner) => s"The winner is $winner"
          case None =>
            val player = if (p().moves.head.xIsNext) "X" else "O"
            s"Next Turn: $player"
        }

        def moves = p().moves.zipWithIndex.map {
          case (move, i) =>
            val desc = if (i == 0) " Go to game start" else s"Go to move #$i"

            <.li(
              <.button(^.key := i,
                ^.onClick --> p.dispatchCB(Rewind(i)),
                desc
              )
            )
        }

        <.div(^.className := "game",
          <.div(^.className := "game-board",
            Board(p().moves.head, i => p.dispatchCB(Move(i)))
          ),
          <.div(^.className := "game-info",
            <.div(status),
            <.ol(moves: _*)
          )
        )
      }
    }

    val component = ScalaComponent.builder[ModelProxy[GameState]]("Game")
      .renderBackend[Backend]
      .build

  }

}