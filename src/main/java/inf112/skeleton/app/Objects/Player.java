package inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.game.Game;

public class Player extends Sprite {
    Game.Direction currentDirection;
    Tile backupLocation;

    public Player(Texture texture, Game.Direction startDirection) {
        super(texture);
        this.currentDirection = startDirection;
        backupLocation = null;
    }

    public void moveForward(int steps, int moveDistance, Game game, Tile currentTile) {
        for (int i = 0; i < steps; i++) {
            moveForward(moveDistance);
            game.updatePlayerPositionInGrid(currentTile);
            currentTile = game.grid.getTileFromCoordinates(this.getY(), this.getX());
            game.checkCollision();

        }
    }

    private void moveForward(int moveDistance) {
        if (checkForNegativeCoordinates(moveDistance)) {
            return;
        }
        switch (currentDirection) {
            case North:
                this.setY(this.getY() + moveDistance);
                break;
            case East:
                this.setX(this.getX() + moveDistance);
                break;
            case South:
                this.setY(this.getY() - moveDistance);
                break;
            case West:
                this.setX(this.getX() - moveDistance);
                break;
        }
    }

    private void moveBackwards (int moveDistance) {
        switch (currentDirection) {
            case North: this.setY(this.getY() - moveDistance);
                break;
            case East: this.setX(this.getX() - moveDistance);
                break;
            case South: this.setY(this.getY() + moveDistance);
                break;
            case West: this.setX(this.getX() + moveDistance);
                break;
        }
    }


    private boolean checkForNegativeCoordinates(int moveDistance) {
        switch (currentDirection) {
            case West:
                return (this.getX() - moveDistance) < 0;
            case South:
                return (this.getY() - moveDistance) < 0;
        }

        return false;
    }

    public void turnRight() {
        this.rotate(-90);

        switch (currentDirection) {
            case North:
                currentDirection = Game.Direction.East;
                break;
            case East:
                currentDirection = Game.Direction.South;
                break;
            case South:
                currentDirection = Game.Direction.West;
                break;
            case West:
                currentDirection = Game.Direction.North;
        }

    }

    public void turnLeft() {
        this.rotate(90);

        switch (currentDirection) {
            case North:
                currentDirection = Game.Direction.West;
                break;
            case East:
                currentDirection = Game.Direction.North;
                break;
            case South:
                currentDirection = Game.Direction.East;
                break;
            case West:
                currentDirection = Game.Direction.South;
        }

    }


    public void uTurn() {
        this.rotate(180);

        switch (currentDirection) {
            case North:
                currentDirection = Game.Direction.South;
                break;
            case East:
                currentDirection = Game.Direction.West;
                break;
            case South:
                currentDirection = Game.Direction.North;
                break;
            case West:
                currentDirection = Game.Direction.East;
        }
    }

    public void setBackupLocation(Tile backupLocation) {
        this.backupLocation = backupLocation;
    }

    public void setCurrentDirection(Game.Direction currentDirection) {
        this.currentDirection = currentDirection;
    }


    public void resetToBackupLocation(int tilesizeInPx, Game game) {
        if (this.backupLocation != null) {
            Tile currentTile = game.grid.getTileFromCoordinates(this.getY(), this.getX());
            this.setY((float) (backupLocation.y * tilesizeInPx));
            this.setX((float) (backupLocation.x * tilesizeInPx));
            game.updatePlayerPositionInGrid(currentTile);
        }
    }

}


