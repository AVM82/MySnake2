package com.codenjoy.dojo.snake.Astar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * Created by AVM on 04.08.2016.
 */
public class World {

    Cell start;
    Cell finish;
    private Field workField;
    private LinkedList <Cell> openList = new LinkedList<>();
    private LinkedList <Cell> closeList = new LinkedList<>();
    private ArrayList<Cell> routeList = new ArrayList<>();

    public World(Field workField) {
        super();
        this.workField = workField;
    }

    public ArrayList<Cell> getRouteList() {
        if (routeList.isEmpty()){
            return null;
        }
        else {

            return routeList;
        }
    }

    private  ArrayList<Cell> calculatePath(Cell start, Cell finish, boolean longWay){
        this.start = start;
        this.finish = finish;
        openList.push(start);
        boolean found = false;
        boolean noRoute = false;
        Cell nextStep = start;
        while(!found && !noRoute){
            Cell cellMinF = openList.getFirst();


            for (Cell cell : openList) {

                if (longWay){

                    if (cellMinF.getF() < cell.getF()) {
                        cellMinF = cell;
                    }
                } else {

                    if (cellMinF.getF() > cell.getF()) {
                        cellMinF = cell;
                    }
                }
            }



            closeList.add(cellMinF);
            openList.remove(cellMinF);
            LinkedList <Cell> neighbors = cellMinF.getNeighbors();
            for (Cell neighbor : neighbors) {
                if (workField.getAt(neighbor.getX(),neighbor.getY()) == -1) {
                    continue;
                }
                if (closeList.contains(neighbor)) {
                    continue;
                }
                if (!openList.contains(neighbor)) {
                    openList.add(neighbor);
                    neighbor.setParent(cellMinF);
                    neighbor.setG(Cost.getG(neighbor));
                    neighbor.setH(Cost.getH(neighbor,finish));
                    neighbor.setF(Cost.getF(neighbor));
                    continue;
                }
                if (neighbor.getG() < cellMinF.getG()) {
                    neighbor.setParent(cellMinF);
                    neighbor.setG(Cost.getG(neighbor));
                    neighbor.setF(Cost.getF(neighbor));

                }
            }
            if (openList.contains(finish)) {
                for (Cell c : openList) {
                    if (c.equals(finish)){
                        finish.setParent(c.getParent());
                    }


                }
                found = true;

            }


            if (openList.isEmpty()) {
                noRoute = true;
            }


        }
        if (!noRoute) {
            Cell way = finish.getParent();
//            nextStep = finish;
            routeList.add(finish);
//            routeList.add(way);
            while (!way.equals(start)) {
//                nextStep = way;
                routeList.add(way);
                way = way.getParent();

//                    if (nextStep == null) break;
            }
        }

        return routeList;
    }

    public String getDirection(Cell start, Cell finish, boolean longWay) {
//        Cell start, Cell nextStep
        ArrayList<Cell> path = calculatePath(start, finish, longWay);
        if (!path.isEmpty()) {
            Cell nextStep = path.get(path.size()-1);
            if (nextStep.getX() < start.getX()) {
                return "LEFT";
            } else {
                if (nextStep.getX() > start.getX()) {
                    return "RIGHT";
                } else {
                    if (nextStep.getY() < start.getY()) {
                        return "UP";
                    } else {
                        if (nextStep.getY() > start.getY()) {
                            return "DOWN";
                        }
                    }
                }
            }
        }
        return "noRoute";
    }


    public Field getWorkField() {
        return workField;
    }
}


