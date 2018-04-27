package com.claassen.reactPlayground.components.antDesign

import java.util.Date

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import diode.react.ModelProxy
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.Dynamic.{global => g}
import js.JSConverters._
import moment._

object DatePicker extends ReactBridgeComponent {

  @JSImport("antd", "DatePicker")
  @js.native
  object RawComponent extends js.Object


  override lazy val componentValue = RawComponent

  def apply(allowClear: Boolean = true,
            autoFocus: Boolean = false,
            className: String = "",
            disabled: Boolean = false,
            open: js.UndefOr[Boolean] = js.undefined,
            dateRender: js.UndefOr[js.Function2[String, String, js.Object]] = js.undefined,
            disabledDate: js.UndefOr[js.UndefOr[moment.Date] => Boolean] = js.undefined,
            getCalendarContainer: js.UndefOr[js.Function] = js.undefined,
            locale: js.UndefOr[js.Object] = js.undefined,
            placeHolder: String = "",
            popupStyle: js.UndefOr[js.Object] = js.undefined,
            dropdownClassName: String = "",
            size: String = "",
            style: js.UndefOr[js.Object] = js.undefined,
            onOpenChange: js.UndefOr[Boolean => Callback] = js.undefined,
            defaultValue: js.UndefOr[moment.Date] = js.undefined,
            disabledTime: js.UndefOr[moment.Date => Boolean] = js.undefined,
            format: String = "YYYY-MM-DD",
            renderExtraFooter: js.UndefOr[js.Function0[js.Object]] = js.undefined,
            showTime: Boolean = false,
            showToday: Boolean = true,
            value: js.UndefOr[moment.Date] = js.undefined,
            onCalendarChange: js.UndefOr[(js.UndefOr[moment.Date], js.UndefOr[moment.Date], String, String) => Callback] = js.undefined,
            onChange: js.UndefOr[(js.UndefOr[moment.Date], String) => Callback] = js.undefined,
            onOk: js.UndefOr[Callback] = js.undefined): WithProps = auto
}

object DatePickerExample {

  case class Props(value: Option[moment.Date] = None)

  case class OnChange(value: Option[moment.Date]) extends Action

  class Handler[M](modelRW: ModelRW[M,Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case OnChange(v) => updated(Props(v))
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def handleChange(date: js.UndefOr[moment.Date], dateString: String): Callback = {
      val d = date.toOption.filter(_ != null)
      g.console.log(d.toString)
      g.console.log(date)
      $.props.flatMap(_.dispatchCB(OnChange(d)))
    }


    def checkDisabledDate(date: js.UndefOr[moment.Date]): Boolean = {
      date.toOption.filter(_ != null).exists(x => Set(0, 6).contains(x.day()))
    }

    def render(p: ModelProxy[Props]) = {
      val currentValue: String = p().value.map(_.format()).getOrElse("None")

      <.div(
        <.div(
          DatePicker(defaultValue = p().value.orUndefined,
            disabledDate = checkDisabledDate _,
            onChange = handleChange _
          )
        ),
        <.div(
          <.label("Value: "), currentValue
        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("DatePickerExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}