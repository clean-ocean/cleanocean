package org.academiadecodigo.murlogs.cleanocean.gameobjects;


import org.academiadecodigo.murlogs.cleanocean.CollisionDetector;
import org.academiadecodigo.murlogs.cleanocean.Main;
import org.academiadecodigo.murlogs.cleanocean.Game;
import org.academiadecodigo.murlogs.cleanocean.gameobjects.trash.Trash;
import org.academiadecodigo.murlogs.cleanocean.gameobjects.trash.TrashType;
import org.academiadecodigo.murlogs.cleanocean.grid.Grid;
import org.academiadecodigo.murlogs.cleanocean.grid.GridColor;
import org.academiadecodigo.murlogs.cleanocean.grid.GridDirection;
import org.academiadecodigo.murlogs.cleanocean.grid.position.GridPosition;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;


public class Player implements KeyboardHandler {

    private static final int CAPACITY = 25;
    private int[] trash = new int[5];
    private int trashWeight = 0;
    private int score = 0;
    private boolean inBeach = true;
    private Keyboard keyboard;//= new Keyboard(this);
    private Eco[] ecos;
    private GridPosition position;
    protected GridDirection currentDirection;
    private Grid grid;

    private boolean slow = false;
    private int s = 2;
    //private String pic = "girl_front_stopped.png";
    private boolean randomWalk;
    private int randomWalkCounter = 20;

    private CollisionDetector collisionDetector;


    public Player(Grid grid, GridPosition pos, Eco[] ecos) {
        trash = new int[5];
        trashWeight = 0;
        score = 0;
        inBeach = true;
        position = pos;
        this.ecos = ecos;
        position.setColor(GridColor.GREEN);
        this.grid = grid;
        keyboard = new Keyboard(this);
        init();
    }


    public void init() {

        position.setColor(GridColor.GREEN);
        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_W);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_A);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_S);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_D);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);


  /*
       // apanhar o lixo carregando no espaço - por exemplo!
       // ou então não apanhar com o espaço, apanhar automaticamente
       // e caso tenha a mochila cheia e apanhe mais algum o mochila rompe
       // e cai o lixo OU ENTÃO o lixo serve como obstáculo até que esvazie a mochila

        KeyboardEvent pickTrash = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_SPACE);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
*/
        keyboard.addEventListener(up);
        keyboard.addEventListener(left);
        keyboard.addEventListener(down);
        keyboard.addEventListener(right);

    }

    public void move(GridDirection direction) {


        currentDirection = direction;


        if (collisionDetector.detectObstacle(position, direction)) {
            return;
        }

        Trash trash = collisionDetector.detectTrash(position, direction);

        if (trash != null && !trash.getPicked()) {
            pickTrash(trash);
        }

        if (randomWalk) {
            direction = GridDirection.values()[(int) (Math.random() * GridDirection.values().length)];
            position.moveInDirection(direction, 1);
            randomWalkCounter--;
            if (randomWalkCounter == 0) {
                randomWalkCounter = 20;
                randomWalk = false;
            }
        } else {
            if (s % 2 != 0) {
                int random = (int) Math.floor(Math.random() * 10);
                if (random <= 5) {
                    position.moveInDirection(direction, 1);
                }

            }
            if (s % 2 == 0) {
                position.moveInDirection(direction, 1);
            }
        }


        /*if (slow) {
            position.moveInDirection(GridDirection.values()[(int) Math.floor(Math.random() * GridDirection.values().length)], 1);
            s++;
            if (s == 10) {
                slow = false;
                s = 0;
            }
            System.out.println(s);
        }
        if (!slow) {
            position.moveInDirection(direction, 1);
        }
*/


        // empty bagTrash
        if (isInBeach()) {
            if (getPosition().getRow() == 1 && getPosition().getCol() > 27) {
                int points = 0;
                for (int i = 0; i < ecos.length; i++) {
                    points += ecos[i].recycleTrash(getTrash()[i]);
                }
                clearTrash();
                setScore(points);
                Game.score.setText("SCORE: " + score);
                //System.out.println(getScore());
            }
        }
    }

    public void pickTrash(Trash trash) {
        if (CAPACITY < (trash.getTrashType().getWeight() + trashWeight)) {
            Game.weight.setText("FULL");
            return;
        }
        trash.setPicked();
        addTrash(trash.getTrashType());
        System.out.println(trashWeight);
    }

    public void setCollisionDetector(CollisionDetector collisionDetector) {
        this.collisionDetector = collisionDetector;
    }


    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                //up
                currentDirection = GridDirection.UP;
                break;
            case KeyboardEvent.KEY_A:
                //left
                currentDirection = GridDirection.LEFT;
                break;
            case KeyboardEvent.KEY_S:
                //down
                currentDirection = GridDirection.DOWN;
                break;
            case KeyboardEvent.KEY_D:
                //right
                currentDirection = GridDirection.RIGHT;
                break;
        }
        move(currentDirection);
    }

    public void clearTrash() {
        trashWeight = 0;
        for (int i = 0; i < trash.length; i++) {
            trash[i] = 0;
        }
        Game.paper.setText("PAPER: " + trash[0]);
        Game.metal.setText("METAL: " + trash[1]);
        Game.plastic.setText("PLASTIC: " + trash[2]);
        Game.glass.setText("GLASS: " + trash[3]);
        Game.organic.setText("ORGANIC: " + trash[4]);
        Game.weight.setText("WEIGHT: " + trashWeight + " /25");
    }

    public int[] recycle() {
        return trash;
    }

    public void addTrash(TrashType trashType) {

        switch (trashType) {
            case PAPER:
                trash[0]++;
                trashWeight += trashType.getWeight();
                Game.paper.setText("PAPER: " + trash[0]);
                System.out.println(trash[0]);
                break;

            case METAL:
                trash[1]++;
                trashWeight += trashType.getWeight();
                Game.metal.setText("METAL: " + trash[1]);
                System.out.println(trash[1]);
                break;

            case PLASTIC:
                trash[2]++;
                trashWeight += trashType.getWeight();
                Game.plastic.setText("PLASTIC: " + trash[2]);
                System.out.println(trash[2]);
                break;

            case GLASS:
                trash[3]++;
                trashWeight += trashType.getWeight();
                Game.glass.setText("GLASS: " + trash[3]);
                System.out.println(trash[3]);
                break;

            case ORGANIC:
                trash[4]++;
                trashWeight += trashType.getWeight();
                Game.organic.setText("ORGANIC: " + trash[4]);
                System.out.println(trash[4]);
                break;
        }

        Game.weight.setText("WEIGHT: " + trashWeight + " /25");
    }

    public int[] getTrash() {
        return trash;
    }

    public int getScore() {
        return score;
    }


    public void setScore(int score) {
        this.score = this.score + score;
    }


    public boolean isInBeach() {
        return inBeach;
    }

    /*public void setInBeach(boolean inBeach) {
        this.inBeach = inBeach;
    }*/

    public GridPosition getPosition() {
        return position;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /*public void translate() {
        if (!isInBeach()) {
            position.setPos(Main.COLS-1, 0);
            //grid.makeGridPosition(Main.COLS - 1, 0, "...");
            return;
        }
        position.setPos(Main.COLS-1, Main.ROWS-1);
        //grid.makeGridPosition(Main.COLS-1, Main.ROWS-1, "....");
    }
    */

    public int getTrashWeight() {
        return trashWeight;
    }

    public void setSlow() {
        s = 3;
    }

    public void setRandomWalk() {
        randomWalk = true;
    }
}
