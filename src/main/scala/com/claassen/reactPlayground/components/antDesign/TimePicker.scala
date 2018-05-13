package com.claassen.reactPlayground.components.antDesign

import java.util.Date

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import js.JSConverters._
import scala.scalajs.js.|
import moment._

import scala.collection.mutable


object TimePicker extends ReactBridgeComponent {

  @JSImport("antd", "TimePicker")
  @js.native
  object RawComponent extends js.Object


  override lazy val componentValue = RawComponent

  def apply(
             allowEmpty: Boolean = true,
             autoFocus: Boolean = false,
             className: String | Unit = {},
             clearText: String = "clear",
             defaultOpenValue: js.UndefOr[moment.Date] = js.undefined,
             //             defaultOpenValue: moment.Date | Unit = {},
             defaultValue: js.UndefOr[moment.Date] = js.undefined,
             //             defaultValue: moment.Date | Unit = {},
             disabled: Boolean = false,
             disabledHours: (() => js.Array[Int]) | Unit = {},
             disabledMinutes: (Int => js.Array[Int]) | Unit = {},
             disabledSeconds: ((Int, Int) => js.Array[Int]) | Unit = {},
             format: String | Unit = {},
             hideDisabledOptions: Boolean = false,
             hourStep: Int = 1,
             inputReadOnly: Boolean = false,
             minuteStep: Int = 1,
             open: Boolean | Unit = {},
             placeholder: String | Unit = {},
             popupClassName: String | Unit = {},
             secondStep: Int = 1,
             use12Hours: Boolean = false,
             //             value: moment.Date | Unit = {},
             onChange: ((moment.Date, String) => Callback) | Unit = {},
             onOpenChange: (Boolean => Callback) | Unit = {}
           ): WithProps = auto
}

object TimePickerExample {

  case class Props(value: Option[moment.Date] = None)

  case class OnChange(value: Option[moment.Date]) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case OnChange(v) => updated(Props(v))
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def handleChange(date: moment.Date, dateString: String): Callback = {
      val d = Option(date)
      $.props.flatMap(_.dispatchCB(OnChange(d)))
    }

    def businessHours(): js.Array[Int] = Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 19, 20, 21, 22, 23).toJSArray

    def render(p: ModelProxy[Props]) = {
      <.div(
        TimePicker(
          format = "hh:mm a",
          minuteStep = 10,
          use12Hours = true,
          disabledHours = businessHours _,
          defaultOpenValue = Moment("10:am", "HH:a"),
          defaultValue = p().value.orUndefined,
          //                  defaultValue = p().value.fold[moment.Date | Unit](())(x => x),
          onChange = handleChange _

        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("TimePickerExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}