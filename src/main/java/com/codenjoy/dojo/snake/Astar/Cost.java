package com.codenjoy.dojo.snake.Astar;

/**
 * Created by AVM on 04.08.2016.
 */
public class Cost {


    static public int getF(Cell cell){

       return cell.getH() + cell.getG();
   }

   static public int getH(Cell current, Cell finish){

      return manhattan(current, finish);
   }

   static public int getG(Cell current){

       if (current.getParent().getX() == current.getX() || current.getParent().getY() == current.getY()) {
           return current.getParent().getG() + 10;
       } else {
           return current.getParent().getG() + 14;
       }
   }

    private static int manhattan(Cell current, Cell finish) {

        return 10 * (Math.abs(current.getX() - finish.getX()) + Math.abs(current.getY() - finish.getY()));


    }


}
