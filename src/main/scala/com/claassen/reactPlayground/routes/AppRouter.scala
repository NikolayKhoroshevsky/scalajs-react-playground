package com.claassen.reactPlayground.routes

import com.claassen.reactPlayground.components.{Footer, TopNav}
import com.claassen.reactPlayground.models.Menu
import com.claassen.reactPlayground.pages._
import japgolly.scalajs.react.extra.router.{Resolution, RouterConfigDsl, RouterCtl, _}
import japgolly.scalajs.react.vdom.html_<^._

object AppRouter {

  sealed trait AppPage

  case object Home extends AppPage

  case class Items(p: Item) extends AppPage

  val config = RouterConfigDsl[AppPage].buildConfig { dsl =>
    import dsl._
    val miscRoutes: Rule =
      Misc.routes.prefixPath_/("#misc").pmap[AppPage](Items) {
        case Items(p) => p
      }
    val calendarRoutes: Rule =
      Calendars.routes.prefixPath_/("#calendars").pmap[AppPage](Items) {
        case Items(p) => p
      }
    val antDesignRoutes: Rule =
      AntDesign.routes.prefixPath_/("#antd").pmap[AppPage](Items) {
        case Items(p) => p
      }
    val reactBootstrapRoutes: Rule =
      ReactBootstrap.routes.prefixPath_/("#bootstrap").pmap[AppPage](Items) {
        case Items(p) => p
      }
    (trimSlashes
      | staticRoute(root, Home) ~> render(HomePage())
      | miscRoutes
      | calendarRoutes
      | antDesignRoutes
      | reactBootstrapRoutes)
      .notFound(redirectToPage(Home)(Redirect.Replace))
      .renderWith(layout)
  }

  val mainMenu = Vector(
    Menu("Home", Home),
    Menu("Misc", Items(Misc.MShoppingList)),
    Menu("Calendars", Items(Calendars.MFullCalendar)),
    Menu("Ant Design", Items(AntDesign.MBadge)),
    Menu("Bootstrap", Items(ReactBootstrap.MNavbar))
  )

  def layout(c: RouterCtl[AppPage], r: Resolution[AppPage]) =
    <.div(
      TopNav(TopNav.Props(mainMenu, r.page, c)),
      r.render(),
      Footer()
    )

  val baseUrl = BaseUrl.fromWindowOrigin

  val router = Router(baseUrl, config)
}
