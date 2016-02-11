import sprite.Grass;
import sprite.Tree;
import sprite.box.OnOffBox;
import sprite.box.RedExclamationBox;
import sprite.box.WoodCircleBox;
import spritesheet.CastleFortressSpriteSheet;
import spritesheet.EnemySpriteSheet;
import spritesheet.GroundTilesSpriteSheet;
import spritesheet.MarioSpriteSheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by Cody Thomas Zeitler on 12/14/2015.
 */
public class LevelTwo extends JPanel implements ActionListener{

    public boolean isLevelComplete;
    public boolean getIsLevelComplete(){return isLevelComplete;}

    //All boxes that need to be checked in gamecontrol

    private DoorList doorList; //All of the doors associated with level two.
    private CollisionBoxList collisionBoxList;
    private OnOffBoxList onOffBoxList;
    private RedExclamationBoxList redExclamationBoxList;

    //All of the red exclamation boxes on the screen.
    private RedExclamationBox redExclamationBox;

    //All of the borders of the screen.
    private WoodCircleBox topBorder;
    private WoodCircleBox leftBorder;
    private WoodCircleBox bottomBorder;
    private WoodCircleBox rightBorder;
    private WoodCircleBox offsetBottom;

    //Random unpositioned trees.
    private Tree tree1;
    private Tree tree2;
    private Tree tree3;
    private Tree tree4;
    private Tree topRigthTreeLeftOfOnOffBox;
    private Tree topRightTreeSouthOfOnOffBox;

    //All green koopas present in level two.
    private GreenTurtle bottomPatrollingKoopa;
    private GreenTurtle middlePatrollingKoopa;
    private GreenTurtle topPatrollingKoopa;

    //All of the OnOffBoxes located in the level two.
    private OnOffBox topleftOnOffBox;
    private OnOffBox topRightOnOffBox;
    private OnOffBox bottomRightOnOffBox;

    //Background of the screen
    private Grass grassBackground;

    //Rectangle if mario intersects he wins the level.
    private RedBossDoor redBossDoor;
    private Rectangle victoryRectangle;
    public Rectangle getVictoryBox(){return victoryRectangle;}

    private LevelTitle levelTitle;
    private Life lives;

    //sound object that is playing during the level.
    private MushroomDanceSong mushroomDanceSong;


    private Timer levelTwoGameLoop;
    private void initalizeGameLoop (){

        levelTwoGameLoop = new Timer(1000/60, this);
        levelTwoGameLoop.addActionListener(bottomPatrollingKoopa);
        levelTwoGameLoop.addActionListener(middlePatrollingKoopa);
        levelTwoGameLoop.addActionListener(topPatrollingKoopa);



    }
    public Timer getGameLoop(){
        return levelTwoGameLoop;
    }

