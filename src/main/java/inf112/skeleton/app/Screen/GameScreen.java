package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.Objects.LaserAnimation;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;
import inf112.skeleton.app.game.DrawCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.game.RoundExecutor;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    Texture HeartSprite;
    Texture ThreeLifeSprite;
    Texture TwoLifeSprite;
    Texture OneLifeSprite;
    Texture NoLifeSprite;
    private RoboGame game;
    public GameMap gameMap;
    public MainMenuScreen mainMenu;
    public int TILE_SIZE_IN_PX;
    public TiledMap tiledMap;
    public SpriteBatch sb;
    RoboGame.Direction startDirection;
    public TileGrid grid;
    public StackOfCards deck;
    private ArrayList<MoveCard> cardsOnBoard;
    Player player;
    private DrawCards drawCards;
    private RoundExecutor roundExector;
    LaserAnimation laserAnimation;
    int laserTimer;

    private static final int upTopX = 1000;
    private static final int upTopY = 700;
    private static final int WidthButton = 240;
    private static final int HeightButton = 100;
    private static final int HeartCordY = 600;
    private int HeartCordX = 1000;
    private static final int HeartHeight = 60;
    private static final int HeartWidth = 45;

    public GameScreen (RoboGame game, Player player){
        this.game = game;
        this.player = player;
        this.drawCards = new DrawCards(game);
        this.roundExector = new RoundExecutor(game.playerList);
        this.laserAnimation = new LaserAnimation();
        this.laserTimer = 0;
    }



    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.sb.begin();
        game.drawHUD();
        RoboGame.camera.update();
        RoboGame.tiledMapRenderer.setView(RoboGame.camera);
        RoboGame.tiledMapRenderer.render();

        game.sb.setProjectionMatrix(RoboGame.camera.combined);
        game.drawSpritesFromGrid();

        game.sb.begin();
        drawLifeTokens(game.playerList);
        drawHearts();
        game.sb.end();

        playRound();
        animateLaser();
    }

    private void animateLaser() {
        if (roundExector.shootLaserNow) {
            for (Player player : game.playerList) {
                player.shootLaser(game.grid);
                this.laserTimer++;
            }
            roundExector.shootLaserNow = false;
        }

        if (laserTimer >= 150) {
            for (Player player : game.playerList) {
                player.removeLaser();
            }
            laserTimer = 0;
        }

        if (laserTimer >= 0)
            laserTimer++;

    }

    private void playRound() {
        if (!roundExector.isCurrentlyExecutingRound)
            drawCards.drawCards();

        if (drawCards.allPlayersDone())
            roundExector.isCurrentlyExecutingRound = true;

        if (roundExector.isCurrentlyExecutingRound)
            roundExector.playPlayerNextCard();
    }


    private void drawLifeTokens(List<Player> players) {
        int xDrawLocation = upTopX;

        for (Player player : players) {
            if (player.playerTokens == 2) {
                game.sb.draw(TwoLifeSprite, xDrawLocation, upTopY, WidthButton, HeightButton);
            } else if (player.playerTokens == 1) {
                game.sb.draw(OneLifeSprite, xDrawLocation, upTopY, WidthButton, HeightButton);

            } else if (player.playerTokens == 0) {
                game.sb.draw(NoLifeSprite, xDrawLocation, upTopY, WidthButton, HeightButton);
            } else
                game.sb.draw(ThreeLifeSprite, xDrawLocation, upTopY, WidthButton, HeightButton);
        xDrawLocation = -400;
        }
    }

    public void drawHearts(){
        List<Player> playerList = game.playerList;
        int currentHeartPos = HeartCordX;

        for (Player player : playerList) {
            for (int i = 0; i < player.playerHP; i++) {
                game.sb.draw(HeartSprite, currentHeartPos, HeartCordY, HeartWidth, HeartHeight);
                currentHeartPos += 40;
            }
            currentHeartPos = -400;
        }

    }

    @Override
    public void show() {
        gameMap = new GameMap("map.v.01.tmx");
        ThreeLifeSprite = new Texture("LifeSprites/exmpl3Life.png");
        TwoLifeSprite = new Texture("LifeSprites/exmpl2Life.png");
        OneLifeSprite = new Texture("LifeSprites/exmpl1Life.png");
        NoLifeSprite = new Texture("LifeSprites/exmpl0Life.png");
        HeartSprite = new Texture("LifeSprites/heart.png");
    }


    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}