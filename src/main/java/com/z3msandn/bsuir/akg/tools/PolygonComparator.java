package com.z3msandn.bsuir.akg.tools;

import com.z3msandn.bsuir.akg.graphic.shapes.Polygon3D;

import java.util.Comparator;

public class PolygonComparator implements Comparator<Polygon3D> {

    @Override
    public int compare(Polygon3D o1, Polygon3D o2) {

        double v1 = o1.getAverageX();
        double v2 = o2.getAverageX();
        if (v1 == v2) {
            return 0;
        }
        double res = v2 - v1;
        return (res) < 0 ? 1 : -1;
    }
}
