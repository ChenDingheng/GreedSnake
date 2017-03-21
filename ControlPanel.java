import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by dell on 2017/2/20.
 */
public class ControlPanel extends JPanel implements ActionListener, ChangeListener, KeyListener{
    static final int MARK_MIN=0;
    static final int MARK_MAX=10;
    static final int MARK_INIT=0;
    private JPanel infoPanel,buttonPanel;
    private ColorConfig snakePanel;
    private JTextField scoreField;
    private JSlider slider;
    private JButton playButton,pauseButton,stopButton;
    private Timer timer;
    private GreedSnake game;
    private EtchedBorder border=new EtchedBorder(EtchedBorder.RAISED, Color.white,Color.lightGray);

    private Direction direction=Direction.LEFT;

    public ControlPanel(final GreedSnake game){
        this.game=game;
        setLayout(new GridLayout(3,1,0,4));
        snakePanel=new ColorConfig(game);
        scoreField=new JTextField("0");
        slider=new JSlider(JSlider.HORIZONTAL,MARK_MIN,MARK_MAX,MARK_INIT);
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setBorder(BorderFactory.createEmptyBorder(0,0,10,0));
        Font font=new Font("Serif",Font.ITALIC,15);
        slider.setFont(font);
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        infoPanel=new JPanel(new GridLayout(4,1,0,0));
        infoPanel.add(new JLabel("Game Level"));
        infoPanel.add(slider);
        infoPanel.add(new JLabel("Obtained Score"));
        infoPanel.add(scoreField);

        playButton=new JButton("Start");
        pauseButton=new JButton("Pause");
        stopButton=new JButton("Stop");
        buttonPanel=new JPanel(new GridLayout(3,1,0,1));
        buttonPanel.add(playButton);
        buttonPanel.add(pauseButton);
        buttonPanel.add(stopButton);
        add(snakePanel);
        add(infoPanel);
        add(buttonPanel);

        playButton.addActionListener(this);
        pauseButton.addActionListener(this);
        stopButton.addActionListener(this);

        slider.addChangeListener(this);

        //timer=new Timer(500, this);
        timer=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                scoreField.setText(""+game.getScore());
            }
        });
        timer.start();
        pauseButton.addKeyListener(this);
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source=(JSlider)e.getSource();
        if(source.getValueIsAdjusting()){
            game.setLevel(source.getValue());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Start"))
            game.playGame();
        else if(e.getActionCommand().equals("Pause"))
            game.pauseGame();
        else if(e.getActionCommand().equals("Continue"))
            game.resumeGame();
        else if(e.getActionCommand().equals("Stop"))
            game.stopGame();
    }



    public void setPlayButtonEnable(boolean enable){
        playButton.setEnabled(enable);
    }

    public void setPauseButtonLabel(boolean pause){
        pauseButton.setText(pause?"Pause":"Continue");
    }

    public void reset(){
        scoreField.setText("0");
        slider.setValue(MARK_INIT);
        game.setLevel(0);
        game.setScore(0);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(!game.isPlaying())
            return;
        switch (e.getKeyCode()){
            case KeyEvent.VK_DOWN:
                if(direction!=Direction.DOWN && direction!=Direction.UP){
                    game.changeDirection(Direction.DOWN);
                    direction=Direction.DOWN;
                    System.out.println("Down");
                }
                break;
            case KeyEvent.VK_LEFT:
                if(direction!=Direction.LEFT && direction!=Direction.RIGHT){
                    game.changeDirection(Direction.LEFT);
                    direction=Direction.LEFT;
                    System.out.println("Left");
                }
                break;
            case KeyEvent.VK_RIGHT:
                if(direction!=Direction.RIGHT && direction!=Direction.LEFT){
                    game.changeDirection(Direction.RIGHT);
                    direction=Direction.RIGHT;
                    System.out.println("Right");
                }
                break;
            case KeyEvent.VK_UP:
                if(direction!=Direction.UP && direction!=Direction.DOWN){
                    game.changeDirection(Direction.UP);
                    direction=Direction.UP;
                    System.out.println("Up");
                }
                break;
            default:
                break;

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
