package com.claassen.reactPlayground.components

import com.claassen.reactPlayground.components.reactBootstrap.{Nav, NavItem, Navbar}
import com.claassen.reactPlayground.components.reactBootstrap.NavbarExample.Click

import scalacss.Defaults._
import scalacss.ScalaCssReact._
import com.claassen.reactPlayground.models.Menu
import com.claassen.reactPlayground.routes.AppRouter.AppPage
import japgolly.scalajs.react._
import japgolly.scalajs.react.extra.Reusability
import japgolly.scalajs.react.extra.router.RouterCtl
import japgolly.scalajs.react.vdom.html_<^._

import scala.scalajs.js.|

object TopNav {

  object Style extends StyleSheet.Inline {

    import dsl._

    val navMenu = style(display.flex,
                        alignItems.center,
                        backgroundColor(c"#F2706D"),
                        margin.`0`,
                        listStyle := "none")

    val menuItem = styleF.bool { selected =>
      styleS(
        padding(20.px),
        fontSize(1.5.em),
        cursor.pointer,
        color(c"rgb(244, 233, 233)"),
        mixinIfElse(selected)(backgroundColor(c"#E8433F"), fontWeight._500)(
          &.hover(backgroundColor(c"#B6413E")))
      )
    }
  }

  case class Props(menus: Vector[Menu],
                   selectedPage: AppPage,
                   ctrl: RouterCtl[AppPage])

  implicit val currentPageReuse = Reusability.by_==[AppPage]
  implicit val propsReuse = Reusability.by((_: Props).selectedPage)

  val component = ScalaComponent
    .builder[Props]("TopNav")
    .render_P { P =>

      Navbar(inverse = true, fixedTop = true)(
        Navbar.Header()(
          Navbar.Brand()(
            <.a(^.href:="#", "Home")
          )
        ),
        Nav(activeKey = P.selectedPage.eventKey.fold[String|Unit](())(x => x))(
          P.menus.map( m =>
            NavItem(eventKey = m.route.eventKey.get, onSelect= {_:String => P.ctrl.set(m.route)})(m.name)
          ):_*
        )
      )
    }
    .configure(Reusability.shouldComponentUpdate)
    .build

  def apply(props: Props) = component(props)

}
