package ch.supsi.myproj.guiclient.graphicelement;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class Bubble extends Group {
    private final int p = 14;
    private final int s = 2;
    private final int pt = 10;
    private final int st = 2;

    private final Font textFont = Font.font("Arial", 17);
    private final Paint textColor = Color.WHITE;
    private final Font timeFont = Font.font("Arial", 12);
    private final Paint timeColor = Color.LIGHTGRAY;

    private Rectangle r;
    private final int edgeRadius = 30;

    public Bubble(String text, String time, Paint bubbleColor) {
        super();
        init(0, 0, text, time, bubbleColor);
    }

    private void init(int x, int y, String text, String time, Paint bubbleColor) {
        //temp for text
        Text temp = new Text(text);
        temp.setFont(textFont);
        int textW = (int) temp.getLayoutBounds().getWidth();
        int textH = (int) temp.getLayoutBounds().getHeight();
        int w = textW + p * 2 + s * 2;
        int h = textH + p * 2;
        temp = null;

        //tmp for time
        Text tmp = new Text(time);
        tmp.setFont(timeFont);
        int timeW = (int) tmp.getLayoutBounds().getWidth();
        int timeH = (int) tmp.getLayoutBounds().getHeight();
        h += textH;
        tmp = null;

        //label text
        Label l = new Label(text);
        l.setFont(textFont);
        l.setTextFill(textColor);
        l.setTranslateX(x + p + s);
        l.setTranslateY(y + p);

        //label time
        Label t = new Label(time);
        t.setFont(timeFont);
        t.setTextFill(timeColor);
        t.setTranslateX(x + (w - (timeW + pt + st)));
        t.setTranslateY(y + textH + pt * 2);

        //bubble
        r = new Rectangle();
        r.setTranslateX(x);
        r.setTranslateY(y);
        r.setWidth(w);
        r.setHeight(h);
        r.setArcHeight(this.edgeRadius);
        r.setArcWidth(this.edgeRadius);
        r.setFill(bubbleColor);

        getChildren().addAll(r, l, t);
    }

}
