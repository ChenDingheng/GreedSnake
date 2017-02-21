import java.awt.*;

/**
 * Created by dell on 2017/2/21.
 */
class ColorGroup {
    static Color COLOR_BACK= Color.WHITE;
    static Color COLOR_SNAKE=new Color(43,110,187);
    static Color COLOR_BEAN=Color.RED;
    static Color COLOR_EATEDBEAN=Color.CYAN;

    static void setCOLOR_BACK(Color temp){
        COLOR_BACK=temp;
    }

    static void setCOLOR_SNAKE(Color temp){
        COLOR_SNAKE=temp;
    }

    static void setCOLOR_BEAN(Color temp){
        COLOR_BEAN=temp;
    }

    static void setCOLOR_EATEDBEAN(Color temp){
        COLOR_EATEDBEAN=temp;
    }
}
