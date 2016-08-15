package com.codenjoy.dojo.snake.Astar;

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
}
