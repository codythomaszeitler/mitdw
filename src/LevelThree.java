import sprite.GrayRock;
import sprite.RedLava;
import sprite.RedLavaFlipped;
import sprite.doubleeyeballfireball.DoubleEyeFireball;
import sprite.tile.DarkGrayCastleMiddleTile;
import spritesheet.CastleFortressSpriteSheet;
import spritesheet.MarioSpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Cody Thomas Zeitler on 12/17/2015.
 */
public class LevelThree extends JPanel implements ActionListener, Runnable{

    private int origin_x;
    private int origin_y;

    private Timer gameLoop;

    private GreenTurtle greenTurtle;

    private int frameCounter; //how many frames have passed since mario spawned.

    private LinkedList<DarkGrayCastleMiddleTile> backgroundList; //collection of background tiles to be painted.
    private DarkGrayCastleMiddleTile background; //pointer (somewhat) that is used to create all the background tiles that will be stored in the linked list to keep track of it.

    private LinkedList<RedLava> bottomRedLavaLinkedList; //collection of lava painted that is on the bootom part of the screen.
    private LinkedList<RedLavaFlipped> topRedLavaLinkedList; //collection of lava painted that is on the top part of the screen.
    private RedLava bottomRedLava; //pointer (somewhat) that is used to create all the lava that will be stored in the linked list to keep track of it.
    private RedLavaFlipped topRedLava; //pointer (somewhat) that is used to create all the lava that will be stored in the linked list to keep track of it.

    private GrayRock grayRock;  //pointer (somewhat) that is used to create all the gray rocks that will be stored in the linked list to keep track of it.
    private LinkedList<GrayRock> grayRockLinkedList; //collection of gray rocks that will be painted around the level three area.

    private int screenScrollSpeed; //how fast the screen will scroll left.
    //how much the screen has moved in total, this value will be used to reset every element that is present
    //in this level if mario is to die.
    private int total_origin_x_moved;

    //All fireballs within the level.
    private DoubleEyeFireball doubleEyeFireball; //very most left fireball.
    private DoubleEyeFireball secondDoubleEyeFireball; //second to most left fireball.
    private DoubleEyeFireball thirdDoubleEyeFireball; //third to most left fireball.

    //All thwomps in level three.
    private LinkedList<Thwomp> thwompLinkedList;
    private Thwomp firstThwomp; //leftmost thwomp
    private Thwomp secondThwomp; //second to left most thwmop.
    private Thwomp thirdThwomp; //right most thwomp.

    private SwordFightSong swordFightSong;
    private EvilJestSound evilJestSound;
    public SwordFightSong getSwordFightSong(){
        return swordFightSong;
    }
    private Level.Levels current_level;
    private Life lives;
    private LevelTitle levelTitle;

    private RedBossDoor redBossDoor;
    private boolean isLevelComplete;
    public boolean getIsLevelComplete(){
        return isLevelComplete;
    }


