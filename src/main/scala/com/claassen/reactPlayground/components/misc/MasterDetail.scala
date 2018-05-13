package com.claassen.reactPlayground.components.misc

import japgolly.scalajs.react._
import com.claassen.reactPlayground.components.reactBootstrap._
import japgolly.scalajs.react.vdom.html_<^._
import diode.react.ModelProxy
import diode.{Action, ActionHandler, ActionResult, ModelRW}

object MasterDetail {

  case class Props(showDetail: Boolean,
                   master: VdomNode,
                   detail: VdomNode)

  class Backend($: BackendScope[Props, Unit]) {

    def render(P: Props) = {
      Grid()(
        Row(className = "show-grid")(
          Col(md = if (P.showDetail) 8 else 12)(
            P.master
          ),
          <.div(^.className := (if (P.showDetail) "" else "hidden"),
            Col(md = 4)(
              P.detail
            )
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[Props]("MasterDetail")
    .renderBackend[Backend]
    .build

  def apply(showDetail: Boolean, master: VdomNode, detail: VdomNode) =
    component(Props(showDetail, master, detail)).vdomElement
}

object MasterDetailExample {

  case class Item(title: String, body: String)

  case class Props(selected: Option[Item])

  case class ShowItem(item: Item) extends Action

  case object HideItem extends Action

  class Handler[M](modelRW: ModelRW[M, Props]) extends ActionHandler(modelRW) {
    override protected def handle: PartialFunction[Any, ActionResult[M]] = {
      case HideItem => updated(Props(None))
      case ShowItem(item) => updated(Props(Some(item)))
    }
  }


  class Backend($: BackendScope[ModelProxy[Props], Unit]) {

    val items = List(
      Item("Item 1", "Lorem ipsum dolor sit amet, graece labores scripserit nec ad, mucius probatus at vis, tation insolens ei mea. Munere verterem cotidieque ius an. Ad nam partem timeam scriptorem, mei ea ignota corrumpit, vis blandit intellegam no. Elit quando animal id nec, ius audiam probatus voluptatibus ne, duo ne vero invenire persecuti."),
      Item("Item 2", "In sit amet tempor dolor. Mauris feugiat est ac lectus iaculis, sed fermentum sem tristique. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Morbi volutpat dolor eget lorem accumsan mattis. Nulla sit amet nulla nulla. Nunc suscipit nec augue sed sagittis. Maecenas feugiat suscipit tellus vel tempus. In orci nisi, eleifend sed dictum ac, tincidunt vel felis. Proin urna arcu, scelerisque quis gravida quis, aliquet ut ante. Curabitur pulvinar justo sed neque accumsan sollicitudin."),
      Item("Item 3", "Curabitur a nisl commodo, posuere odio sed, cursus ex. Aliquam consectetur vulputate nibh, sit amet eleifend urna faucibus eu. Ut vitae arcu sed libero dignissim molestie. Vivamus quis varius velit, sit amet consequat justo. Nam commodo vehicula sollicitudin. Nulla tempus nulla leo, et aliquam metus accumsan ut. In facilisis neque diam, ut varius tellus gravida nec. Proin ut imperdiet purus. Vestibulum aliquam efficitur nunc a ultricies. Sed vulputate neque nulla, eu lobortis massa ultrices eget. Etiam eleifend, dolor vitae gravida mollis, erat ex venenatis sapien, sed pellentesque turpis urna quis erat.")
    )

    def selectItem(item: Item)(e: ReactMouseEvent): Callback = $.props.flatMap(_.dispatchCB(ShowItem(item)))
    def hideItem(e: ReactMouseEvent): Callback = $.props.flatMap(_.dispatchCB(HideItem))

    def render(P: ModelProxy[Props]) = {
      val (selectedTitle, selectedBody) = P().selected.map(x => (x.title, x.body)).getOrElse(("", ""))

      <.div(
        <.h2("Master Detail"),
        MasterDetail(
          P().selected.nonEmpty,
          <.div(
            <.ol(
              items.map { item =>
                <.li(
                  Button(
                    bsStyle = "link",
                    onClick = selectItem(item) _
                  )(item.title)
                )
              }: _*
            )
          ),
          <.div(
            <.div( ^.className := "float-right",
              Button(
                bsStyle = "danger",
                onClick = hideItem _
              )(Glyphicon(glyph = "remove")())
            ),
            <.h3(selectedTitle),
            <.p(selectedBody)
          )
        )
      )
    }
  }

  val component = ScalaComponent.builder[ModelProxy[Props]]("MasterDetailExample")
    .renderBackend[Backend]
    .build

  def apply(props: ModelProxy[Props]) = component(props).vdomElement
}
