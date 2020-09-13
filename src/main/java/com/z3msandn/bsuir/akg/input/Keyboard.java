package com.z3msandn.bsuir.akg.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

    private char key = '\0';

    public ClickType getKeyPressed() {
        switch (key) {
            case 'w':
                return ClickType.KEY_W;
            case 's':
                return ClickType.KEY_S;
            case 'd':
                return ClickType.KEY_D;
            case 'a':
                return ClickType.KEY_A;
            default:
                return ClickType.UNKNOWN;
        }
    }

    public void keyTyped(KeyEvent e) {
    }

    public void keyPressed(KeyEvent e) {
        key = e.getKeyChar();
    }

    public void keyReleased(KeyEvent e) {
        key = '\0';
    }
}