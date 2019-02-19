package group1.team2.src.main.java.inf112.skeleton.app.Objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.demo.Game;

public class Player extends Sprite {
    Game.Direction currentDirection;

    public Player(Texture texture, Game.Direction startDirection) {
        super(texture);
        this.currentDirection = startDirection;
    }

    public void moveForward(int steps, int moveDistance) {
        System.out.println(currentDirection);
        for (int i = 0; i < steps; i++) {
            moveForward(moveDistance);
        }
    }

    private void moveForward(int moveDistance) {
        if (checkForNegativeCoordiantes(moveDistance)) {
            return;
        }
        switch (currentDirection) {
            case North: this.setY(this.getY() + moveDistance);
            break;
            case East: this.setX(this.getX() + moveDistance);
            break;
            case South: this.setY(this.getY() - moveDistance);
            break;
            case West: this.setX(this.getX() - moveDistance);
            break;
        }
    }

    private boolean checkForNegativeCoordiantes(int moveDistance) {
        switch (currentDirection) {
            case West: return (this.getX() - moveDistance) < 0;
            case South: return (this.getY() - moveDistance) < 0;
        }

        return false;
    }

    public void turnRight(int moveDistance) {
            switch (currentDirection) {
                case North:
                    this.rotate(180);
                case South:
                    this.rotate(-90);
                case West:
                    this.rotate(180);

            currentDirection = Game.Direction.East;
        }
    }

    public void turnLeft(int moveDistance) {
                switch (currentDirection) {
                    case South:
                        this.rotate(180);
                    case North:
                        this.rotate(-90);
                    case East:
                        this.rotate(180);
                }
                currentDirection = Game.Direction.West;

    }

    public void turnUp(int moveDistance) {
                switch (currentDirection) {
                    case West: this.rotate(180);
                    case East: this.rotate(-90);
                    case South: this.rotate(180);
                }
                currentDirection = Game.Direction.North;
    }

    public void turnDown(int moveDistance) {

            switch (currentDirection) {
                case East: this.rotate(180);
                case West: this.rotate(-90);
                case North: this.rotate(180);
            }
            currentDirection = Game.Direction.South;

    }

    public void uTurn() {
        switch (currentDirection) {
            case East:
                this.rotate(180);
                this.currentDirection = Game.Direction.West;
                break;
            case West:
                this.rotate(180);
                this.currentDirection = Game.Direction.East;
                break;
            case North:
                this.rotate(180);
                this.currentDirection = Game.Direction.South;
                break;
            case South:
                this.rotate(180);
                this.currentDirection = Game.Direction.North;
                break;
        }
    }



}
