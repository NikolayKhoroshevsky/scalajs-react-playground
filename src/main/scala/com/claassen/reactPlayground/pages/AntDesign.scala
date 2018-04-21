package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.antDesign.{BadgeExample, DatePickerExample}


object AntDesign extends ChapterPage {

  case object MBadge
    extends Item("Badge", "badge", () => BadgeExample(3))

  case object MDatePicker
    extends Item("DatePicker", "datepicker", () => DatePickerExample())

  val menu = Vector(MBadge, MDatePicker)
}

