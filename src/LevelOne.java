/**
 * Created by Cody Thomas Zeitler on 12/8/2015.
 */



import spritesheet.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.ListIterator;


//Considered Completed.
public class LevelOne extends JPanel implements ActionListener{

    private BackGroundImageGrayMiddle background_image_gray_middle;
    private BackGroundImageBox background_image_box;
    private LightBrownDirtMiddle lightBrownDirtMiddleBlock;
    private GrassLeft grassLeftBlock; //one of the background shrubs sitting on the ground.
    private GrassLeft grassLeftBlock1; //one of the background shrubs sitting on the ground.
    private GrassLeft grassLeftBlock2; //one of the background shrubs sitting on the ground.
    private GrassLeft grassLeftBlock3; //one of the background shrubs sitting on the ground.
    private GrassLeft grassLeftBlock4; //one of the background shrubs sitting on the ground.
    private GreenTurtle greenTurtle;
    private RedTurtle redTurtle;
    private RedTurtle redTurtle2;
    private GreenTurtle greenTurtleTopSide;
    private GreenTurtle greenTurtleLowerSide;
    private JungleSong jungleSong;
    public JungleSong getJungleSong(){
        return jungleSong;
    }
    private EvilJestSound evilJestSound;
    private int songLoopCounter;
    private boolean hasPlayedOnce = false;

    private LevelTitle levelTitle;

    public static RedBossDoor redBossDoor;

    private LinkedList<Rectangle> collision_box_list;
    public LinkedList getCollisionBoxList(){
        return collision_box_list;
    }
    public void setCollisionBoxList(LinkedList<Rectangle> collision_box_list){
        this.collision_box_list = collision_box_list;
    }

    private Level.Levels current_level;
    private Life lives;

    private boolean isLevelComplete;
    public boolean getIsLevelComplete() {
        return isLevelComplete;
    }

    public void setCurrentLevel(Level.Levels current_level){this.current_level = current_level;}
    public Level.Levels getCurrentLevel(){return current_level;}

    private Rectangle rect_1;
    private Rectangle rect_2;
    private Rectangle rect_3;
    private Rectangle rect_4;
    private Rectangle rect_5;
    private Rectangle rect_6;

    private Rectangle rect_7;
    private Rectangle rect_8;
    private Rectangle rect_9;
    private Rectangle rect_10;
    private Rectangle rect_11;


    private Timer game_loop;
    public Timer getTimer(){
        return game_loop;}


