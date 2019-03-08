package inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.collision.objects.CollisionHandler;
import inf112.skeleton.app.game.Game;
import inf112.skeleton.app.grid.Tile;
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
        this.backupLocation = null;
    }

    public void moveStraight(int steps, int moveDistance, TileGrid grid) {
        for (int i = 0; i < steps; i++) {
            moveStraight(moveDistance, grid);
        }
        checkCollision(grid);
    }

    private void checkCollision(TileGrid grid) {
        CollisionHandler collisionHandler = new CollisionHandler(grid, this);
        collisionHandler.checkCollision();
    }

    private void handleDeath(TileGrid grid) {
        if (backupLocation != null) {
            resetToBackupLocation(grid);
            deleteBackupLocation();
        }
    }

    private void moveStraight(int moveDistance, TileGrid grid) {

        switch (currentDirection) {
            case North:
                this.setPosition((int) getY()+moveDistance, (int) getX(), grid);
                break;
            case East:
                this.setPosition((int) getY(), (int) getX() + moveDistance, grid);
                break;
            case South:
                this.setPosition((int) getY()- moveDistance, (int) getX(), grid);
                break;
            case West:
                this.setPosition((int) getY(), (int) getX() - moveDistance, grid);
                break;
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


    public void resetToBackupLocation(TileGrid grid) {
        int tileSizeInPx = grid.tileSizeInPx;
        if (this.backupLocation != null) {
            setPosition(backupLocation.y * tileSizeInPx, backupLocation.x *tileSizeInPx, grid);
            System.out.println(grid.getTileFromCoordinates(this.getY(), this.getX()));
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

    public boolean checkIfMoveIsOutOfBounds(int y, int x, TileGrid grid) {
        if (y < 0 || x < 0)
            return true;

        int yTile = y /grid.tileSizeInPx;
        int xTile = x/grid.tileSizeInPx;
        if (yTile >= grid.rows || xTile >= grid.columns)
            return true;

        return false;
    }


    public void setPosition(int y, int x, TileGrid grid) {
        if (checkIfMoveIsOutOfBounds(y, x, grid)) {
            handleDeath(grid);
            return;
        }

        Tile currentTile = grid.getTileFromCoordinates(getY(), getX());
        setX(x);
        setY(y);

        currentTile.getGameObjects().remove(this);
        grid.getTileFromCoordinates(y, x).addGameObject(this);
        checkCollision(grid);
    }

}


