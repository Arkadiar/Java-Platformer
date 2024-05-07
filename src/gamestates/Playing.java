package gamestates;

import entities.PlayerClass;
import levels.LevelManager;
import main.gameClass;
import ui.pauseOverlay;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class Playing extends State implements Statemethods{
    private PlayerClass playerClass;
    private LevelManager levelManger;
    private pauseOverlay pauseOverlay;
    private boolean paused = false;

    public Playing(gameClass game) {
        super(game);
        initClasses();
    }

    private void initClasses() {
        levelManger= new LevelManager(game);
        playerClass = new PlayerClass(200, 200,(int) (120 * gameClass.SCALE), (int) (80 * gameClass.SCALE));
        playerClass.loadLevelData(levelManger.getCurrentLevel().getLvlData());
        pauseOverlay = new pauseOverlay(this);
    }



    @Override
    public void update() {
        if(!paused){
            levelManger.update();
            playerClass.update();
        }else
            pauseOverlay.update();

    }

    @Override
    public void draw(Graphics g) {
        levelManger.draw(g);
        playerClass.render(g);
        if(paused)
            pauseOverlay.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton()== MouseEvent.BUTTON1){
            playerClass.setAttack(true);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(paused)
            pauseOverlay.mousePressed(e);


    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseReleased(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if(paused)
            pauseOverlay.mouseMoved(e);

    }

    public void unpauseGame(){
        paused = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                playerClass.setLeft(true);
                break;
            case KeyEvent.VK_D:
                playerClass.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                playerClass.setJump(true);
                break;
            case KeyEvent.VK_ESCAPE:
                paused=!paused;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        switch(e.getKeyCode()){
            case KeyEvent.VK_A:
                playerClass.setLeft(false);
                break;
            case KeyEvent.VK_D:
                playerClass.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                playerClass.setJump(false);
                break;

        }
    }
    public PlayerClass getPlayer(){
        return playerClass;
    }

    public void windowFocusLost() {
        playerClass.resetDirBools();
    }
}