    public LevelOne(){
        background_image_gray_middle = new BackGroundImageGrayMiddle(0,0,50, 50);
        background_image_box = new BackGroundImageBox(0,0,50,50);
        lightBrownDirtMiddleBlock = new LightBrownDirtMiddle(0,0,50,50);
        grassLeftBlock = new GrassLeft(150,150,50,50); //Creating a non-collidable object shrub .
        grassLeftBlock1 = new GrassLeft( 300, 300, 75, 75);
        grassLeftBlock2 = new GrassLeft( 800, 800, 50, 50);
        grassLeftBlock3 = new GrassLeft( 1350, 250, 65, 65);
        grassLeftBlock4 = new GrassLeft( 1230, 700, 50, 50);

        collision_box_list = new LinkedList<>();
        greenTurtle = new GreenTurtle(1250,480,65,125); //Creating green turtle at (1250, 480) with width 65 and height 125.
        greenTurtle.setIndexInLinkedList(5); //Giving green turtle a specified index that it is located in the environment linked list.
        redTurtle = new RedTurtle(700,300,55,115);
        redTurtle.setIndexInLinkedList(16);
        redTurtle2 = new RedTurtle(700, 700, 55, 115);
        redTurtle2.setIndexInLinkedList(17);
        greenTurtleTopSide = new GreenTurtle(1300, 75, 65, 125);
        greenTurtleTopSide.setIndexInLinkedList(18);
        greenTurtleLowerSide = new GreenTurtle(1600, 655, 65, 125);
        greenTurtleLowerSide.setIndexInLinkedList(19); //Giving green turtle a specified index that it is located in the environment linked list.
        jungleSong = new JungleSong(); //Instantiating song that is played within level one.
        evilJestSound = new EvilJestSound();
        //This event allows a thread to run the sound for level one. It needs to be in this format since
        //swing is not thread safe.
        EventQueue.invokeLater(new Runnable(){

            public void run(){
                jungleSong.playSound();
            }

        });
        redBossDoor = new RedBossDoor(1505,800,75,125);

        levelTitle = new LevelTitle(500, 38, 50, "Boxy Beach");

        current_level = Level.Levels.ONE;
        lives = new Life();
        lives.setX(2);
        lives.setY(38);

        rect_1 = new Rectangle( 50, 300, 175, 175);
        rect_2 = new Rectangle( 450, 50, 175, 175);
        rect_3 = new Rectangle( 450, 225, 175, 175);
        rect_4 = new Rectangle( 450, 400 , 175 ,175 );
        rect_5 = new Rectangle( 450, 575, 175, 175);
        rect_6 = new Rectangle( 225, 600, 225, 225);
        rect_7 = new Rectangle( 935, GameControl.getMainGameFrame().getHeight() - 350, 275, 275);
        rect_8 = new Rectangle( 1010, GameControl.getMainGameFrame().getHeight() - 550, 200, 200);
        rect_9 = new Rectangle( 800, 50, 150,150);
        rect_10 = new Rectangle( 1050, 375, 100, 100);
        rect_11 = new Rectangle( 1500, 50, 200, 200);


        createCollisionBoxList();
        game_loop = new Timer(1000/60, this);
        game_loop.addActionListener(greenTurtle);
        game_loop.addActionListener(redTurtle);
        game_loop.addActionListener(redTurtle2);
        game_loop.addActionListener(greenTurtleTopSide);
        game_loop.addActionListener(greenTurtleLowerSide);


        greenTurtle.setEnvironment(collision_box_list, greenTurtle.getIndexInLinkedList());
        redTurtle.setEnvironment(collision_box_list, redTurtle.getIndexInLinkedList());
        redTurtle2.setEnvironment(collision_box_list, redTurtle2.getIndexInLinkedList());
        greenTurtleTopSide.setEnvironment(collision_box_list, greenTurtleTopSide.getIndexInLinkedList());
        greenTurtleLowerSide.setEnvironment(collision_box_list,  greenTurtleLowerSide.getIndexInLinkedList());
    }
    public Rectangle getVictoryBox(){
        return redBossDoor.getVictoryBox();
    }

    public void stopSound(){
        jungleSong.stopSound();
    }

    private void createCollisionBoxList(){

        Rectangle left_side_boxes = new Rectangle(0, 0, 50, GameControl.getMainGameFrame().getHeight());
        Rectangle right_side_boxes = new Rectangle(GameControl.getMainGameFrame().getWidth() - 50, 0,
                50, GameControl.getMainGameFrame().getHeight());
        Rectangle top_side_boxes = new Rectangle(0, 0, GameControl.getMainGameFrame().getWidth(), 50);
        Rectangle bottom_side_boxes = new Rectangle(0, GameControl.getMainGameFrame().getHeight() - 75,
                GameControl.getMainGameFrame().getWidth(), 50);

        collision_box_list.add(left_side_boxes);
        collision_box_list.add(right_side_boxes);
        collision_box_list.add(top_side_boxes);
        collision_box_list.add(bottom_side_boxes);
        collision_box_list.add(rect_1);
        collision_box_list.add(greenTurtle.getCollisionRectangle()); //5
        collision_box_list.add(rect_2);
        collision_box_list.add(rect_3);
        collision_box_list.add(rect_4);
        collision_box_list.add(rect_5);
        collision_box_list.add(rect_6);
        collision_box_list.add(rect_7);
        collision_box_list.add(rect_8);
        collision_box_list.add(rect_9);
        collision_box_list.add(rect_10);
        collision_box_list.add(rect_11);
        collision_box_list.add(redTurtle.getCollisionRectangle());
        collision_box_list.add(redTurtle2.getCollisionRectangle());
        collision_box_list.add(greenTurtleTopSide.getCollisionRectangle());
        collision_box_list.add(greenTurtleLowerSide.getCollisionRectangle());
    }

    public boolean checkCollision(){

        ListIterator<Rectangle> iterator = (ListIterator<Rectangle>) collision_box_list.iterator();

        while(iterator.hasNext()){

            Rectangle temp = iterator.next();

            if(temp.intersects(GameControl.getMario().getCollisionRectangle())){
                return true;
            }
        }
        return false;
    }

