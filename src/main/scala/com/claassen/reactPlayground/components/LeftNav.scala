package com.claassen.reactPlayground.components

import com.claassen.reactPlayground.components.reactBootstrap.{Nav, NavItem}
import com.claassen.reactPlayground.pages.Item

import scalacss.Defaults._
import scalacss.ScalaCssReact._
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js.|
import scala.scalajs.js.Dynamic.{global => g}

object LeftNav {

  object Style extends StyleSheet.Inline {

    import dsl._

    val container = style(display.flex,
      flexDirection.column,
      listStyle := "none",
      padding.`0`)

    val menuItem = styleF.bool { selected =>
      styleS(
        lineHeight(48.px),
        padding :=! "0 25px",
        cursor.pointer,
        textDecoration := "none",
        mixinIfElse(selected)(color.red, fontWeight._500)(
          color.black,
          &.hover(color(c"#555555"), backgroundColor(c"#ecf0f1"))
        )
      )
    }
  }

  case class Props(menus: Seq[Item],
                   selectedPage: Item,
                   ctrl: RouterCtl[Item])

  implicit val currentPageReuse = Reusability.by_==[Item]
  implicit val propsReuse = Reusability.by((_: Props).selectedPage)

  val component = ScalaComponent
    .builder[Props]("LeftNav")
    .render_P { P =>

      g.console.log("selected: " + P.selectedPage.routerPath)

      Nav(bsStyle="tabs", stacked=true, activeKey = P.selectedPage.routerPath)(
        P.menus.map(m =>
          NavItem(eventKey = m.routerPath, onSelect = { _: String => P.ctrl.set(m) })(m.title)
        ): _*
      )
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def apply(props: Props) = component(props)

}
