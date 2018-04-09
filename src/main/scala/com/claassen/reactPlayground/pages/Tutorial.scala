package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.tutorial.{Game, ShoppingList}

object Tutorial extends ChapterPage {
  case object MShoppingList extends Item("ShoppingList", "shoppinglist", () => ShoppingList())
  case object TicTacToe extends Item("TicTacToe", "tictactoe", () => Game())
  val menu = Vector(MShoppingList, TicTacToe)
}
