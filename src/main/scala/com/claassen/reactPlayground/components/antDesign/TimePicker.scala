package com.claassen.reactPlayground.components.antDesign

import java.util.Date

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import moment._

object TimePicker extends ReactBridgeComponent {

  @JSImport("antd", "TimePicker")
  @js.native
  object RawComponent extends js.Object


  override lazy val componentValue = RawComponent

  def apply(): WithProps = auto
}

object TimePickerExample {

  class Backend($: BackendScope[Unit, Unit]) {

    def render(p: Unit, s: Unit) = {
      <.div(
        TimePicker()
      )
    }
  }

  val component = ScalaComponent.builder[Unit]("TimePickerExample")
    .renderBackend[Backend]
    .build

  def apply() = component().vdomElement
}