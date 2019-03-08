package inf112.skeleton.app.map;

import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/*
* An abstraction over a TiledMap, with some methods that makes it easier to get
* layers from the map.
 */
public class GameMap  {
    TiledMap tiledMap;

    public GameMap(String fileName) {
        this.tiledMap = new TmxMapLoader().load(fileName);
    }

    public MapLayer getMapLayerByName(String layerName) {
        MapLayer layer = tiledMap.getLayers().get(layerName);
        return layer;
    }

    public MapLayer getMapLayerByIndex(int i) {
        MapLayer layer = tiledMap.getLayers().get(i);
        return layer;
    }

    public TiledMap getTiledMap() {
        return this.tiledMap;
    }

    public int getTileSize() {
        TiledMapTileLayer layer = (TiledMapTileLayer) getMapLayerByIndex(0);
        return (int) layer.getTileWidth();
    }
}
