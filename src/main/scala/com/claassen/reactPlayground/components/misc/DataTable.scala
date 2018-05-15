package com.claassen.reactPlayground.components.misc

import japgolly.scalajs.react._
import com.claassen.reactPlayground.components.reactBootstrap._
import japgolly.scalajs.react.vdom.html_<^._
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import org.scalajs.dom.ext.Ajax

import scala.scalajs.js
import scala.scalajs.js.Dynamic.{global => g}
import scala.concurrent.ExecutionContext.Implicits.global
import kantan.csv._
import kantan.csv.ops._

import scala.scalajs.js.|

case class Contact(first: String, last: String, email: String)

case class DataTableProps[A](data: List[A])


object DataTable {

  case class Props(users: List[Contact])

  case class State(pageSize: Int = 10,
                   currentPage: Int = 0,
                   sortBy: Option[String] = None,
                   filter: Option[String] = None)

  class Backend($: BackendScope[Props, State]) {

    def handlePaging(page: Int): Callback = $.modState(_.copy(currentPage = page))

    def handleSort(sortBy: String): Callback = $.modState(_.copy(sortBy = Some(sortBy)))

    def handleClear(e: ReactMouseEvent): Callback = $.modState(_.copy(filter = None, currentPage = 0))

    def handlePageSizeChange(v: js.Any) = {
      $.modState(_.copy(pageSize = v.toString.toInt,currentPage = 0))
    }

    def handleFilterChange(e: ReactEventFromInput) = {
      val value = e.target.value
      if (value == null || value.isEmpty) {
        $.modState(_.copy(filter = None, currentPage = 0))
      } else {
        $.modState(_.copy(filter = Some(value.toLowerCase), currentPage = 0))
      }
    }

    def render(P: Props, S: State) = {
      val filterValue = S.filter.getOrElse("")
      val filtered = S.filter match {
        case None =>
          P.users
        case Some(filter) =>
          P.users.filter { x =>
            x.first.toLowerCase.contains(filter) ||
              x.last.toLowerCase.contains(filter) ||
              x.email.toLowerCase.contains(filter)
          }
      }
      val pages = Math.ceil(filtered.length.toDouble / S.pageSize).toInt
      val sorted = S.sortBy match {
        case None => filtered
        case Some(orderPlusfield) =>
          val asc = if (orderPlusfield.head == '+') true else false
          val field = orderPlusfield.tail
          val ordering = field match {
            case "first" => Ordering.by((_: Contact).first)
            case "last" => Ordering.by((_: Contact).last)
            case "email" => Ordering.by((_: Contact).email)
          }
          filtered.sorted(if (asc) ordering else ordering.reverse)
      }

      def sortChevron(sortField: String) = {
        def sortClass(name: String) = if (S.sortBy.contains(name)) "sortby" else "sortable"

        <.span(
          ^.className := sortClass(sortField),
          ^.onClick --> handleSort(sortField),
          Glyphicon(glyph = s"triangle-${if (sortField.head == '+') "top" else "bottom"}")
        )
      }

      def fieldHeader(title: String, sortField: String) =
        <.th(title,
          sortChevron(s"+$sortField"),
          sortChevron(s"-$sortField")
        )


      <.div(
        <.form()(
          FormGroup()(
            InputGroup()(
              InputGroup.Addon()(Glyphicon(glyph = "search")()),
              FormControl(
                `type` = "text",
                value = filterValue,
                onChange = handleFilterChange _)(),
              InputGroup.Button()(Button(onClick = handleClear _)("Clear"))
            )
          )
        ),
        Table(striped = true, bordered = true, condensed = true, hover = true)(
          <.thead(
            <.tr(
              fieldHeader("First Name ", "first"),
              fieldHeader("Last Name ", "last"),
              fieldHeader("Email", "email")
            )
          ),
          <.tbody(
            sorted.slice(S.currentPage * S.pageSize, S.currentPage * S.pageSize + S.pageSize)
              .map { user =>
                <.tr(
                  <.td(user.first),
                  <.td(user.last),
                  <.td(user.email)
                )
              }: _*
          )
        ),

        <.div( ^.className := "float-right",
        ButtonToolbar()(
          ToggleButtonGroup(
            defaultValue = S.pageSize,
            name = "pageSize",
            onChange = handlePageSizeChange _
          )(
            ToggleButton(value = 10)("10"),
            ToggleButton(value = 25)("25"),
            ToggleButton(value = 50)("50")
          )
        )
        ),

          // TODO: only show when there is more items than pageSize
        FinitePagination(FinitePagination.Props(pages, 5, S.currentPage, handlePaging))
      )
    }
  }

  val component = ScalaComponent.builder[Props]("DataTable")
    .initialState(State())
    .renderBackend[Backend]
    .build

  def apply(data: List[Contact]) = component(Props(data)).vdomElement


}

object DataTableExample {

  case class Props(users: List[Contact] = Nil, source: String = "/classes/data/sample.csv")

  case class Loaded(users: List[Contact]) extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case Loaded(users) =>
        println(s"updating with ${users.length} users")
        updated(value.copy(users = users))
    }
  }

  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    def render(P: ModelProxy[Props]) = {
      val props = P()

      if (props.users.isEmpty) {
        println(s"loading '${props.source}'")
        Ajax.get(props.source).onSuccess {
          case xhr =>
            println(s"loaded '${props.source}'")
            type Row = (String, String, String)
            val contacts = xhr.responseText.asCsvReader[Row](rfc.withHeader).map(_.toOption).flatten
              .map(Contact.tupled).toList
            $.props.flatMap(_.dispatchCB(Loaded(contacts))).runNow()
        }
      }
      println("refreshing table")
      <.div(
        <.h1("Data Table"),
        DataTable(props.users)
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("DataTableExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}

