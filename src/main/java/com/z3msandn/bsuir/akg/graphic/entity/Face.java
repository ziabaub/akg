package com.z3msandn.bsuir.akg.graphic.entity;

import java.util.Arrays;

public class Face {
    public Indexes[] indexes;

    public Face(Indexes[] indexes) {
        this.indexes = indexes;
    }

    @Override
    public String toString() {
        return "Face{" +
                "indexes=" + Arrays.toString(indexes) +
                '}';
    }
}
