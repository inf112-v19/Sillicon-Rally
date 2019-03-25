package inf112.skeleton.app.game;

import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.collision.objects.CollisionHandler;
import inf112.skeleton.app.collision.objects.ConveyorBeltObject;
import inf112.skeleton.app.collision.objects.GameObjectFactory;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

import java.util.List;

public class PlayerMovements {
    Player player;
    float yLoc;
    float xLoc;
    CollisionHandler collisionHandler;

    public PlayerMovements(Player player) {
        this.player = player;
        yLoc = player.getY();
        xLoc = player.getX();
    }


    public void moveStraight(int steps, int moveDistance, TileGrid grid) {
        for (int i = 0; i < steps; i++) {
            moveStraight(moveDistance, grid);
            //collisionHandler.doubleCheck(grid, player);
        }
    }



    private void moveStraight(int moveDistance, TileGrid grid) {
        switch (player.currentDirection) {
            case North:
                setPosition((int) yLoc + moveDistance, (int) xLoc, grid);
                break;
            case East:
                setPosition((int) yLoc, (int) xLoc + moveDistance, grid);
                break;
            case South:
                setPosition((int) yLoc - moveDistance, (int) xLoc, grid);
                break;
            case West:
                setPosition((int) yLoc, (int) xLoc - moveDistance, grid);
                break;
        }
        yLoc = player.getY();
        xLoc = player.getX();
    }

    public boolean checkIfMoveIsOutOfBounds(int y, int x, TileGrid grid) {
        if (y < 0 || x < 0)
            return true;

        int yTile = y / grid.tileSizeInPx;
        int xTile = x / grid.tileSizeInPx;
        if (yTile >= grid.rows || xTile >= grid.columns)
            return true;

        return false;
    }


    public void setPosition(int y, int x, TileGrid grid) {
        if (checkIfMoveIsOutOfBounds(y, x, grid)) {
            player.handleDeath(grid);
            return;
        }

        Tile currentTile = grid.getTileFromCoordinates(yLoc, xLoc);
        player.setX(x);
        player.setY(y);

        yLoc = player.getY();
        xLoc = player.getX();

        currentTile.getGameObjects().remove(player);
        grid.getTileFromCoordinates(y, x).addGameObject(player);
    }

    public void rotateClockwise() {
        if (player.getSprite() != null)
            player.getSprite().rotate(-90);

        switch (player.currentDirection) {
            case North:
                player.currentDirection = RoboGame.Direction.East;
                break;
            case East:
                player.currentDirection = RoboGame.Direction.South;
                break;
            case South:
                player.currentDirection = RoboGame.Direction.West;
                break;
            case West:
                player.currentDirection = RoboGame.Direction.North;
        }
    }


    public void rotateCounterClockwise() {
        if (player.getSprite() != null)
            player.getSprite().rotate(90);

        switch (player.currentDirection) {
            case North:
                player.currentDirection = RoboGame.Direction.West;
                break;
            case East:
                player.currentDirection = RoboGame.Direction.North;
                break;
            case South:
                player.currentDirection = RoboGame.Direction.East;
                break;
            case West:
                player.currentDirection = RoboGame.Direction.South;
        }
    }

    public void uTurn() {
        player.getSprite().rotate(180);

        switch (player.currentDirection) {
            case North:
                player.currentDirection = RoboGame.Direction.South;
                break;
            case East:
                player.currentDirection = RoboGame.Direction.West;
                break;
            case South:
                player.currentDirection = RoboGame.Direction.North;
                break;
            case West:
                player.currentDirection = RoboGame.Direction.East;
        }
    }
}
