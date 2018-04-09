package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.items.{Item1Data, Item2Data, ItemsInfo}

object ItemsPage extends ChapterPage {

  case object Info extends Item("Info", "info", () => ItemsInfo())

  case object Item1 extends Item("Item1", "item1", () => Item1Data())

  case object Item2 extends Item("Item2", "item2", () => Item2Data())

  val menu = Vector(Info, Item1, Item2)

}




