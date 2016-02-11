/**
 * Created by Cody Thomas Zeitler on 12/8/2015.
 */

import javax.swing.*;
import java.awt.event.*;


public class GameControl implements ActionListener, KeyListener {

    //Enumeration type for difficulty.
    public enum Difficulty{
        EASY(10), NORMAL(7), HARD(30);

        int diff;

        private Difficulty(int diff){
            this.diff = diff;
        }

        public int getDifficulty(){
            return diff;
        }
    }

    //Difficultly for mario run speed.
    private static Difficulty game_difficulty = Difficulty.NORMAL;
    public static Difficulty getDifficulty (){return game_difficulty;}

    private Level currentLevel; //object that stores all the data to manipulate the level text on the top left of the screen.
    private Life currentLifes; //object that stores and manipulates all data associated with lives.

    private static Mario mario =  new Mario(125,125, 50, 75);  //Level one starting positions.
    public static Mario getMario(){
        return mario;
    }

    private static GameFrame mainGameFrame; //main game frame, static only one exist.
    //retrieves the game frame object, so you can access private variables such as height, width, mario etc.
    public static GameFrame getMainGameFrame(){
        return mainGameFrame;
    }

    private String gameTitle; //title that is displayed on the physical screen.
    private int gameWindowWidth; //width of the physical screen.
    private int gameWindowHeight; //height of the phsyical screen.
    private LevelOne levelOne; //level one object.
    private LevelTwo levelTwo; //level two object.
    private LevelThree levelThree; //level three object.
    private Timer timer; //main game timer, activates every 1000 / 60 ms, a.k.a 60 frames per second.


    public GameControl() {

        gameWindowWidth = 1900; //unchangeable - frame width.
        gameWindowHeight = 1025; //unchangeable - frame height.
        //Game Frame title, dispalyed in the upper left of the frame.
        gameTitle = "Mario in the Darker World @LostTriangleStudios.";
        //Instantiating the GameFrame with the above three variables.
        //Game Frame is a JFrame.
        mainGameFrame = new GameFrame(gameTitle, gameWindowWidth, gameWindowHeight);
        mainGameFrame.addKeyListener(this); //making it so the frame is listening for a keyListener based on the code listed in this class.
        //Instantiating life object, this is what's displayed ON SCREEN on the top left corner.
        currentLifes = new Life();

        levelOne = new LevelOne(); //Instantiating level one object.
        currentLevel = new Level(Level.Levels.ONE); //Setting current level to level one.
        currentLevel.setCurrentLevel(levelOne.getCurrentLevel()); //set current level one. (this seems redundant).
        mainGameFrame.add(levelOne); //Adding the level one jPanel to the main game frame.
        timer = levelOne.getTimer(); //Retrieving timer from level one object.
        timer.addActionListener(mario); //Adding mario actionListener to timer so mario data is updated every timer clock cycle.
        timer.addActionListener(this); //Repaint and level completion actionListener.

        //Setting frame so the user can see it. This should be called after the JPanels are added so  you don't
        //get a blank screen.
        mainGameFrame.setVisible(true);
    }
    //Setting up level two to be the next thing executed by the timer and shown by the screen.
    private void initializeLevelTwo(){

        levelTwo = new LevelTwo(); //creating level two.
        currentLevel.setCurrentLevel(Level.Levels.TWO); //setting the current level to level two
        //setting the main game timer to level two's specific game timer.
        //This has a actionListener already specified within it that updates everything in level two data wise
        // minus mario and checking if the level needs to be switched out.
        timer = levelTwo.getGameLoop();

        //Taking the timer retrieved from the level two object's timer and making it so the timer updates
        //mario's coordinates based on the last arrow key pressed.
        timer.addActionListener(mario);
        timer.addActionListener(this);
    }
    //Setting up level three to be the next thing executed by the timer and shown by the screen.
    private void initializeLevelThree(){

        levelThree = new LevelThree(); //creaing level three.
        currentLevel.setCurrentLevel(Level.Levels.THREE); //setting current level to level three.
        timer = levelThree.getGameLoop(); //retrieving timer from level three object that has been set up.
        timer.addActionListener(mario); //making it so mario's data is updated with the newly retried timer.
        timer.addActionListener(this);

    }

