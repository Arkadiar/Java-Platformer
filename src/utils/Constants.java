package utils;

import main.GamePanel;
import main.gameClass;

public class Constants {
    public static class UI{
        public static class Buttons{
            public static final int B_WIDTH_DEFAULT= 140;
            public static final int B_HEIGHT_DEFAULT=56;
            public static final int B_WIDTH =(int) (B_WIDTH_DEFAULT * gameClass.SCALE);
            public static final int B_HEIGHT =(int) (B_HEIGHT_DEFAULT * gameClass.SCALE);
        }

        public static class PauseButtons{
            public static final int SOUND_SIZE_DEFAULT = 42;
            public static final int SOUND_SIZE = (int) (SOUND_SIZE_DEFAULT * gameClass.SCALE);
        }
        public static class UrmButtons{
            public static final int URM_DEFAULT_SIZE = 56;
            public static final int URM_SIZE = (int)((URM_DEFAULT_SIZE*gameClass.SCALE)-30);
        }
    }

    public static class Directions{
        public static final int LEFT=0;
        public static final int UP=1;
        public static final int RIGHT=2;
        public static final int DOWN=3;
    }
    public static class PlayerConstants{
        public static final int RUNNING =8;
        public static final int IDLE =5;
        public static final int JUMP = 6;
        public static final int FALLING = 3;
        public static final int DEATH = 2;
        public static final int HIT = 4;
        public static final int ATTACK_1 = 0;
        public static final int ATTACK_2 = 1;

        public static int GetSpriteAmount(int player_action) {
            switch (player_action) {
                case RUNNING:
                case IDLE:
                case DEATH:
                    return 10;
                case JUMP:
                case FALLING:
                    return 3;
                case HIT:
                    return 1;
                case ATTACK_1:
                    return 4;
                case ATTACK_2:
                    return 6;

                default:
                    return 1;
            }

        }
    }
}
