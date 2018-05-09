package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.AppCircuit
import com.claassen.reactPlayground.components.reactBootstrap._


object ReactBootstrap extends ChapterPage {

  val name = "ReactBootstrap"
  val key = "bootstrap"

  case object MNavbar extends Item("Navbar", "navbar", () => {
    val connection = AppCircuit.connect(_.app.bootstrap.navbar)
    connection(NavbarExample(_)).vdomElement
  })

  case object MListGroup extends Item("ListGroup", "listgroup", () => {
    val connection = AppCircuit.connect(_.app.bootstrap.listgroup)
    connection(ListGroupExample(_)).vdomElement
  })

  case object MButtons extends Item("Buttons","buttons", () => ButtonExample())

  case object MWell extends Item("Well","well", () => WellExample())

  case object MCollapse extends Item("Collapse", "collapse", ()  => CollapseExample())

  case object MDropdowns extends Item("Dropdowns","dropdowns", () => DropdownExample())

  case object MPanel extends Item("Panel","panel", () => PanelExample())
  case object MForms extends Item("Forms","forms", () => FormsExample())

  case object MLayout extends Item("Layout","layout", ()=> LayoutExample())

  case object MLabel extends Item("Label","label", () => LabelExample())

  case object MTable extends Item("Table","table", () => TableExample())

  case object MPagination extends Item("Pagination","pagination",  () => {
    val connection = AppCircuit.connect(_.app.bootstrap.pagination)
    connection(PaginationExample(_)).vdomElement
  })

  case object MTabs extends Item("Tabs", "tabs", () => TabExample())

  val menu = Vector(
    MButtons,
    MCollapse,
    MDropdowns,
    MForms,
    MLabel,
    MLayout,
    MListGroup,
    MNavbar,
    MPagination,
    MPanel,
    MTable,
    MTabs,
    MWell)
}

