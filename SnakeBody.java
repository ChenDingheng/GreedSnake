import javax.swing.*;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.Thread.sleep;

/**
 * Created by dell on 2017/2/20.
 */
public class SnakeBody implements Runnable{
    private LinkedList<SnakeNode> snakeList;
    private GreedSnake game;
    private GameCanvas canvas;
    private final static int LEVELS=10;
    private final static int SCORES=10;
    private final static int PER_LEVEL_SPEED_UP=10;
    private boolean running=true,pause=false;
    private int timeInterval=200;
    private int curLevelScore;
    private SnakeNode food;
    private Direction direction=Direction.UP;

    public SnakeBody(final GreedSnake game,int iniSnakeBodyLength){
        this.game=game;
        curLevelScore=0;
        canvas=GameCanvas.getCanvasInstance();
        snakeList=new LinkedList<SnakeNode>();
        int rows=canvas.getRows();
        int cols=canvas.getCols();
        for(int i=0;i<iniSnakeBodyLength;i++){
            snakeList.add(new SnakeNode(rows/2,cols/2+i));
            canvas.setColorFlag(rows/2,cols/2+i,IsWhat.isSnake);
        }
        createFood();
        canvas.repaint();
    }

    public void pauseMove(){
        pause=true;
    }

    public void resumeMove(){
        pause=false;
    }

    public void stopMove(){
        running=false;
    }

    public void createFood(){
        LP:
        while(true){
            int x=(int)(Math.random()*canvas.getCols());
            int y=(int)(Math.random()*canvas.getRows());
            Iterator snakeSq=snakeList.iterator();
            while(snakeSq.hasNext()){
                if(snakeSq.next().equals(new Point(x,y))){
                    continue LP;
                }
            }
            food=new SnakeNode(x,y);
            canvas.setColorFlag(x,y,IsWhat.isFood);
            break;
        }
        canvas.repaint();
    }

    public void changeDirection(Direction direction){
        this.direction=direction;
    }

    private boolean moveOn(){
        SnakeNode snakeHead=(SnakeNode)snakeList.getFirst();
        int x=snakeHead.getRow();
        int y=snakeHead.getCol();
        boolean isFood=false;
        switch(direction){
            case LEFT:y--;break;
            case RIGHT:y++;break;
            case UP:x--;break;
            case DOWN:x++;break;
            default:break;
        }

        if(x<0 || x>canvas.getCols()-1 || y<0 || y>canvas.getRows()-1){
            return false;
        }

        if(x==food.getRow() && y==food.getCol()){
            isFood=true;
        }

        if(isFood){
            int score=game.getScore();
            score+=SCORES;
            game.setScore(score);
            curLevelScore+=SCORES;
            snakeList.addFirst(new SnakeNode(x,y));
            createFood();
            canvas.setColorFlag(x,y,IsWhat.isSnake);
            canvas.repaint();
        }
        else{
            snakeHead=new SnakeNode(x,y);
            snakeList.addFirst(snakeHead);
            canvas.setColorFlag(x,y,IsWhat.isSnake);
            SnakeNode snakeTail=(SnakeNode)snakeList.getLast();
            snakeList.removeLast();
            canvas.setColorFlag(snakeTail.getRow(),snakeTail.getCol(),IsWhat.isBackground);
            canvas.repaint();
        }
        canvas.repaint();
        SnakeNode head=(SnakeNode)snakeList.getFirst();
        for(int i=1;i<snakeList.size();i++){
            if(head.equals((SnakeNode)snakeList.get(i)))
                return false;
        }
        return true;
    }

    @Override
    public void run(){
        while(running){
            try{
                sleep(timeInterval-game.getLevel()*PER_LEVEL_SPEED_UP);
            }
            catch(InterruptedException e){
                e.printStackTrace();
            }
            if(!pause){
                if(!moveOn()){
                    JOptionPane.showMessageDialog(null,"Uncarefully Bite yourself","Game Over",JOptionPane.INFORMATION_MESSAGE);
                    running=false;
                }
            }
        }
        //Thread.yield();
    }
}
