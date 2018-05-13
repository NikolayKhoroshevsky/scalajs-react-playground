package com.claassen.reactPlayground.components.misc

import japgolly.scalajs.react._
import com.claassen.reactPlayground.components.reactBootstrap._
import japgolly.scalajs.react.vdom.html_<^._
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}
import org.scalajs.dom.ext.Ajax
import scala.scalajs.js

case class Contact(first: String, last: String, email: String)

case class DataTableProps[A](data: List[A])

import scala.concurrent.ExecutionContext.Implicits.global
import kantan.csv._
import kantan.csv.ops._

object DataTable {

  case class Props(users: List[Contact])

  case class State(pageSize: Int = 10, currentPage: Int = 0)

  class Backend($: BackendScope[Props, State]) {

    def render(P: Props, S: State) = {

      Table(striped = true, bordered = true, condensed = true, hover = true)(
        <.thead(
          <.tr(
            <.th("First Name ",
              <.span(^.style := js.Dynamic.literal(color = "#aaaaaa"),
                Glyphicon(glyph = "triangle-top"), Glyphicon(glyph = "triangle-bottom")
              )
            ),
            <.th("Last Name ",
              <.span(^.style := js.Dynamic.literal(color = "#aaaaaa"),
                Glyphicon(glyph = "triangle-top"), Glyphicon(glyph = "triangle-bottom")
              )
            ),
            <.th("Email ",
              <.span(^.style := js.Dynamic.literal(color = "#aaaaaa"),
                Glyphicon(glyph = "triangle-top"), Glyphicon(glyph = "triangle-bottom")
              )
            )
          )
        ),
        <.tbody(
          P.users.slice(S.currentPage * S.pageSize, S.currentPage * S.pageSize + S.pageSize)
            .map { user =>
              <.tr(
                <.td(user.first),
                <.td(user.last),
                <.td(user.email)
              )
            }: _*
        )
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

