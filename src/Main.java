import spritesheet.*;

/**
 * Created by Cody Thomas Zeitler on 12/7/2015.
 */
public class Main {

    public static void main(String [] args){

        new ForestAreaZeldaSpriteSheet();
        new GrassyAreaSpriteSheet();
        new ForestTilesSpriteSheet();
        new UndergroundSpriteSheet();
        new CastleFortressSpriteSheet();
        new GhostHouseSpriteSheet();
        new MarioSpriteSheet();
        new EnemySpriteSheet();
        new GroundTilesSpriteSheet();
        new GeneralTilesSpriteSheet();

        GameControl gameControl = new GameControl();
        gameControl.startGame();

    }

}
