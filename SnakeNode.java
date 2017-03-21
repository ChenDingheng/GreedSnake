import java.awt.*;

/**
 * Created by dell on 2017/2/20.
 */
public class SnakeNode {
    private int row,col;
    private Color headColor;
    private Color tailColor;
    private Color bodyColor;

    public SnakeNode(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void setRow(int row){
        this.row=row;
    }

    public int getRow(){
        return row;
    }

    public void setCol(int col){
        this.col=col;
    }

    public int getCol(){
        return col;
    }

    public boolean equals(SnakeNode temp){
        return row == temp.row && col == temp.col ? true : false;
    }
}
