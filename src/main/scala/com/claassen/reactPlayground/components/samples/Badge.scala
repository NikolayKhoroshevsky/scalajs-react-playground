package com.claassen.reactPlayground.components.samples

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

object Badge extends ReactBridgeComponent {

  @JSImport("antd", "Badge")
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(count: Double,
            dot: Boolean = false,
            offset: js.UndefOr[js.Array[Double]] = js.undefined,
            overflowCount: Double = 99,
            showZero: Boolean = false,
            status: String = "",
            text: String = ""): WithProps = auto
}

object BadgeExample {

  class Backend($: BackendScope[Int, Int]) {

    def render(p: Int, s: Int) = {
      <.div(
        Badge(count = s)(
          <.a(^.href := "#", ^.className := "head-example")
        ),
        <.button(
          ^.onClick --> $.modState(_ + 1),
          "+"
        ),
        <.button(
          ^.onClick --> $.modState(_ - 1),
          "-"
        )
      )
    }
  }

  val component = ScalaComponent.builder[Int]("BadgeExample")
    .initialStateFromProps(p => p)
    .renderBackend[Backend]
    .build

  def apply(count: Int) = component(count).vdomElement
}