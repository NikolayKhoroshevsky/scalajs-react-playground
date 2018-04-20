package com.claassen.reactPlayground.components.samples

import japgolly.scalajs.react.BackendScope

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object ReactCounter {

  object ReactCounter {

    case class Props(counter: Int, increase: Callback, decrease: Callback, reset: Callback)

    class Backend($: BackendScope[Props, Unit]) {
      def render(p: Props) = {
        <.div(
          <.h3("Counter"),
          <.p("Value = ", <.b(p.counter)),
          <.div(
            ^.className := "btn-group",
            <.button(
              ^.className := "btn btn-default",
              ^.onClick --> p.increase,
              "Increase"
            ),
            <.button(
              ^.className := "btn btn-default",
              ^.onClick --> p.decrease,
              "Decrease"
            ),
            <.button(
              ^.className := "btn btn-default",
              ^.onClick --> p.reset,
              "Reset"
            )
          )
        )
      }
    }

    val component = ScalaComponent.builder[Props]("Counter")
      .renderBackend[Backend]
      .build

    def apply(counter: Int, increase: Callback, decrease: Callback, reset: Callback) =
      component(Props(counter, increase, decrease, reset))
  }

  object CounterState {

    class Backend($: BackendScope[Unit, Int]) {
      def handleIncrease: Callback = $.modState(_ + 1)

      def handleDecrease: Callback = $.modState(_ - 1)

      def handleReset: Callback = $.setState(0)

      def render(s: Int) = {
        ReactCounter(s, handleIncrease, handleDecrease, handleReset)
      }
    }

    val component = ScalaComponent.builder[Unit]("CounterState")
      .initialState(0)
      .renderBackend[Backend]
      .build

    def apply() = component().vdomElement
  }

  def apply() = CounterState()
}
