package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.AppCircuit
import com.claassen.reactPlayground.components.misc._
import japgolly.scalajs.react.vdom.Implicits._


object Misc extends ChapterPage {

  val name = "Misc"
  val key = "misc"

  case object MShoppingList extends Item("ShoppingList", "shoppinglist", () => ShoppingList())

  case object MTicTacToWithDiode
    extends Item("TicTacToe", "tictactoe-diode", () => {
      val connection = AppCircuit.connect(_.app.misc.ticTacToe)
      connection(p => TicTacToe(p)).vdomElement
    })

  case object MReactCounter
    extends Item("ReactCounter", "reactcounter-diode", () => {
      val connection = AppCircuit.connect(_.app.misc.counter)
      connection(p => ReactCounter(p)).vdomElement
    })

  case object MReactCollapse
    extends Item("ReactCollapse", "reactcollapse", () => {
      val connection = AppCircuit.connect(_.app.misc.collapse)
      connection(p => ReactCollapseExample(p)).vdomElement
    })

  case object MMomentInterop
    extends Item("Moment", "moment", () => {
      val connection = AppCircuit.connect(_.app.misc.moment)
      connection(p => MomentInterop(p)).vdomElement
    })

  case object MMasterDetail
    extends Item("Master/Detail", "master-detail", () => {
      val connection = AppCircuit.connect(_.app.misc.masterDetail)
      connection(p => MasterDetailExample(p)).vdomElement
    })

  val menu = Vector(MShoppingList,
    MTicTacToWithDiode,
    MReactCounter,
    MReactCollapse,
    MMomentInterop,
    MMasterDetail)
}