    public LevelThree(){


        //Creating double eye fireball on the furthest left side.
        doubleEyeFireball = new DoubleEyeFireball(1175,0, 90, 90, true);
        doubleEyeFireball.setBottomY(630); //shows the lowest part of the y axis the fire ball will travel.
        doubleEyeFireball.setTopY(-100); //shows the highest part of the y axis the fire ball will travel.
        doubleEyeFireball.setSpeedOfFireball(13); //sets how fast the fireball moves.

        secondDoubleEyeFireball = new DoubleEyeFireball(1600, 500, 90, 90, false);
        secondDoubleEyeFireball.setBottomY(1100); //shows the lowest part of the y axis the fire ball will travel.
        secondDoubleEyeFireball.setTopY(400);//shows the highest part of the y axis the fire ball will travel.
        secondDoubleEyeFireball.setSpeedOfFireball(13);//sets how fast the fireball moves.

        thirdDoubleEyeFireball = new DoubleEyeFireball(2280,0,90,90, false);
        thirdDoubleEyeFireball.setBottomY(1100); //shows the lowest part of the y axis the fire ball will travel.
        thirdDoubleEyeFireball.setTopY(-100);//shows the highest part of the y axis the fire ball will travel.
        thirdDoubleEyeFireball.setSpeedOfFireball(13);//sets how fast the fireball moves.

        firstThwomp = new Thwomp(3000, 0, 250, 250);
        secondThwomp = new Thwomp(3450, 0, 250, 250);
        thirdThwomp = new Thwomp(3900, 0, 250, 250);

        greenTurtle = new GreenTurtle(500,500,50,50); //Instantiating green turtle into memory.

        backgroundList = new LinkedList<>(); //Linked list that will paint all the background tiles.

        redBossDoor = new RedBossDoor(4275, 100, 125, 200);

        isLevelComplete = false;
        //Initializing sound object.
        swordFightSong = new SwordFightSong();
        evilJestSound = new EvilJestSound();

        EventQueue.invokeLater(new Runnable(){

            public void run(){
                swordFightSong.playSound();
            }

        });
        levelTitle = new LevelTitle(500, 38, 50, "Insidious Inferno");

        current_level = Level.Levels.THREE;
        lives = new Life();
        lives.setX(2);
        lives.setY(38);
        /*
        Creating all background tile objects. This will be painted in
        the paintComponent function.
        */
        for(int z = 0; z < GameControl.getMainGameFrame().getHeight() ; z = z +  50) {
            for (int i = 0; i < GameControl.getMainGameFrame().getWidth() + 2550; i = i +  50 ) {

                background = new DarkGrayCastleMiddleTile(i, z, 50, 50);
                backgroundList.add(background);
            }
        }

        bottomRedLavaLinkedList = new LinkedList<>(); //collection of bottom red lava.
        topRedLavaLinkedList = new LinkedList<>(); //collection of top red lava.

        //Adding all of the bottom red lava that will be painted onto the screen.
        for(int i = 0; i < GameControl.getMainGameFrame().getWidth() * 3; i = i + 250){

            bottomRedLava = new RedLava(i,GameControl.getMainGameFrame().getHeight() - 125,250,200);
            bottomRedLavaLinkedList.add(bottomRedLava);

        }
        //Adding all of the top border of the red lava that will be painted onto the screen.
        for(int i = 0; i < GameControl.getMainGameFrame().getWidth() * 3; i = i + 250){

            topRedLava = new RedLavaFlipped(i, - 110,250,200);
            topRedLavaLinkedList.add(topRedLava);

        }

        //Adding all gray rocks that will be painted onto the screen.
        /* For GrayRock object.
        1st parameter: x coordinate of object.
        2nd parameter: y coordinate of object.
        3rd parameter: width of object.
        4th parameter: height of object.
         */
        grayRockLinkedList = new LinkedList<>();
        grayRock = new GrayRock(0,0,300,300);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(0,GameControl.getMainGameFrame().getHeight() - 350,300,300);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(600,375, 250, 250);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(600, 0, 250, 250);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(600, 750, 250 ,250);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(-200,300,300,190);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(-200, 490, 300, 190);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(1300, 0, 425, 425);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(1150, 700, 425, 425);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2000,800, 150,150);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2000,650,150,150);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2000,500,150,150);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2000,350,150,150);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2000,300,50,50);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2050, 300, 50 ,50);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2100,300,50,50);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2500,0,250,250);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2500,250,250,250);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2500,500,250,250);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2150,300,75,75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2225,300,75,75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2300,300,75,75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2425,500,75,75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2350, 500, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2275, 500,75,75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2150,800,250,250);
        grayRockLinkedList.add(grayRock);

        //Rocks on the stopped screen.
        grayRock = new GrayRock(2750, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2825, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2900, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(2975, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3050, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3125, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3200, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3275, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3350, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3425, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3500, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3575, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3650, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3725, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3800, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3875, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3950, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4025, 675, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4100, 675, 75, 75);
        grayRockLinkedList.add(grayRock);



        grayRock = new GrayRock(3050, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3125, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3200, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3275, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3350, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3425, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3500, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3575, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3650, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3725, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3800, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3875, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(3950, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4025, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4100, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4175, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4250, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4325, 325, 75, 75);
        grayRockLinkedList.add(grayRock);
        grayRock = new GrayRock(4400, 325, 75, 75);
        grayRockLinkedList.add(grayRock);


        screenScrollSpeed = -2; //how fast the screen will scroll left.
        //how much the screen has moved in total, this value will be used to reset every element that is present
        //in this level if mario is to die.
        total_origin_x_moved = 0;

        origin_x = 0;
        origin_y = 0;

        frameCounter = 0;

        initializeGameLoop();
    }
    /*
    Instantiating the timer and putting all moving enemies into a timer to make them move.
     */
    private void initializeGameLoop(){
        //sixty frames per second run all actionPerformeds associated with it.
        gameLoop = new Timer(1000/60,this);
        gameLoop.addActionListener(doubleEyeFireball);
        gameLoop.addActionListener(secondDoubleEyeFireball);
        gameLoop.addActionListener(thirdDoubleEyeFireball);
        gameLoop.addActionListener(firstThwomp);
        gameLoop.addActionListener(thirdThwomp);
        gameLoop.addActionListener(secondThwomp);
    }
    /*
    return gameLoop(timer) associated with the LevelThree object.
     */
    public Timer getGameLoop(){
        return gameLoop;
    }

    /*
    Checks to see if mario has intersected with any of the collision rectangles associated with level three.
     */
    public boolean checkCollision(){

        //Checks to see if mario has touched any of the gray rocks.
        Iterator<GrayRock> grayRockIterator = grayRockLinkedList.iterator();
        while(grayRockIterator.hasNext()){

            if(grayRockIterator.next().getCollisionBox().intersects(GameControl.getMario().getCollisionRectangle())){
                return true;
            }
        }

        //Checks to see if mario has intersected any of the bottom lavas.
        Iterator<RedLava> redLavaIterator = bottomRedLavaLinkedList.iterator();
        while(redLavaIterator.hasNext()){

            if(redLavaIterator.next().getCollisionBox().intersects(GameControl.getMario().getCollisionRectangle())){
                return true;
            }
        }

        //Checks to see if mario has intersected any of the top lava.
        Iterator<RedLavaFlipped> topRedLavaIterator = topRedLavaLinkedList.iterator();
        while(topRedLavaIterator.hasNext()){

            if(topRedLavaIterator.next().getCollisionBox().intersects(GameControl.getMario().getCollisionRectangle())){
                return true;
            }
        }

        //Checks to see if mario intersects the left wall.
        if(GameControl.getMario().getCollisionRectangle().intersects(
                new Rectangle(-50,0,50,GameControl.getMainGameFrame().getHeight()))){
            return true;
        }
        //Checks to see if mario intersects the right wall.
        if(GameControl.getMario().getCollisionRectangle().intersects(
                new Rectangle(GameControl.getMainGameFrame().getWidth(), 0, 50, GameControl.getMainGameFrame().getHeight())
        )){
            return true;
        }

        //Checks  to see if mario intersects the left most fireball.
        if(GameControl.getMario().getCollisionRectangle().intersects(doubleEyeFireball.getCollisionBox())){
            return true;
        }
        //Checks to see if mario intersects the second to most left fireball.
        if(GameControl.getMario().getCollisionRectangle().intersects(secondDoubleEyeFireball.getCollisionBox())){
            return true;
        }
        //Checks to see if mario intersects the third to most left fireball.
        if(GameControl.getMario().getCollisionRectangle().intersects(thirdDoubleEyeFireball.getCollisionBox())){
            return true;
        }
        //Checks to see if mario intersects the very left most firstThwomp.
        if(GameControl.getMario().getCollisionRectangle().intersects(firstThwomp.getBounds())){
            return true;
        }
        //Checks to see if mario intersects the second to left thwomp.
        if(GameControl.getMario().getCollisionRectangle().intersects(secondThwomp.getBounds())){
            return true;
        }
        //Checks to see if mario intersects the very right most thwomp
        if(GameControl.getMario().getCollisionRectangle().intersects(thirdThwomp.getBounds())){
            return true;
        }
        if(GameControl.getMario().getCollisionRectangle().intersects(redBossDoor.getVictoryBox())){
            isLevelComplete = true;
        }
        return false; //Mario didn't touch anything he wasn't suppposed to.

    }

    /*
    Moves every single element back to the original starting point of the level.
    Moves every element total_x_origin_moved backwards.
     */
    public void resetLevel(){

        ListIterator<RedLava> iterator = (ListIterator<RedLava>) bottomRedLavaLinkedList.iterator();
        while (iterator.hasNext()) {

            RedLava temp = iterator.next();
            temp.setX(temp.getX()  + total_origin_x_moved);
        }

        Iterator<GrayRock> grayRockIterator = grayRockLinkedList.iterator();
        while(grayRockIterator.hasNext()){

            GrayRock temp = grayRockIterator.next();
            temp.setX(temp.getX() + total_origin_x_moved);
        }

        Iterator<RedLavaFlipped> topRedLavaFlippedListIterator = topRedLavaLinkedList.iterator();
        while(topRedLavaFlippedListIterator.hasNext()){

            RedLavaFlipped temp = topRedLavaFlippedListIterator.next();
            temp.setX(temp.getX() + total_origin_x_moved);
        }

        Iterator<DarkGrayCastleMiddleTile> backgroundIterator = backgroundList.iterator();
        while(backgroundIterator.hasNext()){

            DarkGrayCastleMiddleTile temp = backgroundIterator.next();
            temp.setX(temp.getX() + total_origin_x_moved);
        }
        doubleEyeFireball.setX(doubleEyeFireball.getX() + total_origin_x_moved);
        secondDoubleEyeFireball.setX(secondDoubleEyeFireball.getX() + total_origin_x_moved);
        thirdDoubleEyeFireball.setX(thirdDoubleEyeFireball.getX() + total_origin_x_moved);
        firstThwomp.setDx1(firstThwomp.getDx1() + total_origin_x_moved);
        secondThwomp.setDx1(secondThwomp.getDx1() + total_origin_x_moved);
        thirdThwomp.setDx1(thirdThwomp.getDx1() + total_origin_x_moved);
        redBossDoor.setX(redBossDoor.getX() + total_origin_x_moved);
        total_origin_x_moved = 0;
        frameCounter = 0;
    }

    public void actionPerformed(ActionEvent e){
        if(frameCounter >= 180 && frameCounter < 1450) {
            GameControl.getMario().setX(GameControl.getMario().getX() + screenScrollSpeed);
        }
        EventQueue.invokeLater(this); //creates a thread to update all elements within the level. (swing is not thread safe, therefore this EventQueue.invokeLater(this) is needed.
    }
    public void run(){

        frameCounter++; //every time the frame increases

        if(!isLevelComplete) {
            if (checkCollision()) {

                lives.setNumberOfLives(lives.getNumberOfLives() - 1);
                EventQueue.invokeLater(new Runnable() {

                    public void run() {
                        evilJestSound.playSound();
                    }

                });
                //Gets the static mario from the main game frame and resets him to level three coordinates.
                GameControl.getMario().resetMarioPosition(Level.Levels.THREE);
                resetLevel(); //changes the level back to it's initial state.
            }
        }

        //Once it has been 3 seconds (60 frames in 1 seconds 180 frames is therefore 3 seconds)
        //start moving every element in level three.


        if(frameCounter >= 180 && frameCounter < 1450) {

            //Start moving bottom red lava.
            ListIterator<RedLava> iterator = (ListIterator<RedLava>) bottomRedLavaLinkedList.iterator();
            while (iterator.hasNext()) {

                RedLava temp = iterator.next();
                temp.setX(temp.getX() + screenScrollSpeed);
            }
            //Start moving top red lava.
            Iterator<RedLavaFlipped> topRedLavaFlippedListIterator = topRedLavaLinkedList.iterator();
            while (topRedLavaFlippedListIterator.hasNext()) {

                RedLavaFlipped temp = topRedLavaFlippedListIterator.next();
                temp.setX(temp.getX() + screenScrollSpeed);
            }
            //Start moving the background tiles.
            Iterator<DarkGrayCastleMiddleTile> backgroundIterator = backgroundList.iterator();
            while (backgroundIterator.hasNext()) {

                DarkGrayCastleMiddleTile temp = backgroundIterator.next();
                temp.setX(temp.getX() + screenScrollSpeed);
            }
            //Starts moving the gray rocks.
            Iterator<GrayRock> grayRockIterator = grayRockLinkedList.iterator();
            while (grayRockIterator.hasNext()) {

                GrayRock temp = grayRockIterator.next();
                temp.setX(temp.getX() + screenScrollSpeed);
            }
            //Start moving all the red fire balls.
            doubleEyeFireball.setX(doubleEyeFireball.getX() + screenScrollSpeed);
            secondDoubleEyeFireball.setX(secondDoubleEyeFireball.getX() + screenScrollSpeed);
            thirdDoubleEyeFireball.setX(thirdDoubleEyeFireball.getX() + screenScrollSpeed);

            firstThwomp.setDx1(firstThwomp.getDx1() + screenScrollSpeed);
            secondThwomp.setDx1(secondThwomp.getDx1() + screenScrollSpeed);
            thirdThwomp.setDx1(thirdThwomp.getDx1() + screenScrollSpeed);

            redBossDoor.setX(redBossDoor.getX() + screenScrollSpeed);

            total_origin_x_moved -= screenScrollSpeed;
        }


    }

    public void paintComponent(Graphics g){

        Iterator<DarkGrayCastleMiddleTile> backgroundIterator = backgroundList.iterator();

        while(backgroundIterator.hasNext()){

            DarkGrayCastleMiddleTile temp = backgroundIterator.next();
            g.drawImage(temp.getAssociatedSpriteSheet(),
                    temp.getDx1(), temp.getDy1(),
                    temp.getDx2(), temp.getDy2(),
                    temp.getSX1(), temp.getSY1(),
                    temp.getSX2(), temp.getSY2(),
                    null);

        }

        Iterator<GrayRock> grayRockIterator = grayRockLinkedList.iterator();

        while(grayRockIterator.hasNext()){

            GrayRock temp = grayRockIterator.next();
            g.drawImage(temp.getAssociatedSpriteSheet(),
                    temp.getDx1(), temp.getDy1(),
                    temp.getDx2(), temp.getDy2(),
                    temp.getSX1(), temp.getSY1(),
                    temp.getSX2(), temp.getSY2(),
                    null);


        }

        int s_double_eye_fireball[] = doubleEyeFireball.getSLocations();

        g.drawImage(doubleEyeFireball.getAssociatedSpriteSheet(),
                doubleEyeFireball.getDx1(), doubleEyeFireball.getDy1(),
                doubleEyeFireball.getDx2(), doubleEyeFireball.getDy2(),
                s_double_eye_fireball[0], s_double_eye_fireball[1],
                s_double_eye_fireball[2], s_double_eye_fireball[3],
                null);
        int s_second_double_eye_fireball[] = secondDoubleEyeFireball.getSLocations();

        g.drawImage(secondDoubleEyeFireball.getAssociatedSpriteSheet(),
                secondDoubleEyeFireball.getDx1(), secondDoubleEyeFireball.getDy1(),
                secondDoubleEyeFireball.getDx2(), secondDoubleEyeFireball.getDy2(),
                s_second_double_eye_fireball[0], s_second_double_eye_fireball[1],
                s_second_double_eye_fireball[2], s_second_double_eye_fireball[3],
                null);


        int s_third_double_eye_fireball[] = thirdDoubleEyeFireball.getSLocations();

        g.drawImage(thirdDoubleEyeFireball.getAssociatedSpriteSheet(),
                thirdDoubleEyeFireball.getDx1(), thirdDoubleEyeFireball.getDy1(),
                thirdDoubleEyeFireball.getDx2(), thirdDoubleEyeFireball.getDy2(),
                s_third_double_eye_fireball[0], s_third_double_eye_fireball[1],
                s_third_double_eye_fireball[2], s_third_double_eye_fireball[3],
                null);

        //Drawing very most left thwomp.
        int[] s_thwomp_s_locations = firstThwomp.getSLocations();

        g.drawImage(firstThwomp.getAssociatedSpriteSheet(),
                firstThwomp.getDx1(), firstThwomp.getDy1(),
                firstThwomp.getDx2(), firstThwomp.getDy2(),
                s_thwomp_s_locations[0], s_thwomp_s_locations[1],
                s_thwomp_s_locations[2], s_thwomp_s_locations[3],
                null);

        //Drawing second to most left thwomp.
        int[] s_second_thwomp_locations = secondThwomp.getSLocations();
        g.drawImage(secondThwomp.getAssociatedSpriteSheet(),
                secondThwomp.getDx1(), secondThwomp.getDy1(),
                secondThwomp.getDx2(), secondThwomp.getDy2(),
                s_second_thwomp_locations[0], s_second_thwomp_locations[1],
                s_second_thwomp_locations[2], s_second_thwomp_locations[3],
                null);

        int[] s_third_thwomp_locations = thirdThwomp.getSLocations();
        g.drawImage(thirdThwomp.getAssociatedSpriteSheet(),
                thirdThwomp.getDx1(), thirdThwomp.getDy1(),
                thirdThwomp.getDx2(), thirdThwomp.getDy2(),
                s_third_thwomp_locations[0], s_third_thwomp_locations[1],
                s_third_thwomp_locations[2], s_third_thwomp_locations[3],
                null);


        //Drawing all red lava.

        ListIterator<RedLava> redLavaListIterator = (ListIterator<RedLava>) bottomRedLavaLinkedList.iterator();

        while(redLavaListIterator.hasNext()){

            RedLava temp = redLavaListIterator.next();


            g.drawImage(temp.getAssociatedSpriteSheet(),
                    temp.getDx1(), temp.getDy1(),
                    temp.getDx2(), temp.getDy2(),
                    temp.getSX1(), temp.getSY1(),
                    temp.getSX2(), temp.getSY2(),
                    null);
        }

        Iterator<RedLavaFlipped> topRedLavaListIterator =  topRedLavaLinkedList.iterator();

        while(topRedLavaListIterator.hasNext()){

            RedLavaFlipped temp = topRedLavaListIterator.next();


            g.drawImage(temp.getAssociatedSpriteSheet(),
                    temp.getDx1(), temp.getDy1(),
                    temp.getDx2(), temp.getDy2(),
                    temp.getSX1(), temp.getSY1(),
                    temp.getSX2(), temp.getSY2(),
                    null);
        }
        g.drawImage(CastleFortressSpriteSheet.getSpriteSheet(),
                redBossDoor.getX(), redBossDoor.getY(),
                redBossDoor.getWidth() + redBossDoor.getX(), redBossDoor.getHeight() + redBossDoor.getY(),
                redBossDoor.getSX1(), redBossDoor.getSY1(),
                redBossDoor.getSX2(), redBossDoor.getSY2(),
                null);
        g.setFont(lives.getFont());
        g.setColor(Color.WHITE);
        g.drawString(lives.getString(), lives.getX(), lives.getY());
        g.setFont(levelTitle.getFont());
        g.setColor(Color.WHITE);
        g.drawString(levelTitle.getNameOfLevel(), levelTitle.getX(), levelTitle.getY());

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
