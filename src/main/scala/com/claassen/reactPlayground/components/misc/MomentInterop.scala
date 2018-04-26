package com.claassen.reactPlayground.components.misc

import diode._
import diode.react.ModelProxy
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{BackendScope, _}
import moment._

import scala.scalajs.js


object MomentInterop {

  case class Props(now: moment.Date = Moment())

  case object Tick extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case Tick =>
        updated(Props(Moment()))
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    var interval: js.UndefOr[js.timers.SetIntervalHandle] = js.undefined

    def tick = $.props.flatMap { p =>
      p.dispatchCB(Tick)
    }

    def mount = Callback {
      interval = js.timers.setInterval(1000)($.props.flatMap(_.dispatchCB(Tick)).runNow())
    }

    def unmount = Callback {
      interval foreach js.timers.clearInterval
      interval = js.undefined
    }

    def render(p: ModelProxy[Props]) = {
      <.div(
        <.div(^.id := "container",
          p().now.format()
        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("MomentInterop")
    .renderBackend[Backend]
    .componentDidMount(_.backend.mount)
    .componentWillUnmount(_.backend.unmount)
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}

