package inf112.skeleton.app.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import inf112.skeleton.app.Objects.LaserAnimation;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.DrawCards;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.game.RoundExecutor;
import inf112.skeleton.app.grid.TileGrid;
import inf112.skeleton.app.map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class GameScreen implements Screen {
    //Sprite related
    Texture HeartSprite;
    Texture ThreeLifeSprite;
    Texture TwoLifeSprite;
    Texture OneLifeSprite;
    Texture NoLifeSprite;
    BitmapFont font;
    Point[] statusScreenPoints;
    LaserAnimation laserAnimation;
    int laserTimer;


    //Function related
    private RoboGame game;
    private DrawCards drawCards;
    private List<PlayerStatus> playerStatusList;
    private RoundExecutor roundExector;
    private int xLoc;
    private int yLoc;
    public GameMap gameMap;
    public TileGrid grid;
    PlayerStatus playerStatus;




    public GameScreen (RoboGame game){
        this.game = game;

        drawCards = new DrawCards(game);
        roundExector = new RoundExecutor(game.playerList, game);
        laserAnimation = new LaserAnimation();
        font = new BitmapFont();
        playerStatusList = new ArrayList<>();

        laserTimer = 0;
        statusScreenPoints = createSixPoints();


        for (int i = 0; i < game.playerList.size(); i++) {
            Player pl = game.playerList.get(i);
            Point point = statusScreenPoints[i];
            PlayerStatus status = new PlayerStatus(pl, point, game);
            playerStatusList.add(status);
        }
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
        for (PlayerStatus status : playerStatusList) {
            status.draw(game.sb);
        }


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

        if (laserTimer >= 50) {
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


        if (roundExector.isCurrentlyExecutingRound) {
            Gdx.input.setInputProcessor(null);
            roundExector.playPlayerNextCard();
        }
    }


    public Point[] createSixPoints() {
        Point[] arr = new Point[6];
        arr[0] = new Point(-400, 900);
        arr[1] = new Point(1200, 900);
        arr[2] = new Point(-400, 600);
        arr[3] = new Point(1200, 600);
        arr[4] = new Point(-400, 300);
        arr[5] = new Point(1200, 300);

        return arr;
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