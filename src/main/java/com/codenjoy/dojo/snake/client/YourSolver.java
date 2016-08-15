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


import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.RandomDice;
import com.codenjoy.dojo.snake.Astar.Cell;
import com.codenjoy.dojo.snake.Astar.Field;
import com.codenjoy.dojo.snake.Astar.Way;
import com.codenjoy.dojo.snake.model.Elements;

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


        String go;

        boolean findPath = false;
        boolean findTail = false;

        go = new Way(new Field(workField)).getNextStep(start,finish,false);
        if (!go.equals("noRout")){findPath = true;}
        if (findPath){
            Field newField = moveSnakeToGoal();
            String go2 = new Way(newField).getNextStep(newField.getStart(), newField.getFinish(), false);
            if (!go2.equals("noRout")){findTail = true;}
        }
        if (findPath == false || findTail == false) {

            return goWithNoRout();
        }



        return go;
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
