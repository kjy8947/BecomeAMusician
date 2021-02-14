package model;

import java.util.ArrayList;

public class List {
    private ArrayList<Chord> chordsToMemorize;
    private ArrayList<Chord> chordsMemorized;
    private ArrayList<Scale> scalesToMemorize;
    private ArrayList<Scale> scalesMemorized;

    public List() {
        chordsToMemorize = new ArrayList<>();
        chordsMemorized = new ArrayList<>();
        scalesToMemorize = new ArrayList<>();
        scalesMemorized = new ArrayList<>();
    }
}
