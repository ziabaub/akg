package com.z3msandn.bsuir.akg.graphic;

import com.z3msandn.bsuir.akg.graphic.entity.Point3D;
import com.z3msandn.bsuir.akg.graphic.entity.PointConverter;
import com.z3msandn.bsuir.akg.graphic.entity.Vector3D;
import com.z3msandn.bsuir.akg.graphic.shapes.Polygon3D;
import com.z3msandn.bsuir.akg.graphic.shapes.Tetrahedron;
import com.z3msandn.bsuir.akg.input.ClickType;
import com.z3msandn.bsuir.akg.input.Keyboard;
import com.z3msandn.bsuir.akg.input.Mouse;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.logging.Logger;

public class Display extends Canvas implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(Display.class.getName());
    private static final String TITLE = "3D Renderer";
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    private static boolean running = false;

    private Mouse mouse;
    private Keyboard keyboard;
    private Thread thread;
    private JFrame frame;
    private Tetrahedron tetrahedron;

    public Display() {
        frame = new JFrame();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));

        mouse = new Mouse();
        keyboard = new Keyboard();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addMouseWheelListener(mouse);
        addKeyListener(keyboard);
    }

    public void showDisplay(Tetrahedron tetrahedron) {
        frame.setTitle(TITLE);
        frame.add(this);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
        this.tetrahedron = tetrahedron;
        start();
    }

    private synchronized void start() {
        running = true;
        thread = new Thread(this, "Dipslay");
        thread.start();
    }

    private synchronized void stop() {
        try {
            running = false;
            thread.join();
        } catch (InterruptedException e) {
            LOGGER.info("Thread " + this.getName() + " interrupted");
            thread.interrupt();
        }
    }

    public void run() {
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double updatePerSecond = 1000000000.0 / 60;
        double nextUpdateToward = 0;
        int frames = 0;
        //init();

        while (running) {
            long now = System.nanoTime();
            nextUpdateToward += (now - lastTime) / updatePerSecond;
            lastTime = now;
            while (nextUpdateToward >= 1) {
                update();
                nextUpdateToward--;
            }
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                frame.setTitle(TITLE + "|" + frames + "fps");
                frames = 0;
            }
        }
        stop();
    }

//    private void init() {
//        int s = 100;
//        Point3D p1 = new Point3D(s / 2, -s / 2, -s / 2);
//        Point3D p2 = new Point3D(s / 2, s / 2, -s / 2);
//        Point3D p3 = new Point3D(s / 2, s / 2, s / 2);
//        Point3D p4 = new Point3D(s / 2, -s / 2, s / 2);
//        Point3D p5 = new Point3D(-s / 2, -s / 2, -s / 2);
//        Point3D p6 = new Point3D(-s / 2, s / 2, -s / 2);
//        Point3D p7 = new Point3D(-s / 2, s / 2, s / 2);
//        Point3D p8 = new Point3D(-s / 2, -s / 2, s / 2);
//        this.tetrahedron = new Tetrahedron(
//                new Polygon3D( p5, p6, p7, p8),
//                new Polygon3D( p1, p2, p6, p5),
//                new Polygon3D( p1, p5, p8, p4),
//                new Polygon3D( p2, p6, p7, p3),
//                new Polygon3D(p4, p3, p7, p8),
//                new Polygon3D( p1, p2, p3, p4)
//        );
//    }

    private void init() {
        int s = 100;
        Vector3D p1 = new Vector3D(s / 2, -s / 2, -s / 2,1);
        Vector3D p2 = new Vector3D(s / 2, s / 2, -s / 2,1);
        Vector3D p3 = new Vector3D(s / 2, s / 2, s / 2,1);
        Vector3D p4 = new Vector3D(s / 2, -s / 2, s / 2,1);
        Vector3D p5 = new Vector3D(-s / 2, -s / 2, -s / 2,1);
        Vector3D p6 = new Vector3D(-s / 2, s / 2, -s / 2,1);
        Vector3D p7 = new Vector3D(-s / 2, s / 2, s / 2,1);
        Vector3D p8 = new Vector3D(-s / 2, -s / 2, s / 2,1);
        this.tetrahedron = new Tetrahedron(
                new Polygon3D( p5, p6, p7, p8),
                new Polygon3D( p1, p2, p6, p5),
                new Polygon3D( p1, p5, p8, p4),
                new Polygon3D( p2, p6, p7, p3),
                new Polygon3D(p4, p3, p7, p8),
                new Polygon3D( p1, p2, p3, p4)
        );
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        Graphics2D graphics2D= (Graphics2D) g.create();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH * 2, HEIGHT * 2);

        tetrahedron.render(graphics2D);
        g.dispose();
        bs.show();
    }

    private void update() {
        ClickType keyPressed = keyboard.getKeyPressed();
        if (keyPressed != ClickType.UNKNOWN) {
            keyboardUpdate();
        } else if (mouse.getMouseB() != ClickType.UNKNOWN){
            mouseUpdate();
        }

    }


    private void mouseUpdate() {
        int x = mouse.getMouseX();
        int y = mouse.getMouseY();
        if (mouse.getMouseB() == ClickType.LEFT_CLICK) {
            int xDif = (x - mouse.getInitialX()) / mouse.getMs();
            int yDif = (y - mouse.getInitialY()) / mouse.getMs();
            this.tetrahedron.rotate(true, 0, -yDif, xDif);
        } else if (mouse.getMouseB() == ClickType.RIGHT_CLICK) {
            int xDif = (x - mouse.getInitialX()) / mouse.getMs();
            this.tetrahedron.rotate(true, xDif, 0, 0);
        }
        mouse.setInitialX(x);
        mouse.setInitialY(y);
    }

    private void keyboardUpdate() {
        int x = mouse.getMouseX();
        if (keyboard.getKeyPressed() == ClickType.KEY_W) {
            PointConverter.zoomIn();
        } else if (keyboard.getKeyPressed() == ClickType.KEY_S) {
            PointConverter.zoomOut();
        }
        mouse.setInitialX(x);
    }

}
