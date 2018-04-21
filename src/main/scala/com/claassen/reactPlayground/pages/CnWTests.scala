package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.cnwtests.{BigCalendar, BigCalendarExample, FullCalendar}

object CnWTests extends ChapterPage {
  case object MBigCalendar extends Item("Big Calendar", "bigcalendar", () => BigCalendarExample())
  case object MFullCalendar extends Item("Full Calendar", "fullcalendar", () => FullCalendar())

  val menu = Vector(MFullCalendar, MBigCalendar)
}
