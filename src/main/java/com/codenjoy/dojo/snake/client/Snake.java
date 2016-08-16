package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.snake.Astar.Cell;

import java.util.ArrayList;

/**
 * Created by AVM on 16.08.2016.
 */
public class Snake {


    private ArrayList<Point> snake;



    public Snake(ArrayList <Point> snake) {
        super();
        this.snake = snake;


    }

    public void setHead(PointImpl point) {
        snake.add(0,point);
    }
}
