package com.codenjoy.dojo.snake.client;

import com.codenjoy.dojo.snake.Astar.Cell;
import com.codenjoy.dojo.snake.Astar.Field;

import java.util.ArrayList;

/**
 * Created by AVM on 16.08.2016.
 */
public interface MoveSnake {

    Field moveSnakeToGoal(ArrayList<Cell> path);

}
