package com.z3msandn.bsuir.akg.graphic.entity;

public class Matrix3D extends MatrixN {

    Matrix3D() { // create a new identity transformation
        super(4);
        identity();
    }

    void rotateX(double theta) { // rotate transformation about the X axis

        Matrix3D tmp = new Matrix3D();
        double c = Math.cos(theta);
        double s = Math.sin(theta);

        tmp.set(1,1, c);
        tmp.set(1,2,-s);
        tmp.set(2,1, s);
        tmp.set(2,2, c);

        preMultiply(tmp);
    }
    void rotateY(double theta) { // rotate transformation about the Y axis

        Matrix3D tmp = new Matrix3D();
        double c = Math.cos(theta);
        double s = Math.sin(theta);

        tmp.set(2,2, c);
        tmp.set(2,0,-s);
        tmp.set(0,2, s);
        tmp.set(0,0, c);

        preMultiply(tmp);
    }
    void rotateZ(double theta) { // rotate transformation about the Z axis

        Matrix3D tmp = new Matrix3D();
        double c = Math.cos(theta);
        double s = Math.sin(theta);

        tmp.set(0,0, c);
        tmp.set(0,1,-s);
        tmp.set(1,0, s);
        tmp.set(1,1, c);

        preMultiply(tmp);
    }

    void translate(double a, double b, double c) { // translate

        Matrix3D tmp = new Matrix3D();

        tmp.set(0,3, a);
        tmp.set(1,3, b);
        tmp.set(2,3, c);

        preMultiply(tmp);
    }
    void translate(Vector3D v) { translate(v.get(0), v.get(1), v.get(2)); }

    void scale(double s) { // scale uniformly

        Matrix3D tmp = new Matrix3D();

        tmp.set(0,0, s);
        tmp.set(1,1, s);
        tmp.set(2,2, s);

        preMultiply(tmp);
    }
    void scale(double r, double s, double t) { // scale non-uniformly

        Matrix3D tmp = new Matrix3D();

        tmp.set(0,0, r);
        tmp.set(1,1, s);
        tmp.set(2,2, t);

        preMultiply(tmp);
    }
    void scale(Vector3D v) { scale(v.get(0), v.get(1), v.get(2)); }
}