package com.claassen.reactPlayground.css

import scalacss.Defaults._
import scalacss.internal.mutable.GlobalRegistry
import com.claassen.reactPlayground.components.{LeftNav, TopNav}
import com.claassen.reactPlayground.pages.{HomePage, ItemsPage}

object AppCSS {

  def load = {
    GlobalRegistry.register(GlobalStyle,
                            TopNav.Style,
                            LeftNav.Style,
                            ItemsPage.Style,
                            HomePage.Style)
    GlobalRegistry.onRegistration(_.addToDocument())
  }
}