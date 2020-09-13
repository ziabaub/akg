package com.z3msandn.bsuir.akg.graphic.shapes;

import com.z3msandn.bsuir.akg.graphic.Display;
import com.z3msandn.bsuir.akg.graphic.entity.Matrix3D;
import com.z3msandn.bsuir.akg.graphic.entity.Point3D;
import com.z3msandn.bsuir.akg.graphic.entity.PointConverter;
import com.z3msandn.bsuir.akg.graphic.entity.Vector3D;
import com.z3msandn.bsuir.akg.tools.PolygonComparator;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class Polygon3D {
    private Vector3D[] vector3DS; //


    private Point3D[] point3DS;
    private Color color = Color.WHITE;

    public Polygon3D(Vector3D... vector3DS) { //
        this.vector3DS = new Vector3D[vector3DS.length];
        IntStream.range(0, vector3DS.length).forEach(i -> this.vector3DS[i] = vector3DS[i]);
    }

    public Polygon3D(Color color, Point3D... point3DS) {
        this.color = color;
        this.point3DS = new Point3D[point3DS.length];
        IntStream.range(0, point3DS.length).forEach(i -> this.point3DS[i] = point3DS[i]);
    }

    public Polygon3D(Point3D... point3DS) {
        this.point3DS = new Point3D[point3DS.length];
        IntStream.range(0, point3DS.length).forEach(i -> this.point3DS[i] = point3DS[i]);
    }

    void render(Graphics2D g) {
        Path2D path = new Path2D.Double();
        IntStream.range(0, vector3DS.length).forEach(i -> {
            Point2D.Double aDouble = PointConverter.toPoint(vector3DS[i]);
            if (i == 0) {
                path.moveTo(aDouble.x, aDouble.y);
            } else {
                path.lineTo(aDouble.x, aDouble.y);
            }
        });
        path.closePath();
        g.setColor(this.color);
        g.draw(path);
    }

//    void render(Graphics2D g) {
//        Path2D path = new Path2D.Double();
//        IntStream.range(0, point3DS.length).forEach(i -> {
//            Point2D.Double aDouble = PointConverter.toPoint(point3DS[i]);
//            if (i == 0) {
//                path.moveTo(aDouble.x, aDouble.y);
//            } else {
//                path.lineTo(aDouble.x, aDouble.y);
//            }
//        });
//        path.closePath();
//        g.setColor(this.color);
//        g.draw(path);
//    }

    //    void rotate(boolean clockwise, double xDegree, double yDegree, double zDegree) {
//        Arrays.stream(point3DS).forEach(p -> {
//            PointConverter.rotateAxisX(p, clockwise, xDegree);
//            //PointConverter.rotateAxisY(p, clockwise, yDegree);
//            //PointConverter.rotateAxisZ(p, clockwise, zDegree);
//        });
//    }
    void rotate(boolean clockwise, double xDegree, double yDegree, double zDegree) {
        for (int i =0 ; i< vector3DS.length; i++){
            PointConverter.rotateAxisX(vector3DS[i], clockwise, xDegree);
        }
//        Arrays.stream(vector3DS).forEach(p -> {
//            PointConverter.rotateAxisX(p, clockwise, xDegree);
//           // PointConverter.rotateAxisY(p, clockwise, yDegree);
//            //PointConverter.rotateAxisZ(p, clockwise, zDegree);
//        });
       // rot(Display.WIDTH / 2,Display.HEIGHT / 2,90);

    }

//    public double sinX(double x) {
//        return Math.sin(x * Math.PI / 180);
//    }
//
//    public double cosX(double x) {
//        return Math.cos(x * Math.PI / 180);
//    }
//
//    public void rot(int xPivot, int yPivot, double degree) {
//        Arrays.stream(vector3DS).forEach(p -> {
//            double xShif = p.get(1) - xPivot;
//            double yShif = p.get(2) - yPivot;
//            p.set(
//                    xPivot + (xShif * cosX(degree) - yShif * sinX(degree)),
//                    yPivot + (xShif * sinX(degree) + yShif * cosX(degree))
//            );
//        });
//    }

    void setColor(Color color) {
        this.color = color;
    }


//    public double getAverageX() {
//        double sum = 0;
//        for (Point3D p : point3DS) {
//            if (p != null)
//                sum += p.x;
//        }
//        return sum / this.point3DS.length;
//    }

    public double getAverageX() {
        double sum = 0;
        for (Vector3D p : vector3DS) {
            sum += p.get(0);
        }
        return sum / this.vector3DS.length;
    }

    static void sortPolygons(Polygon3D[] polygon3DS) {
        List<Polygon3D> polygon3DList = new ArrayList<>();
        IntStream.range(0, polygon3DS.length).forEach(i -> polygon3DList.add(polygon3DS[i]));
        polygon3DList.sort(new PolygonComparator());
        IntStream.range(0, polygon3DS.length).forEach(i -> polygon3DS[i] = polygon3DList.get(i));
    }
}
