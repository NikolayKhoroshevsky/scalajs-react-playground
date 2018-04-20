package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.cnwtests.{BigCalendar, FullCalendar}

object CnWTests extends ChapterPage {
  case object MBigCalendar extends Item("Big Calendar", "bigcalendar", () => BigCalendar())
  case object MFullCalendar extends Item("Full Calendar", "fullcalendar", () => FullCalendar())

  val menu = Vector(MBigCalendar, MFullCalendar)
}
