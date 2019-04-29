package inf112.skeleton.app.DfsTest;

import com.badlogic.gdx.graphics.Texture;
import inf112.skeleton.app.Objects.Dfs;
import inf112.skeleton.app.Objects.Graph;
import inf112.skeleton.app.Objects.Player;
import inf112.skeleton.app.Objects.Position;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.PlayerMovements;
import inf112.skeleton.app.game.RoboGame;
import org.junit.jupiter.api.Test;
import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;

import static org.testng.Assert.assertEquals;

public class GraphTest {
    SetupVariables setup = new SetupVariables();

    @Test
    public void testAdj() {
        int[] testArray = new int[9];
        for (int i = 0; i < testArray.length; i++) {
            testArray[i] = i;
        }

        Graph graph = new Graph(testArray);
        ArrayList[] adj = graph.getAdj();

        for (int i = 0; i < adj.length; i++) {
            int indexToCheck = 0;
            for (int j = 0; j < adj[i].size(); j++) {
                if (i == j)
                    continue;

                assertEquals(j, adj[i].get(indexToCheck));
                indexToCheck++;
            }
            indexToCheck = 0;
        }
    }

    @Test
    public void isIllegalMoveTest() {
        Position position = new Position(2,0, RoboGame.Direction.South);
        Texture textureCard = new Texture("cardLayouts/Move1.png");
        MoveCard moveCard = new MoveCard(MoveCard.Type.move3, textureCard);
        PlayerMovements movements = new PlayerMovements(new Player(),position.y*setup.grid.tileSizeInPx,
                position.x*setup.grid.tileSizeInPx, position.dir);

        boolean legalMove = position.movePosition(moveCard.getType(), setup.grid);
        System.out.println(position);
        assertEquals(false, legalMove);

    }

    @Test
    public void DfsTest() {
        Texture textureCard = new Texture("cardLayouts/Move1.png");
        Position position = new Position(2,0, RoboGame.Direction.North);

        MoveCard[] arr ={
            new MoveCard(MoveCard.Type.reverse, textureCard),
            new MoveCard(MoveCard.Type.reverse, textureCard),
            new MoveCard(MoveCard.Type.reverse, textureCard),
            new MoveCard(MoveCard.Type.turnright, textureCard),
            new MoveCard(MoveCard.Type.turnright, textureCard),
            new MoveCard(MoveCard.Type.turnright, textureCard),
            new MoveCard(MoveCard.Type.turnright, textureCard),
            new MoveCard(MoveCard.Type.turnright, textureCard),
            new MoveCard(MoveCard.Type.turnright, textureCard)
        };


        Dfs dfs = new Dfs(setup.grid, 5, position, arr);
        dfs.dfs();
        LinkedList<Integer> queue = dfs.getSelectedCards();

        assertEquals( arr[queue.poll()].getType() == MoveCard.Type.reverse, false);
    }


    @Test
    public void queueTest() {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);

        assertEquals(new Integer(1), queue.poll());
    }
}