    public LevelTwo(){

        //Creaing all of the list that will be available during execution.
        doorList = new DoorList();
        collisionBoxList = new CollisionBoxList();
        onOffBoxList = new OnOffBoxList();
        redExclamationBoxList = new RedExclamationBoxList();

        //Initializing all OnOffBoxes;
        topleftOnOffBox = new OnOffBox(65, 65, 50, 50);
        onOffBoxList.add(topleftOnOffBox);
        topRightOnOffBox = new OnOffBox(1725,100, 50, 50);
        onOffBoxList.add(topRightOnOffBox);
        bottomRightOnOffBox = new OnOffBox(1650, 600, 50, 50);
        onOffBoxList.add(bottomRightOnOffBox);

        //Initializing RedExclamationBoxes;
        redExclamationBox = new RedExclamationBox(700,525,50,50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(750,525,50,50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(800, 525, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(850, 525, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(850, 575, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(850, 625, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(850, 675, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(850, 725, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(800, 725, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(750, 725, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(700, 725, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(700, 675, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(700, 625, 50, 50);
        redExclamationBoxList.add(redExclamationBox);
        redExclamationBox = new RedExclamationBox(700, 575, 50, 50);
        redExclamationBoxList.add(redExclamationBox);

        //Instantiating RedBossDoor
        redBossDoor = new RedBossDoor(750,575,100,150);


        //Initializing all tree objects.
        tree1 = new Tree(225,525, 250, 250);
        tree2 = new Tree(700, 200, 150 ,150);
        tree3 = new Tree(1200,500, 300, 300);
        tree4 = new Tree(150,150, 100, 100);
        topRigthTreeLeftOfOnOffBox = new Tree(1500, 50, 150, 150);
        topRightTreeSouthOfOnOffBox = new Tree(1700, 300, 150, 150);

        //Setting level and lives left.
        levelTitle = new LevelTitle(500, 38, 50, "Frantic Forest");
        lives = new Life();
        lives.setX(2);
        lives.setY(38);

        grassBackground = new Grass(0,0,100,100);

        //Initializing all border objects.
        topBorder = new WoodCircleBox(0,0,50,50);
        leftBorder = new WoodCircleBox(0,0,50,50);
        bottomBorder = new WoodCircleBox(
                0, GameControl.getMainGameFrame().getHeight() - 75, 50, 50);
        rightBorder = new WoodCircleBox(
                 GameControl.getMainGameFrame().getWidth() - 50, 0, 50, 50);
        offsetBottom = new WoodCircleBox(250,900, 50, 50);

        initializeCollisionBoxes();
        //Initializng and setting all values for green turtles.
        bottomPatrollingKoopa = new GreenTurtle(800,825,65,125);
        collisionBoxList.add(new CollisionBox(bottomPatrollingKoopa.getCollisionRectangle()));
        bottomPatrollingKoopa.setIndexInLinkedList(collisionBoxList.size() - 1);
        bottomPatrollingKoopa.setEnvironment(collisionBoxList, collisionBoxList.size() - 1);

        middlePatrollingKoopa = new GreenTurtle(1600,350, 65, 125);
        collisionBoxList.add(new CollisionBox(middlePatrollingKoopa.getCollisionRectangle()));
        middlePatrollingKoopa.setIndexInLinkedList(collisionBoxList.size() - 1);
        middlePatrollingKoopa.setEnvironment(collisionBoxList, collisionBoxList.size() - 1);

        topPatrollingKoopa = new GreenTurtle(300, 75, 65, 125);
        collisionBoxList.add(new CollisionBox(topPatrollingKoopa.getCollisionRectangle()));
        topPatrollingKoopa.setIndexInLinkedList(collisionBoxList.size() - 1);
        topPatrollingKoopa.setEnvironment(collisionBoxList, collisionBoxList.size() - 1);


        //Setting the "win" sqaure. This will be refactored later.
        victoryRectangle = new Rectangle(100,100, 100, 100);

        //Lets the main game know if the level has been completed yet.
        //e.g: Has mario come into contact with the "victoryRectangle" yet.
        isLevelComplete = false;

        //Initializing sound object.
        mushroomDanceSong = new MushroomDanceSong();
        EventQueue.invokeLater(new Runnable(){

            public void run(){
                mushroomDanceSong.playSound();
            }

        });
        //Creating the timer that will allow the game to run. Will be passed to the GameControl object at run time.
        initalizeGameLoop();


    }
    private void initializeCollisionBoxes(){

        //Adding the collision boxes for the top border.
        for (int i = 0; i < GameControl.getMainGameFrame().getWidth(); i = i + topBorder.getWidth()){

            CollisionBox boxToAdd = new CollisionBox(
                    topBorder.getDx1() + i, topBorder.getDy1(),
                    topBorder.getDx2() + i, topBorder.getDy2());

            collisionBoxList.add(boxToAdd);
        }
        //Adding collision boxes for the left border.
        for (int i = 0; i < GameControl.getMainGameFrame().getHeight(); i = i + leftBorder.getHeight()){

            CollisionBox boxToAdd = new CollisionBox(
                    leftBorder.getDx1(), leftBorder.getDy1() + i,
                    leftBorder.getDx2(), leftBorder.getDy2() + i);

            collisionBoxList.add(boxToAdd);
        }
        //Adding collision boxes for bottom border.
        for (int i = 0; i < GameControl.getMainGameFrame().getWidth(); i = i + bottomBorder.getWidth()){

            CollisionBox boxToAdd = new CollisionBox(
                    bottomBorder.getDx1() + i, bottomBorder.getDy1(),
                    bottomBorder.getDx2() + i, bottomBorder.getDy2());

            collisionBoxList.add(boxToAdd);
        }
        //Adding collision boxes for right border.
        for (int i = 0; i < GameControl.getMainGameFrame().getHeight(); i = i + rightBorder.getHeight()){

            CollisionBox boxToAdd = new CollisionBox(
                    rightBorder.getDx1(), rightBorder.getDy1() + i,
                    rightBorder.getDx2(), rightBorder.getDy2() + i);

            collisionBoxList.add(boxToAdd);
        }
        //Initializing  tree collision rectangles

        collisionBoxList.add(new CollisionBox(tree1.getCollisionBox()));
        collisionBoxList.add(new CollisionBox(tree2.getCollisionBox()));
        collisionBoxList.add(new CollisionBox(tree3.getCollisionBox()));
        collisionBoxList.add(new CollisionBox(tree4.getCollisionBox()));
        collisionBoxList.add(new CollisionBox(topRigthTreeLeftOfOnOffBox.getCollisionBox()));
        collisionBoxList.add(new CollisionBox(topRightTreeSouthOfOnOffBox.getCollisionBox()));

        {
            CollisionBox boxToAdd = new CollisionBox(
                    offsetBottom.getX(), offsetBottom.getY(),
                    offsetBottom.getWidth(), offsetBottom.getHeight());
            collisionBoxList.add(boxToAdd);
        }




    }

    public boolean checkCollision(){

        ListIterator<CollisionBox> iterator = (ListIterator<CollisionBox>) collisionBoxList.iterator();

        while(iterator.hasNext()){

            CollisionBox temp = iterator.next();

            if(temp.intersects(GameControl.getMario().getCollisionRectangle())){
                return true;
            }
        }
        return false;
    }



    public void paintComponent(Graphics g){

        //Painting the grass background
        for(int z = 0; z < GameControl.getMainGameFrame().getHeight() ; z = z + grassBackground.getHeight()) {
            for (int i = 0; i < GameControl.getMainGameFrame().getWidth(); i = i + grassBackground.getWidth()) {

                g.drawImage(grassBackground.getAssociatedSpriteSheet(),
                        grassBackground.getX() + i, grassBackground.getY() + z,
                        grassBackground.getWidth() + i, grassBackground.getHeight() + z,
                        grassBackground.getSX1(), grassBackground.getSY1(),
                        grassBackground.getSX2(),grassBackground.getSY2(),
                        null);

            }
        }


        //Painting the top border.
        for (int i = 0; i < GameControl.getMainGameFrame().getWidth(); i = i + topBorder.getWidth()) {
            g.drawImage(topBorder.getAssociatedSpriteSheet(),
                    topBorder.getDx1() + i, topBorder.getDy1(),
                    topBorder.getDx2() + i, topBorder.getDy2(),
                    topBorder.getSX1(), topBorder.getSY1(),
                    topBorder.getSX2(), topBorder.getSY2(),
                    null);
        }
        //Painting the left  border.
        for (int i = 0; i < GameControl.getMainGameFrame().getHeight(); i = i + leftBorder.getHeight()) {
            g.drawImage(leftBorder.getAssociatedSpriteSheet(),
                    leftBorder.getDx1(), leftBorder.getDy1() + i,
                    leftBorder.getDx2(), leftBorder.getDy2() + i,
                    leftBorder.getSX1(), leftBorder.getSY1(),
                    leftBorder.getSX2(), leftBorder.getSY2(),
                    null);
        }
        //Painting the bottom border.
        for (int i = 0; i < GameControl.getMainGameFrame().getWidth(); i = i + bottomBorder.getWidth()) {
            g.drawImage(bottomBorder.getAssociatedSpriteSheet(),
                    bottomBorder.getDx1() + i, bottomBorder.getDy1(),
                    bottomBorder.getDx2() + i, bottomBorder.getDy2(),
                    bottomBorder.getSX1(), bottomBorder.getSY1(),
                    bottomBorder.getSX2(), bottomBorder.getSY2(),
                    null);
        }
        for (int i = 0; i < GameControl.getMainGameFrame().getHeight(); i = i + rightBorder.getHeight()) {
            g.drawImage(rightBorder.getAssociatedSpriteSheet(),
                    rightBorder.getDx1(), rightBorder.getDy1() + i,
                    rightBorder.getDx2(), rightBorder.getDy2() + i,
                    rightBorder.getSX1(),rightBorder.getSY1(),
                    rightBorder.getSX2(), rightBorder.getSY2(),
                    null);
        }
        g.drawImage(offsetBottom.getAssociatedSpriteSheet(),
                offsetBottom.getDx1(), offsetBottom.getDy1(),
                offsetBottom.getDx2(), offsetBottom.getDy2(),
                offsetBottom.getSX1(),offsetBottom.getSY1(),
                offsetBottom.getSX2(), offsetBottom.getSY2(),
                null);

        g.drawImage(tree1.getAssociatedSpriteSheet(),
                tree1.getDx1(), tree1.getDy1(),
                tree1.getDx2(), tree1.getDy2(),
                tree1.getSX1(), tree1.getSY1(),
                tree1.getSX2(), tree1.getSY2(),
                null);
        g.drawImage(tree1.getAssociatedSpriteSheet(),
                tree2.getDx1(), tree2.getDy1(),
                tree2.getDx2(), tree2.getDy2(),
                tree2.getSX1(), tree2.getSY1(),
                tree2.getSX2(), tree2.getSY2(),
                null);
        g.drawImage(tree1.getAssociatedSpriteSheet(),
                tree3.getDx1(), tree3.getDy1(),
                tree3.getDx2(), tree3.getDy2(),
                tree3.getSX1(), tree3.getSY1(),
                tree3.getSX2(), tree3.getSY2(),
                null);
        g.drawImage(tree1.getAssociatedSpriteSheet(),
                tree4.getDx1(), tree4.getDy1(),
                tree4.getDx2(), tree4.getDy2(),
                tree4.getSX1(), tree4.getSY1(),
                tree4.getSX2(), tree4.getSY2(),
                null);
        g.drawImage(topRigthTreeLeftOfOnOffBox.getAssociatedSpriteSheet(),
                topRigthTreeLeftOfOnOffBox.getDx1(), topRigthTreeLeftOfOnOffBox.getDy1(),
                topRigthTreeLeftOfOnOffBox.getDx2(), topRigthTreeLeftOfOnOffBox.getDy2(),
                topRigthTreeLeftOfOnOffBox.getSX1(), topRigthTreeLeftOfOnOffBox.getSY1(),
                topRigthTreeLeftOfOnOffBox.getSX2(), topRigthTreeLeftOfOnOffBox.getSY2(),
                null);
        g.drawImage(topRightTreeSouthOfOnOffBox.getAssociatedSpriteSheet(),
                topRightTreeSouthOfOnOffBox.getDx1(), topRightTreeSouthOfOnOffBox.getDy1(),
                topRightTreeSouthOfOnOffBox.getDx2(), topRightTreeSouthOfOnOffBox.getDy2(),
                topRightTreeSouthOfOnOffBox.getSX1(), topRightTreeSouthOfOnOffBox.getSY1(),
                topRightTreeSouthOfOnOffBox.getSX2(), topRightTreeSouthOfOnOffBox.getSY2(),
                null);

        //Drawing the OnOffBoxes.
        int sLocationsTopLeftOnOffBox[] = topleftOnOffBox.getSLocations();
        g.drawImage(topleftOnOffBox.getAssociatedSpiteSheet(),
                topleftOnOffBox.getDx1(), topleftOnOffBox.getDy1(),
                topleftOnOffBox.getDx2(), topleftOnOffBox.getDy2(),
                sLocationsTopLeftOnOffBox[0], sLocationsTopLeftOnOffBox[1],
                sLocationsTopLeftOnOffBox[2], sLocationsTopLeftOnOffBox[3],
                null);
        int sLocationsTopRightOnOffBox[] = topRightOnOffBox.getSLocations();
        g.drawImage(topRightOnOffBox.getAssociatedSpiteSheet(),
                topRightOnOffBox.getDx1(), topRightOnOffBox.getDy1(),
                topRightOnOffBox.getDx2(), topRightOnOffBox.getDy2(),
                sLocationsTopRightOnOffBox[0], sLocationsTopRightOnOffBox[1],
                sLocationsTopRightOnOffBox[2], sLocationsTopRightOnOffBox[3],
                null);
        int sLocationsBottomRightOnOffBox[] = bottomRightOnOffBox.getSLocations();
        g.drawImage(bottomRightOnOffBox.getAssociatedSpiteSheet(),
                bottomRightOnOffBox.getDx1(), bottomRightOnOffBox.getDy1(),
                bottomRightOnOffBox.getDx2(), bottomRightOnOffBox.getDy2(),
                sLocationsBottomRightOnOffBox[0], sLocationsBottomRightOnOffBox[1],
                sLocationsBottomRightOnOffBox[2], sLocationsBottomRightOnOffBox[3],
                null);

        //Painting all Red ExclamationBoxes
        /*int sLocationOfTopLeftRedExclamationBox[] = topLeftRedExclamationBox.getSLocations();
        g.drawImage(topLeftRedExclamationBox.getAssociatedSpriteSheet(),
                topLeftRedExclamationBox.getDx1(), topLeftRedExclamationBox.getDy1(),
                topLeftRedExclamationBox.getDx2(), topLeftRedExclamationBox.getDy2(),
                sLocationOfTopLeftRedExclamationBox[0], sLocationOfTopLeftRedExclamationBox[1],
                sLocationOfTopLeftRedExclamationBox[2], sLocationOfTopLeftRedExclamationBox[3],
                null);

        int sLocationsOfRightSideOfTopLeftRedExclamationBox[] = rightSideOfTopLeftRedExclamationBox.getSLocations();
        g.drawImage(topLeftRedExclamationBox.getAssociatedSpriteSheet(),
                rightSideOfTopLeftRedExclamationBox.getDx1(), rightSideOfTopLeftRedExclamationBox.getDy1(),
                rightSideOfTopLeftRedExclamationBox.getDx2(), rightSideOfTopLeftRedExclamationBox.getDy2(),
                sLocationsOfRightSideOfTopLeftRedExclamationBox[0], sLocationsOfRightSideOfTopLeftRedExclamationBox[1],
                sLocationsOfRightSideOfTopLeftRedExclamationBox[2], sLocationsOfRightSideOfTopLeftRedExclamationBox[3],
                null);*/

        ListIterator<RedExclamationBox> iteratorRedBox = redExclamationBoxList.listIterator();

        while(iteratorRedBox.hasNext()){

            RedExclamationBox temp = iteratorRedBox.next();

            int s_locations[] = temp.getSLocations();

            g.drawImage(temp.getAssociatedSpriteSheet(),
                    temp.getDx1(),temp.getDy1(),
                    temp.getDx2(), temp.getDy2(),
                    s_locations[0], s_locations[1],
                    s_locations[2], s_locations[3],
                    null);

        }

        g.drawImage(CastleFortressSpriteSheet.getSpriteSheet(),
                redBossDoor.getX(), redBossDoor.getY(),
                redBossDoor.getWidth() + redBossDoor.getX(), redBossDoor.getHeight() + redBossDoor.getY(),
                redBossDoor.getSX1(), redBossDoor.getSY1(),
                redBossDoor.getSX2(), redBossDoor.getSY2(),
                null);


        int s[] = bottomPatrollingKoopa.getSLocations();

        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                bottomPatrollingKoopa.getX(),bottomPatrollingKoopa.getY(),
                bottomPatrollingKoopa.getWidth() + bottomPatrollingKoopa.getX(),
                bottomPatrollingKoopa.getHeight() +  bottomPatrollingKoopa.getY(),
                s[0], s[1],
                s[2], s[3],
                null);

        int s_locations_middle_patrolling_koopa[] = middlePatrollingKoopa.getSLocations();

        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                middlePatrollingKoopa.getX(),middlePatrollingKoopa.getY(),
                middlePatrollingKoopa.getWidth() + middlePatrollingKoopa.getX(),
                middlePatrollingKoopa.getHeight() +  middlePatrollingKoopa.getY(),
                s_locations_middle_patrolling_koopa[0],  s_locations_middle_patrolling_koopa[1],
                s_locations_middle_patrolling_koopa[2],  s_locations_middle_patrolling_koopa[3],
                null);

        int s_locations_top_patrolling_koopa[] = topPatrollingKoopa.getSLocations();

        g.drawImage(EnemySpriteSheet.getEnemySpriteSheet(),
                topPatrollingKoopa.getX(), topPatrollingKoopa.getY(),
                topPatrollingKoopa.getWidth() +  topPatrollingKoopa.getX(),
                topPatrollingKoopa.getHeight() +   topPatrollingKoopa.getY(),
                s_locations_top_patrolling_koopa[0],  s_locations_top_patrolling_koopa[1],
                s_locations_top_patrolling_koopa[2],  s_locations_top_patrolling_koopa[3],
                null);

        g.setFont(lives.getFont());
        g.setColor(Color.WHITE);
        g.drawString(lives.getString(), lives.getX(), lives.getY());
        g.setFont(levelTitle.getFont());
        g.setColor(Color.WHITE);
        g.drawString(levelTitle.getNameOfLevel(), levelTitle.getX(), levelTitle.getY());

        //mario painting and animation decisions.
        int s_mario[] = GameControl.getMario().getSLocations();

        g.drawImage(MarioSpriteSheet.getSpriteSheet(),
                GameControl.getMario().getX(), GameControl.getMario().getY(),
                GameControl.getMario().getWidth() + GameControl.getMario().getX(),
                GameControl.getMario().getHeight() + GameControl.getMario().getY(),
                s_mario[0], s_mario[1],
                s_mario[2], s_mario[3],
                null);


    }
    public void actionPerformed(ActionEvent e){



        collisionBoxList.set(topPatrollingKoopa.getIndexInLinkedList(),
                new CollisionBox(topPatrollingKoopa.getCollisionRectangle()));
        collisionBoxList.set(middlePatrollingKoopa.getIndexInLinkedList(),
                new CollisionBox(middlePatrollingKoopa.getCollisionRectangle()));
        collisionBoxList.set(bottomPatrollingKoopa.getIndexInLinkedList(),
                new CollisionBox(bottomPatrollingKoopa.getCollisionRectangle()));


        if(checkCollision()){
            onOffBoxList.setAllOff();
            redExclamationBoxList.setAllFilled();
            GameControl.getMario().resetMarioPosition(Level.Levels.TWO);
        }
        if(GameControl.getMario().getCollisionRectangle().intersects(redBossDoor.getVictoryBox())){
            isLevelComplete = true;
        }

        if(redExclamationBoxList.collision(GameControl.getMario().getCollisionRectangle())){
            GameControl.getMario().resetMarioPosition(Level.Levels.TWO);
            onOffBoxList.setAllOff();
        }

        onOffBoxList.intersects(GameControl.getMario().getCollisionRectangle());

        if(onOffBoxList.isAllOn()){
            System.out.println("All of the boxes are on!");
            redExclamationBoxList.setAllEmpty();
        }

        //GameControl.getMainGameFrame().repaint();

    }




}
