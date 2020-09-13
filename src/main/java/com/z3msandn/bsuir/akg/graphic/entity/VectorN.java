package com.z3msandn.bsuir.akg.graphic.entity;
class VectorN {
    private double v[];

    VectorN(int n) { v = new double[n]; }    // create a new vector

    int size() { return v.length; }          // return vector size

    public double get(int j) { return v[j]; }       // get one element

    void set(int j, double f) { v[j] = f; }  // set one element

    void set(VectorN vec) {                  // copy from another vector
        for (int j = 0 ; j < size() ; j++)
            set(j, vec.get(j));
    }

    public String toString() {               // convert to string representation
        String s = "{";
        for (int j = 0 ; j < size() ; j++)
            s += (j == 0 ? "" : ",") + get(j);
        return s + "}";
    }

    void transform(MatrixN mat) {            // multiply by an N × N matrix
        VectorN tmp = new VectorN(size());
        double f;

        for (int i = 0 ; i < size() ; i++) {
            f = 0.;
            for (int j = 0 ; j < size() ; j++)
                f += mat.get(i,j) * get(j);
            tmp.set(i, f);
        }
        set(tmp);
    }

    double distance(VectorN vec) {          // euclidean distance
        double x, y, d = 0;
        for (int i = 0 ; i < size() ; i++) {
            x = vec.get(0) - get(0);
            y = vec.get(1) - get(1);
            d += x * x + y * y;
        }
        return Math.sqrt(d);
    }
}
