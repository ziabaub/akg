package com.z3msandn.bsuir.akg.graphic.entity;

public class Point3D {
    public double x, y, z;
    public double w =1;

    public Point3D(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(double x, double y, double z, double w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }
}
