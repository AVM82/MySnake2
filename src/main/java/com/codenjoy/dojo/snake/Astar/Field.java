package com.codenjoy.dojo.snake.Astar;

import java.util.Arrays;

/**
 * Created by AVM on 04.08.2016.
 */
public class Field {
    int [][] field;

    public Field(int[][] field) {
        super();
        this.field = field;
    }

    public int getAt(int x, int y) {
        return field[x][y];
    }
    public void setAt(int x, int y, int value) {
        field[x][y] = value;
    }

    public Cell getStart() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++){
                if (field[i][j] == 1){
                    return new Cell(i,j);
                }
            }
        }
        return null;
    }
    public Cell getFinish() {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++){
                if (field[i][j] == 2){
                    return new Cell(i,j);
                }
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String s = "";
        for (int i = 0; i < field.length; i++) {
            s += "Field{" +
                    "field=" + Arrays.toString(field[i]) +
                    "}\n";
        }
        return s;

    }
}
