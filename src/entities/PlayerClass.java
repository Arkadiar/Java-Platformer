package entities;

import main.gameClass;
import utils.LoadSave;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import static utils.Constants.Directions.*;
import static utils.Constants.Directions.DOWN;
import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;
import static utils.HelpMethods.getEntityXPos;

public class PlayerClass extends Entity{

    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed= 25;
    private int playerAction = IDLE;
    private int playerDir = -1;
    private boolean moving = false, attacking= false;
    private boolean left, up, right, down, jump;
    private float playerSpeed = 1.0f * gameClass.SCALE;
    private int[][] lvlData;
    private float xDrawOffset = 33 * gameClass.SCALE;
    private float yDrawOffset = 35 * gameClass.SCALE;
//  JUMP / GRAVITY
    private float airSpeed = 0f;
    private float gravity = 0.04f * gameClass.SCALE;
    private float jumpSpeed =-2.25f *gameClass.SCALE;
    private float fallCollision=  0.5f * gameClass.SCALE;
    private boolean inAir = false;


    public PlayerClass(float x, float y, int width, int height) {
        super(x, y, width, height);
        loadAnimations();
        initHitbox(x, y, (int) (20*gameClass.SCALE),(int)(28*gameClass.SCALE));

    }
    public void update(){
        updateAnimationTick();
        setAnimation();
        updatePos();
    }

    public void render(Graphics g){
        g.drawImage(animations[playerAction][aniIndex], (int) (hitBox.x - xDrawOffset), (int)(hitBox.y - yDrawOffset),144,96,null);
        drawHitbox(g);

    }

    private void updateAnimationTick() {
        aniTick++;
        if(aniTick>= aniSpeed){
            aniTick=0;
            aniIndex++;
            if(aniIndex>= GetSpriteAmount(playerAction)){
                aniIndex=0;
                attacking= false;

            }
        }
    }
    private void setAnimation() {

        int startAni = playerAction;
        if (moving)
            playerAction = RUNNING;
        else playerAction = IDLE;

        if(inAir){
            if(airSpeed<0)
                playerAction = JUMP;
                else playerAction= FALLING;
        }

        if(attacking){
            playerAction=ATTACK_1;
        }
        if(startAni!=playerAction){
            resetAniTick();

        }
    }
    private void resetAniTick() {
        aniTick=0;
        aniIndex=0;
    }
    private void updatePos() {
        moving= false;
        if(jump)
            jump();
        if(!left && !right && !inAir)
            return;
        float xSpeed =0;
        if(left)
            xSpeed -=playerSpeed;
        if(right)
            xSpeed += playerSpeed;
        if(!inAir){
            if(!isEntityOnFloor(hitBox, lvlData))
                inAir= true;
        }

        if(inAir){
            if(canMoveHere(hitBox.x, hitBox.y +airSpeed, hitBox.width, hitBox.height, lvlData)){
                hitBox.y+=airSpeed;
                airSpeed+=gravity;
                updateXPos(xSpeed);
            }else{
                hitBox.y= getEntityYPos(hitBox, airSpeed);
                if(airSpeed>0)
                    resetInAir();
                else
                    airSpeed=fallCollision;

                updateXPos(xSpeed);
            }

        }else
            updateXPos(xSpeed);

        moving = true;
    }

    private void jump() {
        if(inAir)
            return;
        inAir= true;
        airSpeed= jumpSpeed;
    }

    private void resetInAir() {
        inAir=false;
        airSpeed=0;
    }

    private void updateXPos(float xSpeed) {
        if(canMoveHere(hitBox.x+xSpeed,hitBox.y, hitBox.width, hitBox.height, lvlData)){
            hitBox.x += xSpeed;
        }else{
            hitBox.x= getEntityXPos(hitBox, xSpeed);
        }
    }





    private void loadAnimations() {

            BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
            animations = new BufferedImage[9][10];
            for(int j =0; j<animations.length;j++)
                for(int i = 0; i< animations[j].length; i++)
                    animations[j][i]= img.getSubimage(i*120, j*80, 120, 80);

    }
    public void loadLevelData(int[][] lvlData){
        this.lvlData= lvlData;
        if (!isEntityOnFloor(hitBox, lvlData))
            inAir = true;

    }

    public void resetDirBools(){
        left=false;
        right=false;
        up=false;
        down=false;
    }
    public boolean isLeft() {
        return left;
    }
    public void setAttack(boolean attacking){
        this.attacking=attacking;

    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setJump(boolean jump){
        this.jump= jump;
    }
}
