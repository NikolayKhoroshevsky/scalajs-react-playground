package com.claassen.reactPlayground.components.antDesign

import java.util.Date

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
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
            dateRender: js.UndefOr[js.Function2[String,String,js.Object]] = js.undefined,
            disabledData: js.UndefOr[js.Function1[String,Boolean]] = js.undefined,
            getCalendarContainer: js.UndefOr[js.Function] = js.undefined,
            locale: js.UndefOr[js.Object] = js.undefined,
            placeHolder: String = "",
            popupStyle: js.UndefOr[js.Object] = js.undefined,
            dropdownClassName: String = "",
            size: String = "",
            style: js.UndefOr[js.Object] = js.undefined,
            onOpenChange: js.UndefOr[Boolean => Callback] = js.undefined,
            defaultValue: js.UndefOr[moment.Date] = js.undefined,
            disabledTime: js.UndefOr[js.Function1[js.Date,Boolean]] = js.undefined,
            format: String = "YYYY-MM-DD",
            renderExtraFooter: js.UndefOr[js.Function0[js.Object]] = js.undefined,
            showTime: Boolean = false,
            showToday: Boolean = true,
            value: js.UndefOr[moment.Date] = js.undefined): WithProps = auto
}

object DatePickerExample {

  class Backend($: BackendScope[Unit, Unit]) {

    def render(p: Unit, s: Unit) = {
      <.div(^.style := js.Dynamic.literal(height = "600px"),
        DatePicker()
      )
    }
  }

  val component = ScalaComponent.builder[Unit]("DatePickerExample")
    .renderBackend[Backend]
    .build

  def apply() = component().vdomElement
}