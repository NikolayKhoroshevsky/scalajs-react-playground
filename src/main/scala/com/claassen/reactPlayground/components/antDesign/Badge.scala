package com.claassen.reactPlayground.components.antDesign

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

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

  case class Props(count: Int = 0)

  case class SetCount(newCount: Int) extends Action

  class Handler[M](modelRW: ModelRW[M,Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case SetCount(newCount) => updated(Props(newCount))
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def render(p: ModelProxy[Props]) = {
      <.div(
        Badge(count = p().count)(
          <.a(^.href := "#", ^.className := "head-example")
        ),
        <.button(
          ^.onClick --> p.dispatchCB(SetCount(p().count + 1)),
          "+"
        ),
        <.button(
          ^.onClick --> p.dispatchCB(SetCount(p().count - 1)),
          "-"
        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("BadgeExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}