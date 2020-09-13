package com.z3msandn.bsuir.akg.parser;

import com.z3msandn.bsuir.akg.graphic.entity.Face;
import com.z3msandn.bsuir.akg.graphic.entity.Indexes;
import com.z3msandn.bsuir.akg.graphic.entity.Point3D;
import com.z3msandn.bsuir.akg.graphic.entity.Vector3D;
import com.z3msandn.bsuir.akg.graphic.shapes.Polygon3D;
import com.z3msandn.bsuir.akg.graphic.shapes.Tetrahedron;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjParser {

    private static final String MAIN_DELIMITER = "# ";
    private static final String VERTEX_DELIMITER = "v";
    private static final String TEXTURE_DELIMITER = "vt";
    private static final String NORMALIZER_DELIMITER = "vn";
    private static final String FACE_DELIMITER = "f";

    public Tetrahedron parse(String data) {
        List<String> dataObject = Arrays.asList(data.split(MAIN_DELIMITER));
        List<Point3D> points = parseVertex(dataObject.get(0));
        List<Point3D> textures = parseTexture(dataObject.get(1));
        List<Point3D> normalizers = parseNormal(dataObject.get(2));

        List<Vector3D> vector3DS = parseVertexV(dataObject.get(0));
        List<Face> faces = parseFace(dataObject.get(3));

        return fetchTetrahedron(faces, points, textures, normalizers, vector3DS);
    }

    private List<Point3D> parseVertex(String vertex) {
        List<String> line = getLines(vertex, VERTEX_DELIMITER);
        List<Point3D> points = new ArrayList<>();
        line.forEach(s -> {
            String[] coo = s.trim().split(" ");
            double x = Double.parseDouble(coo[0]);
            double y = Double.parseDouble(coo[1]);
            double z = Double.parseDouble(coo[2]);
            double w = (coo.length == 4) ? Double.parseDouble(coo[3]) : 1;
            points.add(new Point3D(x, y, z, w));
        });
        return points;
    }

    private List<Vector3D> parseVertexV(String vertex) {
        List<String> line = getLines(vertex, VERTEX_DELIMITER);
        List<Vector3D> points = new ArrayList<>();
        line.forEach(s -> {
            String[] coo = s.trim().split(" ");
            double x = Double.parseDouble(coo[0]);
            double y = Double.parseDouble(coo[1]);
            double z = Double.parseDouble(coo[2]);
            double w = (coo.length == 4) ? Double.parseDouble(coo[3]) : 1;
            points.add(new Vector3D(x, y, z, w));
        });
        return points;
    }

    private List<Point3D> parseTexture(String texture) {
        List<String> lines = getLines(texture, TEXTURE_DELIMITER);
        List<Point3D> textures = new ArrayList<>();
        lines.forEach(s -> {
            String[] txt = s.trim().split(" ");
            double x = Double.parseDouble(txt[0]);
            double y = (txt.length > 1) ? Double.parseDouble(txt[1]) : 0;
            double z = (txt.length == 3) ? Double.parseDouble(txt[2]) : 0;
            textures.add(new Point3D(x, y, z));
        });
        return textures;
    }

    private List<Point3D> parseNormal(String normal) {
        List<String> lines = getLines(normal, NORMALIZER_DELIMITER);
        List<Point3D> normalizers = new ArrayList<>();
        lines.forEach(s -> {
            String[] norm = s.trim().split(" ");
            double x = Double.parseDouble(norm[0]);
            double y = Double.parseDouble(norm[1]);
            double z = Double.parseDouble(norm[2]);
            normalizers.add(new Point3D(x, y, z));
        });
        return normalizers;
    }

    private List<Face> parseFace(String face) {
        List<String> lines = getLines(face, FACE_DELIMITER);
        List<Face> faces = new ArrayList<>();
        lines.forEach(s -> {
            List<String> norms = Arrays.asList(s.trim().split(" "));
            List<Indexes> indexes = new ArrayList<>();
            norms.forEach(n -> {
                String[] no = n.split("/");
                int p = Integer.parseInt(no[0]);
                int t = Integer.parseInt(no[1]);
                int nor = Integer.parseInt(no[2]);
                indexes.add(new Indexes(p, t, nor));
            });
            faces.add(new Face(indexes.toArray(new Indexes[0])));
        });
        return faces;
    }

    private List<String> getLines(String data, String faceDelimiter) {
        String n = data.substring(data.indexOf(faceDelimiter));
        String trimmed = n.replace(faceDelimiter, "").trim();
        return Arrays.asList(trimmed.split("\n"));
    }

    private Tetrahedron fetchTetrahedron(
            List<Face> faces,
            List<Point3D> points,
            List<Point3D> textures,
            List<Point3D> normalizers, List<Vector3D> vector3DS) {
        List<Polygon3D> polygon3DS = new ArrayList<>();
        faces.forEach(f -> {
            Polygon3D p = new Polygon3D(
                    vector3DS.get(f.indexes[0].p - 1),
                    vector3DS.get(f.indexes[1].p - 1),
                    vector3DS.get(f.indexes[2].p - 1)
            );
            polygon3DS.add(p);
        });
        return new Tetrahedron(polygon3DS.toArray(new Polygon3D[0]));
    }
}
