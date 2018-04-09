package com.claassen.reactPlayground.pages

import scalacss.Defaults._
import scalacss.ScalaCssReact._
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import japgolly.scalajs.react.extra.router.{RouterConfigDsl, RouterCtl}
import com.claassen.reactPlayground.components.LeftNav
import japgolly.scalajs.react.vdom.VdomElement

abstract class Item(val title: String,
                    val routerPath: String,
                    val render: () => VdomElement)


trait ChapterPage {

  def menu: Seq[Item]

  object Style extends StyleSheet.Inline {
    import dsl._
    val container = style(display.flex, minHeight(600.px))

    val nav =
      style(width(190.px), borderRight :=! "1px solid rgb(223, 220, 220)")

    val content = style(padding(30.px))
  }


  lazy val routes = RouterConfigDsl[Item].buildRule { dsl =>
    import dsl._
    menu
      .map { i =>
        staticRoute(i.routerPath, i) ~> renderR(
          r => apply(Props(i, r)))
      }
      .reduce(_ | _)
  }

  lazy val component = ScalaComponent
    .builder[Props]("ItemsPage")
    .render_P { P =>
      <.div(
        Style.container,
        <.div(Style.nav,
          LeftNav(LeftNav.Props(menu, P.selectedPage, P.ctrl))),
        <.div(Style.content, P.selectedPage.render())
      )
    }
    .build

  case class Props(selectedPage: Item, ctrl: RouterCtl[Item])

  def apply(props: Props): VdomElement = component(props).vdomElement

}