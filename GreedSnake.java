import javax.swing.*;
import java.awt.*;

/**
 * Created by dell on 2017/2/21.
 */
public class GreedSnake extends JApplet {
    private SnakeBody snakeBody;
    private GameCanvas canvas;
    private ControlPanel controlPanel;
    private boolean playing=false,first=true;
    private int level,score;

    public void init(){
        setName("Greed Snake Game");
        setLayout(new BorderLayout(4,0));
        level=0;
        score=0;

        setSize(500,400);
        Dimension srcSize=Toolkit.getDefaultToolkit().getScreenSize();
        int x=(srcSize.width-getSize().width)/2;
        int y=(srcSize.height-getSize().height)/2;
        setLocation(x,y);
        snakeBody=new SnakeBody(this,3);
        canvas=GameCanvas.getCanvasInstance();
        controlPanel=new ControlPanel(this);
        Container container=getContentPane();
        container.add(canvas,BorderLayout.CENTER);
        container.add(controlPanel,BorderLayout.EAST);
        setVisible(true);
    }

    public ControlPanel getControlPanel(){
        return controlPanel;
    }

    public GameCanvas getCanvas(){
        return canvas;
    }

    public void changeDirection(Direction direction){
        snakeBody.changeDirection(direction);
    }

    public boolean isPlaying(){
        return playing;
    }

    public void playGame(){
        if(!first){
            snakeBody=new SnakeBody(this,3);
            first=false;
        }
        controlPanel.setPlayButtonEnable(false);
        snakeBody.start();
        playing=true;
    }

    public void pauseGame(){
        snakeBody.pauseMove();
        controlPanel.setPauseButtonLabel(false);
        playing=false;
    }

    public void resumeGame(){
        snakeBody.resumeMove();
        controlPanel.setPauseButtonLabel(true);
        playing=true;
    }

    public void stopGame(){
        snakeBody.stopMove();
        controlPanel.setPlayButtonEnable(true);
        controlPanel.setPauseButtonLabel(true);
        reset();
        playing=false;
    }

    public void reset(){
        canvas.reset();
        controlPanel.reset();
    }

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score=score;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level=level;
    }
}
