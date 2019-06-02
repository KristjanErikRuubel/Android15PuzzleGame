package com.example.puzzlegame;

import java.util.ArrayList;
import java.util.HashMap;


/**
 *  This is the map class where button mapping, row mapping and column mappings are stored.
 *  Column and row mappings dont change button mapping is updated when empty tile is moved
 *
 * **/


public class Mapping {

    HashMap<Integer, Integer> buttonMap;
    HashMap<Integer, Integer> winMap;
    HashMap<Integer, ArrayList<Integer>> colMap;
    HashMap<Integer, ArrayList<Integer>> rowMap;
    int emptyTile;
    boolean win = false;

    public void setEmptyTile(int emptyTile) {
        this.emptyTile = emptyTile;
    }

    HashMap getButtonMap() {
        return this.buttonMap;
    }

    public void setButtonMap(HashMap buttonMap) {
        this.buttonMap = buttonMap;
    }

    /**
     * Default constructor
     **/

    Mapping() {

        emptyTile = 16;
        // Init button mapping
                buttonMap = new HashMap<>();
                for (int i = 1; i < 17; i++) {
                    if (i <= 15) {
                        buttonMap.put(i, i);
            } else {
                buttonMap.put(i, null);
            }
        }

        // Map to check against to determine win state
        winMap = new HashMap<>();
        for (int i = 1; i < 17; i++) {
            if (i <= 15) {
                winMap.put(i, i);
            } else {
                winMap.put(i, null);
            }
        }


        int start = 1;
        int end = 5;

        // Default row mapping to check against
        rowMap = new HashMap<>();
        for (int j = 1; j < 5; j++) {
            ArrayList<Integer> rowButtons = new ArrayList<>();
            for (int k = start; k < end; k++) {
                rowButtons.add(k);
            }
            rowMap.put(j, rowButtons);
            start = start + 4;
            end = end + 4;
        }

        int colstart = 1;
        int colend = 14;
        // Default column mapping to check against
        colMap = new HashMap<>();
        for (int j = 1; j < 5; j++) {
            ArrayList<Integer> rowButtons = new ArrayList<>();
            for (int k = colstart; k < colend; k++) {
                rowButtons.add(k);
                k = k + 3;
            }
            colMap.put(j, rowButtons);
            colend = colend + 1;
            colstart = colstart + 1;
        }
    }
}
