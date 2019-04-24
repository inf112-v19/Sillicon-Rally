package inf112.skeleton.app.Objects;

import inf112.skeleton.app.card.MoveCard;

import java.util.ArrayList;
import java.util.List;

public class Graph {
    ArrayList<Integer>[] adj;
    MoveCard[] listOfNine;
    int[] testList;

    public Graph(MoveCard[] listOfNine, int depth) {
        this.listOfNine = listOfNine;
        this.adj = new ArrayList[listOfNine.length];
        initialize();
        createGraph();
    }

    //For testing
    public Graph(int[] testList) {
        this.testList = testList;
        this.adj = new ArrayList[testList.length];
        initialize();
        createGraph(testList);
    }

    private void createGraph(int[] testList) {
        for (int i = 0; i < testList.length; i++) {
            for (int j = 0; j < testList.length; j++) {
                if (j == i)
                    continue;

                adj[i].add(j);
            }
        }
    }

    private void createGraph() {
        for (int i = 0; i < listOfNine.length; i++) {
            for (int j = 0; j < listOfNine.length; j++) {
                if (j == i)
                    continue;

                adj[i].add(j);
            }
        }
    }

    private void initialize() {
        for (int i = 0; i < adj.length; i++) {
            adj[i] = new ArrayList<>();
        }
    }

    public ArrayList[] getAdj() {
        return this.adj;
    }

}
