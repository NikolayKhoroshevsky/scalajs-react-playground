package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.antDesign.{BadgeExample, DatePickerExample, TimePickerExample}


object AntDesign extends ChapterPage {

  case object MBadge
    extends Item("Badge", "badge", () => BadgeExample(3))

  case object MDatePicker
    extends Item("DatePicker", "datepicker", () => DatePickerExample())

  case object MTimePicker
    extends Item("TimePicker", "timepicker", () => TimePickerExample())

  val menu = Vector(MBadge, MDatePicker, MTimePicker)
}

