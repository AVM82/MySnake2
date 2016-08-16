package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.Astar.Cell;
import com.codenjoy.dojo.snake.Astar.Field;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by AVM on 16.08.2016.
 */
public class futureWorld implements MoveSnake {

    Field field;
    Snake snake;

    public futureWorld(Field field, Snake snake) {
        this.field = field;
        this.snake = snake;
    }


    @Override
    public Field moveSnakeToGoal(ArrayList<Cell> path) {

        while(!path.isEmpty()) {
            int index = path.size()-1;
            moveHead(field, path.get(index));
            moveTail(field, path.get(index));


        }



        return field;
    }

    private void moveTail(Field field, Cell cell) {



    }

    private void moveHead(Field field, Cell cell) {
        field.setAt(cell.getX(), cell.getY(), -1);
        snake.setHead(new PointImpl(cell.getX(), cell.getY()));
    }


}