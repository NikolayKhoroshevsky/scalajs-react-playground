package com.claassen.reactPlayground.components.samples

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import scala.scalajs.js.annotation.JSImport
import scalajs.js

/**
  * Component-wrapper for collapse animation with react-motion for
  * elements with variable (and dynamic) height.
  *
  * https://github.com/nkbt/react-collapse
  */
object ReactCollapse {

  @JSImport("react-collapse", "Collapse")
  @js.native
  object RawComponent extends js.Object

  @js.native
  trait Measures extends js.Object {
    val height: Double = js.native
    val width: Double = js.native
  }

  type OnMeasure = js.Function1[Measures, Unit]
  type OnRest = js.Function0[Unit]

  @js.native
  trait Props extends js.Object {
    var isOpened: Boolean = js.native
    var onMeasure: OnMeasure = js.native
    var onRest: OnRest = js.native
  }

  def props(isOpened: Boolean,
            onMeasure: Measures => Callback = _ => Callback.empty,
            onRest: Callback = Callback.empty): Props = {
    val p = (new js.Object).asInstanceOf[Props]
    p.isOpened = isOpened
    p.onMeasure = (measures: Measures) => onMeasure(measures).runNow()
    p.onRest = () => onRest.runNow()
    p
  }

  val component = JsComponent[Props, Children.Varargs, Null](RawComponent)

  def apply(isOpened: Boolean, children: VdomNode) = component(props(isOpened))(children).vdomElement
}

object ReactCollapseExample {

  case class State(isOpened: Boolean, paragraphs: Int)

  class Backend($: BackendScope[State, State]) {
    val text = List(
      "You think water moves fast? You should see ice. It moves like it has a mind. Like it knows it killed the world once and got a taste for murder. After the avalanche, it took us a week to climb out. Now, I don't know exactly when we turned on each other, but I know that seven of us survived the slide... and only five made it out. Now we took an oath, that I'm breaking now. We said we'd say it was the snow that killed the other two, but it wasn't. Nature is lethal but it doesn't hold a candle to man.",
      "Your bones don't break, mine do. That's clear. Your cells react to bacteria and viruses differently than mine. You don't get sick, I do. That's also clear. But for some reason, you and I react the exact same way to water. We swallow it too fast, we choke. We get some in our lungs, we drown. However unreal it may seem, we are connected, you and I. We're on the same curve, just on opposite ends.",
      "Do you see any Teletubbies in here? Do you see a slender plastic tag clipped to my shirt with my name printed on it? Do you see a little Asian child with a blank expression on his face sitting outside on a mechanical helicopter that shakes when you put quarters in it? No? Well, that's what you see at a toy store. And you must think you're in a toy store, because you're here shopping for an infant named Jeb.",
      "You see? It's curious. Ted did figure it out - time travel. And when we get back, we gonna tell everyone. How it's possible, how it's done, what the dangers are. But then why fifty years in the future when the spacecraft encounters a black hole does the computer call it an 'unknown entry event'? Why don't they know? If they don't know, that means we never told anyone. And if we never told anyone it means we never made it back. Hence we die down here. Just as a matter of deductive logic.")

    def onOpenedChange(e: ReactEventFromInput) = $.modState(_.copy(isOpened = e.target.checked))

    def onParagraphsChange(e: ReactEventFromInput) = $.modState(_.copy(paragraphs = e.target.value.toInt))


    def render(p: State, s: State) = {
      def paragraphs = {
        val p: List[TagMod] = (if (s.paragraphs > 0) text.take(s.paragraphs) else List("No Text")).map(<.p(_))
        val tagMods: List[TagMod] = (^.className := "text") :: p
        <.div(tagMods: _*)
      }

      <.div(
        <.div(^.className := "config",
          <.label(^.className := "label",
            "Opened:",
            <.input(^.className := "input",
              ^.`type` := "checkbox",
              ^.checked := s.isOpened,
              ^.onChange ==> onOpenedChange
            )
          ),

          <.label(^.className := "label",
            "Paragraphs:",
            <.input(^.className := "input",
              ^.`type` := "range",
              ^.value := s.paragraphs,
              ^.step := 1,
              ^.min := 0,
              ^.max := 4,
              ^.onChange ==> onParagraphsChange,
              s.paragraphs
            )
          )
        ),
        ReactCollapse(s.isOpened, paragraphs)
      )
    }
  }

  val component = ScalaComponent.builder[State]("ReactCollapseExample")
    .initialStateFromProps(p => p)
    .renderBackend[Backend]
    .build

  def apply() = component(State(isOpened = false, 0)).vdomElement
}