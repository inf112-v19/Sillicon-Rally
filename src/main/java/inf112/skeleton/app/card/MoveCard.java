package inf112.skeleton.app.card;

import java.util.Random;

/**
 * Created by Martin on 01/02/2019.
 */
public class MoveCard {

    Random rand = new Random();
    private Type type;
    private int priority;

    public MoveCard(Type t) {
        type = t;
        switch (t) {
            case move1: priority = rand.nextInt(650) + 490;
            case move2: priority = rand.nextInt(780) + 670;
            case move3: priority = rand.nextInt(840) + 790;
            case turnleft: priority = rand.nextInt(410) + 70;
            case turnright: priority = rand.nextInt(420) + 80;
            case uturn: priority = rand.nextInt(60) + 10;
            case backup: priority = rand.nextInt(480) + 430;
        }
    }

    public int getPriority() {
        return priority;
    }

    public Type getType() {
        return type;
    }

    public enum Type{
        move1, move2, move3, turnleft, turnright, uturn, backup
    }

    public String toString() {
        return "type: " + type + ", priority: " + priority;
    }
}


