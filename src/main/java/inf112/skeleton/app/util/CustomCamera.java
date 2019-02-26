package inf112.skeleton.app.util;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class CustomCamera extends OrthographicCamera {

    public CustomCamera(TiledMap tiledMap) {
        super();
        TiledMapTileLayer layer = (TiledMapTileLayer)tiledMap.getLayers().get(0);
        int x = (int) layer.getTileWidth();
        int y = (int)layer.getTileHeight();

        int heightNumberOfTiles = layer.getHeight();
        int widthNumberOfTiles = layer.getWidth();

        float pixelHeight = heightNumberOfTiles * y + 128;
        float pixelWidth = widthNumberOfTiles * x + 128;


        this.setToOrtho(false, pixelWidth, pixelHeight);
        this.update();

    }
}
