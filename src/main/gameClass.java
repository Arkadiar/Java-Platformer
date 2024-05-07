package main;


import gamestates.Gamestate;
import gamestates.Playing;
import gamestates.Menu;
import levels.Level;


import java.awt.*;

public class gameClass implements Runnable{

    private windowClass gameWindow;
    private GamePanel gamePanel;
    private Thread gameThread;
    private final int FPS_SET = 120;
    private final int UPS_SET =200;

    private Playing playing;
    private Menu menu;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE= 1.5f;
    public final static int TILES_IN_WIDTH= 26;
    public final static int TILES_IN_HEIGHT= 14;
    public final static int TILES_SIZE= (int)(TILES_DEFAULT_SIZE*SCALE);
    public final static int GAME_WIDTH= TILES_SIZE*TILES_IN_WIDTH;
    public final static int GAME_HEIGHT= TILES_SIZE*TILES_IN_HEIGHT;



    public gameClass(){
        initClasses();
        gamePanel = new GamePanel(this);
        gameWindow = new windowClass(gamePanel);
        gamePanel.requestFocus();

        startGameLoop();



    }

    private void initClasses() {
        menu = new Menu(this);
        playing = new Playing(this);

    }

    private void startGameLoop(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    public void update(){
        switch (Gamestate.state){
            case MENU:
                menu.update();
                break;
            case PLAYING:
                playing.update();
                break;
            default:
                break;
        }

    }

    public void render(Graphics g){

        switch (Gamestate.state){
            case MENU:
            menu.draw(g);
            break;
            case PLAYING:
                playing.draw(g);
            break;
            case OPTIONS:
            case QUIT:
            default:
                System.exit(0);
            break;
        }
    }

    @Override
    public void run() {

        double timePerFrame=1000000000.0/ FPS_SET;
        double timePerUpdate =1000000000.0/ UPS_SET;


        long prevTime =System.nanoTime();
        int frames = 0;
        int updates = 0;
        long lastCheck= System.currentTimeMillis();

        double deltaU =0;
        double deltaF = 0;
        while(true){


            long currentTime = System.nanoTime();

            deltaU+= (currentTime-prevTime)/ timePerUpdate;
            deltaF+= (currentTime-prevTime)/ timePerFrame;
            prevTime=currentTime;

            if(deltaU>=1) {
                update();
                updates++;
                deltaU--;
            }

                if(deltaF>=1){
                    gamePanel.repaint();

                    frames++;
                    deltaF--;
                }


            if(System.currentTimeMillis()- lastCheck>=1000){
                lastCheck = System.currentTimeMillis();
                System.out.println("FPS: "+frames+" | UPS: " +updates);
                frames = 0;
                updates=0;
            }
        }

    }


    public void windowFocusLost() {
        if(Gamestate.state == Gamestate.PLAYING){
            playing.getPlayer().resetDirBools();
        }
    }
    public Menu getMenu(){
        return menu;
    }
    public Playing getPlaying(){
        return playing;
    }
}