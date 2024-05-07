package ui;

import gamestates.Gamestate;
import gamestates.Playing;
import main.gameClass;
import utils.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static utils.Constants.UI.PauseButtons.*;
import static utils.Constants.UI.UrmButtons.*;

public class pauseOverlay {
    private Playing playing;
    private BufferedImage backgroundImg;
    private int bgX, bgY, bgW, bgH;
    private SoundButton musicButton, sfxButton;
    private UrmButton menuB,replayB, unpauseB;


    public pauseOverlay(Playing playing){
        this.playing = playing;
        loadBackground();
        createSoundButtons();
        createUrmButtons();


    }

    private void createUrmButtons() {
        int menuX = (int)(320* gameClass.SCALE);
        int replayX = (int)(395*gameClass.SCALE);
        int unpauseX = (int)(468*gameClass.SCALE);
        int bY = (int)(280*gameClass.SCALE);

        menuB = new UrmButton(menuX, bY, URM_SIZE, URM_SIZE, 2);
        replayB = new UrmButton(replayX, bY, URM_SIZE, URM_SIZE, 1);
        unpauseB = new UrmButton(unpauseX, bY, URM_SIZE, URM_SIZE, 0);
    }

    private void createSoundButtons() {
        int soundX = (int) (450*gameClass.SCALE);
        int musicY = (int) (169*gameClass.SCALE);
        int sfxY = (int) (222 *gameClass.SCALE);
        musicButton = new SoundButton(soundX, musicY, SOUND_SIZE,SOUND_SIZE);
        sfxButton = new SoundButton(soundX, sfxY, SOUND_SIZE,SOUND_SIZE);
    }

    private void loadBackground() {
        backgroundImg = LoadSave.GetSpriteAtlas(LoadSave.PAUSE_BG);
        bgW = (int)((backgroundImg.getWidth()-500)* gameClass.SCALE);
        bgH = (int)((backgroundImg.getHeight()-300)* gameClass.SCALE);
        bgX = gameClass.GAME_WIDTH / 2 - bgW / 2;
        bgY = 50;
    }

    public void update(){
        musicButton.update();
        sfxButton.update();
        menuB.update();
        replayB.update();
        unpauseB.update();

    }
    public void draw(Graphics g){
        g.drawImage(backgroundImg, bgX, bgY, bgW, bgH, null);
        musicButton.draw(g);
        sfxButton.draw(g);

        menuB.draw(g);
        replayB.draw(g);
        unpauseB.draw(g);

    }
    public void mouseDragged(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {
        if(isIn(e, musicButton))
            musicButton.setMousePressed(true);
        else if(isIn(e, sfxButton))
            sfxButton.setMousePressed(true);
        else if(isIn(e, menuB))
            menuB.setMousePressed(true);
        else if(isIn(e, replayB))
            replayB.setMousePressed(true);
        else if(isIn(e, unpauseB))
            unpauseB.setMousePressed(true);

    }


    public void mouseReleased(MouseEvent e) {
        if(isIn(e, musicButton)){
            if(musicButton.isMousePressed())
                musicButton.setMuted(!musicButton.isMuted());
        } else if(isIn(e, sfxButton)){
            if(sfxButton.isMousePressed())
                sfxButton.setMuted(!sfxButton.isMuted());
        }else if(isIn(e, menuB)){
            if(menuB.isMousePressed())
                Gamestate.state= Gamestate.MENU;
        }else if(isIn(e, replayB)) {
            if (replayB.isMousePressed())
                System.out.println("Replaying");
        }
        else if(isIn(e, unpauseB)){
            if(unpauseB.isMousePressed())
                playing.unpauseGame();
        }


        musicButton.resetBools();
        sfxButton.resetBools();
        menuB.resetBools();
        replayB.resetBools();
        unpauseB.resetBools();
    }


    public void mouseMoved(MouseEvent e) {
        musicButton.setMouseOver(false);
        sfxButton.setMouseOver(false);
        menuB.setMouseOver(false);
        replayB.setMouseOver(false);
        unpauseB.setMouseOver(false);
        if(isIn(e, musicButton))
            musicButton.setMouseOver(true);
        else if(isIn(e, sfxButton))
            sfxButton.setMouseOver(true);
        else if(isIn(e, menuB))
            menuB.setMouseOver(true);
        else if(isIn(e, replayB))
            replayB.setMouseOver(true);
        else if(isIn(e, unpauseB))
            unpauseB.setMouseOver(true);




    }

    private boolean isIn(MouseEvent e, PauseButton b){
        return b.getBounds().contains(e.getX(), e.getY());
    }



}
