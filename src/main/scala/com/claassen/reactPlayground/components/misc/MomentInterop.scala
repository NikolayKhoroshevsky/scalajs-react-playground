package com.claassen.reactPlayground.components.misc

import diode.react.{ModelProxy, ReactConnector}
import diode.{Action, ActionHandler, Circuit}
import japgolly.scalajs.react.{BackendScope, _}
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.JSImport
import moment._


object MomentInterop {

  class Backend($: BackendScope[Unit, Unit]) {

      def start = Callback {
        Moment.locale("en_US")
        g.console.log(Moment())
      }

      def render(p: Unit, s: Unit) = {
        <.div(
          <.div(^.id := "container",
            Moment().format()
          )
        )
      }
    }

    val component = ScalaComponent.builder[Unit]("MomentInterop")
      .renderBackend[Backend]
      .componentDidMount(_.backend.start)
      .build

    def apply() = component().vdomElement
}

