package com.claassen.reactPlayground.components.misc

import com.claassen.reactPlayground.components.misc.TimePicker.{Props, Time}
import com.claassen.reactPlayground.components.reactBootstrap._
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.{EventListener, OnUnmount}
import japgolly.scalajs.react.raw.SyntheticMouseEvent
import japgolly.scalajs.react.vdom.html_<^._
import moment.Moment
import org.scalajs.dom

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}

object TimePicker {

  object Time {
    def now: Time = {
      val now = Moment()
      val h12 = if (now.hour() > 12) now.hour() - 12 else now.hour()
      val h = if (h12 == 0) 12 else h12
      Time(
        h,
        Math.floor(now.minute() / 10d).toInt * 10, // This could still produce 60
        now.hour() < 12
      )
    }
  }

  case class Time(hour: Int = 12, minute: Int = 0, am: Boolean = true)

  case class State(show: Boolean = false, top: Double = 0, left: Double = 0, time: Option[Time] = None) {
    lazy val shadowTime = Time.now

    def selectTime = time.getOrElse(shadowTime)
  }

  case class Props(name: String, onChange: Option[Time] => Callback, placeholder: String = "Select Time", time: Option[Time] = None)


  class Backend($: BackendScope[Props, State]) extends OnUnmount {

    private val target = Ref[dom.html.Element]
    private val popover = Ref[dom.html.Element]
    private val hourRef = Ref[dom.html.Element]
    private val minuteRef = Ref[dom.html.Element]
    private val amRef = Ref[dom.html.Element]

    def handleToggle(e: ReactMouseEvent): Callback = for {
      _ <- Callback {
        e.stopPropagation()
      }
      state <- $.state
      _ <- if (state.show) handleHide(state) else handleShow(state)
    } yield ()

    def handleHide(state: State): Callback = for {
      props <- $.props
      _ <- props.onChange(state.time)
      _ <- $.setState(state.copy(show = false))
    } yield ()

    def handleShow(state: State): Callback = for {
      el <- target.get
      _ <- $.setState(state.copy(show = true, top = el.offsetTop, left = el.offsetLeft, time = state.time))
    } yield ()


    def closeOnClick(e: dom.MouseEvent): Callback = for {
      el <- popover.get
      _ <- if (belongsTo(e.target.asInstanceOf[dom.html.Element], el)) Callback.empty else {
        $.state.flatMap(handleHide)
      }
    } yield ()

    def belongsTo(target: dom.html.Element, container: dom.html.Element): Boolean =
      target == container || container.contains(target)

    def handleChange(e: ReactEventFromInput) = Callback {}

    def bound(v: Int, default: Int, f: Int => Boolean): Int = if (f(v)) default else v

    def render(P: Props, S: State) = {

      def value: String | Unit = {
        val time = if (S.show) S.time else P.time
        time.fold[String | Unit](()) {
          case Time(hour, minute, am) => f"$hour%02d:$minute%02d ${if (am) "am" else "pm"}"
        }
      }

      <.div(
        FormGroup()(
          <.div(
            InputGroup()(
              ^.className := "tp-input",
              FormControl(`type` = "text", placeholder = P.placeholder, value = value, onChange = handleChange _)(^.onClick ==> handleToggle),
              InputGroup.Addon()(Glyphicon(glyph = "time")())
            )
          ).withRef(target)
        ),
        <.div(
          ^.classSet1(
            "tp-container",
            "hidden" -> !S.show,
            "show" -> S.show),
          ^.style := js.Dictionary(
            "top" -> s"${S.top}px",
            "left" -> s"${S.left}px"
          ),
          InputGroup()(
            ^.className := "tp-input",
            FormControl(`type` = "text", placeholder = P.placeholder, value = value, onChange = handleChange _),
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
                    ^.classSet("tp-selected" -> (S.selectTime.hour == x)),
                    ^.onClick --> $.modState(s => s.copy(time = Some(s.selectTime.copy(hour = x)))),
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
                    ^.classSet("tp-selected" -> (S.selectTime.minute == x)),
                    ^.onClick --> $.modState(s => s.copy(time = Some(s.selectTime.copy(minute = x)))),
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
                  ^.classSet("tp-selected" -> S.selectTime.am),
                  ^.onClick --> $.modState(s => s.copy(time = Some(s.selectTime.copy(am = true)))),
                  "am"
                ),
                <.li(
                  ^.classSet("tp-selected" -> !S.selectTime.am),
                  ^.onClick --> $.modState(s => s.copy(time = Some(s.selectTime.copy(am = false)))),
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
        g.console.log(s"${current.selectTime}")
        hourEl.scrollTop = (current.selectTime.hour - 1) * 20
        minuteEl.scrollTop = current.selectTime.minute / 10 * 20
        amEl.scrollTop = if (current.selectTime.am) 0 else 20

        {}
      }

    } else Callback {}
  }


  val component = ScalaComponent.builder[Props]("TimePicker")
    .initialStateFromProps(x => State(time = x.time))
    .renderBackend[Backend]
    .componentDidUpdate(x => x.backend.didUpdate(x.prevState, x.currentState))
    .configure(
      EventListener[dom.MouseEvent].install("click", _.backend.closeOnClick, _ => dom.window)
    )
    .build

  def apply(props: Props) = component(props).vdomElement

}

object TimerPickerExample {

  case class Props(time: Option[Time] = None)

  case class SetTime(time: Option[Time]) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      g.console.log("handling action")

      {
        case SetTime(time) => updated(value.copy(time = time))
      }
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("TimePickerExample")
    .render_P { P =>

      g.console.log(s"render: ${P().time}")

      <.div(
        <.div(^.paddingBottom := "10px",
          <.h2("Labels"),
          Panel()(
            Panel.Body()(
              TimePicker(TimePicker.Props(
                "tp",
                onChange = t => {
                  g.console.log("dispatch")
                  P.dispatchCB(SetTime(t))
                },
                time = P().time))
            )
          )
        )
      )
    }.build


  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}
