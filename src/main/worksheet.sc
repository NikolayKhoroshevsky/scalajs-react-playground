val x =("A","b")

case class Foo(a: String, b:String)

val y = Foo.tupled(x)