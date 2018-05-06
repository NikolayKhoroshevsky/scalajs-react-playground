package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Table extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Table", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(striped: Boolean =false,
            bordered: Boolean =false,
            condensed: Boolean =false,
            hover: Boolean =false,
            responsive: Boolean =false,
            bsClass: String | Unit = {}): WithProps = auto
}

object TableExample {

  val component = ScalaComponent.static("WellExample")(
    <.div(
      <.div(^.paddingBottom := "10px",
        <.h2("Tables"),
        Panel()(
          Panel.Body()(
            Table(striped = true, bordered = true, condensed = true, hover = true)(
              <.thead(
                <.tr(
                  <.th("#"),
                  <.th("First Name"),
                  <.th("Last Name"),
                  <.th("Username"),
                )
              ),
              <.tbody(
                <.tr(
                  <.td("1"),
                  <.td("Mark"),
                  <.td("Otto"),
                  <.td("@mdo"),
                ),
                <.tr(
                  <.td("2"),
                  <.td("Jacob"),
                  <.td("Thornton"),
                  <.td("@fat"),
                ),
                <.tr(
                  <.td("3"),
                  <.td(^.colSpan := 2, "Larry the Bird"),
                  <.td("@twitter"),
                )
              )
            )
          )
        )
      ),
      <.div(
        <.div(^.paddingBottom := "10px",
          <.h2("Reactive"),
          Panel()(
            Panel.Body()(
              Table(responsive = true)(
                <.thead(
                  <.tr(
                    <.th("#"),
                    <.th("Table heading"),
                    <.th("Table heading"),
                    <.th("Table heading"),
                    <.th("Table heading"),
                    <.th("Table heading"),
                    <.th("Table heading"),
                  ),
                ),
                <.tbody(
                  <.tr(
                    <.td("1"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                  ),
                  <.tr(
                    <.td("2"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                  ),
                  <.tr(
                    <.td("3"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                    <.td("Table cell"),
                  )
                )
              )
            )
          )
        )
      )
    )
  )

  def apply() = component().vdomElement
}
