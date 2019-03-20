package inf112.skeleton.app.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.RoboGame;

public class Savestate extends Sprite {
    RoboGame roboGame;

    public Savestate(RoboGame roboGame){
        super(new Texture(Gdx.files.internal("sprites/clockface.png")));
        this.roboGame = roboGame;

        TiledMap map = roboGame.tiledMap;
        MapLayer layer = map.getLayers().get("Savestate");
    }


    public void handleCollision(Player player){

    }
}