    public void startGame(){
       timer.start();
    }
    public void stopGame(){
        timer.stop();
    }

    //This actionPerformed event constantly checks if the current level is complete.
    //If it is the case that the level has been completed, then if you are on n level, the game needs to
    //switch to the n + 1 level.
    //This also makes the main game timer constantly execute repaint. This suggest that the swing architecture
    //should update whatever is on the screen. (NOTE not force).
    public void actionPerformed(ActionEvent e){

        //If the current level of the game is level one, enter here.
        if(currentLevel.getCurrentLevel() == Level.Levels.ONE) {
            //Enters here when a boolean flag has been activated once level one has been completed. (getIsLevelComplete)
            //This flag is set once mario enters the door of level one.
            if (levelOne.getIsLevelComplete() && currentLevel.getCurrentLevel() == Level.Levels.ONE) {

                timer.stop(); //stops the current main game timer.
                initializeLevelTwo(); //private function that sets up level two.
                mainGameFrame.remove(levelOne); //removes level one from the main frame.
                mainGameFrame.revalidate(); //needed to switch between two jPanels
                mainGameFrame.add(levelTwo); //puts level two into the frame.
                mainGameFrame.revalidate(); //needed to switch between two jPanels
                mainGameFrame.repaint(); //forces the frame to display something (well not force, but suggests it to the swing architecture)
                timer.start(); //starts the timer associated with level two. This is updated within initializeLevelTwo()
            }
        }
        //If the current level of the game is level two.
        if(currentLevel.getCurrentLevel() == Level.Levels.TWO) {
            //Enters here when a boolean flag has been activated once level two has been completed. (getIsLevelComplete)
            //This flag is set once mario enters the end door of level two.
            if (levelTwo.getIsLevelComplete()) {

                timer.stop(); //stops the current main game timer.
                initializeLevelThree(); //private function that sets up level three.
                mainGameFrame.remove(levelTwo); //removes level two from the main frame.
                mainGameFrame.revalidate(); //needs to switch between two jPanels
                mainGameFrame.add(levelThree); //puts level three into the frame.
                mainGameFrame.revalidate(); //needs to switch between two jPanels.
                mainGameFrame.repaint(); //suggest heavily to the swing architecture that it should repaint on the EDT.
                timer.start(); //starts the timer associated with level three. The timer is updated within initializeLevelThree().
            }
        }

        mainGameFrame.repaint(); //updates the main game frame.

    }

    //KeyListener that allows the user to change the mario direciton.
    public void keyPressed(KeyEvent e){

        //If left arrow is pressed, mario starts going left.
        if ( e.getKeyCode() == KeyEvent.VK_LEFT ){

            mario.setMarioDirection("LEFT"); //mario objects direction variable is set to left/

        }
        //If right arrow is pressed, mario starts going right.
        else if ( e.getKeyCode() == KeyEvent.VK_RIGHT ){

            mario.setMarioDirection("RIGHT");//mario objects direction variable is set to right

        }
        //If down arrow is pressed, mario starts going down.
        else if ( e.getKeyCode() == KeyEvent.VK_DOWN ){

            mario.setMarioDirection("DOWN");//mario objects direction variable is set to down

        }
        //If up arrow is pressed, mario starts going up.
        else if ( e.getKeyCode() == KeyEvent.VK_UP ){

            mario.setMarioDirection("UP");//mario objects direction variable is set to up

        }
        //If space bar is pressed, mario commits suicide and re-spawns at the beginning of the level.
        else if (e.getKeyCode() == KeyEvent.VK_SPACE){
            int current_lives = Life.getNumberOfLives() - 1; //<Mario loses a life since he died because he suicided.
            Life.setNumberOfLives(current_lives); //Updates the life counter to minus one of whatever it was.
            mario.resetMarioPosition(currentLevel.getCurrentLevel()); //Mario resets to his starting position of whatever level he was on.
        }
        //Error branch. Only reaches here if user did not up down right left or spacebar.
        else {
            //Prints out whatever key the user pressed.
            System.out.println(e.getKeyChar());
        }

    }
    //Needed here since this class implements KeyListener it HAS to implement the following two functions
    //as well as the one above it.
    public void keyReleased(KeyEvent e){}
    //Forced to implement since it implements KeyListener.
    public void keyTyped(KeyEvent e){}

}
