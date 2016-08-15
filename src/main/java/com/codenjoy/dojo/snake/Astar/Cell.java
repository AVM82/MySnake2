package com.codenjoy.dojo.snake.Astar;

import java.util.LinkedList;

/**
 * Created by AVM on 04.08.2016.
 */
public class Cell {

    private int x;
    private int y;
    private int f = 0;
    private int g = 0;
    private int h = 0;
    private Cell parent;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;


    }

    public LinkedList<Cell> getNeighbors() {
        LinkedList <Cell> ret = new LinkedList<>();
//        ret.add(new Cell(this.getX() - 1,    this.getY() - 1));
        ret.add(new Cell(this.getX(),        this.getY() - 1));
//        ret.add(new Cell(this.getX() + 1,    this.getY() - 1));
        ret.add(new Cell(this.getX() + 1,    this.getY()));
//        ret.add(new Cell(this.getX() + 1,    this.getY() + 1));
        ret.add(new Cell(this.getX(),        this.getY() + 1));
//        ret.add(new Cell(this.getX() - 1,    this.getY() + 1));
        ret.add(new Cell(this.getX() - 1,    this.getY()));
        return ret;
    }

    public void setF(int f) {
        this.f = f;
    }

    public void setG(int g) {
        this.g = g;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getH() {
        return h;
    }

    public int getG() {

        return g;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getF() {
        return f;
    }

    public Cell getParent() {
        return parent;
    }

    public void setParent(Cell parent) {

        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cell cell = (Cell) o;

        if (x != cell.x) return false;
        return y == cell.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Cell{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
