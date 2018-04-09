package com.claassen.reactPlayground.components.tutorial

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js.Dynamic.global

/*class Square extends React.Component {
  render() {
    return (
      <button className="square">
        {/* TODO */}
      </button>
      );
  }
}*/
object Square {

  val component = ScalaComponent.builder[Int]("Square")
    .render($ =>
      <.button(
        ^.className := "square",
        ^.onClick -->Callback { global.alert(s"clicked ${$.props}") },
        $.props.toString
      )
    ).build

  def apply(v: Int) = component(v).vdomElement
}

/*
class Board extends React.Component {
  renderSquare(i) {
    return <Square />;
  }

  render() {
    const status = 'Next player: X';

    return (
      <div>
        <div className="status">{status}</div>
        <div className="board-row">
          {this.renderSquare(0)}
          {this.renderSquare(1)}
          {this.renderSquare(2)}
        </div>
        <div className="board-row">
          {this.renderSquare(3)}
          {this.renderSquare(4)}
          {this.renderSquare(5)}
        </div>
        <div className="board-row">
          {this.renderSquare(6)}
          {this.renderSquare(7)}
          {this.renderSquare(8)}
        </div>
      </div>
      );
  }
}*/
object Board {
  val status = "Next player: X"

  val component = ScalaComponent.builder[Unit]("Board")
    .renderStatic(
      <.div(
        <.div(^.className := "status", status),
        <.div(^.className := "board-row",
          Square(0),
          Square(1),
          Square(2)
        ),
        <.div(^.className := "board-row",
          Square(3),
          Square(4),
          Square(5)
        ),
        <.div(^.className := "board-row",
          Square(6),
          Square(7),
          Square(8)
        )
      )
    ).build

  def apply() = component().vdomElement
}

/*
class Game extends React.Component {
  render() {
    return (
      <div className="game">
        <div className="game-board">
          <Board />
        </div>
        <div className="game-info">
          <div>{/* status */}</div>
          <ol>{/* TODO */}</ol>
        </div>
      </div>
      );
  }
}*/
object Game {
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
