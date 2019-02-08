package inf112.skeleton.app.demo;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MoveableActor extends Sprite {

    @Override
    public void draw(Batch sb) {

    }

    @Override
    public void setX(float x) {
        if (x < 0)
            return;

        super.setX(x);
    }
}
