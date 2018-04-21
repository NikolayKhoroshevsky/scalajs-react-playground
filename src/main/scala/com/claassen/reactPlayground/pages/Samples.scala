package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.samples._


object Samples extends ChapterPage {

  case object MShoppingList extends Item("ShoppingList", "shoppinglist", () => ShoppingList())

  case object MTicTacToe extends Item("TicTacToe", "tictactoe", () => TicTacToe.Game())

  case object MTicTacToWithDiode
    extends Item("TicTacToe w/Diode", "tictactoe-diode", () => TicTacToeWithDiode())

  case object MReactCounter
    extends Item("ReactCounter", "reactcounter", () => ReactCounter())

  case object MReactCounterWithDiode
    extends Item("ReactCounter w/Diode", "reactcounter-diode", () => ReactCounterWithDiode())

  case object MReactCollapse
    extends Item("React Collapse Wrapper", "reactcollapse", () => ReactCollapseExample2())

  val menu = Vector(MShoppingList,
    MTicTacToe, MTicTacToWithDiode,
    MReactCounter, MReactCounterWithDiode,
    MReactCollapse)
}

