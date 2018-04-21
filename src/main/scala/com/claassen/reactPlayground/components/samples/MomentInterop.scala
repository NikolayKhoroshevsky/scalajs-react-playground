package com.claassen.reactPlayground.components.samples

import diode.react.{ModelProxy, ReactConnector}
import diode.{Action, ActionHandler, Circuit}
import japgolly.scalajs.react.{BackendScope, _}
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.JSImport

object MomentInterop {

  @JSImport("moment", JSImport.Default)
  @js.native
  object Moment extends js.Object {
    def apply(): js.Object = js.native
  }

  @JSImport("react-big-calendar", JSImport.Default)
  @js.native
  object BigCalendar extends js.Object {
    def momentLocalizer(moment: Moment.type ): Unit = js.native
  }


  class Backend($: BackendScope[Unit, Unit]) {

      def start = Callback {
        g.console.log(Moment())
        g.console.log(BigCalendar)
        g.console.log(BigCalendar.momentLocalizer(Moment))
      }

      def render(p: Unit, s: Unit) = {
        <.div(
          <.div(^.id := "container")
        )
      }
    }

    val component = ScalaComponent.builder[Unit]("MomentInterop")
      .renderBackend[Backend]
      .componentDidMount(_.backend.start)
      .build

    def apply() = component().vdomElement
}

