package com.z3msandn.bsuir.akg.graphic.shapes;

import com.z3msandn.bsuir.akg.graphic.entity.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ObjectTarget {
    private List<Polygon> polygons = new ArrayList<>();
    private List<Point3D> point3DS = new ArrayList<>();
    private List<Point3D> texturePaints = new ArrayList<>();
    private List<Point3D> normalizers = new ArrayList<>();

    public List<Polygon> getPolygons() {
        return polygons;
    }

    public void setPolygons(List<Polygon> polygons) {
        this.polygons = polygons;
    }

    public List<Point3D> getPoint3DS() {
        return point3DS;
    }

    public void setPoint3DS(List<Point3D> point3DS) {
        this.point3DS = point3DS;
    }

    public List<Point3D> getTexturePaints() {
        return texturePaints;
    }

    public void setTexturePaints(List<Point3D> texturePaints) {
        this.texturePaints = texturePaints;
    }

    public List<Point3D> getNormalizers() {
        return normalizers;
    }

    public void setNormalizers(List<Point3D> normalizers) {
        this.normalizers = normalizers;
    }
}
