package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Grid extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Grid", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(componentClass: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object Row extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Row", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(className: String | Unit = {},
            componentClass: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object Col extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Col", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(xs: Int | Unit = {},
            xsHidden: Boolean | Unit = {},
            xsOffset: Int | Unit = {},
            md: Int | Unit = {},
            mdPush: Int | Unit = {},
            mdPull: Int | Unit = {},
            sm: Int | Unit = {},
            smOffset: Int | Unit = {},
            componentClass: String | Unit = {},
            bsClass: String | Unit = {}): WithProps = auto
}

object LayoutExample {

  val component = ScalaComponent.static("LayoutExample")(
    <.div(^.paddingBottom := "10px",
      <.h2("Basic Grid"),
      Panel()(
        Panel.Body()(
          Grid()(
            Row(className = "show-grid")(
              Col(xs = 12, md = 8)(
                <.code("<Col xs=12 md=8>")
              ),
              Col(xs = 6, md = 4)(
                <.code("<Col xs=6 md=4")
              )
            ),
            Row(className = "show-grid")(
              Col(xs = 6, md = 4)(
                <.code("<Col xs=6 md=4>")
              ),
              Col(xs = 6, md = 4)(
                <.code("<Col xs=6 md=4>")
              ),
              Col(xsHidden = true, md = 4)(
                <.code("<Col xsHidden md=4>")
              )
            ),

            Row(className = "show-grid")(
              Col(xs = 6, xsOffset = 6)(
                <.code("<Col xs=6 xsOffset=6>")
              )
            ),
            Row(className = "show-grid")(
              Col(md = 6, mdPush = 6)(
                <.code("<Col md=6 mdPush=6>")
              ),
              Col(md = 6, mdPull = 6)(
                <.code("<Col md=6 mdPull=6>")
              )
            )
          )
        )
      )
    )
  )

  def apply() = component().vdomElement
}
