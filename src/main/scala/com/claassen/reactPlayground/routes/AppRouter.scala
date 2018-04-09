package com.claassen.reactPlayground.routes

import com.claassen.reactPlayground.components.{Footer, TopNav}
import com.claassen.reactPlayground.models.Menu
import com.claassen.reactPlayground.pages.{HomePage, Item, ItemsPage, Tutorial}
import japgolly.scalajs.react.extra.router.{Resolution, RouterConfigDsl, RouterCtl, _}
import japgolly.scalajs.react.vdom.html_<^._

object AppRouter {

  sealed trait AppPage

  case object Home extends AppPage

  case class Items(p: Item) extends AppPage

  val config = RouterConfigDsl[AppPage].buildConfig { dsl =>
    import dsl._
    val itemRoutes: Rule =
      ItemsPage.routes.prefixPath_/("#items").pmap[AppPage](Items) {
        case Items(p) => p
      }
    val tutorialRoutes: Rule =
      Tutorial.routes.prefixPath_/("#tutorial").pmap[AppPage](Items) {
        case Items(p) => p
      }
    (trimSlashes
      | staticRoute(root, Home) ~> render(HomePage())
      | itemRoutes
      | tutorialRoutes)
      .notFound(redirectToPage(Home)(Redirect.Replace))
      .renderWith(layout)
  }

  val mainMenu = Vector(
    Menu("Home", Home),
    Menu("Items", Items(ItemsPage.Info)),
    Menu("Tutorial", Items(Tutorial.MShoppingList))
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
