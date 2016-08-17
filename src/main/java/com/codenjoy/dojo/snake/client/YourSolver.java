package com.codenjoy.dojo.snake.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2016 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */


import com.codenjoy.dojo.client.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.Astar.Cell;
import com.codenjoy.dojo.snake.Astar.Field;
import com.codenjoy.dojo.snake.Astar.World;
import com.codenjoy.dojo.snake.model.Elements;
import com.sun.org.apache.xerces.internal.dom.ElementImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * User: AVM
 */
public class YourSolver implements Solver<Board> {

    private static final String USER_NAME = "1982AlexVM@gmail.com";

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        System.out.println(board.toString());

        Cell start = new Cell(board.getHead().getX(), board.getHead().getY());

        Cell finish = new Cell(board.getApples().get(0).getX(), board.getApples().get(0).getY());

        int [][] workField = new int[board.size()][board.size()];

        for (int x = 0; x < board.size() ; x++) {
            for (int y = 0; y < board.size(); y++){
                if (board.isAt(x,y,Elements.NONE)){
                    workField[x][y] = 0;
                }else {
                    workField[x][y] = -1;
                    }
                }
            }
            workField[board.getHead().getX()][board.getHead().getY()] = 1;
            workField[board.getApples().get(0).getX()][board.getApples().get(0).getY()] = 2;
        Snake snake = createSnake(board);


        String go;

        boolean findPath = false;
        boolean findTail = false;
        World world = new World(new Field(workField));
        go = world.getDirection(start,finish,false);
        if (!go.equals("noRout")){findPath = true;}
        if (findPath){
            Field newField = new futureWorld(world.getWorkField(),snake).moveSnakeToGoal(world.getRouteList());
            String go2 = new World(newField).getDirection(newField.getStart(), newField.getFinish(), false);

            if (!go2.equals("noRout")){findTail = true;}
        }
        if (findPath == false || findTail == false) {

            return null;//goWithNoRout();// TODO: 16.08.2016
        }



        return go;
    }

    private Snake createSnake(Board board) {
        ArrayList <Point> snake = new ArrayList<Point>();
        Point point = board.getHead();

        snake.add(new PointImpl(point));

        int shiftY = 0;
        int shiftX = 0;

        Direction direction = board.getSnakeDirection();

        //Get shift
        if (direction.equals(Direction.DOWN)) {
            shiftY = -1;
            shiftX = 0;
        }
        if (direction.equals(Direction.UP)) {
            shiftY = 1;
            shiftX = 0;
        }
        if (direction.equals(Direction.LEFT)) {
            shiftX = 1;
            shiftY = 0;
        }
        if (direction.equals(Direction.RIGHT)) {
            shiftX = -1;
            shiftY = 0;
        }
        //-------------------------


        point.move(point.getX() + shiftX, point.getY() + shiftY);
        snake.add(new PointImpl(point));


        boolean tail = board.isAt(point.getX(),point.getY(),Elements.TAIL_END_DOWN,
                                                            Elements.TAIL_END_LEFT,
                                                            Elements.TAIL_END_UP,
                                                            Elements.TAIL_END_RIGHT);


        while (!tail) {

            Elements elements = board.getAt(point.getX(), point.getY());
            direction = getNextPoint(direction, elements, point);
            tail = board.isAt(point.getX(),point.getY(),Elements.TAIL_END_DOWN,
                                                                Elements.TAIL_END_LEFT,
                                                                Elements.TAIL_END_UP,
                                                                Elements.TAIL_END_RIGHT);
            snake.add(new PointImpl(point));
        }


        return new Snake(snake);


    }

    private Direction getNextPoint(Direction direction, Elements elements, Point point) {
        int shiftY = 0;
        int shiftX = 0;

        if (elements.equals(Elements.TAIL_LEFT_DOWN)) {
            if(direction.equals(Direction.LEFT))
            {
                shiftX = 0;
                shiftY = 1;


                direction = Direction.UP;
            }
            if(direction.equals(Direction.DOWN))
            {
                shiftX = -1;
                shiftY = 0;
                direction = Direction.RIGHT;
            }

        }

        if (elements.equals(Elements.TAIL_LEFT_UP)) {
            if(direction.equals(Direction.LEFT))
            {
                shiftX = 0;
                shiftY = -1;
                direction = Direction.DOWN;
            }
            if(direction.equals(Direction.UP))
            {
                shiftX = -1;
                shiftY = 0;
                direction = Direction.RIGHT;
            }

        }
        if (elements.equals(Elements.TAIL_RIGHT_DOWN)) {
            if(direction.equals(Direction.RIGHT))
            {
                shiftX = 0;
                shiftY = 1;
                direction = Direction.UP;
            }
            if(direction.equals(Direction.DOWN))
            {
                shiftX = 1;
                shiftY = 0;
                direction = Direction.LEFT;
            }

        }
        if (elements.equals(Elements.TAIL_RIGHT_UP)) {
            if(direction.equals(Direction.RIGHT))
            {
                shiftX = 0;
                shiftY = -1;
                direction = Direction.DOWN;
            }
            if(direction.equals(Direction.UP))
            {
                shiftX = 1;
                shiftY = 0;
                direction = Direction.RIGHT;
            }

        }

        if (elements.equals(Elements.TAIL_HORIZONTAL)) {
            if(direction.equals(Direction.RIGHT))
            {
                shiftX = -1;
                shiftY = 0;

            }
            if(direction.equals(Direction.LEFT))
            {
                shiftX = 1;
                shiftY = 0;

            }

        }

        if (elements.equals(Elements.TAIL_VERTICAL)) {
            if(direction.equals(Direction.UP))
            {
                shiftX = 0;
                shiftY = 1;

            }
            if(direction.equals(Direction.DOWN))
            {
                shiftX = 0;
                shiftY = -1;

            }

        }
        point.move(point.getX() + shiftX, point.getY() + shiftY);
        return direction;
    }

    public static void main(String[] args) {
        start(USER_NAME, WebSocketRunner.Host.REMOTE);
    }

    public static void start(String name, WebSocketRunner.Host server) {
        try {
            WebSocketRunner.run(server, name,
                    new YourSolver(new RandomDice()),
                    new Board());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
