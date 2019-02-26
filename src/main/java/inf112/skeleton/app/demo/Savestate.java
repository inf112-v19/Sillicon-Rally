package inf112.skeleton.app.demo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.game.Game;

public class Savestate extends Sprite {
    Game game;

    public Savestate(Game game){
        super(new Texture(Gdx.files.internal("sprites/clockface.png")));
        this.game = game;

        TiledMap map = game.tiledMap;
        MapLayer layer = map.getLayers().get("Savestate");
    }


    public void handleCollision(Player player){

    }
}
