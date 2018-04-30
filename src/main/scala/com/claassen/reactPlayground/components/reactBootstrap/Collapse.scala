package com.claassen.reactPlayground.components.reactBootstrap

import com.payalabs.scalajs.react.bridge._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
import scala.scalajs.js.|

object Collapse extends ReactBridgeComponent {

  @JSImport("react-bootstrap/lib/Collapse", JSImport.Default)
  @js.native
  object RawComponent extends js.Object

  override lazy val componentValue = RawComponent

  def apply(in: Boolean = false): WithProps = auto
}

object CollapseExample {

  case class Props(open: Boolean = false)

  class Backend($: BackendScope[Props, Props]) {

    def handleClick(e: ReactMouseEvent): Callback = $.modState(x => x.copy(open = !x.open))

    def render(p: Props, s: Props) = {
      <.div(
        Button(onClick = handleClick _)("click"),
        Collapse(in = s.open)(
          <.div(
            Well()(
              "Anim pariatur cliche reprehenderit, enim eiusmod high life accusamus terry richardson ad squid. Nihil anim keffiyeh helvetica, craft beer labore wes anderson cred nesciunt sapiente ea proident."
            )
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[Props]("CollapseExample")
    .initialStateFromProps(x => x)
    .renderBackend[Backend]
    .build

  def apply() = component(Props()).vdomElement
}