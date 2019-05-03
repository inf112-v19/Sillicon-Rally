package inf112.skeleton.app.DfsTest;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.AIPlayer;
import inf112.skeleton.app.Objects.Dfs;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Objects.Position;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.RoboGame;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DfsTest {
    SetupVariables setup;
    AIPlayer Ai;

    void setup() {
        setup = new SetupVariables();
        Texture playerTexture = new Texture(("RobotSprites/robot" +1+".png"));
        AIPlayer player = new AIPlayer(playerTexture, RoboGame.Direction.North, setup.game, ("AI"));
        Ai = player;
    }

    Position getPlayerPosition(Player player) {
        return new Position(player.getY(), player.getX(), player.getDirection());
    }

    @Test
    void onlyOneCorrectCardTest() {
        setup();
        Texture textureCard = new Texture("cardLayouts/Move1.png");
        MoveCard reverse = new MoveCard(MoveCard.Type.reverse, textureCard);
        MoveCard forward = new MoveCard(MoveCard.Type.move1, textureCard);

        MoveCard[] listOfNine = {reverse, reverse, reverse, reverse, reverse, reverse, reverse, reverse, forward};
        Dfs dfs = new Dfs(setup.grid, 1,getPlayerPosition(Ai),listOfNine);
        dfs.dfs();

        List<Integer> list = dfs.getSelectedCards();
        assertEquals(new Integer(8), ((LinkedList<Integer>) list).pop());
    }

    @Test
    void onlyTwoCorrectCardTest() {
        setup();
        Ai.setDirection(RoboGame.Direction.South);
        Texture textureCard = new Texture("cardLayouts/Move1.png");
        MoveCard moveThree = new MoveCard(MoveCard.Type.move3, textureCard);
        MoveCard reverse = new MoveCard(MoveCard.Type.reverse, textureCard);

        MoveCard[] listOfNine = {reverse, moveThree, moveThree, moveThree, moveThree, moveThree, moveThree, moveThree, reverse};
        Dfs dfs = new Dfs(setup.grid, 2, getPlayerPosition(Ai), listOfNine);
        dfs.dfs();

        LinkedList<Integer> list = dfs.getSelectedCards();
        int a = list.pop();
        int b = list.pop();

        if (a == 0 || a == 8) {
            if ((b == 0 || b == 8) && a != b) {
                assert true;
            }
        } else {
            assert false;
        }
    }
}
