package inf112.skeleton.app.card;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.grid.TileGrid;

import java.util.Random;

/**
 * Created by Martin on 01/02/2019.
 */
public class MoveCard extends Sprite{

    Random rand = new Random();
    private Type type;
    private int priority;


    public MoveCard(Type t, Texture texture) {
        super(texture);
        this.type = t;
        switch (t) {
            case move1: priority = rand.nextInt(650) + 490;
            case move2: priority = rand.nextInt(780) + 670;
            case move3: priority = rand.nextInt(840) + 790;
            case turnleft: priority = rand.nextInt(410) + 70;
            case turnright: priority = rand.nextInt(420) + 80;
            case uturn: priority = rand.nextInt(60) + 10;
            case reverse: priority = rand.nextInt(480) + 430;
        }
    }

    public int getPriority() {
        return priority;
    }

    public Type getType() {
        return this.type;
    }

    public enum Type{
        move1, move2, move3, turnleft, turnright, uturn, reverse
    }

    public String toString() {
        return type + "";
    }


    /*
        For testing
     */

    /*public void handleTestCollision(Type t, Player player, TileGrid grid){
        int moveDistance = grid.tileSizeInPx;
        this.type = t;
        switch (t) {
            case move1: player.moveStraight(1, moveDistance, grid);
            case move2: player.moveStraight(2, moveDistance, grid);
            case move3: player.moveStraight(3, moveDistance, grid);
            case turnleft: player.rotateCounterClockwise();
            case turnright: player.rotateClockwise();
            case uturn: player.rotateClockwise();
            case reverse: priority = rand.nextInt(480) + 430;
        }
    }*/
}


