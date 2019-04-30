package inf112.skeleton.app.collision.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import inf112.skeleton.app.Objects.IGameObject;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.Tile;
import inf112.skeleton.app.grid.TileGrid;

public class FlagObject implements IGameObject {
    Sprite sprite;
    public int yLocation;
    public int xLocation;
    Tile flagTile;
    public int flagNumber;
    public int maxFlags= 4;

    public FlagObject(RectangleMapObject flagFromTiled, TileGrid grid) {
        yLocation = (int) flagFromTiled.getRectangle().getY();
        xLocation = (int) flagFromTiled.getRectangle().getX();
        Texture texture = new Texture("sprites/flag2.png");
        sprite = new Sprite(texture);
        sprite.setSize(100,100);

        flagTile = grid.getTileFromCoordinates(yLocation, xLocation);
        flagTile.addGameObject(this);

        flagNumber =(int)flagFromTiled.getProperties().get("nr");
    }

    public FlagObject(int y, int x, TileGrid grid){
        this.yLocation = y;
        this.xLocation = x;
        sprite = null;
        flagTile = grid.getTileFromCoordinates(yLocation, xLocation);
        flagTile.addGameObject(this);

    }

    public void handleCollision(Player player, TileGrid grid) {
        Tile playerTile = grid.getTileFromCoordinates(player.getY(), player.getX());


        if (flagTile.equals(playerTile) && (player.flagNr== flagNumber)) {
            player.setBackupLocation(flagTile);
            player.flagNr++;
        }
        System.out.println("this is flagNumber: " + flagNumber);
        System.out.println(presentNextFlag(player)); //TODO victory screen
    }

    public String presentNextFlag(Player player){
        if (player.flagNr>maxFlags){
            player.collectedAllFlags = true;
            player.setPlayerOutOfGame();
            return "you win";
        }
        else{
            return "player needs flagNumber nr: " + player.flagNr;
        }
    }




    @Override
    public Sprite getSprite() {
        sprite.setY(yLocation);
        sprite.setX(xLocation);

        return sprite;
    }


    public void removeFlagFromMap(TileGrid grid) {
        this.sprite = null;

        Tile myTile = grid.getTileFromCoordinates(this.yLocation, this.xLocation);
        myTile.getGameObjects().remove(this);
    }

    public Tile getTile() {
        return this.flagTile;
    }
}
