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
    TileGrid grid;
    Tile backupLocation;
    Sprite sprite;
    private RoboGame game;
    private PlayerMovements playerMovements;

    public int playerHP;
    public int playerTokens;
    public final int MAX_HP = 6;
    public final int MAX_DAMAGE_TOKENS = 3;


    public MoveCard[] movecardArray;
    boolean[] booList;
    public String name;



    public void setName(String name) {
        this.name = name;
    }


    public Player(Texture texture, RoboGame.Direction startDirection, RoboGame game) {
        this.sprite = new Sprite(texture);
        this.backupLocation = null;
        this.game = game;
        playerMovements = new PlayerMovements(this, 0, 0, startDirection);
        this.playerHP = MAX_HP;
        this.grid = game.grid;
        this.playerTokens = MAX_DAMAGE_TOKENS;

        this.movecardArray = new MoveCard[5];
    }

    //Constructor used for testing purposes only
    public Player() {
        RoboGame.Direction direction = RoboGame.Direction.West;
        backupLocation = null;
        this.cardsToBePlayed = new MoveCard[5];
        playerMovements = new PlayerMovements(this, 0 ,0, direction);
        this.playerHP = MAX_HP;
        this.playerTokens = MAX_DAMAGE_TOKENS;
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
        playerTokens -= 1;
        playerHP = MAX_HP;
        System.out.println("Tokens:" + playerTokens + ", HP:" + playerHP);
    }

    public void moveStraight(int speed, int moveDistance, TileGrid grid) {
        playerMovements.moveStraight(speed, moveDistance, grid);
    }

    public void rotateClockwise(){playerMovements.rotateClockwise(grid);}

    public void rotateCounterClockwise() {playerMovements.rotateCounterClockwise(grid);}


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

        sprite.setX(getX());
        sprite.setY(getY());
        return this.sprite;
    }

    public void damagePlayer(int damage, TileGrid grid) {

        this.playerHP -= damage;
        System.out.println("you took " + damage + " damage. New HP: " + playerHP +
                " \n Player tokens: " + playerTokens + "Max damage Tokens" + MAX_DAMAGE_TOKENS);

        if (playerHP <= 0)
            handleDeath(grid);
            }

    @Override
    public void handleCollision(Player player, TileGrid grid) { }

    public void setX(float x) {

        playerMovements.setX(x);
    }

    public void setY(float y) {
        playerMovements.setY(y);
    }

    public float getY() { return playerMovements.getY(); }


    public float getX() { return playerMovements.getX(); }

    public RoboGame.Direction getDirection() {return playerMovements.getDirection();}

    public void setDirection(RoboGame.Direction dir) {
        playerMovements.setDirection(dir);
    }

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

    public void movePlayer(MoveCard.Type type, int tile_size_in_px, TileGrid grid) {

        switch (type) {
            case move1:
                float a = getY();
                playerMovements.moveStraight(1, tile_size_in_px, grid);
                System.out.println(getY());
                float b = getY();
                break;
            case move2:
                playerMovements.moveStraight(2, tile_size_in_px, grid);
                break;
            case move3:
                playerMovements.moveStraight(3, tile_size_in_px, grid);
                break;
            case reverse:
                playerMovements.moveStraight(1, tile_size_in_px*-1, grid);
                break;
            case uturn:
                playerMovements.uTurn(grid);
                break;
            case turnleft:
                playerMovements.rotateCounterClockwise(grid);
                break;
            case turnright:
                playerMovements.rotateClockwise(grid);
                break;

                default:
                    System.out.println("Invalid movement");
                    break;
        }
    }


    /*
>>>>>>> testAnimation
    @Override
    public boolean keyDown(int keycode) {
        int moveDistance = game.TILE_SIZE_IN_PX;

        if (keycode == Input.Keys.RIGHT) {
            playerMovements.rotateClockwise(game.grid);
            checkForDamageTaken(grid);
        }

        if (keycode == Input.Keys.LEFT) {
            playerMovements.rotateCounterClockwise(game.grid);
            checkForDamageTaken(grid);
        }

        if (keycode == Input.Keys.valueOf("1")) {
            int index = 0;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("2")) {
            int index = 1;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("3")) {
            int index = 2;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("4")) {
            int index = 3;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("5")) {
            int index = 4;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("6")) {
            int index = 5;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("7")) {
            int index = 6;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("8")) {
            int index = 7;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("9")) {
            int index = 8;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.U) {
            playerMovements.uTurn(game.grid);
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
            nextRound();
        }

        checkCollision(game.grid);

        return false;
    }*/

    @Override
    public boolean keyDown(int keycode) {
        int moveDistance = game.TILE_SIZE_IN_PX;

        if (keycode == Input.Keys.RIGHT) {
            playerMovements.rotateClockwise(game.grid);
            checkForDamageTaken(grid);
        }

        if (keycode == Input.Keys.LEFT) {
            playerMovements.rotateCounterClockwise(game.grid);
            checkForDamageTaken(grid);
        }

        if (keycode == Input.Keys.valueOf("1")) {
            int index = 0;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("2")) {
            int index = 1;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("3")) {
            int index = 2;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("4")) {
            int index = 3;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("5")) {
            int index = 4;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("6")) {
            int index = 5;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("7")) {
            int index = 6;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("8")) {
            int index = 7;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.valueOf("9")) {
            int index = 8;
            if (game.listOfNine[index] != null) {
                game.chooseCard(index);
            }
        }

        if (keycode == Input.Keys.U) {
            playerMovements.uTurn(game.grid);
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
            nextRound();
        }

        checkCollision(game.grid);

        return false;
    }


    public void nextRound() {
            game.putCardsBackInDeck();
            game.drawNineCardsFromDeck();
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

    public void handleInput(float deltaTime, RoboGame game) {
        playerMovements.isKeyPressed(deltaTime, game);

    }

    public void update(float deltaTime, TileGrid grid) {
        playerMovements.update(deltaTime, grid);
    }
}


