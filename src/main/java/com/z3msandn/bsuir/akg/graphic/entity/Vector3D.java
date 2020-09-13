package com.z3msandn.bsuir.akg.graphic.entity;

public class Vector3D extends VectorN {

    public Vector3D(double x, double y, double z, double w) {
        super(4);
        set(x,y,z,w);
    }                                    // create a new 3D homogeneous vector

    void set(double x, double y, double z, double w) {          // set value of vector
        set(0, x);
        set(1, y);
        set(2, z);
        set(3, w);
    }

    void set(double x, double y, double z) {
        set(x, y, z, 1);
    } // set value of a 3D point

    @Override
    public String toString() {
        return "Vector3D{}";
    }
}