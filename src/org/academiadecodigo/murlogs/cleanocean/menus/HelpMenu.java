package org.academiadecodigo.murlogs.cleanocean.menus;

import org.academiadecodigo.murlogs.cleanocean.Game;
import org.academiadecodigo.murlogs.cleanocean.grid.Grid;
import org.academiadecodigo.murlogs.cleanocean.grid.SimpleGfxGrid;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class HelpMenu implements KeyboardHandler {


    private Picture background;
    private Picture reverseMenuSelector;

    private Picture helpDescription1;
    private Picture helpDescription2;
    private Picture helpDescription3;
    private Picture helpDescription4;
    private Picture helpDescription5;
    private Picture helpDescription6;
    private Picture helpDescription7;
    private Picture helpDescription8;
    private Picture helpDescription9;
    private Picture aKey;
    private Picture aKeyRed;
    private Picture aExplained;
    private Picture dKey;
    private Picture dKeyRed;
    private Picture dExplained;
    private Picture sKey;
    private Picture sKeyRed;
    private Picture sExplained;
    private Picture wKey;
    private Picture wKeyRed;
    private Picture wExplained;


    SimpleGfxGrid gfxGrid;

    private Grid grid;
    private Game game;
    private StarterMenu starterMenu;

    Keyboard keyboard = new Keyboard(this);
    KeyboardEvent pressW;
    KeyboardEvent pressA;
    KeyboardEvent pressS;
    KeyboardEvent pressD;
    KeyboardEvent pressQ;

    KeyboardEvent releaseW;
    KeyboardEvent releaseA;
    KeyboardEvent releaseS;
    KeyboardEvent releaseD;

    public HelpMenu(Game game) {
        this.game = game;
    }

    public void helperMenu() {


        keyboardInit();
        // help menu

        SimpleGfxGrid gfxGrid = null;
        background = new Picture(gfxGrid.getPADDING(), gfxGrid.getPADDING(), "menu background.png");
        background.draw();

        helpDescription1 = new Picture(150, 50, "helpDescription1.png");
        helpDescription1.draw();

        helpDescription2 = new Picture(175, 100, "helpDescription2.png");
        helpDescription2.draw();

        helpDescription3 = new Picture(200, 150, "helpDescription3.png");
        helpDescription3.draw();

        helpDescription4 = new Picture(150, 200, "helpDescription4.png");
        helpDescription4.draw();

        helpDescription5 = new Picture(350, 250, "helpDescription5.png");
        helpDescription5.draw();

        helpDescription6 = new Picture(550, 300, "helpDescription6.png");
        helpDescription6.draw();

        helpDescription7 = new Picture(700, 350, "helpDescription7.png");
        helpDescription7.draw();

        helpDescription8 = new Picture(250, 400, "helpDescription8.png");
        helpDescription8.draw();

        helpDescription9 = new Picture(250, 450, "helpDescription9.png");
        helpDescription9.draw();

        reverseMenuSelector = new Picture(677, 520, "menu selector reversed.png");

        wKey = new Picture(175, 575, "W.png");
        wKey.draw();
        wKeyRed = new Picture(175, 575, "wRed.png");

        aKey = new Picture(125, 625, "A.png");
        aKey.draw();
        aKeyRed = new Picture(123, 623, "aRed.png");

        sKey = new Picture(175, 625, "S.png");
        sKey.draw();
        sKeyRed = new Picture(173, 623, "sRed.png");

        dKey = new Picture(225, 625, "D.png");
        dKey.draw();
        dKeyRed = new Picture(223, 623, "dRed.png");


        wExplained = new Picture(425, 550, "w to up.png");
        aExplained = new Picture(375, 600, "a to left.png");
        dExplained = new Picture(475, 650, "d to right.png");
        sExplained = new Picture(425, 700, "s to down.png");
    }


    public void keyboardInit() {

        // Select right or left to choose play or help in the menu
        pressW = new KeyboardEvent();
        pressW.setKey(KeyboardEvent.KEY_W);
        pressW.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        pressA = new KeyboardEvent();
        pressA.setKey(KeyboardEvent.KEY_A);
        pressA.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        pressS = new KeyboardEvent();
        pressS.setKey(KeyboardEvent.KEY_S);
        pressS.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        pressD = new KeyboardEvent();
        pressD.setKey(KeyboardEvent.KEY_D);
        pressD.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        pressQ = new KeyboardEvent();
        pressQ.setKey(KeyboardEvent.KEY_Q);
        pressQ.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        releaseW = new KeyboardEvent();
        releaseW.setKey(KeyboardEvent.KEY_W);
        releaseW.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        releaseA = new KeyboardEvent();
        releaseA.setKey(KeyboardEvent.KEY_A);
        releaseA.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        releaseS = new KeyboardEvent();
        releaseS.setKey(KeyboardEvent.KEY_S);
        releaseS.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);

        releaseD = new KeyboardEvent();
        releaseD.setKey(KeyboardEvent.KEY_D);
        releaseD.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);



        keyboard.addEventListener(pressW);
        keyboard.addEventListener(pressA);
        keyboard.addEventListener(pressS);
        keyboard.addEventListener(pressD);
        keyboard.addEventListener(pressQ);

        keyboard.addEventListener(releaseW);
        keyboard.addEventListener(releaseA);
        keyboard.addEventListener(releaseS);
        keyboard.addEventListener(releaseD);


    }


    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {


        switch (keyboardEvent.getKey()) {
            case KeyboardEvent.KEY_W:
                wExplained.draw();
                wKey.delete();
                wKeyRed.draw();
                break;
            case KeyboardEvent.KEY_A:
                aExplained.draw();
                aKey.delete();
                aKeyRed.draw();
                break;
            case KeyboardEvent.KEY_S:
                sExplained.draw();
                sKey.delete();
                sKeyRed.draw();
                break;
            case KeyboardEvent.KEY_D:
                dExplained.draw();
                dKey.delete();
                dKeyRed.draw();
                break;
            case KeyboardEvent.KEY_Q:
                starterMenu.starterMenu();
                break;
        }

    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_W:
                wExplained.delete();
                wKeyRed.delete();
                wKey.draw();
                break;
            case KeyboardEvent.KEY_A:
                aExplained.delete();
                aKeyRed.delete();
                aKey.draw();
                break;
            case KeyboardEvent.KEY_S:
                sExplained.delete();
                sKeyRed.delete();
                sKey.draw();
                break;
            case KeyboardEvent.KEY_D:
                dExplained.delete();
                dKeyRed.delete();
                dKey.draw();
                break;

        }
    }

}
