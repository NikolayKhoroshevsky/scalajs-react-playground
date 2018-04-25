package com.claassen.reactPlayground.components.misc

import com.claassen.reactPlayground.AppCircuit
import diode.react.{ModelProxy, ReactConnectProxy, ReactConnector}
import diode.{Action, ActionHandler, Circuit}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object ReactCounterBuilder {
  def build() = {
    val connection = AppCircuit.connect(_.app.misc.counter)
    connection(p => ReactCounter(p)).vdomElement
  }
}

object ReactCounterProtocol {
  case class CounterModel(counter: Int)

  case object Increase extends Action

  case object Decrease extends Action

  case object Reset extends Action

}

object ReactCounter {

  import ReactCounterProtocol._

  class Backend($: BackendScope[ModelProxy[CounterModel], Unit]) {
    def render(props: ModelProxy[CounterModel]) = {
      <.div(
        <.h3("Counter"),
        <.p("Value = ", <.b(props().counter)),
        <.div(
          ^.className := "btn-group",
          <.button(
            ^.className := "btn btn-default",
            ^.onClick --> props.dispatchCB(Increase),
            "Increase"
          ),
          <.button(
            ^.className := "btn btn-default",
            ^.onClick --> props.dispatchCB(Decrease),
            "Decrease"
          ),
          <.button(
            ^.className := "btn btn-default",
            ^.onClick --> props.dispatchCB(Reset),
            "Reset"
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[CounterModel]]("Counter")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[CounterModel]) = component(props)
}
