import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by dell on 2017/2/20.
 */
public class ControlPanel extends JPanel{
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

        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                game.playGame();
            }
        });

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                if(pauseButton.getText().equals("Pause"))
                    game.pauseGame();
                else
                    game.resumeGame();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                game.stopGame();
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent event) {
                JSlider source=(JSlider)event.getSource();
                if(source.getValueIsAdjusting()){
                    game.setLevel(source.getValue());
                }
            }
        });

        timer=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                scoreField.setText(""+game.getScore());
            }
        });

        timer.start();
        addKeyListener(new ControlKeyListener());
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

    private class ControlKeyListener extends KeyAdapter {
        private Direction direction=Direction.LEFT;
        public void keyPressed(KeyEvent ke){
            if(!game.isPlaying())
                return;
            switch(ke.getKeyCode()){
                case KeyEvent.VK_DOWN:
                    if(direction!=Direction.DOWN && direction!=Direction.UP){
                        game.changeDirection(Direction.DOWN);
                        direction=Direction.DOWN;
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if(direction!=Direction.LEFT && direction!=Direction.RIGHT){
                        game.changeDirection(Direction.LEFT);
                        direction=Direction.LEFT;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if(direction!=Direction.RIGHT && direction!=Direction.LEFT){
                        game.changeDirection(Direction.RIGHT);
                        direction=Direction.RIGHT;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if(direction!=Direction.UP && direction!=Direction.DOWN){
                        game.changeDirection(Direction.UP);
                        direction=Direction.UP;
                    }
                    break;
                default:
                    break;
            }
        }
    }
}
