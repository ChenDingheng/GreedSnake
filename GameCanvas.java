import javax.swing.*;
import java.awt.*;

/**
 * Created by dell on 2017/2/21.
 */
public class GameCanvas extends JPanel {
    private int rows=20,cols=20;
    private int boxWidth,boxHeight;
    private IsWhat[][] colorFlags;
    private static GameCanvas instance=null;

    private GameCanvas(){
        colorFlags=new IsWhat[rows][cols];
        for(int i=0;i<colorFlags.length;i++)
            for(int j=0;j<colorFlags[i].length;j++)
                colorFlags[i][j]=IsWhat.isBackground;
    }

    public static GameCanvas getCanvasInstance() {
        if(instance==null)
            instance=new GameCanvas();
        return instance;
    }

    public void setRows(int rows){
        this.rows=rows;
    }

    public int getRows(){
        return rows;
    }

    public void setCols(int cols){
        this.cols=cols;
    }

    public int getCols(){
        return cols;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        windowReSize();
        for(int i=0;i<colorFlags.length;i++)
            for(int j=0;j<colorFlags[i].length;j++){
            switch(colorFlags[i][j]){
                case isFood:
                    g.setColor(ColorGroup.COLOR_BEAN);
                    g.fill3DRect(j*boxWidth,i*boxHeight,boxWidth,boxHeight,true);
                    break;
                case isSnake:
                    g.setColor(ColorGroup.COLOR_SNAKE);
                    g.fill3DRect(j*boxWidth,i*boxHeight,boxWidth,boxHeight,true);
                    break;
                case isBackground:
                    g.setColor(ColorGroup.COLOR_BACK);
                    g.fill3DRect(j*boxWidth,i*boxHeight,boxWidth,boxHeight,true);
                    break;
                default:
                    break;
            }
            }
    }

    public void reset(){
        for(int i=0;i<colorFlags.length;i++)
            for(int j=0;j<colorFlags[i].length;j++)
                colorFlags[i][j]=IsWhat.isBackground;
        repaint();
    }

    public void windowReSize(){
        boxWidth=getSize().width/cols;
        boxHeight=getSize().height/rows;
    }

    public IsWhat getColorFlag(int row,int col){
        return colorFlags[row][col];
    }

    public void setColorFlag(int row,int col,IsWhat what){
        colorFlags[row][col]=what;
    }
}
