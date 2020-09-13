package com.z3msandn.bsuir.akg.graphic.entity;

import com.z3msandn.bsuir.akg.graphic.Display;

import java.awt.*;

public class PointConverter {
    private static double scale = 80;
    private static double zoomFactor = 1.1;

    public static void zoomIn() {
        scale *= zoomFactor;
    }

    public static void zoomOut() {
        scale /= zoomFactor;
    }

    public static Point.Double toPoint(Vector3D v) {
        double x3d = v.get(0) * scale;
        double y3d = v.get(1) * scale;
        double depth = v.get(2) * scale;
        double[] newVal = scale(x3d, y3d, depth);
        double x2d = (int) (Display.WIDTH / 2 + newVal[0]);
        double y2d = (int) (Display.HEIGHT / 2 - newVal[1]);
        return new Point.Double(x2d, y2d);
    }

//    public static Point.Double toPoint(Point3D point3D) {
//        double x3d = point3D.y * scale;
//        double y3d = point3D.z * scale;
//        double depth = point3D.x * scale;
//        double[] newVal = scale(x3d, y3d, depth);
//        double x2d = (int) (Display.WIDTH / 2 + newVal[0]);
//        double y2d = (int) (Display.HEIGHT / 2 - newVal[1]);
//        return new Point.Double(x2d, y2d);
//    }
    static int x = 1;
    public static void rotateAxisX(Vector3D p, boolean clockwise, double degree) {
        System.out.println(p);
//        double radius = Math.sqrt(Math.pow(p.get(1), 2) + Math.pow(p.get(2), 2));
        double theta = Math.atan2(p.get(2), p.get(0));
        //theta += 2 * Math.PI / 360 * degree * (clockwise ? 1 : -1);
        //System.out.println(theta);
//        p.set(1, radius * Math.cos(theta));
//        p.set(2, radius * Math.sin(theta));
//        double y = p.get(1);
//        double z = p.get(2);
//        double yz =
//        {0.103434,0.63749,0.102854,1.0}
//        {0.14243428537659655,0.63749,-0.031464456474858314,1.0}
        Matrix3D matrixN = new Matrix3D();
        matrixN.rotateY(0.003);
        p.transform(matrixN);
       int x = 0;

    }

//    public static void rotateAxisX(Vector3D p, boolean clockwise, double degree) {
//        double radius = Math.sqrt(Math.pow(p.get(1), 2) + Math.pow(p.get(2), 2));
//        double theta = Math.atan2(p.get(2), p.get(1));
//        theta += 2 * Math.PI / 360 * degree * (clockwise ? 1 : -1);
//        p.set(2, radius * Math.sin(theta));
//        p.set(1, radius * Math.cos(theta));
//    }

//    public static void rotateAxisX(Point3D p, boolean clockwise, double degree) {
//        double radius = Math.sqrt(Math.pow(p.y, 2) + Math.pow(p.z, 2));
//        double theta = Math.atan2(p.z, p.y);
//        theta += 2 * Math.PI / 360 * degree * (clockwise ? -1 : 1);
//        p.z = radius * Math.sin(theta);
//        p.y = radius * Math.cos(theta);
//
//    }

    public static void rotateAxisY(Vector3D p, boolean clockwise, double degree) {
        double radius = Math.sqrt(Math.pow(p.get(0), 2) + Math.pow(p.get(2), 2));
        double theta = Math.atan2(p.get(0), p.get(2));
        theta += 2 * Math.PI / 360 * degree * (clockwise ? -1 : 1);
        p.set(0, radius * Math.sin(theta));
        p.set(2, radius * Math.cos(theta));
    }

//    public static void rotateAxisY(Point3D p, boolean clockwise, double degree) {
//        double radius = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.z, 2));
//        double theta = Math.atan2(p.x, p.z);
//        theta += 2 * Math.PI / 360 * degree * (clockwise ? -1 : 1);
//        p.x = radius * Math.sin(theta);
//        p.z = radius * Math.cos(theta);
//    }

    public static void rotateAxisZ(Vector3D p, boolean clockwise, double degree) {
        double radius = Math.sqrt(Math.pow(p.get(1), 2) + Math.pow(p.get(0), 2));
        double theta = Math.atan2(p.get(1), p.get(0));
        theta += 2 * Math.PI / 360 * degree * (clockwise ? 1 : -1);
        p.set(1, radius * Math.sin(theta));
        p.set(0, radius * Math.cos(theta));
    }

    public static void rotateAxisZ(Point3D p, boolean clockwise, double degree) {
        double radius = Math.sqrt(Math.pow(p.y, 2) + Math.pow(p.x, 2));
        double theta = Math.atan2(p.y, p.x);
        theta += 2 * Math.PI / 360 * degree * (clockwise ? 1 : -1);
        p.y = radius * Math.sin(theta);
        p.x = radius * Math.cos(theta);
    }

    private static double[] scale(double x3d, double y3d, double depth) {
        double dist = Math.sqrt(Math.pow(x3d, 2) + Math.pow(y3d, 2));
        double theta = Math.atan2(y3d, x3d);
        double depth2 = 15 - depth;
        double localScale = Math.abs(1400 / (depth2 + 1400));
        dist *= localScale;
        return new double[]{
                dist * Math.cos(theta),
                dist * Math.sin(theta)
        };
    }


}
