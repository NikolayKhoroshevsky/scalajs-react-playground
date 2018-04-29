package com.claassen.reactPlayground.routes

import com.claassen.reactPlayground.components.{Footer, TopNav}
import com.claassen.reactPlayground.models.Menu
import com.claassen.reactPlayground.pages._
import japgolly.scalajs.react.extra.router.{Resolution, RouterConfigDsl, RouterCtl, _}
import japgolly.scalajs.react.vdom.html_<^._

object AppRouter {

  sealed trait AppPage {
    def eventKey: Option[String]
  }

  case object Home extends AppPage {
    val eventKey: Option[String] = None
  }

  case class Items(key: String, p: Item) extends AppPage {
    def eventKey = Some(key)
  }


  val config = RouterConfigDsl[AppPage].buildConfig { dsl =>
    import dsl._

    def chapterRoutes(chapterPage: ChapterPage): Rule = {
      chapterPage.routes.prefixPath_/(chapterPage.prefix).pmap[AppPage](Items(chapterPage.key, _)) {
        case Items(_, p) => p
      }
    }

    (trimSlashes
      | staticRoute(root, Home) ~> render(HomePage())
      | chapterRoutes(Misc)
      | chapterRoutes(Calendars)
      | chapterRoutes(AntDesign)
      | chapterRoutes(ReactBootstrap))
      .notFound(redirectToPage(Home)(Redirect.Replace))
      .renderWith(layout)
  }

  val mainMenu = Vector(Misc, Calendars, AntDesign, ReactBootstrap).map { chapterPage =>
    Menu(chapterPage.name, Items(chapterPage.key, chapterPage.menu.head))
  }

  def layout(c: RouterCtl[AppPage], r: Resolution[AppPage]) =
    <.div(
      TopNav(TopNav.Props(mainMenu, r.page, c)),
      r.render(),
      Footer()
    )

  val baseUrl = BaseUrl.fromWindowOrigin

  val router = Router(baseUrl, config)
}
