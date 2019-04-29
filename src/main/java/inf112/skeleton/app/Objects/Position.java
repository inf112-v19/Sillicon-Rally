package inf112.skeleton.app.Objects;

import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.PlayerMovements;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

import static inf112.skeleton.app.game.RoboGame.Direction.*;

public class Position {
    public float x;
    public float y;
    public RoboGame.Direction dir;

    public Position(float y, float x, RoboGame.Direction dir) {
        this.y = y;
        this.x = x;
        this.dir = dir;
    }

    public boolean movePosition(MoveCard.Type type, TileGrid grid) {
        boolean legalMove = true;

        switch (type) {
            case move1:
                legalMove = moveStraight(1, grid.tileSizeInPx, grid);
                break;
            case move2:
                legalMove = moveStraight(2, grid.tileSizeInPx, grid);
                break;
            case move3:
                legalMove = moveStraight(3, grid.tileSizeInPx, grid);
                break;
            case reverse:
                legalMove = moveStraight(1, grid.tileSizeInPx*-1, grid);
            case uturn:
                uTurn(grid);
                break;
            case turnleft:
                rotateCounterClockwise(grid);
                break;
            case turnright:
                rotateClockwise(grid);
                break;
        }

        return legalMove;
    }

    private void rotateClockwise(TileGrid grid) {
        switch (this.dir) {
            case North:
                setDirection(RoboGame.Direction.East);
                break;
            case East:
                setDirection(RoboGame.Direction.South);
                break;
            case South:
                setDirection(RoboGame.Direction.West);
                break;
            case West:
                setDirection(RoboGame.Direction.North);
        }
    }

    private void rotateCounterClockwise(TileGrid grid) {
        switch (this.dir) {
            case North:
                setDirection(RoboGame.Direction.West);
                break;
            case East:
                setDirection(RoboGame.Direction.North);
                break;
            case South:
                setDirection(RoboGame.Direction.East);
                break;
            case West:
                setDirection(RoboGame.Direction.South);
        }

    }

    public void setDirection(RoboGame.Direction dir) {
        this.dir = dir;
    }

    private void uTurn(TileGrid grid) {


        switch (this.dir) {
            case North:
                setDirection(South);
                break;
            case East:
                setDirection(West);
                break;
            case South:
                setDirection(North);
                break;
            case West:
                setDirection(East);
        }
    }

    public boolean moveStraight(int steps, int moveDistance, TileGrid grid) {
        for (int i = 0; i < steps; i++) {
            if (!moveStraight(moveDistance, grid))
                return false;
        }
        return true;
    }

    private boolean moveStraight(int moveDistance, TileGrid grid) {

        switch (this.dir) {
            case North:
                return setPosition((this.y + moveDistance), this.x, grid);
            case East:
                return setPosition( this.y,  this.x + moveDistance, grid);
            case South:
                return setPosition((int) this.y - moveDistance, (int) this.x, grid);
            case West:
                return setPosition((int) this.y, (int) this.x - moveDistance, grid);

        }
        return false;
    }

    public boolean setPosition(float yDest, float xDest, TileGrid grid) {
        if (checkIfMoveIsOutOfBounds(yDest, xDest, grid)) {
            return false;
        }

        Tile currentTile = grid.getTileFromCoordinates(this.y, this.x);

        this.x = xDest;
        this.y = yDest;

        return true;
    }

    public boolean checkIfMoveIsOutOfBounds(float y, float x, TileGrid grid) {
        if (y < 0 || x < 0)
            return true;

        float yTile = y / grid.tileSizeInPx;
        float xTile = x / grid.tileSizeInPx;
        if (yTile >= grid.rows || xTile >= grid.columns)
            return true;

        return false;
    }

    private void updatePosition(PlayerMovements movements) {
        this.y = movements.getY();
        this.x = movements.getX();
        this.dir = movements.getDirection();
    }


    public Position copy() {
        return new Position(this.y, this.x, this.dir);
    }

    public String toString() {
        return "Y: " + this.y + " X: " + this.x + " Direction: " + this.dir;
    }
}
