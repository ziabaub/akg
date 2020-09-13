package com.z3msandn.bsuir;

import com.z3msandn.bsuir.akg.file.tools.Reader;
import com.z3msandn.bsuir.akg.graphic.Display;
import com.z3msandn.bsuir.akg.graphic.shapes.Tetrahedron;
import com.z3msandn.bsuir.akg.parser.ObjParser;

public class Main {
    public static void main(String[] args) {
        Reader reader = new Reader();
        String coos = reader.read("Model2.obj");
        ObjParser objParser = new ObjParser();
        Tetrahedron tetrahedron = objParser.parse(coos);

        Display display = new Display();
        display.showDisplay(tetrahedron);
    }
}
