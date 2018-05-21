package com.claassen.reactPlayground.components.misc

import com.claassen.reactPlayground.components.reactBootstrap._
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.{EventListener, OnUnmount}
import japgolly.scalajs.react.raw.SyntheticMouseEvent
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}

object TimePicker {

  case class State(show: Boolean = false, top: Double = 0, left: Double = 0, hour: Int = 12, minute: Int = 0, am: Boolean = true) {

    def display = f"$hour%02d:$minute%02d:${if (am) "am" else "pm"}"
  }

  class Backend($: BackendScope[Unit, State]) extends OnUnmount {

    private val target = Ref[dom.html.Element]
    private val popover = Ref[dom.html.Element]
    private val hourRef = Ref[dom.html.Element]
    private val minuteRef = Ref[dom.html.Element]
    private val amRef = Ref[dom.html.Element]

    def handleToggle(e: ReactMouseEvent): Callback =
      target.get.flatMap { el =>
        g.console.log("toggle")
        e.stopPropagation()
        $.modState(x => x.copy(show = !x.show, top = el.offsetTop, left = el.offsetLeft))
      }

    def closeOnClick(e: dom.MouseEvent): Callback = popover.get.flatMap { el =>
      if (belongsTo(e.target.asInstanceOf[dom.html.Element], el)) {
        Callback.empty
      } else {
        g.console.log("hide")
        $.modState(x => if (x.show) x.copy(show = false) else x)
      }
    }

    def belongsTo(target: dom.html.Element, container: dom.html.Element): Boolean =
      target == container || container.contains(target)

    def handleChange(e: ReactEventFromInput) = Callback {}

    def render(S: State) = {

      <.div(
        FormGroup()(
          <.div(
            InputGroup()(
              ^.className := "tp-input",
              FormControl(`type` = "text", value = S.display, onChange = handleChange _ )(^.onClick ==> handleToggle),
              InputGroup.Addon()(Glyphicon(glyph = "time")())
            )
          ).withRef(target)
        ),
        <.div(
          ^.classSet1(
            "popover",
            "hidden" -> !S.show,
            "show" -> S.show),
          ^.style := js.Dictionary(
            "top" -> s"${S.top}px",
            "left" -> s"${S.left}px"
          ),
          InputGroup()(
            ^.className := "tp-input",
            FormControl(`type` = "text", value = S.display, onChange = handleChange _ ),
            InputGroup.Addon()(Glyphicon(glyph = "remove-circle")())
          ),
          <.div(^.className := "tp-box",
            <.div(
              ^.classSet1(
                "tp-select-list"
              ),
              <.ul(
                List(<.li("\u00A0"),
                  <.li("\u00A0")
                )
                  ::: (1 to 12).map { x =>
                  <.li(
                    ^.classSet("tp-selected" -> (S.hour == x)),
                    ^.onClick --> $.modState(s => s.copy(hour = x)),
                    f"$x%02d"
                  )
                }.toList
                  ::: List(<.li("\u00A0"),
                  <.li("\u00A0")
                ): _*
              )
            ).withRef(hourRef),
            <.div(
              ^.classSet1(
                "tp-select-list tp-divider"
              ),
              <.ul(
                List(<.li("\u00A0"),
                  <.li("\u00A0")
                )
                  ::: (0 to 50 by 10).map { x =>
                  <.li(
                    ^.classSet("tp-selected" -> (S.minute == x)),
                    ^.onClick --> $.modState(s => s.copy(minute = x)),
                    f"$x%02d"
                  )
                }.toList
                  ::: List(<.li("\u00A0"),
                  <.li("\u00A0")
                ): _*
              )
            ).withRef(minuteRef),
            <.div(
              ^.classSet1(
                "tp-select-list tp-divider"
              ),
              <.ul(
                <.li("\u00A0"),
                <.li("\u00A0"),
                <.li(
                  ^.classSet("tp-selected" -> S.am),
                  ^.onClick --> $.modState(x => x.copy(am = true)),
                  "am"
                ),
                <.li(
                  ^.classSet("tp-selected" -> !S.am),
                  ^.onClick --> $.modState(x => x.copy(am = false)),
                  "pm"
                ),
                <.li("\u00A0"),
                <.li("\u00A0")
              )
            ).withRef(amRef)
          )
        ).withRef(popover)
      )
    }

    def didUpdate(previous: State, current: State): Callback = if (current.show) {

      for {
        targetEl <- target.get
        hourEl <- hourRef.get
        minuteEl <- minuteRef.get
        amEl <- amRef.get
      } yield {
        hourEl.scrollTop = (current.hour - 1) * 20
        g.console.log(current.display)
        minuteEl.scrollTop = current.minute / 10 * 20
        amEl.scrollTop = if (current.am) 0 else 20

        {}
      }

    } else Callback {}
  }


  val component = ScalaComponent.builder[Unit]("TimePicker")
    .initialState(State())
    .renderBackend[Backend]
    .componentDidUpdate(x => x.backend.didUpdate(x.prevState, x.currentState))
    .configure(
      EventListener[dom.MouseEvent].install("click", _.backend.closeOnClick, _ => dom.window)
    )
    .build

  def apply() = component().vdomElement

}

object TimerPickerExample {

  val component = ScalaComponent.static("TimePickerExample")(
    <.div(
      <.div(^.paddingBottom := "10px",
        <.h2("Labels"),
        Panel()(
          Panel.Body()(
            TimePicker()
          )
        )
      )
    )
  )


  def apply() = component().vdomElement
}
