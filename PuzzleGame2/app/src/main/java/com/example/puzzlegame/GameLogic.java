package com.example.puzzlegame;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains game navigation logic.
 */

public class GameLogic {

    // import maps
    Mapping Maps = new Mapping();

    /**
     * This function validates if button press was valid, if it was then it updates button map
     * and checks against win state.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    void ValidateButtonPress(String buttonID) {
        // check if button is not empty.
        if (!buttonID.equals("")) {
            boolean move = isAllowed(buttonID);
            if (move) {
                UpdateGameboardButtonMap(buttonID);
            }
        }
    }


    /**
     * This function is for updating game mapping, it allows empty button to move
     * in upward, downward, left and right direction.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private void UpdateGameboardButtonMap(String buttonID) {


        // check if move was in row if it was not means that move is in column.
        boolean Moveinrow = moveInRow(buttonID);

        // get current mapping
        HashMap ButtonMap = (HashMap) Maps.buttonMap;

        // clone mapping
        HashMap newMapping = (HashMap) ButtonMap.clone();

        //System.out.println("moveinrow " + Moveinrow);

        if (Moveinrow) {
            int CurrentButton = Maps.emptyTile; // current empty
            int NewEmpty = Integer.parseInt(buttonID);  // pressed
            int range = CurrentButton - NewEmpty;

            // left and right navigation
            if (range > 0) {
                //System.out.println("row left");
                for (int i = 0; i < range; i++) {
                    CurrentButton--;
                    int temp = (int) ButtonMap.get(CurrentButton);
                    newMapping.replace(CurrentButton, null);
                    CurrentButton++;
                    newMapping.replace(CurrentButton, temp);
                    CurrentButton--;
                }
            } else {
                range = range * -1;
                for (int i = 0; i < range; i++) {
                    //System.out.println("row right");
                    CurrentButton++;
                    int temp = (int) ButtonMap.get(CurrentButton);
                    newMapping.replace(CurrentButton, null);
                    CurrentButton--;
                    newMapping.replace(CurrentButton, temp);
                    CurrentButton++;
                }
            }
        } else {
            // up and down navigation
            //System.out.println("column nav");
            int CurrentButton = Maps.emptyTile;  // current empty
            int NewEmpty = Integer.parseInt(buttonID); // pressed
            int range = (CurrentButton - NewEmpty) / 4;

            if (range > 0) {
                //System.out.println("col up");
                for (int i = 0; i < range; i++) {
                    CurrentButton = CurrentButton - 4;
                    int temp = (int) ButtonMap.get(CurrentButton);
                    newMapping.replace(CurrentButton, null);
                    CurrentButton = CurrentButton + 4;
                    newMapping.replace(CurrentButton, temp);
                    CurrentButton = CurrentButton - 4;
                }
            } else {
                //System.out.println("col down");
                range = range * -1;
                for (int i = 0; i < range; i++) {
                    CurrentButton = CurrentButton + 4;
                    int temp = (int) ButtonMap.get(CurrentButton);
                    newMapping.replace(CurrentButton, null);
                    CurrentButton = CurrentButton - 4;
                    newMapping.replace(CurrentButton, temp);
                    CurrentButton = CurrentButton + 4;
                }
            }
        }



        // saves new mapping and empty tile location
        Maps.setButtonMap(newMapping);
        Maps.setEmptyTile(Integer.parseInt(buttonID));

        // checks button map against win state
        //boolean win = Maps.winMap.equals(Maps.buttonMap);
        //if (win){Maps.win = true;}

    }


    /**
     * This function validates if move is allowed.
     */
    private boolean isAllowed(String buttonID) {
        boolean MoveInRow = moveInRow(buttonID);
        if (MoveInRow) {
            return true;
        }
        // if move is not in row check if it is in column
        // Move is not allowed if it is not in row or column
        return moveInCol(buttonID);
    }


    /**
     * This function checks if move was in row.
     */
    boolean moveInRow(String buttonId) {
        int emptyTile = Maps.emptyTile;
        HashMap<Integer, ArrayList<Integer>> rowMap = Maps.rowMap;
        // Iterate over row map and find if move is row
        for (Integer row : rowMap.keySet()) {
            ArrayList<Integer> value = rowMap.get(row);
            if (value.contains(Integer.parseInt(buttonId)) && value.contains(emptyTile)) {
                System.out.println(value);
                return true;
            }
        }
        return false;
    }

    /**
     * This function checks if move was in column.
     */
    boolean moveInCol(String buttonId) {

        int emptyTile = Maps.emptyTile;
        HashMap<Integer, ArrayList<Integer>> colMap = Maps.colMap;
        // Iterate over column map and find if move is column
        for (Integer col : colMap.keySet()) {
            ArrayList<Integer> value = (ArrayList<Integer>) colMap.get(col);

            if (value.contains(Integer.parseInt(buttonId)) && value.contains(emptyTile)) {
                System.out.println(value);
                return true;
            }
        }
        return false;
    }
}
