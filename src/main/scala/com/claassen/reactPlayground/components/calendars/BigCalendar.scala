package com.claassen.reactPlayground.components.calendars

import java.util.Date

import com.payalabs.scalajs.react.bridge.{ReactBridgeComponent, WithProps}
import japgolly.scalajs.react.{BackendScope, _}
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}
import scala.scalajs.js.annotation.JSImport
import moment._

object BigCalendar extends ReactBridgeComponent {

  @JSImport("react-big-calendar", JSImport.Default)
  @js.native
  object RawComponent extends js.Object {
    def momentLocalizer(m: js.Any): Unit = js.native
  }

  override lazy val componentValue = {
    RawComponent.momentLocalizer(Moment)
    RawComponent
  }

  def apply(/**
              * Props passed to main calendar `<div>`.
              *
              */
            elementProps: js.UndefOr[js.Object] = js.undefined,

            defaultDate: js.UndefOr[js.Date] = js.undefined,

            /**
              * The current date value of the calendar. Determines the visible view range.
              * If `date` is omitted then the result of `getNow` is used; otherwise the
              * current date is used.
              *
              * &controllable onNavigate
              */
            date: js.UndefOr[js.Date] = js.undefined,

            /**
              * The current view of the calendar.
              *
              * &default 'month'
              * &controllable onView
              */
            view: js.UndefOr[String] = js.undefined,

            /**
              * The initial view set for the Calendar.
              * &type Calendar.Views ('month'|'week'|'work_week'|'day'|'agenda')
              * &default 'month'
              */
            defaultView: js.UndefOr[String] = js.undefined,

            /**
              * An array of event objects to display on the calendar. Events objects
              * can be any shape, as long as the Calendar knows how to retrieve the
              * following details of the event:
              *
              *  - start time
              *  - end time
              *  - title
              *  - whether its an "all day" event or not
              *  - any resource the event may be a related too
              *
              * Each of these properties can be customized or generated dynamically by
              * setting the various "accessor" props. Without any configuration the default
              * event should look like:
              *
              * ```js
              * Event {
              * title: string,
              * start: Date,
              * end: Date,
              * allDay?: boolean
              * resource?: any,
              * }
              * ```
              */
            events: js.UndefOr[js.Array[js.Object]] = js.undefined,

            /**
              * Accessor for the event title, used to display event information. Should
              * resolve to a `renderable` value.
              *
              * ```js
              * string | (event: Object) => string
              * ```
              *
              * &type {(func|string)}
              */
            titleAccessor: js.UndefOr[String] = js.undefined,

            /**
              * Accessor for the event tooltip. Should
              * resolve to a `renderable` value. Removes the tooltip if null.
              *
              * ```js
              * string | (event: Object) => string
              * ```
              *
              * &type {(func|string)}
              */
            tooltipAccessor: js.UndefOr[String] = js.undefined,

            /**
              * Determines whether the event should be considered an "all day" event and ignore time.
              * Must resolve to a `boolean` value.
              *
              * ```js
              * string | (event: Object) => boolean
              * ```
              *
              * &type {(func|string)}
              */
            allDayAccessor: js.UndefOr[String] = js.undefined,

            /**
              * The start date/time of the event. Must resolve to a JavaScript `Date` object.
              *
              * ```js
              * string | (event: Object) => Date
              * ```
              *
              * &type {(func|string)}
              */
            startAccessor: js.UndefOr[String] = js.undefined,

            /**
              * The end date/time of the event. Must resolve to a JavaScript `Date` object.
              *
              * ```js
              * string | (event: Object) => Date
              * ```
              *
              * &type {(func|string)}
              */
            endAccessor: js.UndefOr[String] = js.undefined,

            /**
              * Returns the id of the `resource` that the event is a member of. This
              * id should match at least one resource in the `resources` array.
              *
              * ```js
              * string | (event: Object) => Date
              * ```
              *
              * &type {(func|string)}
              */
            resourceAccessor: js.UndefOr[String] = js.undefined,

            /**
              * An array of resource objects that map events to a specific resource.
              * Resource objects, like events, can be any shape or have any properties,
              * but should be uniquly identifiable via the `resourceIdAccessor`, as
              * well as a "title" or name as provided by the `resourceTitleAccessor` prop.
              */
            resources: js.UndefOr[js.Array[js.Object]] = js.undefined,

            /**
              * Provides a unique identifier for each resource in the `resources` array
              *
              * ```js
              * string | (resource: Object) => any
              * ```
              *
              * &type {(func|string)}
              */
            resourceIdAccessor: js.UndefOr[String] = js.undefined,

            /**
              * Provides a human readable name for the resource object, used in headers.
              *
              * ```js
              * string | (resource: Object) => any
              * ```
              *
              * &type {(func|string)}
              */
            resourceTitleAccessor: js.UndefOr[String] = js.undefined,

            /**
              * Determines the current date/time which is highlighted in the views.
              *
              * The value affects which day is shaded and which time is shown as
              * the current time. It also affects the date used by the Today button in
              * the toolbar.
              *
              * Providing a value here can be useful when you are implementing time zones
              * using the `startAccessor` and `endAccessor` properties.
              *
              * &type {func}
              * &default () => new Date()
              */
            getNow: js.UndefOr[() => js.Date] = js.undefined,

            /**
              * Callback fired when the `date` value changes.
              *
              * &controllable date
              */
            onNavigate: js.UndefOr[() => Callback] = js.undefined,

            /**
              * Callback fired when the `view` value changes.
              *
              * &controllable view
              */
            onView: js.UndefOr[() => Callback] = js.undefined,

            /**
              * Callback fired when date header, or the truncated events links are clicked
              *
              */
            onDrillDown: js.UndefOr[() => Callback] = js.undefined,

            /**
              * Callback fired when the visible date range changes. Returns an Array of dates
              * or an object with start and end dates for BUILTIN views.
              *
              * Cutom views may return something different.
              */
            onRangeChange: js.UndefOr[() => Callback] = js.undefined,

            /**
              * A callback fired when a date selection is made. Only fires when `selectable` is `true`.
              *
              * ```js
              * (
              * slotInfo: {
              * start: Date,
              * end: Date,
              * slots: Array<Date>,
              * action: "select" | "click" | "doubleClick",
              * bounds: ?{ // For "select" action
              * x: number,
              * y: number,
              * top: number,
              * right: number,
              * left: number,
              * bottom: number,
              * },
              * box: ?{ // For "click" or "doubleClick" actions
              * clientX: number,
              * clientY: number,
              * x: number,
              * y: number,
              * },
              * }
              * ) => any
              * ```
              */
            onSelectSlot: js.UndefOr[() => Unit] = js.undefined,

            /**
              * Callback fired when a calendar event is selected.
              *
              * ```js
              * (event: Object, e: SyntheticEvent) => any
              * ```
              *
              * &controllable selected
              */
            onSelectEvent: js.UndefOr[() => Unit] = js.undefined,

            /**
              * Callback fired when a calendar event is clicked twice.
              *
              * ```js
              * (event: Object, e: SyntheticEvent) => void
              * ```
              */
            onDoubleClickEvent: js.UndefOr[() => Unit] = js.undefined,

            /**
              * Callback fired when dragging a selection in the Time views.
              *
              * Returning `false` from the handler will prevent a selection.
              *
              * ```js
              * (range: { start: Date, end: Date }) => ?boolean
              * ```
              */
            onSelecting: js.UndefOr[() => Unit] = js.undefined,

            /**
              * The selected event, if any.
              */
            selected: js.UndefOr[js.Object] = js.undefined,

            /**
              * An array of built-in view names to allow the calendar to display.
              * accepts either an array of builtin view names,
              *
              * ```jsx
              * views={['month', 'day', 'agenda']}
              * ```
              * or an object hash of the view name and the component (or boolean for builtin).
              *
              * ```jsx
              * views={{
              * month: true,
              * week: false,
              * myweek: WorkWeekViewComponent,
              * }}
              * ```
              *
              * Custom views can be any React component, that implements the following
              * interface:
              *
              * ```js
              * interface View {
              * static title(date: Date, { formats: DateFormat[], culture: string?, ...props }): string
              * static navigate(date: Date, action: 'PREV' | 'NEXT' | 'DATE'): Date
              * }
              * ```
              *
              * &type Calendar.Views ('month'|'week'|'work_week'|'day'|'agenda')
              * &View
              * ['month', 'week', 'day', 'agenda']
              */
            //views: componentViews,

            /**
              * The string name of the destination view for drill-down actions, such
              * as clicking a date header, or the truncated events links. If
              * `getDrilldownView` is also specified it will be used instead.
              *
              * Set to `null` to disable drill-down actions.
              *
              * ```js
              * <BigCalendar
              * drilldownView="agenda"
              * />
              * ```
              */
            drilldownView: js.UndefOr[String] = js.undefined,

            /**
              * Functionally equivalent to `drilldownView`, but accepts a function
              * that can return a view name. It's useful for customizing the drill-down
              * actions depending on the target date and triggering view.
              *
              * Return `null` to disable drill-down actions.
              *
              * ```js
              * <BigCalendar
              * getDrilldownView={(targetDate, currentViewName, configuredViewNames) =>
              * if (currentViewName === 'month' && configuredViewNames.includes('week'))
              * return 'week'
              *
              * return null;
              * }}
              * />
              * ```
              */
            getDrilldownView: js.UndefOr[() => Unit] = js.undefined,

            /**
              * Determines the end date from date prop in the agenda view
              * date prop + length (in number of days) = end date
              */
            length: js.UndefOr[Int] = js.undefined,

            /**
              * Determines whether the toolbar is displayed
              */
            toolbar: js.UndefOr[Boolean] = js.undefined,

            /**
              * Show truncated events in an overlay when you click the "+_x_ more" link.
              */
            popup: js.UndefOr[Boolean] = js.undefined,

            /**
              * Distance in pixels, from the edges of the viewport, the "show more" overlay should be positioned.
              *
              * ```jsx
              * <BigCalendar popupOffset={30}/>
              * <BigCalendar popupOffset={{x: 30, y: 20}}/>
              * ```
              */
            //    popupOffset: js.PropTypes.oneOfType([
            //      PropTypes.number,
            //      PropTypes.shape({ x: PropTypes.number, y: PropTypes.number }),
            //    ]),

            /**
              * Allows mouse selection of ranges of dates/times.
              *
              * The 'ignoreEvents' option prevents selection code from running when a
              * drag begins over an event. Useful when you want custom event click or drag
              * logic
              */
            selectable: js.UndefOr[Boolean] = js.undefined, //PropTypes.oneOf([true, false, 'ignoreEvents']),

            /** Determines whether you want events to be resizable */
            resizable: js.UndefOr[Boolean] = js.undefined,

            /**
              * Specifies the number of miliseconds the user must press and hold on the screen for a touch
              * to be considered a "long press." Long presses are used for time slot selection on touch
              * devices.
              *
              * &type {number}
              * &default 250
              */
            longPressThreshold: js.UndefOr[Int] = js.undefined,

            /**
              * Determines the selectable time increments in week and day views
              */
            step: js.UndefOr[Int] = js.undefined,

            /**
              * The number of slots per "section" in the time grid views. Adjust with `step`
              * to change the default of 1 hour long groups, with 30 minute slots.
              */
            timeslots: js.UndefOr[Int] = js.undefined,

            /**
              * Switch the calendar to a `right-to-left` read direction.
              */
            rtl: js.UndefOr[Boolean] = js.undefined,

            /**
              * Optionally provide a function that returns an object of className or style props
              * to be applied to the the event node.
              *
              * ```js
              * (
              * event: Object,
              * start: Date,
              * end: Date,
              * isSelected: boolean
              * ) => { className?: string, style?: Object }
              * ```
              */
            eventPropGetter: js.UndefOr[(Event, js.Date, js.Date, Boolean) => js.Object] = js.undefined,

            /**
              * Optionally provide a function that returns an object of className or style props
              * to be applied to the the time-slot node. Caution! Styles that change layout or
              * position may break the calendar in unexpected ways.
              *
              * ```js
              * (date: Date) => { className?: string, style?: Object }
              * ```
              */
            slotPropGetter: js.UndefOr[() => Unit] = js.undefined,

            /**
              * Optionally provide a function that returns an object of className or style props
              * to be applied to the the day background. Caution! Styles that change layout or
              * position may break the calendar in unexpected ways.
              *
              * ```js
              * (date: Date) => { className?: string, style?: Object }
              * ```
              */
            dayPropGetter: js.UndefOr[() => Unit] = js.undefined,

            /**
              * Support to show multi-day events with specific start and end times in the
              * main time grid (rather than in the all day header).
              *
              * **Note: This may cause calendars with several events to look very busy in
              * the week and day views.**
              */
            showMultiDayTimes: js.UndefOr[() => Unit] = js.undefined,

            /**
              * Constrains the minimum _time_ of the Day and Week views.
              */
            min: js.UndefOr[js.Date] = js.undefined,

            /**
              * Constrains the maximum _time_ of the Day and Week views.
              */
            max: js.UndefOr[js.Date] = js.undefined,

            /**
              * Determines how far down the scroll pane is initially scrolled down.
              */
            scrollToTime: js.UndefOr[js.Date] = js.undefined,

            /**
              * Specify a specific culture code for the Calendar.
              *
              * **Note: it's generally better to handle this globally via your i18n library.**
              */
            culture: js.UndefOr[String] = js.undefined,

            /**
              * Localizer specific formats, tell the Calendar how to format and display dates.
              *
              * `format` types are dependent on the configured localizer; both Moment and Globalize
              * accept strings of tokens according to their own specification, such as: `'DD mm yyyy'`.
              *
              * ```jsx
              * let formats = {
              * dateFormat: 'dd',
              *
              * dayFormat: (date, culture, localizer) =>
              *     localizer.format(date, 'DDD', culture),
              *
              * dayRangeHeaderFormat: ({ start, end }, culture, local) =>
              *     local.format(start, { date: 'short' }, culture) + ' — ' +
              *     local.format(end, { date: 'short' }, culture)
              * }
              *
              * <Calendar formats={formats} />
              * ```
              *
              * All localizers accept a function of
              * the form `(date: Date, culture: ?string, localizer: Localizer) -> string`
              */
            /*
                formats: PropTypes.shape({
                  /**
                    * Format for the day of the month heading in the Month view.
                    * e.g. "01", "02", "03", etc
                    */
                  dateFormat,

                  /**
                    * A day of the week format for Week and Day headings,
                    * e.g. "Wed 01/04"
                    *
                    */
                  dayFormat: dateFormat,

                  /**
                    * Week day name format for the Month week day headings,
                    * e.g: "Sun", "Mon", "Tue", etc
                    *
                    */
                  weekdayFormat: dateFormat,

                  /**
                    * The timestamp cell formats in Week and Time views, e.g. "4:00 AM"
                    */
                  timeGutterFormat: dateFormat,

                  /**
                    * Toolbar header format for the Month view, e.g "2015 April"
                    *
                    */
                  monthHeaderFormat: dateFormat,

                  /**
                    * Toolbar header format for the Week views, e.g. "Mar 29 - Apr 04"
                    */
                  dayRangeHeaderFormat: dateRangeFormat,

                  /**
                    * Toolbar header format for the Day view, e.g. "Wednesday Apr 01"
                    */
                  dayHeaderFormat: dateFormat,

                  /**
                    * Toolbar header format for the Agenda view, e.g. "4/1/2015 — 5/1/2015"
                    */
                  agendaHeaderFormat: dateRangeFormat,

                  /**
                    * A time range format for selecting time slots, e.g "8:00am — 2:00pm"
                    */
                  selectRangeFormat: dateRangeFormat,

                  agendaDateFormat: dateFormat,
                  agendaTimeFormat: dateFormat,
                  agendaTimeRangeFormat: dateRangeFormat,

                  /**
                    * Time range displayed on events.
                    */
                  eventTimeRangeFormat: dateRangeFormat,

                  /**
                    * An optional event time range for events that continue onto another day
                    */
                  eventTimeRangeStartFormat: dateFormat,

                  /**
                    * An optional event time range for events that continue from another day
                    */
                  eventTimeRangeEndFormat: dateFormat,
                }),
            */

            /**
              * Customize how different sections of the calendar render by providing custom Components.
              * In particular the `Event` component can be specified for the entire calendar, or you can
              * provide an individual component for each view type.
              *
              * ```jsx
              * let components = {
              * event: MyEvent, // used by each view (Month, Day, Week)
              * toolbar: MyToolbar,
              * agenda: {
              * event: MyAgendaEvent // with the agenda view use a different component to render events
              * }
              * }
              * <Calendar components={components} />
              * ```
              */
            /*
                components: PropTypes.shape({
                  event: elementType,
                  eventWrapper: elementType,
                  dayWrapper: elementType,
                  dateCellWrapper: elementType,

                  toolbar: elementType,

                  agenda: PropTypes.shape({
                    date: elementType,
                    time: elementType,
                    event: elementType,
                  }),

                  day: PropTypes.shape({
                    header: elementType,
                    event: elementType,
                  }),
                  week: PropTypes.shape({
                    header: elementType,
                    event: elementType,
                  }),
                  month: PropTypes.shape({
                    header: elementType,
                    dateHeader: elementType,
                    event: elementType,
                  }),
                }),
            */

            /**
              * String messages used throughout the component, override to provide localizations
              */
            /*
                messages: PropTypes.shape({
                  allDay: PropTypes.node,
                  previous: PropTypes.node,
                  next: PropTypes.node,
                  today: PropTypes.node,
                  month: PropTypes.node,
                  week: PropTypes.node,
                  day: PropTypes.node,
                  agenda: PropTypes.node,
                  date: PropTypes.node,
                  time: PropTypes.node,
                  event: PropTypes.node,
                  showMore: PropTypes.func,
                }),
            */): WithProps = auto
}

class Event(val id: Int,
            val title: String,
            val start: js.Date,
            val end: js.Date,
            val allDay: Boolean) extends js.Object

object BigCalendarExample {

  val events = (0 to 30).map { x =>
    val start = Moment().startOf("month").add(x, "days")
    val end = Moment(start).add(1, "hours")
    new Event(x, s"Event [$x]", start.toDate(), end.toDate(), allDay = false)
  }.toList

  case class Props(events: List[Event])

  class Backend($: BackendScope[Props, Unit]) {

    def eventProps(event: Event, start: js.Date, end: js.Date, selected: Boolean) = {
      g.console.log(s"id: ${event.id}, start $start, end: $end, selected: $selected")
      js.Dynamic.literal()
    }

    def render(p: Props) = {

      def getEvents(): js.Array[js.Object] = js.Array(p.events: _*)

      <.div(^.style := js.Dynamic.literal(height = "600px", width = "1000px"),
        "Calendar",
        BigCalendar(
          defaultDate = Moment().toDate(),
          events = getEvents(),
          eventPropGetter = eventProps _
        )
      )
    }
  }

  val component = ScalaComponent.builder[Props]("BigCalendarExample")
    .renderBackend[Backend]
    .build

  def apply() = component(Props(events)).vdomElement
}
