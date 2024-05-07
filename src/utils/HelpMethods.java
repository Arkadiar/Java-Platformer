package utils;

import main.gameClass;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class HelpMethods {
    public static boolean canMoveHere(float x, float y, float width, float height, int[][] lvlData){
        if(!isSolid(x,y,lvlData))
            if(!isSolid(x+width, y + height, lvlData))
                if(!isSolid(x+width, y, lvlData))
                    if(!isSolid(x, y + height,lvlData))
                        return true;
        return false;


    }

    private static boolean isSolid(float x, float y, int[][] lvlData){
        if(x < 0 || x >= gameClass.GAME_WIDTH)
            return true;

        if(y < 0 || y >= gameClass.GAME_HEIGHT)
            return true;


        float xIndex =  x / gameClass.TILES_SIZE;
        float yIndex = y / gameClass.TILES_SIZE;

        int value = lvlData[(int) yIndex][(int) xIndex];

        if(value >= 48 || value <0 ||value != 11)
            return true;
        return false;

    }
    public static float getEntityXPos(Rectangle2D.Float hitBox, float xSpeed){
        int curTile = (int)(hitBox.x/ gameClass.TILES_SIZE);
        if( xSpeed> 0){
            // right
            int tileXPos = curTile *gameClass.TILES_SIZE;
            int xOffset = (int)(gameClass.TILES_SIZE - hitBox.width);
            return tileXPos + xOffset -1;
        }else{
            //left
            return curTile * gameClass.TILES_SIZE;
        }
    }

    public static float getEntityYPos(Rectangle2D.Float hitBox, float airSpeed){
        int curTile = (int)(hitBox.y/ gameClass.TILES_SIZE);
        if(airSpeed>0){
            //falling
            int tileYPos = curTile *gameClass.TILES_SIZE;
            int yOffset = (int)(gameClass.TILES_SIZE - hitBox.height);
            return tileYPos + yOffset -1;
        }else{
            //jumping
            return curTile * gameClass.TILES_SIZE;
        }

    }
    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvlData){
        if(!isSolid(hitBox.x, hitBox.y+hitBox.height+1, lvlData)){
            if(!isSolid(hitBox.x+hitBox.width, hitBox.y + hitBox.height+1, lvlData)){
                return false;
            }
        }
        return true;
    }

}
