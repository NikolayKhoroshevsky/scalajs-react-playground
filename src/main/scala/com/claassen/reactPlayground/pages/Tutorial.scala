package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.tutorial._


object Tutorial extends ChapterPage {

  case object MShoppingList extends Item("ShoppingList", "shoppinglist", () => ShoppingList())

  case object MTicTacToe extends Item("TicTacToe", "tictactoe", () => TicTacToe.Game())

//  case object MTicTacToWithDiode
//    extends Item("TicTacToe w/ Diode", "tictactoe-diode", () => TicTacToeWithDiode())

  case object MReactCounter
    extends Item("React Counter", "reactcounter", () => ReactCounter())

  case object MReactCounterWithDiode
    extends Item("React Counter w/ Diode", "reactcounter-diode", () => ReactCounterWithDiode())

  val menu = Vector(MShoppingList, MTicTacToe, MReactCounter, MReactCounterWithDiode)
}
