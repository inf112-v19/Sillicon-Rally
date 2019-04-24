package inf112.skeleton.app.game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class CustomCamera extends OrthographicCamera {
    public float pixelWidth;
    public float pixelHeight;

    public CustomCamera(TiledMap tiledMap) {
        super();
        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        int x = (int) layer.getTileWidth();
        int y = (int)layer.getTileHeight();

        int heightNumberOfTiles = layer.getHeight();
        int widthNumberOfTiles = layer.getWidth();

        this.pixelHeight = heightNumberOfTiles * y ;
        this.pixelWidth = widthNumberOfTiles * x;


        this.setToOrtho(false, pixelWidth*2, pixelHeight*2);
        this.update();

    }
}
