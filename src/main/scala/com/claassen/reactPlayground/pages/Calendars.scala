package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.calendars.{BigCalendar, BigCalendarExample, FullCalendar}

object Calendars extends ChapterPage {

  val name = "Calendars"
  val key = "calendars"

  case object MBigCalendar extends Item("Big Calendar", "bigcalendar", () => BigCalendarExample())
  case object MFullCalendar extends Item("Full Calendar", "fullcalendar", () => FullCalendar())

  val menu = Vector(MFullCalendar, MBigCalendar)
}
