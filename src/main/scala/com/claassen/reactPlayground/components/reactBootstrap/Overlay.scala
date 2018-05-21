package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.{EventListener, OnUnmount}
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}


object OverlayExample {

  case class State(show: Boolean = false, top: Double = 0, left: Double = 0)

  class Backend($: BackendScope[Unit, State]) extends OnUnmount {

    private val target = Ref[dom.html.Element]
    private val popover = Ref[dom.html.Element]

    def handleToggle(e: ReactMouseEvent): Callback =
      target.get.flatMap { el =>
        g.console.log("toggle")
        e.stopPropagation()
        $.modState(x => x.copy(show = !x.show, top = el.offsetTop + el.offsetHeight, left = el.offsetLeft))
      }

    def closeOnClick(e: dom.MouseEvent): Callback = popover.get.flatMap { el =>
      if ( belongsTo(e.target.asInstanceOf[dom.html.Element], el)) {
        Callback.empty
      } else {
        g.console.log("hide")
        $.modState(x => if(x.show) x.copy(show = false) else x)
      }
    }

    def belongsTo(target: dom.html.Element, container: dom.html.Element): Boolean =
      target == container || container.contains(target)


    def render(S: State) = {

      <.div(
        <.div(^.paddingBottom := "10px",
          <.h2("Overlay"),
          Panel()(
            Panel.Body()(
              <.div(
                Button(
                  id = "overlay-target",
                  onClick = handleToggle _
                )("I am an Overlay target")
              ).withRef(target),
              <.div(^.id := "overlay",
                ^.classSet1(
                  "popover",
                  "hidden" -> !S.show,
                  "show" -> S.show),
                ^.style := js.Dictionary(
                  "top" -> s"${S.top}px",
                  "left" -> s"${S.left}px"
                ),
                <.h3(^.className := "popover-title", "Check this out!"),
                <.div(^.className := "popoever-content", "It's a popover")
              ).withRef(popover)
            )
          )
        )
      )

    }
  }

  val component = ScalaComponent.builder[Unit]("OverlayExample")
    .initialState(State())
    .renderBackend[Backend]
    .configure(
      EventListener[dom.MouseEvent].install("click", _.backend.closeOnClick, _ => dom.window)
    )
    .build

  def apply() = component().vdomElement

}
