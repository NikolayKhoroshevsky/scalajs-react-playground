package com.claassen.reactPlayground.pages

import com.claassen.reactPlayground.components.antDesign.BadgeExample


object AntDesign extends ChapterPage {

  case object MBadge
    extends Item("Badge", "badge", () => BadgeExample(3))

  val menu = Vector(MBadge)
}

