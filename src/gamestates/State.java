package gamestates;

import main.gameClass;
import ui.MenuButton;

import java.awt.event.MouseEvent;

public class State {
    protected gameClass game;

    public State(gameClass game){
        this.game = game;
    }

    public boolean isIn(MouseEvent e, MenuButton mb){
        return mb.getBounds().contains(e.getX(), e.getY());
    }

    public gameClass getGame(){
        return game;
    }
}
