package inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.collision.objects.CollisionHandler;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class Player implements IGameObject {
    public MoveCard[] cardsToBePlayed;
    public RoboGame.Direction currentDirection;
    Tile backupLocation;
    Sprite sprite;
    float x;
    float y;
    public int maxHP = 6;
    private int currentHP = maxHP;

    //Constructor used for testing purposes only
    public Player() {
        this.currentDirection = RoboGame.Direction.West;
        backupLocation = null;
        this.cardsToBePlayed = new MoveCard[5];
    }

    public Player(Texture texture, RoboGame.Direction startDirection) {
        this.sprite = new Sprite(texture);
        this.currentDirection = startDirection;
        this.backupLocation = null;
    }

    public void moveStraight(int steps, int moveDistance, TileGrid grid) {
        for (int i = 0; i < steps; i++) {
            moveStraight(moveDistance, grid);
        }
    //    checkCollision(grid);
    }

    public void checkCollision(TileGrid grid) {
        CollisionHandler collisionHandler = new CollisionHandler(grid, this);
        collisionHandler.checkCollision();
    }

    public void takeDmg(int amount, TileGrid grid){
        currentHP-=amount;
        if(currentHP <= 0){
            handleDeath(grid);
        }
        System.out.println("current hp:" + currentHP);
    }
    public void healDmg(int amount){
        currentHP+=amount;
        if(currentHP > maxHP){
            currentHP=maxHP;
        }
        System.out.println("current hp:" + currentHP);
    }


    private void handleDeath(TileGrid grid) {
        if (backupLocation != null) {
            resetToBackupLocation(grid);
            deleteBackupLocation();
        }
        System.out.println("You died");
        currentHP=maxHP;
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

    public void rotateClockwise() {
        if (sprite != null)
            sprite.rotate(-90);

        switch (currentDirection) {
            case North:
                currentDirection = RoboGame.Direction.East;
                break;
            case East:
                currentDirection = RoboGame.Direction.South;
                break;
            case South:
                currentDirection = RoboGame.Direction.West;
                break;
            case West:
                currentDirection = RoboGame.Direction.North;
        }

    }

    public void rotateCounterClockwise() {
        if (sprite != null)
            sprite.rotate(90);

        switch (currentDirection) {
            case North:
                currentDirection = RoboGame.Direction.West;
                break;
            case East:
                currentDirection = RoboGame.Direction.North;
                break;
            case South:
                currentDirection = RoboGame.Direction.East;
                break;
            case West:
                currentDirection = RoboGame.Direction.South;
        }

    }



    public void uTurn() {
        sprite.rotate(180);

        switch (currentDirection) {
            case North:
                currentDirection = RoboGame.Direction.South;
                break;
            case East:
                currentDirection = RoboGame.Direction.West;
                break;
            case South:
                currentDirection = RoboGame.Direction.North;
                break;
            case West:
                currentDirection = RoboGame.Direction.East;
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

    @Override
    public void handleCollision(Player player, TileGrid grid) {

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
       // checkCollision(grid);
    }

    public void emptyList() {
        for (int i = 0; i < cardsToBePlayed.length; i++) {
            cardsToBePlayed[i] = null;
        }
    }

    public void addToList(int index, MoveCard card) {
        cardsToBePlayed[index] = card;
    }

    public boolean listIsFull() {
        for (int i = 0; i < cardsToBePlayed.length; i++) {
            if (cardsToBePlayed[i] == null) {
                return false;
            }
        }
        return true;
    }

}


