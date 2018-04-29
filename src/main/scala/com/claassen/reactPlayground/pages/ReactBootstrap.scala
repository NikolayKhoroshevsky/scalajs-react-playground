package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.AppCircuit
import com.claassen.reactPlayground.components.reactBootstrap.NavbarExample


object ReactBootstrap extends ChapterPage {

  val name = "ReactBootstrap"
  val key = "bootstrap"

  case object MNavbar extends Item("Navbar", "navbar", () => {
    val connection = AppCircuit.connect(_.app.bootstrap.navbar)
    connection(NavbarExample(_)).vdomElement
  })

  val menu = Vector(MNavbar)
}

