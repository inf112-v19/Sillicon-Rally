package inf112.skeleton.app.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class PlayerMovements {
    Player player;
    public float y;
    public float x;
    RoboGame.Direction direction ;
    float targetY;
    float targetX;
    float speed;

    public PlayerMovements(Player player, float y, float x, RoboGame.Direction direction) {
        this.player = player;
        this.y = y;
        this.x = x;
        this.direction = direction;
        this.speed = 0;
    }

    public void setY(float y){this.y = y;}
    public void setX(float x) {this.x = x;}

    public float getY() {return this.y;}
    public float getX() {return this.x;}

    public RoboGame.Direction getDirection() {return this.direction;}


    public boolean moveStraight(int steps, int moveDistance, TileGrid grid) {
        for (int i = 0; i < steps; i++) {
            if (!moveStraight(moveDistance, grid))
                return false;
            player.checkForDamageTaken(grid);
        }
        return true;
    }


    private boolean moveStraight(int moveDistance, TileGrid grid) {
        switch (direction) {
            case North:
                return setPosition((int) this.y + moveDistance, (int) this.x, grid);
            case East:
                return setPosition((int) this.y, (int) this.x + moveDistance, grid);
            case South:
                return setPosition((int) this.y - moveDistance, (int) this.x, grid);
            case West:
                return setPosition((int) this.y, (int) this.x - moveDistance, grid);

        }
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


    public boolean setPosition(float yDest, float xDest, TileGrid grid) {
        if (checkIfMoveIsOutOfBounds(yDest, xDest, grid)) {
            player.handleDeath(grid);
            return false;
        }

        Tile currentTile = grid.getTileFromCoordinates(this.y, this.x);

        setX(xDest);
        setY(yDest);

        currentTile.getGameObjects().remove(player);
        grid.getTileFromCoordinates(yDest, xDest).addGameObject(player);
        return true;
    }

    public void rotateClockwise(TileGrid grid) {
        //player.checkForDamageTaken(grid);
        if (player.getSprite() != null)
            player.getSprite().rotate(-90);

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


    public void rotateCounterClockwise(TileGrid grid) {
        if (player.getSprite() != null)
            player.getSprite().rotate(90);
        //player.checkForDamageTaken(grid);


        switch (getDirection()) {
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

    public void uTurn(TileGrid grid) {
        if (player.getSprite() != null)
            player.getSprite().rotate(180);
        //player.checkForDamageTaken(grid);

        switch (getDirection()) {
            case North:
                setDirection(RoboGame.Direction.South);
                break;
            case East:
                setDirection(RoboGame.Direction.West);
                break;
            case South:
                setDirection(RoboGame.Direction.North);
                break;
            case West:
                setDirection(RoboGame.Direction.East);
        }
    }



    public void isKeyPressed(float deltaTime, RoboGame game) {

    }




    public void update(float deltaTime, TileGrid grid) {

    }

    public void setDirection(RoboGame.Direction dir) {
        this.direction = dir;
    }

}
