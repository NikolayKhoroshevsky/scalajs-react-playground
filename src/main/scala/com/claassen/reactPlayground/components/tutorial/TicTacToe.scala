package com.claassen.reactPlayground.components.tutorial

import japgolly.scalajs.react._
import japgolly.scalajs.react.component.builder.Lifecycle
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js.Dynamic.global

object Square {

  import Game.SquareState

  case class Props(value: SquareState, onClick: Callback)

  val component = ScalaComponent.builder[Props]("Square")
    .initialState[SquareState](SquareState.None)
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

  import Game.SquareState

  case class BoardState(xIsNext: Boolean = true, squares: Vector[SquareState] = Vector.fill(9)(SquareState.None)) {
    def move(i: Int): BoardState = if (squares(i) == SquareState.None) {
      val square = if (xIsNext) SquareState.X else SquareState.O
      BoardState(!xIsNext, squares.patch(i, Seq(square), 1))
    } else {
      this
    }
  }

  class Backend($: BackendScope[Unit, BoardState]) {

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

    def handleClick(i: Int): CallbackTo[Unit] = {
      $.modState(_.move(i))
    }

    def calculateWinner(s: BoardState): Option[String] = {
      if (winningLines.exists {
        case List(a, b, c) =>
          s.squares(a) != SquareState.None &&s.squares(a) == s.squares(b) && s.squares(a) == s.squares(c)
      }) {
        if (s.xIsNext) Some("O") else Some("X")
      } else None
    }

    def render(s: BoardState) = {
      def status = calculateWinner(s) match {
        case Some(winner) => s"The winner is $winner"
        case None =>
          val player = if (s.xIsNext) "X" else "O"
          s"Next Turn: $player"
      }

      def renderSquare(i: Int) = {
        Square(value = s.squares(i), onClick = handleClick(i))
      }

      <.div(
        <.div(^.className := "status", status),
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

  val component = ScalaComponent.builder[Unit]("Board")
    .initialState(BoardState())
    .renderBackend[Backend]
    .build

  def apply() = component().vdomElement
}

object Game {

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

  val component = ScalaComponent.builder[Unit]("Game")
    .renderStatic(
      <.div(^.className := "game",
        <.div(^.className := "game-board",
          Board()
        ),
        <.div(^.className := "game-info",
          <.div(""),
          <.ol("")
        )
      )
    ).build

  def apply() = component().vdomElement
}
