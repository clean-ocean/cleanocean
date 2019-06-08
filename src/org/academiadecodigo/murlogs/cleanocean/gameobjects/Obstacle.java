package org.academiadecodigo.murlogs.cleanocean.gameobjects;

import org.academiadecodigo.murlogs.cleanocean.grid.Grid;
import org.academiadecodigo.murlogs.cleanocean.grid.GridColor;
import org.academiadecodigo.murlogs.cleanocean.grid.position.GridPosition;

public class Obstacle {

    private GridPosition position;
    private Grid grid;

    public Obstacle(GridPosition pos) {
        this.position = pos;
        pos.setColor(GridColor.RED);
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public GridPosition getPosition() {
        return position;
    }

}