    //Controls where the dynamic  collision boxes are on the map.
    public void actionPerformed(ActionEvent e){

        if(!isLevelComplete) {
            //Runs to see if the static variable mario has collided with any of the boxes within this stage.
            if (checkCollision()) {
                lives.setNumberOfLives(lives.getNumberOfLives() - 1);


                EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        evilJestSound.playSound();
                    }

                });
                GameControl.getMario().resetMarioPosition(Level.Levels.ONE); //resetting mario to level one position.
            }
        }

        //Checks to see if the static variable mario has collided with the red door on the bottom right side
        //to change a flag (isLevelComplete) to true to allow the program to switch the game state to level two.
        if(GameControl.getMario().getCollisionRectangle().intersects(getVictoryBox())){
            //GameControl.getMario().setMarioPosition(Level.Levels.TWO);
            isLevelComplete = true;
        }



        Rectangle greenTurtleCollisionBox = new Rectangle(
                greenTurtle.getX(), greenTurtle.getY(),
                greenTurtle.getWidth(), greenTurtle.getWidth());

        collision_box_list.set(greenTurtle.getIndexInLinkedList(), greenTurtleCollisionBox);

        Rectangle redTurtleCollisionBox = new Rectangle(
                redTurtle.getX(), redTurtle.getY(),
                redTurtle.getWidth(), redTurtle.getHeight());

        collision_box_list.set(redTurtle.getIndexInLinkedList(), redTurtleCollisionBox);

        Rectangle redTurtle2CollisionBox = new Rectangle(

                redTurtle2.getX(), redTurtle2.getY(),
                redTurtle2.getWidth(), redTurtle2.getHeight());

        collision_box_list.set(redTurtle2.getIndexInLinkedList(), redTurtle2CollisionBox);

        Rectangle greenTurtleTopSideCollisionBox = new Rectangle(
                greenTurtleTopSide.getX(),greenTurtleTopSide.getY(),
                greenTurtleTopSide.getWidth(), greenTurtleTopSide.getHeight());

        collision_box_list.set(greenTurtleTopSide.getIndexInLinkedList(), greenTurtleTopSideCollisionBox);

        Rectangle greenTurtleLowerSideCollisionBox = new Rectangle(
                greenTurtleLowerSide.getX(),  greenTurtleLowerSide.getY(),
                greenTurtleLowerSide.getWidth(),  greenTurtleLowerSide.getHeight());

        collision_box_list.set( greenTurtleLowerSide.getIndexInLinkedList(), greenTurtleLowerSideCollisionBox);


    }

    //Painting in here is pretty simple (even though there'sBottomPatrollingGreenKoopaLocations a lot of code down there.)
    //Every object you see on screen in level one

    public void paintComponent(Graphics g){

        for(int z = 0; z < GameControl.getMainGameFrame().getHeight() ; z = z + 50) {
            for (int i = 0; i < GameControl.getMainGameFrame().getWidth(); i = i + 50) {

                g.drawImage(GroundTilesSpriteSheet.getSpriteSheet(),
                        lightBrownDirtMiddleBlock.getX() + i, lightBrownDirtMiddleBlock.getY() + z,
                        lightBrownDirtMiddleBlock.getWidth() + i, lightBrownDirtMiddleBlock.getHeight() + z,
                        lightBrownDirtMiddleBlock.getSX1(), lightBrownDirtMiddleBlock.getSY1(),
                        lightBrownDirtMiddleBlock.getSX2(), lightBrownDirtMiddleBlock.getSY2(),
                        null);

            }
        }
        //System.out.println(grassLeftBlock.getDx1() + " " + grassLeftBlock.getDy1() +
            //" " + grassLeftBlock.getDx2() + " " + grassLeftBlock.getDy2());

        g.drawImage(GroundTilesSpriteSheet.getSpriteSheet(),
                grassLeftBlock.getDx1(),grassLeftBlock.getDy1(),
                grassLeftBlock.getDx2(), grassLeftBlock.getDy2(),
                grassLeftBlock.getSX1(), grassLeftBlock.getSY1(),
                grassLeftBlock.getSX2(), grassLeftBlock.getSY2(),
                null);
        g.drawImage(GroundTilesSpriteSheet.getSpriteSheet(),
                grassLeftBlock1.getDx1(),grassLeftBlock1.getDy1(),
                grassLeftBlock1.getDx2(), grassLeftBlock1.getDy2(),
                grassLeftBlock1.getSX1(), grassLeftBlock1.getSY1(),
                grassLeftBlock1.getSX2(), grassLeftBlock1.getSY2(),
                null);
        g.drawImage(GroundTilesSpriteSheet.getSpriteSheet(),
                grassLeftBlock2.getDx1(),grassLeftBlock2.getDy1(),
                grassLeftBlock2.getDx2(), grassLeftBlock2.getDy2(),
                grassLeftBlock2.getSX1(), grassLeftBlock2.getSY1(),
                grassLeftBlock2.getSX2(), grassLeftBlock2.getSY2(),
                null);
        g.drawImage(GroundTilesSpriteSheet.getSpriteSheet(),
                grassLeftBlock3.getDx1(),grassLeftBlock3.getDy1(),
                grassLeftBlock3.getDx2(), grassLeftBlock3.getDy2(),
                grassLeftBlock3.getSX1(), grassLeftBlock3.getSY1(),
                grassLeftBlock3.getSX2(), grassLeftBlock3.getSY2(),
                null);
        g.drawImage(GroundTilesSpriteSheet.getSpriteSheet(),
                grassLeftBlock4.getDx1(),grassLeftBlock4.getDy1(),
                grassLeftBlock4.getDx2(), grassLeftBlock4.getDy2(),
                grassLeftBlock4.getSX1(), grassLeftBlock4.getSY1(),
                grassLeftBlock4.getSX2(), grassLeftBlock4.getSY2(),
                null);

        for(int i = 0; i < GameControl.getMainGameFrame().getWidth();  i = i + 50)
            g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                    background_image_box.getX() + i, background_image_box.getY(),
                    background_image_box.getWidth() + i, background_image_box.getHeight(),
                    background_image_box.getSX1(), background_image_box.getSY1(),
                    background_image_box.getSX2(), background_image_box.getSY2(),
                    null);
        for(int i = 0; i < GameControl.getMainGameFrame().getHeight(); i = i + 50){
            g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                    background_image_box.getX(), background_image_box.getY() + i,
                    background_image_box.getWidth(), background_image_box.getHeight() + i,
                    background_image_box.getSX1(), background_image_box.getSY1(),
                    background_image_box.getSX2(), background_image_box.getSY2(),
                    null);
        }
        for(int i = 0; i < GameControl.getMainGameFrame().getWidth(); i = i + 50){
            g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                    background_image_box.getX() + i, GameControl.getMainGameFrame().getHeight() - 75,
                    background_image_box.getWidth() + i, GameControl.getMainGameFrame().getHeight() - 25,
                    background_image_box.getSX1(), background_image_box.getSY1(),
                    background_image_box.getSX2(), background_image_box.getSY2(),
                    null);

        }
        //background_image_box.getBackgroundSprite()
        for(int i = 0; i < GameControl.getMainGameFrame().getHeight(); i = i + 50){
            g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                    GameControl.getMainGameFrame().getWidth() - 50, background_image_box.getY() + i,
                    GameControl.getMainGameFrame().getWidth() , background_image_box.getHeight() + i,
                    background_image_box.getSX1(), background_image_box.getSY1(),
                    background_image_box.getSX2(), background_image_box.getSY2(),
                    null);
        }
        //background_image_box.getBackgroundSprite()
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_1.getX(), (int)rect_1.getY(),
                (int)rect_1.getWidth() + (int)rect_1.getX(), (int)rect_1.getHeight() + (int)rect_1.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.setFont(lives.getFont());
        g.setColor(Color.WHITE);
        g.drawString(lives.getString(), lives.getX(), lives.getY());
        g.setFont(levelTitle.getFont());
        g.setColor(Color.WHITE);
        g.drawString(levelTitle.getNameOfLevel(), levelTitle.getX(), levelTitle.getY());

        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_2.getX(), (int)rect_2.getY(),
                (int)rect_2.getWidth() + (int)rect_2.getX(), (int)rect_2.getHeight() + (int)rect_2.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_3.getX(), (int)rect_3.getY(),
                (int)rect_3.getWidth() + (int)rect_3.getX(), (int)rect_3.getHeight() + (int)rect_3.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_4.getX(), (int)rect_4.getY(),
                (int)rect_4.getWidth() + (int)rect_4.getX(), (int)rect_4.getHeight() + (int)rect_4.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_5.getX(), (int)rect_5.getY(),
                (int)rect_5.getWidth() + (int)rect_5.getX(), (int)rect_5.getHeight() + (int)rect_5.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_6.getX(), (int)rect_6.getY(),
                (int)rect_6.getWidth() + (int)rect_6.getX(), (int)rect_6.getHeight() + (int)rect_6.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_7.getX(), (int)rect_7.getY(),
                (int)rect_7.getWidth() + (int)rect_7.getX(), (int)rect_7.getHeight() + (int)rect_7.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_8.getX(), (int)rect_8.getY(),
                (int)rect_8.getWidth() + (int)rect_8.getX(), (int)rect_8.getHeight() + (int)rect_8.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_9.getX(), (int)rect_9.getY(),
                (int)rect_9.getWidth() + (int)rect_9.getX(), (int)rect_9.getHeight() + (int)rect_9.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_10.getX(), (int)rect_10.getY(),
                (int)rect_10.getWidth() + (int)rect_10.getX(), (int)rect_10.getHeight() + (int)rect_10.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);
        g.drawImage(GhostHouseSpriteSheet.getSpriteSheet(),
                (int)rect_11.getX(), (int)rect_11.getY(),
                (int)rect_11.getWidth() + (int)rect_11.getX(), (int)rect_11.getHeight() + (int)rect_11.getY(),
                background_image_box.getSX1(), background_image_box.getSY1(),
                background_image_box.getSX2(), background_image_box.getSY2(),
                null);

        g.drawImage(CastleFortressSpriteSheet.getSpriteSheet(),
                redBossDoor.getX(), redBossDoor.getY(),
                redBossDoor.getWidth() + redBossDoor.getX(), redBossDoor.getHeight() + redBossDoor.getY(),
                redBossDoor.getSX1(), redBossDoor.getSY1(),
                redBossDoor.getSX2(), redBossDoor.getSY2(),
                null);

        int s[] = greenTurtle.getSLocations();
        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                greenTurtle.getX(),greenTurtle.getY(),
                greenTurtle.getWidth() + greenTurtle.getX(),
                greenTurtle.getHeight() +  greenTurtle.getY(),
                s[0], s[1],
                s[2], s[3],
                null);

        int[] red_turtle_s_locations = redTurtle.getSLocations();

        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                redTurtle.getX(), redTurtle.getY(),
                redTurtle.getWidth() + redTurtle.getX(),
                redTurtle.getHeight() +  redTurtle.getY(),
                red_turtle_s_locations[0], red_turtle_s_locations[1],
                red_turtle_s_locations[2], red_turtle_s_locations[3],
                null);

        int[] red_turtle2_s_locations = redTurtle2.getSLocations();

        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                redTurtle2.getX(), redTurtle2.getY(),
                redTurtle2.getWidth() + redTurtle2.getX(),
                redTurtle2.getHeight() +  redTurtle2.getY(),
                red_turtle2_s_locations[0], red_turtle2_s_locations[1],
                red_turtle2_s_locations[2], red_turtle2_s_locations[3],
                null);

        int[] green_turtle_top_side_s_locations = greenTurtleTopSide.getSLocations();

        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                greenTurtleTopSide.getX(), greenTurtleTopSide.getY(),
                greenTurtleTopSide.getWidth() + greenTurtleTopSide.getX(),
                greenTurtleTopSide.getHeight() +  greenTurtleTopSide.getY(),
                green_turtle_top_side_s_locations[0], green_turtle_top_side_s_locations[1],
                green_turtle_top_side_s_locations[2], green_turtle_top_side_s_locations[3],
                null);

        int [] green_turtle_lower_side_s_locations = greenTurtleLowerSide.getSLocations();

        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                greenTurtleLowerSide.getX(), greenTurtleLowerSide.getY(),
                greenTurtleLowerSide.getWidth() + greenTurtleLowerSide.getX(),
                greenTurtleLowerSide.getHeight() +  greenTurtleLowerSide.getY(),
                green_turtle_lower_side_s_locations[0], green_turtle_lower_side_s_locations[1],
                green_turtle_lower_side_s_locations[2], green_turtle_lower_side_s_locations[3],
                null);

        int s_mario[] = GameControl.getMario().getSLocations();

        g.drawImage(MarioSpriteSheet.getSpriteSheet(),
                GameControl.getMario().getX(), GameControl.getMario().getY(),
                GameControl.getMario().getWidth() + GameControl.getMario().getX(),
                GameControl.getMario().getHeight() + GameControl.getMario().getY(),
                s_mario[0], s_mario[1],
                s_mario[2], s_mario[3],
                null);

    }

}
