package group1.team2.src.main.java.inf112.skeleton.app.Objects;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import inf112.skeleton.app.demo.Game;

public class Player extends Sprite {
    Game.Direction currentDirection;

    public Player(Texture texture, Game.Direction startDirection) {
        super(texture);
        this.currentDirection = startDirection;
    }

    public void moveRight(int moveDistance) {
        if (currentDirection == Game.Direction.East) {
            this.setX(this.getX() + moveDistance);
        }
        else {
            switch (currentDirection) {
                case North:
                    this.rotate(180);
                case South:
                    this.rotate(-90);
                case West:
                    this.rotate(180);
            }
            currentDirection = Game.Direction.East;
        }
    }

    public void moveLeft(int moveDistance) {
            if (currentDirection == Game.Direction.West) {
                this.setX(this.getX() - moveDistance);
            } else {
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
    }

    public void moveUp(int moveDistance) {
            if (currentDirection == Game.Direction.North) {
                this.setY(this.getY() + moveDistance);
            } else {
                switch (currentDirection) {
                    case West: this.rotate(180);
                    case East: this.rotate(-90);
                    case South: this.rotate(180);
                }
                currentDirection = Game.Direction.North;
            }

    }

    public void moveDown(int moveDistance) {
        if (currentDirection == Game.Direction.South) {
            this.setY(this.getY() - moveDistance);
        } else {
            switch (currentDirection) {
                case East: this.rotate(180);
                case West: this.rotate(-90);
                case North: this.rotate(180);
            }
            currentDirection = Game.Direction.South;
        }
    }

}
