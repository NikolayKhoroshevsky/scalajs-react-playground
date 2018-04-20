package com.claassen.reactPlayground.wrappers

import org.querki.jquery._

import scala.scalajs.js

@js.native
trait JQueryFullCalendar extends JQuery {
  def fullCalendar(): js.Any = ???
}

object JQueryFullCalendar {
  implicit def jqueryFullCalendarExtensions(): JQueryFullCalendar =
    $.asInstanceOf[JQueryFullCalendar]
}
