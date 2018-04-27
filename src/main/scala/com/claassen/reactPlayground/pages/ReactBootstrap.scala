package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.AppCircuit
import com.claassen.reactPlayground.components.reactBootstrap.NavbarExample


object ReactBootstrap extends ChapterPage {

  case object MNavbar extends Item("Navbar", "navbar", () => NavbarExample())

  val menu = Vector(MNavbar)
}

