package inf112.skeleton.app.Objects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.collision.objects.CollisionHandler;
import inf112.skeleton.app.game.PlayerMovements;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class Player implements IGameObject, InputProcessor {
    public MoveCard[] cardsToBePlayed;
    public RoboGame.Direction currentDirection;
    Tile backupLocation;
    Sprite sprite;
    float x;
    float y;
    public int playerHP ;
    public final int MAX_HP = 6;
    private RoboGame game;
    private PlayerMovements playerMovements;
    TileGrid grid;


    //Constructor used for testing purposes only
    public Player() {
        this.currentDirection = RoboGame.Direction.West;
        backupLocation = null;
        this.cardsToBePlayed = new MoveCard[5];
        playerMovements = new PlayerMovements(this);
        this.playerHP = MAX_HP;
    }

    public Player(Texture texture, RoboGame.Direction startDirection, RoboGame game) {
        this.sprite = new Sprite(texture);
        this.currentDirection = startDirection;
        this.backupLocation = null;
        this.game = game;
        playerMovements = new PlayerMovements(this);
        this.playerHP = MAX_HP;
        this.grid = game.grid;
    }


    public void checkCollision(TileGrid grid) {
        CollisionHandler collisionHandler = new CollisionHandler(grid, this);
        collisionHandler.checkCollision();
    }

    public void checkForDamageTaken(TileGrid grid) {
        CollisionHandler collisionHandler = new CollisionHandler(grid, this);
        collisionHandler.checkForDamageDealer();
    }



    public void handleDeath(TileGrid grid) {
        if (backupLocation != null) {
            resetToBackupLocation(grid);
            deleteBackupLocation();
        }
        System.out.println("You died");
        playerHP = MAX_HP;
    }

    public void moveStraight(int speed, int moveDistance, TileGrid grid) {
        playerMovements.moveStraight(speed, moveDistance, grid);
    }

    public void rotateClockwise(){playerMovements.rotateClockwise();}
    public void rotateCounterClockwise() {playerMovements.rotateCounterClockwise();}



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
            playerMovements.setPosition(backupLocation.y * tileSizeInPx, backupLocation.x *tileSizeInPx, grid);
            System.out.println(grid.getTileFromCoordinates(this.getY(), this.getX()));
        }
    }

    @Override
    public Sprite getSprite() {
        if (this.sprite == null)
            return null;

        sprite.setX(this.x);
        sprite.setY(this.y);
        return this.sprite;
    }

    public void damagePlayer(int damage, TileGrid grid) {
        this.playerHP -= damage;
        System.out.println("you took " + damage + " damage");

        if (playerHP <= 0)
            handleDeath(grid);
    }

    @Override
    public void handleCollision(Player player, TileGrid grid) { }

    public void setX(float x) { this.x = x; }

    public void setY(float y) { this.y = y; }

    public float getY() { return this.y; }

    public float getX() { return this.x; }

    public boolean checkIfMoveIsOutOfBounds(int y, int x, TileGrid grid) {
        return playerMovements.checkIfMoveIsOutOfBounds(y, x, grid);
    }


    public void setPosition(int y, int x, TileGrid grid) {
        playerMovements.setPosition(y, x, grid);
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

    @Override
    public boolean keyDown(int keycode) {
        int moveDistance = game.TILE_SIZE_IN_PX;

        if (keycode == Input.Keys.RIGHT) {
            playerMovements.rotateClockwise();
        }

        if (keycode == Input.Keys.LEFT) {
            playerMovements.rotateCounterClockwise();
        }

        if (keycode == Input.Keys.valueOf("1")) {
            int index = 0;
            if (game.list[index] != null) {
                game.list[index].translateY(75);
                //movePlayer(index, moveDistance, currentTile);
                //list[index] = null;
                game.booList[index] = true;
            }
        }

        if (keycode == Input.Keys.valueOf("2")) {
            int index = 1;
            if (game.list[index] != null) {
                game.list[index].translateY(75);
                //movePlayer(index, moveDistance, currentTile);
                //list[index] = null;
                game.booList[index] = true;
            }
        }

        if (keycode == Input.Keys.valueOf("3")) {
            int index = 2;
            if (game.list[index] != null) {
                game.list[index].translateY(75);
                //movePlayer(index, moveDistance, currentTile);
                //list[index] = null;
                game.booList[index] = true;
            }
        }

        if (keycode == Input.Keys.valueOf("4")) {
            int index = 3;
            if (game.list[index] != null) {
                game.list[index].translateY(75);
                //movePlayer(index, moveDistance, currentTile);
                //list[index] = null;
                game.booList[index] = true;
            }
        }

        if (keycode == Input.Keys.valueOf("5")) {
            int index = 4;
            if (game.list[index] != null) {
                game.list[index].translateY(75);
                //movePlayer(index, moveDistance, currentTile);
                //list[index] = null;
                game.booList[index] = true;
            }
        }

        if (keycode == Input.Keys.valueOf("6")) {
            int index = 5;
            game.list[index].translateY(75);
            game.booList[index] = true;
        }

        if (keycode == Input.Keys.valueOf("7")) {
            int index = 6;
            game.list[index].translateY(75);
            game.booList[index] = true;
        }

        if (keycode == Input.Keys.valueOf("8")) {
            int index = 7;
            game.list[index].translateY(75);
            game.booList[index] = true;
        }

        if (keycode == Input.Keys.valueOf("9")) {
            int index = 8;
            game.list[index].translateY(75);
            game.booList[index] = true;
        }

        if (keycode == Input.Keys.U) {
            playerMovements.uTurn();
        }
        if (keycode == Input.Keys.UP) {
            playerMovements.moveStraight(1, moveDistance, game.grid);
        }
        if (keycode == Input.Keys.W) {
            playerMovements.moveStraight(2, moveDistance, game.grid);
        }

        if (keycode == Input.Keys.DOWN) {
            playerMovements.moveStraight(1,moveDistance * (-1),game.grid);
        }

        if (keycode == Input.Keys.R) {
            game.drawNineCardsFromDeck();
        }

        checkCollision(game.grid);

        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }
}


