package com.z3msandn.bsuir.akg.graphic.shapes;

import java.awt.*;
import java.util.Arrays;

public class Tetrahedron {
    private Polygon3D[] polygon3DS;
    private Color color;

    public Tetrahedron(Color color, Polygon3D... polygon3DS) {
        this.polygon3DS = polygon3DS;
        this.color = color;
        this.setPolygonColor();
    }

    public Tetrahedron(Polygon3D... polygon3DS) {
        this.polygon3DS = polygon3DS;
    }

    public void render (Graphics2D g){
        Arrays.stream(polygon3DS).forEach(p -> p.render(g));
    }

    public  void rotate(boolean clockwise, double xDegree, double yDegree, double zDegree) {
        //Arrays.stream(polygon3DS).forEach(p->p.rotate(clockwise,xDegree,yDegree,zDegree));
        for(int i = 0 ;i< polygon3DS.length; i++){
            polygon3DS[i].rotate(clockwise,xDegree,yDegree,zDegree);
        }
        System.out.println(polygon3DS.length);
        //this.sortPolygon();
    }

    private void sortPolygon(){
        Polygon3D.sortPolygons(polygon3DS);
    }

    private void setPolygonColor(){
        Arrays.stream(polygon3DS).forEach(p -> p.setColor(this.color));
    }
}
