package ua.danyazero.obsessionstarter.pages;

import ua.danyazero.obsession.api.Page;
import ua.danyazero.obsession.api.State;
import ua.danyazero.obsession.api.Text;
import ua.danyazero.obsession.api.component.ReactiveComponent;
import ua.danyazero.obsession.api.route.Query;
import ua.danyazero.obsession.api.view.ReactiveView;
import ua.danyazero.obsession.style.*;

import static ua.danyazero.obsession.elements.ReactiveElements.*;
import static ua.danyazero.obsession.style.Css.*;

@Page(
        path = "/",
        title = "Counter",
        stylesheets = "https://fonts.googleapis.com/css2?family=Share+Tech+Mono&display=swap"
)
public class Counter implements ReactiveComponent {

    @State
    private int value;

    public Counter(@Query(defaultValue = "0") int value) {
        this.value = value;
    }

    @Override
    public ReactiveView render() {
        return div(it -> it.style(wrapper),
                div(it -> it.style(counter),

                        button(it -> it.style(button, crement).onClick(() -> value--), Text.of("-")),
                        span(it -> it.style(counterValue), Text.of(value)),
                        button(it -> it.style(button, crement).onClick(() -> value++), Text.of("+")),

                        div(it -> it.style(corner, cornerTl)),
                        div(it -> it.style(corner, cornerTr)),
                        div(it -> it.style(corner, cornerBl)),
                        div(it -> it.style(corner, cornerBr))
                ),
                button(it -> it.style(button, resetButton).onClick(() -> value = 0), Text.of("reset"))
        );
    }

    final Style wrapper = s -> s
            .display(Display.FLEX)
            .flexDirection(FlexDirection.COLUMN)
            .alignItems(AlignItems.CENTER)
            .gap(px(24));

    final Style counter = s -> s
            .position(Position.RELATIVE)
            .display(Display.FLEX)
            .alignItems(AlignItems.CENTER)
            .gap(px(20))
            .padding(px(30), px(40))
            .border(px(1), BorderStyle.SOLID, rgba(165, 201, 202, 0.25));

    final Style corner = s -> s
            .position(Position.ABSOLUTE)
            .width(px(20))
            .height(px(20))
            .when(value > 0,
                    st -> st.border(px(2), BorderStyle.SOLID, NamedColor.GREEN_YELLOW),
                    st -> st.border(px(2), BorderStyle.SOLID, NamedColor.ORANGE_RED)
            )
            ;

    final Style cornerTl = s -> s
            .top(px(-2))
            .left(px(-2))
            .borderRight(BorderStyle.NONE)
            .borderBottom(BorderStyle.NONE);

    final Style cornerBl = s -> s
            .bottom(px(-2))
            .left(px(-2))
            .borderRight(BorderStyle.NONE)
            .borderTop(BorderStyle.NONE);

    final Style cornerTr = s -> s
            .top(px(-2))
            .right(px(-2))
            .borderLeft(BorderStyle.NONE)
            .borderBottom(BorderStyle.NONE);

    final Style cornerBr = s -> s
            .bottom(px(-2))
            .right(px(-2))
            .borderLeft(BorderStyle.NONE)
            .borderTop(BorderStyle.NONE);

    final Style button = s -> s
            .backgroundColor(ColorKeyword.TRANSPARENT)
            .fontFamily(CssWide.INHERIT)
            .cursor(Cursor.POINTER);

    final Style crement = s -> s
            .width(px(70))
            .height(px(70))
            .border(px(1), BorderStyle.SOLID, hex("#a5c9ca"))
            .color(hex("#a5c9ca"))
            .fontSize(px(28))
            .when(value > 0,
                    st -> st.hover(sty -> sty.borderColor(NamedColor.GREEN_YELLOW).color(NamedColor.GREEN_YELLOW)),
                    st -> st.hover(sty -> sty.borderColor(NamedColor.ORANGE_RED).color(NamedColor.ORANGE_RED))
            );


    final Style counterValue = s -> s
            .minWidth(px(140))
            .textAlign(TextAlign.CENTER)
            .when(value > 0,
                    st -> st.color(NamedColor.GREEN_YELLOW),
                    st -> st.color(NamedColor.ORANGE_RED)

            )
            .fontSize(px(64));

    final Style resetButton = s -> s
            .display(Display.FLEX)
            .alignItems(AlignItems.CENTER)
            .gap(px(8))
            .padding(px(8), px(18))
            .border(px(1), BorderStyle.SOLID, rgba(165, 201, 202, 0.4))
            .color(hex("#a5c9ca"))
            .fontSize(px(13))
            .letterSpacing(px(2))
            .textTransform(TextTransform.UPPERCASE)
            .hover(st -> st
                    .borderColor(hex("#a5c9ca"))
                    .color(hex("#1b2430"))
                    .backgroundColor(hex("#a5c9ca"))
            );

    static final GlobalStyle RESET = s -> s
            .rule(Selector.EVERY, st -> st
                    .margin(px(0))
                    .padding(px(0))
                    .boxSizing(BoxSizing.BORDER_BOX))
            .rule(Selector.BODY, st -> st
                    .minHeight(vh(100))
                    .display(Display.FLEX)
                    .justifyContent(JustifyContent.CENTER)
                    .alignItems(AlignItems.CENTER)
                    .backgroundColor(hex("#1b2430"))
                    .fontFamily(family("Share Tech Mono"), GenericFamily.MONOSPACE));

}
