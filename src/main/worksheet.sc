case class Contact(first: String, last: String, email: String)
val users = List(
Contact("Alan","Hamilton","alan.hamilton@host.com"),
  Contact("Nathan","Alsop","nathan.alsop@host.com"),
  Contact("Brandon","Bond","brandon.bond@host.com"),
  Contact("Lillian","Mackay","lillian.mackay@host.com"),
  Contact("Diana","Kerr","diana.kerr@host.com"),
  Contact("Samantha","Gray","samantha.gray@host.com"),
  Contact("Warren","Hamilton","warren.hamilton@host.com")
)

val filter = "ck"
users.filter { x =>
  x.first.toLowerCase.contains(filter) ||
    x.last.toLowerCase.contains(filter) ||
    x.email.toLowerCase.contains(filter)
}.foreach(println)

7/2
