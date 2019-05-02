package inf112.skeleton.app.Screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.collision.objects.GameObjectFactory;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.game.RoundExecutor;


public class PlayerStatus {
    Player player;
    RoboGame game;
    float xLocation;
    float yLocation;
    Sprite heartSprite;
    private Sprite TwoLifeSprite;
    private Sprite ThreeLifeSprite;
    private Sprite OneLifeSprite;
    private Sprite NoLifeSprite;
    private BitmapFont font;

    public PlayerStatus(Player player, Point point, RoboGame game) {
        this.player = player;
        this.game = game;
        heartSprite = new Sprite(new Texture("LifeSprites/heart.png"));
        font = new BitmapFont();

        xLocation = point.x;
        yLocation = point.y;

        createSprites();
    }


    public void draw(SpriteBatch sb) {
        drawHearts(sb);
        drawLifeTokens(sb);
        drawNextFlagToPickUp(sb);
        drawPlayerName(sb);
        drawCurrentPlayer(sb);
        if (player.collectedAllFlags == true){
            drawPlayerOutOfGrid(sb);
        }
    }

    private void drawPlayerName(SpriteBatch sb) {
        font.getData().setScale(3);
        font.setColor(new Color(Color.YELLOW));
        String name = player.name;

        font.draw(sb, name, xLocation + 200, yLocation + 180);

    }

    public void drawCurrentPlayer(SpriteBatch sb) {
            if (player == game.playerList.get(RoundExecutor.playersTurn)) {
                font.getData().setScale(3);
                font.setColor(new Color(Color.GREEN));
                String str = "Current player";

                font.draw(sb, str, xLocation, yLocation);
            }
    }



    public void drawPlayerOutOfGrid(SpriteBatch spriteBatch){
        spriteBatch.draw(GameObjectFactory.playerTexture, xLocation+350, yLocation+400);
    }

    private void drawHearts(SpriteBatch sb) {
        int HeartHeight = 80;
        int HeartWidth = 65;

        float currentHeartPosX = xLocation;
        float heartPosY = yLocation;

        for (int i = 0; i < player.playerHP; i++) {
            sb.draw(heartSprite, currentHeartPosX, heartPosY, HeartWidth, HeartHeight);
            currentHeartPosX += 60;
        }
    }



    private void drawNextFlagToPickUp(SpriteBatch sb) {
        font.getData().setScale(3);
        font.setColor(new Color(999));


        String playerFlags = "Next flagNumber: " + (player.flagNr);
        if (player.flagNr == 5) {
            playerFlags = "Winner!!!";
        }
        font.draw(sb, playerFlags, xLocation, yLocation - 20);
    }



    private void drawLifeTokens(SpriteBatch sb) {
        float xDrawLocation = xLocation-20;
        float yDrawLocation = yLocation + 50;
        int width = 200;
        int height = 200;

        if (player.playerTokens == 2) {
            sb.draw(TwoLifeSprite, xDrawLocation, yDrawLocation, width, height);
        } else if (player.playerTokens == 1) {
            sb.draw(OneLifeSprite, xDrawLocation, yDrawLocation, width, height);

        } else if (player.playerTokens == 0) {
            sb.draw(NoLifeSprite, xDrawLocation, yDrawLocation, width, height);
        } else
            sb.draw(ThreeLifeSprite, xDrawLocation, yDrawLocation, width, height);
    }

    public void createSprites() {
        ThreeLifeSprite = new Sprite(new Texture("LifeSprites/exmpl3Life.png"));
        TwoLifeSprite = new Sprite(new Texture("LifeSprites/exmpl2Life.png"));
        OneLifeSprite = new Sprite(new Texture("LifeSprites/exmpl1Life.png"));
        NoLifeSprite = new Sprite(new Texture("LifeSprites/exmpl0Life.png"));
        heartSprite = new Sprite(new Texture("LifeSprites/heart.png"));
    }

}
