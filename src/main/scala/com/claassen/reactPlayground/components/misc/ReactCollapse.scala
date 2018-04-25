package com.claassen.reactPlayground.components.misc

import com.claassen.reactPlayground.AppCircuit
import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.{ReactEventFromInput, _}

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport

object ReactCollapseExampleBuilder {
  def build() = {
    val connection = AppCircuit.connect(_.app.misc.collapse)
    connection(p => ReactCollapseExample(p)).vdomElement
  }
}

object ReactCollapse extends ReactBridgeComponent {

  @JSImport("react-collapse", "Collapse")
  @js.native
  object RawComponent extends js.Object

  @js.native
  trait Measures extends js.Object {
    val height: Double = js.native
    val width: Double = js.native
  }

  override lazy val componentValue = RawComponent

  def apply(isOpened: Boolean = false,
            onMeasure: js.UndefOr[Measures => Callback] = js.undefined,
            onRest: js.UndefOr[Callback] = js.undefined): WithProps = auto
}

object ReactCollapseExample {

  case class Props(isOpened: Boolean = false, paragraphs: Int = 1)

  case object ToggleOpen extends Action

  case class SetParagraphs(v: Int) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case ToggleOpen => updated(value.copy(isOpened = !value.isOpened))
      case SetParagraphs(i) => updated(value.copy(paragraphs = i))
    }
  }


  class Backend($: BackendScope[ModelProxy[Props], Unit]) {
    val text = List(
      "You think water moves fast? You should see ice. It moves like it has a mind. Like it knows it killed the world once and got a taste for murder. After the avalanche, it took us a week to climb out. Now, I don't know exactly when we turned on each other, but I know that seven of us survived the slide... and only five made it out. Now we took an oath, that I'm breaking now. We said we'd say it was the snow that killed the other two, but it wasn't. Nature is lethal but it doesn't hold a candle to man.",
      "Your bones don't break, mine do. That's clear. Your cells react to bacteria and viruses differently than mine. You don't get sick, I do. That's also clear. But for some reason, you and I react the exact same way to water. We swallow it too fast, we choke. We get some in our lungs, we drown. However unreal it may seem, we are connected, you and I. We're on the same curve, just on opposite ends.",
      "Do you see any Teletubbies in here? Do you see a slender plastic tag clipped to my shirt with my name printed on it? Do you see a little Asian child with a blank expression on his face sitting outside on a mechanical helicopter that shakes when you put quarters in it? No? Well, that's what you see at a toy store. And you must think you're in a toy store, because you're here shopping for an infant named Jeb.",
      "You see? It's curious. Ted did figure it out - time travel. And when we get back, we gonna tell everyone. How it's possible, how it's done, what the dangers are. But then why fifty years in the future when the spacecraft encounters a black hole does the computer call it an 'unknown entry event'? Why don't they know? If they don't know, that means we never told anyone. And if we never told anyone it means we never made it back. Hence we die down here. Just as a matter of deductive logic.")

    def render(props: ModelProxy[Props]) = {
      def paragraphs = {
        val para: List[TagMod] = (if (props().paragraphs > 0) text.take(props().paragraphs) else List("No Text")).map(<.p(_))
        val tagMods: List[TagMod] = (^.className := "text") :: para
        <.div(tagMods: _*)
      }

      <.div(
        <.div(^.className := "config",
          <.label(
            "Opened:",
            <.input(^.className := "input",
              ^.`type` := "checkbox",
              ^.checked := props().isOpened,
              ^.onChange --> props.dispatchCB(ToggleOpen)
            )
          ),

          <.label(
            "Paragraphs:",
            <.input(^.className := "input",
              ^.`type` := "range",
              ^.value := props().paragraphs,
              ^.step := 1,
              ^.min := 0,
              ^.max := 4,
              ^.onChange ==> { e: ReactEventFromInput =>
                props.dispatchCB(SetParagraphs(e.target.value.toInt))
              }
            ),
            props().paragraphs
          )
        ),
        <.div(
          ^.style := js.Dynamic.literal(width = "600px"),
          ReactCollapse(props().isOpened)(
            paragraphs
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("ReactCollapseExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}