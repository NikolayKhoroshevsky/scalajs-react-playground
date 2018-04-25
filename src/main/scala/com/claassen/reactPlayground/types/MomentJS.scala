package com.claassen.reactPlayground.types

import java.util.Date

import scala.scalajs.js
import scala.scalajs.js.annotation.JSImport
//import moment._

object MomentJS {

  @JSImport("moment", JSImport.Default)
  @js.native
  object Raw extends js.Object {
    def apply(): js.Object = js.native
    def apply(date: Date): js.Object = js.native
  }

  def apply(): js.Object = Raw()
  def apply(date: Date): js.Object = Raw(date)

}
