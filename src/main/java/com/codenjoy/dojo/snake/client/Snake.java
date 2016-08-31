package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

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

    public  void setTail() {
        snake.remove(snake.size()-1);
    }

    public Point getTail() {

        return new PointImpl(snake.get(snake.size()-1));
    }

    public Point getHead() {

        return new PointImpl(snake.get(0));
    }
}
