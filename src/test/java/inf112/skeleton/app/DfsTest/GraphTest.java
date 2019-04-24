package inf112.skeleton.app.DfsTest;

import inf112.skeleton.app.Objects.Graph;
import inf112.skeleton.app.SetupVariables.SetupVariables;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
}
