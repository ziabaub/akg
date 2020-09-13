package com.z3msandn.bsuir.akg.graphic.entity;

public class Indexes {
    public int p, t, n;

    public Indexes(int p, int t, int n) {
        this.p = p;
        this.t = t;
        this.n = n;
    }

    @Override
    public String toString() {
        return "Indexes{" +
                "x=" + p +
                ", y=" + t +
                ", z=" + n +
                '}';
    }
}
