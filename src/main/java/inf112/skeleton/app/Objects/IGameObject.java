package inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.grid.TileGrid;

public interface IGameObject {

    /*
    * Returns a sprite, null if no sprite is being held.
     */
    public Sprite getSprite();

    public void handleCollision(Player player, TileGrid grid);


}
