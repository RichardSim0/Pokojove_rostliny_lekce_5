package com.engeto.rostliny;

import java.util.Comparator;

public class PlantDateComparator implements Comparator<Plant> {
    @Override
    public int compare(Plant o1, Plant o2) {
        return o1.getPlanted().compareTo(o2.getPlanted());
    }
}

