package inf112.skeleton.app.Objects;

import inf112.skeleton.app.card.MoveCard;
import inf112.skeleton.app.game.PlayerMovements;
import inf112.skeleton.app.game.RoboGame;
import inf112.skeleton.app.grid.TileGrid;

import java.util.Iterator;
import java.util.LinkedList;

public class Dfs {
    MoveCard[] listOfNine;
    boolean[] selected;
    LinkedList<Integer> queue;
    int cardsToChoose;
    RoboGame game;
    int selectedCards = 0;
    boolean foundAllCards;
    Position currentPosition;
    Position originalPosition;
    TileGrid grid;

    //Only created so we can use the checkIfMoveIsOutOfBounds method
    PlayerMovements movements;
    Graph graph;

    public Dfs(RoboGame game, int cardsToChoose, Position position) {
        this.game = game;
        this.grid = game.grid;
        this.listOfNine = game.listOfNine;
        selected = new boolean[listOfNine.length];
        this.cardsToChoose = cardsToChoose;
        this.queue = new LinkedList<>();
        this.currentPosition = position;
        this.originalPosition = position;
        this.movements = new PlayerMovements(new Player(),position.y, position.x, position.dir);
    }

    public void dfs() {
        this.graph = new Graph(listOfNine, cardsToChoose);

        for (int i = 0; i < listOfNine.length; i++) {
            if (foundAllCards)
                break;

            initialize();
            dfs(i);
        }
    }

    private void initialize() {
        for (int i = 0; i < selected.length; i++) {
            selected[i] = false;
        }
        selectedCards = 0;
        foundAllCards = false;
        currentPosition = originalPosition;
    }

    private void dfs(int i) {
        selected[i] = true;
        selectedCards++;
        queue.add(i);

        if (queue.size() == cardsToChoose) {
            foundAllCards = true;
            return;
        }


        Iterator<Integer> iter = graph.getAdj()[i].listIterator();
        while (iter.hasNext() && !foundAllCards) {
            int n = iter.next();
            if (!selected[n] && !isIllegalMove(listOfNine[i]))
                dfs(n);
        }
    }

    private boolean isIllegalMove(MoveCard moveCard) {
        currentPosition.movePosition(moveCard.getType(), movements, grid);
        return movements.checkIfMoveIsOutOfBounds(currentPosition.y, currentPosition.x, grid);

    }

    public LinkedList<Integer> getSelectedCards() {
        return queue;
    }
}
