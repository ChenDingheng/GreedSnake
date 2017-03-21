import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by dell on 2017/2/20.
 * modified ok!
 */
public class ColorConfig extends JPanel implements ActionListener{

    private GreedSnake applet;
    private JButton[] colorPanelButton=new JButton[3];
    private JButton commitButton,cancelButton;
    Color[] color=new Color[3];


    public ColorConfig(GreedSnake applet){
        this.applet=applet;
        setLayout(new GridLayout(6,1,0,1));
        colorPanelButton[0]=new JButton("Color of Background");
        colorPanelButton[1]=new JButton("Color of GreedSnake");
        colorPanelButton[2]=new JButton("Color of Food");

        for(int i=0;i<colorPanelButton.length;i++){
            colorPanelButton[i].setLayout(new GridLayout(2,1));
            colorPanelButton[i].addActionListener(this);
            add(colorPanelButton[i]);
        }

        colorPanelButton[0].setBackground(ColorGroup.COLOR_BACK);
        colorPanelButton[1].setBackground(ColorGroup.COLOR_SNAKE);
        colorPanelButton[2].setBackground(ColorGroup.COLOR_BEAN);

        commitButton=new JButton("Confirm");
        commitButton.addActionListener(this);
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(this);
        add(commitButton);
        add(cancelButton);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        for(int i=0;i<color.length;i++){
            if(e.getSource()==colorPanelButton[i]){
                color[i]= JColorChooser.showDialog(ColorConfig.this,"Choose Color",Color.WHITE);
                if(color[i]!=null){
                    colorPanelButton[i].setBackground(color[i]);
                }
            }
        }
        if(e.getSource()==commitButton){
            color[0]=(color[0]==null?ColorGroup.COLOR_BACK:color[0]);
            color[1]=(color[1]==null?ColorGroup.COLOR_SNAKE:color[1]);
            color[2]=(color[2]==null?ColorGroup.COLOR_BEAN:color[4]);
            ColorGroup.setCOLOR_BACK(color[0]);
            ColorGroup.setCOLOR_SNAKE(color[1]);
            ColorGroup.setCOLOR_BEAN(color[2]);
            GameCanvas.getCanvasInstance().repaint();
        }
        else if(e.getSource()==cancelButton){
            colorPanelButton[0].setBackground(ColorGroup.COLOR_BACK);
            colorPanelButton[1].setBackground(ColorGroup.COLOR_SNAKE);
            colorPanelButton[2].setBackground(ColorGroup.COLOR_BEAN);
        }
    }
}
