package utils;

import main.GamePanel;
import main.gameClass;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {
    public static final String PLAYER_ATLAS = "SpriteChar/PNGs.png";
    public static final String LEVEL_ATLAS = "SpriteChar/outside_sprites.png";
    public static final String LEVEL_ONE_DATA = "SpriteChar/level_one_data.png";
    public static final String MENU_BUTTONS = "SpriteChar/atlas1.png";
    public static final String MENU_BG = "SpriteChar/menuBG.png";
    public static final String PAUSE_BG = "SpriteChar/pauseBG.png";
    public static final String SOUND_BUTTONS = "SpriteChar/sound_button.png";
    public static final String URM_BUTTONS = "SpriteChar/urm_buttons.png";
    // C:\Users\Arkadia\Desktop\Java Games\First Game\res\spriteChar\_WallClimb.png
    //C:\Users\Arkadia\Desktop\Java Games\First Game\res\spriteChar\menuBG.png
    //C:\Users\Arkadia\Desktop\Java Games\First Game\res\spriteChar\button_atlas.png
    

    public static BufferedImage GetSpriteAtlas(String fileName) {
        BufferedImage img = null;
        InputStream is = LoadSave.class.getResourceAsStream("/res/" + fileName);


        try {
            img = ImageIO.read(is);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return img;


    }

    public static int[][] GetLevelData() {
        int[][] lvlData = new int[gameClass.TILES_IN_HEIGHT][gameClass.TILES_IN_WIDTH];
        BufferedImage img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value= color.getRed();
                if(value>=48)
                    value = 0;
                lvlData[j][i] = color.getRed();
            }
        }
        return lvlData;
    }
}

