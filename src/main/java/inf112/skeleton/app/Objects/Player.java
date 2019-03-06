package inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.grid.TileGrid;

public class Player implements IGameObject {
    Game.Direction currentDirection;
    Tile backupLocation;
    Sprite sprite;
    float x;
    float y;

    //Constructor used for testing purposes only
    public Player() {
        this.currentDirection = Game.Direction.West;
        backupLocation = null;
    }

    public Player(Texture texture, Game.Direction startDirection) {
        this.sprite = new Sprite(texture);
        this.currentDirection = startDirection;
        backupLocation = null;
    }

    public void moveForward(int steps, int moveDistance, Game game, Tile currentTile) {
        for (int i = 0; i < steps; i++) {
            moveForward(moveDistance, game);
            game.updatePlayerPositionInGrid(currentTile);
            currentTile = game.grid.getTileFromCoordinates(this.getY(), this.getX());
        }
        game.checkCollision();
    }

    private void moveForward(int moveDistance, Game game) {
        if (chekForOutOfMapMove(moveDistance, game)) {
            handleDeath(game);
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


    private boolean chekForOutOfMapMove(int moveDistance, Game game) {
        switch (currentDirection) {
            case West:
                return (this.getX() - moveDistance) < 0;
            case South:
                return (this.getY() - moveDistance) < 0;
        }

        float newXLocation = this.getX() + moveDistance;
        float newYLocation = this.getY() + moveDistance;

        try {
            game.grid.getTileFromCoordinates(newYLocation, newXLocation);
        } catch (IllegalArgumentException e) {
            return true;
        }

        return false;
    }

    private void handleDeath(Game game) {
        if (backupLocation != null) {
            resetToBackupLocation(game);
            deleteBackupLocation();
        }
    }

    public void turnRight() {
        sprite.rotate(-90);

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
        sprite.rotate(90);

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
        sprite.rotate(180);

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
        System.out.println(backupLocation);
    }

    public void deleteBackupLocation() {
        this.backupLocation = null;
    }

    public void setCurrentDirection(Game.Direction currentDirection) {
        this.currentDirection = currentDirection;
    }


    public void resetToBackupLocation(Game game) {
        int tilesizeInPx = game.getTileSize();
        if (this.backupLocation != null) {
            setPosition(backupLocation.y * tilesizeInPx, backupLocation.x *tilesizeInPx, game.grid);
        }
    }

    @Override
    public Sprite getSprite() {
        sprite.setX(this.x);
        sprite.setY(this.y);
        return this.sprite;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getY() {
        return this.y;
    }

    public float getX() {
        return this.x;
    }


    public void setPosition(int y, int x, TileGrid grid) {
        Tile currentTile = grid.getTileFromCoordinates(getY(), getX());
        setX(x);
        setY(y);

        currentTile.getGameObjects().remove(this);
        grid.getTileFromCoordinates(y, x).addGameObject(this);
    }

}


