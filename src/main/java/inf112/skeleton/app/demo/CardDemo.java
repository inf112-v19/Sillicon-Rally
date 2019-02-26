package inf112.skeleton.app.demo;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.card.StackOfCards;

/**
 * Created by Martin on 06/02/2019.
 */
public class CardDemo implements ApplicationListener {

    private SpriteBatch batch;
    private StackOfCards deck;


    @Override
    public void create() {
        deck = new StackOfCards();
        MoveCard kort1 = deck.nextCard();
        MoveCard kort2 = deck.nextCard();
        MoveCard kort3 = deck.nextCard();
        MoveCard kort4 = deck.nextCard();


        kort1.setSize(100, 200);
        kort1.setPosition(1,1);

        kort2.setSize(50, 100);
        kort2.setPosition(500,500);


    }


    @Override
    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


        batch.begin();


        batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
