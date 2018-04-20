package com.claassen.reactPlayground.components.cnwtests

import diode.{Action, ActionHandler, Circuit}
import diode.react.{ModelProxy, ReactConnector}
import japgolly.scalajs.react.BackendScope
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}

object FullCalendar {

  case class RootModel(calendar: CalendarModel)

  case class CalendarModel(events: List[String], view: String)

  case class ChangeView(view: String) extends Action

  object FullCalendar2 {

    class Backend($: BackendScope[ModelProxy[CalendarModel], String]) {

      def start = Callback {
        g.console.log("init calendar")
        val args = js.Dynamic.literal(
          editable = false,
          selectable = true,
          header = js.Dynamic.literal(
            left = "prev,next today",
            center = "title",
            right = "month,agendaWeek,agendaDay"
          ),
          eventLimit = true,
          events = { (start: js.Date, end: js.Date, callback: js.Dynamic) =>
            g.console.log(start)
            g.console.log(end)
            callback(js.Array(
              js.Dynamic.literal(
                start = "2018-04-08T15:30:00-07:00",
                end = "2018-04-08T16:30:00-07:00",
                allDay = false,
                title = "Test"
              )))
          },
          viewRender = { (view: js.Dynamic, el: js.Object) =>
            g.console.log(view.name)
            val v = view.name.asInstanceOf[String]
            //$.props.runNow().dispatchCB(ChangeView(v))
            $.setState(v).runNow()
          }
        )
        g.$("#calendar").fullCalendar(args)
      }

      def render(p: ModelProxy[CalendarModel], s: String) = {
        <.div(
          <.div(^.id := "calendar"),
          <.div("view: ", s)
        )
      }
    }

    val component = ScalaComponent.builder[ModelProxy[CalendarModel]]("Calendar")
        .initialState("??")
      .renderBackend[Backend]
      .componentDidMount(_.backend.start)
      .build

    def apply(props: ModelProxy[CalendarModel]) = component(props)
  }

  object AppCircuit extends Circuit[RootModel] with ReactConnector[RootModel] {
    // define initial value for the application model
    def initialModel = RootModel(CalendarModel(Nil, "unknown"))

    val counterHandler = new ActionHandler(zoomTo(_.calendar.view)) {
      override def handle = {
        case ChangeView(view) => updated(view)
      }
    }

    override protected def actionHandler: AppCircuit.HandlerFunction = counterHandler
  }

  val connection = AppCircuit.connect(_.calendar)

  def apply() = connection(FullCalendar2(_)).vdomElement
}

