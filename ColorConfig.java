import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by dell on 2017/2/20.
 */
public class ColorConfig extends JPanel{
    public ColorConfig(GreedSnake applet){
        this.applet=applet;
        setLayout(new GridLayout(6,1,0,1));
        panel[0]=new JButton("Color of Background");
        panel[1]=new JButton("Color of GreedSnake");
        panel[2]=new JButton("Color of Food");
        panel[3]=new JButton("Color of EatedFood");

        for(int i=0;i<panel.length;i++){
            panel[i].setLayout(new GridLayout(2,1));
            panel[i].addActionListener(new ActionHandler());
            add(panel[i]);
        }

        panel[0].setBackground(ColorGroup.COLOR_BACK);
        panel[1].setBackground(ColorGroup.COLOR_SNAKE);
        panel[2].setBackground(ColorGroup.COLOR_BEAN);
        panel[3].setBackground(ColorGroup.COLOR_EATEDBEAN);

        commitButton=new JButton("Confirm");
        commitButton.addActionListener(new ActionHandler());
        cancelButton=new JButton("Cancel");
        cancelButton.addActionListener(new ActionHandler());
        add(commitButton);
        add(cancelButton);
        setVisible(true);
    }

    private class ActionHandler implements ActionListener{
        public void actionPerformed(ActionEvent e){
            for(int i=0;i<color.length;i++){
                if(e.getSource()==panel[i]){
                    color[i]= JColorChooser.showDialog(ColorConfig.this,"Choose Color",Color.WHITE);
                    if(color[i]!=null){
                        panel[i].setBackground(color[i]);
                    }
                }
            }
            if(e.getSource()==commitButton){
                color[0]=(color[0]==null?ColorGroup.COLOR_BACK:color[0]);
                color[1]=(color[1]==null?ColorGroup.COLOR_SNAKE:color[1]);
                color[2]=(color[2]==null?ColorGroup.COLOR_BEAN:color[2]);
                color[3]=(color[3]==null?ColorGroup.COLOR_EATEDBEAN:color[3]);
                ColorGroup.setCOLOR_BACK(color[0]);
                ColorGroup.setCOLOR_SNAKE(color[1]);
                ColorGroup.setCOLOR_BEAN(color[2]);
                ColorGroup.setCOLOR_EATEDBEAN(color[3]);
                GameCanvas.getCanvasInstance().repaint();
            }
            else if(e.getSource()==cancelButton){
                panel[0].setBackground(ColorGroup.COLOR_BACK);
                panel[1].setBackground(ColorGroup.COLOR_SNAKE);
                panel[2].setBackground(ColorGroup.COLOR_BEAN);
                panel[3].setBackground(ColorGroup.COLOR_EATEDBEAN);
            }
        }
    }

    private GreedSnake applet;
    private JButton[] panel=new JButton[4];
    private JButton commitButton,cancelButton;
    Color[] color=new Color[4];
}
