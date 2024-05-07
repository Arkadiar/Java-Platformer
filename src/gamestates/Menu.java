package gamestates;

import main.gameClass;
import ui.MenuButton;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements Statemethods {
    private MenuButton[] buttons= new MenuButton[3];
    private BufferedImage backgroundImg;
    private int menuX, menuY, menuWidth, menuHeight;


    public Menu(gameClass game) {

        super(game);
        loadButtons();
        loadBg();
    }

    private void loadBg() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BG);
        menuWidth = (int) ((backgroundImg.getWidth()-300)*gameClass.SCALE);
        menuHeight = (int) ((backgroundImg.getHeight()-150)*gameClass.SCALE);
        menuX = gameClass.GAME_WIDTH / 2 - menuWidth / 2;
        menuY = (int) (-10 * gameClass.SCALE);

    }

    private void loadButtons() {
        buttons[0] = new MenuButton(gameClass.GAME_WIDTH / 2, (int) (150*gameClass.SCALE), 0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(gameClass.GAME_WIDTH / 2, (int) (220*gameClass.SCALE), 1, Gamestate.OPTIONS);
        buttons[2] = new MenuButton(gameClass.GAME_WIDTH / 2, (int) (290*gameClass.SCALE), 2, Gamestate.QUIT);
    }

    @Override
    public void update() {
        for(MenuButton mb: buttons)
            mb.update();
    }

    @Override
    public void draw(Graphics g) {

        g.drawImage(backgroundImg, menuX, menuY, menuWidth, menuHeight, null);
        for(MenuButton mb: buttons)
            mb.draw(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb: buttons){
            if(isIn(e, mb)){
                mb.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb: buttons){
            if(isIn(e, mb)){
                if(mb.isMousePressed())
                    mb.applyGameState();
                break;
            }
        }
        resetButtons();
    }

    private void resetButtons() {
        for(MenuButton mb:buttons)
            mb.resetBools();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb: buttons)
            mb.setMouseOver(false);
        for(MenuButton mb:buttons){
            if(isIn(e, mb)){
                mb.setMouseOver(true);
                break;
            }
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_ENTER)
            Gamestate.state=Gamestate.PLAYING;
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
