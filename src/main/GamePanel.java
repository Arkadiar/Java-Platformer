package main;
import inputs.KeyboardInputs;
import inputs.MouseInputs;

import java.awt.*;
import javax.swing.*;

import static main.gameClass.*;

public class GamePanel extends JPanel {

    private MouseInputs mouseInputs;
    private gameClass game;

    public GamePanel(gameClass game){
        mouseInputs= new MouseInputs(this);
        this.game = game;

        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);

    }


    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH,GAME_HEIGHT);

        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }
    public void gameUpdate(){
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);

    }
    public gameClass getGame(){
        return game;
    }
}
