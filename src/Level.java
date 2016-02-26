/**
 * Created by Cody Thomas Zeitler on 12/8/2015.
 */
public class Level {

    public enum Levels{

        ONE,TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,ELEVEN, VICTORY;

    }

    Levels current_level;

    public Level(Levels current_level){
        this.current_level = current_level;

    }

    public Levels getCurrentLevel(){
        return current_level;
    }

    public void setCurrentLevel(Levels current_level){
        this.current_level = current_level;
    }

}
