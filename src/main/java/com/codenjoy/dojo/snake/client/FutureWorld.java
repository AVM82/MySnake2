package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.Astar.Cell;
import com.codenjoy.dojo.snake.Astar.Field;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by AVM on 16.08.2016.
 */
public class FutureWorld implements MoveSnake {

    Field field;
    Snake snake;

    public FutureWorld(Field field, Snake snake) {
        this.field = field;
        this.snake = snake;
    }


    @Override
    public Field moveSnakeToGoal(ArrayList<Cell> path) {

        while(!path.isEmpty()) {
            int index = path.size()-1;
            moveHead(this.field, path.get(index));
            moveTail(this.field);
            path.remove(index);


        }



        return field;
    }

    private void moveTail(Field field) {
        Point tail = snake.getTail();
        field.setAt(tail.getX(), tail.getY(),0);
        snake.setTail();
        tail = snake.getTail();
        field.setAt(tail.getX(), tail.getY(),2);

    }

    private void moveHead(Field field, Cell cell) {
        Point headNow = snake.getHead();
        field.setAt(headNow.getX(), headNow.getY(), -1);
        field.setAt(cell.getX(), cell.getY(), 1);
        snake.setHead(new PointImpl(cell.getX(), cell.getY()));
    }


}
