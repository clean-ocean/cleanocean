package org.academiadecodigo.murlogs.cleanocean.gameobjects;


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
    private int[] trash;
    private int trashCounter;
    private int score;
    private boolean inBeach;
    private Keyboard keyboard;

    private GridPosition position;
    protected GridDirection currentDirection;
    private Grid grid;


    public Player(GridPosition pos) {
        trash = new int[5];
        trashCounter = 0;
        score = 0;
        inBeach = true;
        position = pos;
        pos.setColor(GridColor.GREEN);
        keyboard = new Keyboard(this);
        init();
    }

    public void init() {
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

        keyboard.addEventListener(up);
        keyboard.addEventListener(left);
        keyboard.addEventListener(down);
        keyboard.addEventListener(right);

    }

    public void move(GridDirection direction) {
        this.currentDirection = direction;
        getPosition().moveInDirection(direction, 1);
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
        trashCounter = 0;
        for (int i = 0; i < trash.length; i++) {
            trash[i] = 0;
        }
    }

    public int[] recycle() {
        return trash;
    }

    public void addTrash(TrashType trashType) {

        switch (trashType) {
            case PAPER:
                if (CAPACITY < (trashType.getWeight() + trashCounter)) {
                    trash[0] += trashType.getWeight();
                    trashCounter = trashType.getWeight();
                }
                break;
            case METAL:
                if (CAPACITY < (trashType.getWeight() + trashCounter)) {
                    trash[1] += trashType.getWeight();
                    trashCounter = trashType.getWeight();
                }
                break;
            case PLASTIC:
                if (CAPACITY < (trashType.getWeight() + trashCounter)) {
                    trash[2] += trashType.getWeight();
                    trashCounter = trashType.getWeight();
                }
                break;
            case GLASS:
                if (CAPACITY < (trashType.getWeight() + trashCounter)) {
                    trash[3] += trashType.getWeight();
                    trashCounter = trashType.getWeight();
                }
                break;
            case ORGANIC:
                if (CAPACITY < (trashType.getWeight() + trashCounter)) {
                    trash[4] += trashType.getWeight();
                    trashCounter = trashType.getWeight();
                }
                break;
        }

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

    public void setInBeach(boolean inBeach) {
        this.inBeach = inBeach;
    }

    public GridPosition getPosition() {
        return position;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }


}
