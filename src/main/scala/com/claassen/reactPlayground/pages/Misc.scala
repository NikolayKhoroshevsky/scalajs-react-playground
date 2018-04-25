package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.AppCircuit
import com.claassen.reactPlayground.components.misc.{ReactCounterBuilder, _}


object Misc extends ChapterPage {


  case object MShoppingList extends Item("ShoppingList", "shoppinglist", () => ShoppingList())

  case object MTicTacToWithDiode
    extends Item("TicTacToe", "tictactoe-diode", TicTacToeBuilder.build)

  case object MReactCounter
    extends Item("ReactCounter", "reactcounter-diode", ReactCounterBuilder.build)

  case object MReactCollapse
    extends Item("ReactCollapse", "reactcollapse", () => ReactCollapseExampleBuilder.build)

  case object MMomentInterop
  extends Item("Moment", "moment", () => MomentInterop())

  val menu = Vector(MShoppingList,
    MTicTacToWithDiode,
    MReactCounter,
    MReactCollapse,
    MMomentInterop)
}
