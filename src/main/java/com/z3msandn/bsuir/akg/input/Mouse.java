package com.z3msandn.bsuir.akg.input;

import java.awt.event.*;

public class Mouse implements MouseListener, MouseMotionListener, MouseWheelListener {

    private int initialX = 0;
    private int initialY = 0;
    private int ms = 2;
    private int mouseX = -1;
    private int mouseY = -1;
    private int mouseB = -1;
    private int scroll = -1;


    public int getMs() {
        return ms;
    }

    public void setMs(int ms) {
        this.ms = ms;
    }

    public int getInitialY() {
        return initialY;
    }

    public void setInitialY(int initialY) {
        this.initialY = initialY;
    }

    public int getInitialX() {
        return initialX;
    }

    public void setInitialX(int initialX) {
        this.initialX = initialX;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public ClickType getMouseB() {
       // System.out.println(mouseX+"    "+mouseY);
        switch (this.mouseB) {
            case 1:
                return ClickType.LEFT_CLICK;
            case 3:
                return ClickType.RIGHT_CLICK;
            default:
                return ClickType.UNKNOWN;
        }
    }

    public int getScroll() {
        return scroll;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.mouseB = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseX = -1;
        mouseY = -1;
        mouseB = -1;
        scroll = 0;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.mouseX = e.getX();
        this.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
    }
}
