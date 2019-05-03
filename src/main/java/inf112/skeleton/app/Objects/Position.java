package inf112.skeleton.app.Objects;

import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.PlayerMovements;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;

import static inf112.skeleton.app.game.RoboGame.Direction.*;

public class Position {
    public float xPosition;
    public float yPosition;
    public RoboGame.Direction direction;


    public Position(float y, float x, RoboGame.Direction dir) {
        this.yPosition = y;
        this.xPosition = x;
        this.direction = dir;
    }


    public boolean setPosition(float yDest, float xDest, TileGrid grid) {
        if (checkIfMoveIsOutOfBounds(yDest, xDest, grid)) {
            return false;
        }
        xPosition = xDest;
        yPosition = yDest;

        return true;
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
                uTurn();
                break;
            case turnleft:
                rotateCounterClockwise();
                break;
            case turnright:
                rotateClockwise();
                break;
        }
        return legalMove;
    }


    public void setDirection(RoboGame.Direction dir) { this.direction = dir; }

    private void rotateClockwise() {
        switch (this.direction) {
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


    private void rotateCounterClockwise() {
        switch (this.direction) {
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


    private void uTurn() {
        switch (this.direction) {
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

        switch (this.direction) {
            case North:
                return setPosition((this.yPosition + moveDistance), this.xPosition, grid);
            case East:
                return setPosition( this.yPosition,  this.xPosition + moveDistance, grid);
            case South:
                return setPosition((int) this.yPosition - moveDistance, (int) this.xPosition, grid);
            case West:
                return setPosition((int) this.yPosition, (int) this.xPosition - moveDistance, grid);

        }
        return false;
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



    public Position copy() {
        return new Position(this.yPosition, this.xPosition, this.direction);
    }

    public String toString() {
        return "Y: " + this.yPosition + " X: " + this.xPosition + " Direction: " + this.direction;
    }
}
