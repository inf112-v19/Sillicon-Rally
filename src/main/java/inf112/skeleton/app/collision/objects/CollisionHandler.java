package inf112.skeleton.app.collision.objects;

import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.demo.Savestate;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

import java.util.List;

public class CollisionHandler {
    Player player;
    TileGrid grid;

    public CollisionHandler(TileGrid grid, Player player) {
        this.player = player;
        this.grid = grid;
    }

    public void checkCollision() {
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());
        List spritesOnTile = playerTile.getGameObjects();
        System.out.println(spritesOnTile);
        for (int i = 0; i < spritesOnTile.size(); i++) {
            if (spritesOnTile.get(i).equals(player))
                continue;
            if (spritesOnTile.get(i) instanceof TeleportObstacle) {
                ((TeleportObstacle) spritesOnTile.get(i)).handleTeleportCollision(player, grid);
            }
            if(spritesOnTile.get(i) instanceof Savestate){
                ((Savestate) spritesOnTile.get(i)).handleCollision(player);
            }
            if (spritesOnTile.get(i) instanceof  FlagObject) {
                ((FlagObject) spritesOnTile.get(i)).handleCollision(player, grid);
            }
            if (spritesOnTile.get(i) instanceof ConveyorObjectWest){
                ((ConveyorObjectWest) spritesOnTile.get(i)).handleCollision(player, grid);
            }
            if (spritesOnTile.get(i) instanceof ConveyorBeltObject){
                ((ConveyorBeltObject) spritesOnTile.get(i)).handleCollision(player, grid);
            }
            if (spritesOnTile.get(i) instanceof ConveyorObjectNorth){
                ((ConveyorObjectNorth) spritesOnTile.get(i)).handleCollision(player, grid);
            }
            if (spritesOnTile.get(i) instanceof ConveyorObjectSouth){
                ((ConveyorObjectSouth) spritesOnTile.get(i)).handleCollision(player, grid);
            }
        }

    }
}
