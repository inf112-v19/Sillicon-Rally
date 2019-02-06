package inf112.skeleton.app.util;

import java.util.ArrayList;

/**
 * Created by Martin on 06/02/2019.
 */
public class Cell {

    private ArrayList<Object> list;

    public Cell() {
        this.list = new ArrayList<>();
    }

    public void add(Object elem) {
        this.list.add(elem);
    }

    public void removeElem(Object elem) {
        for (Object obj : list) {
            if (elem == obj) {
                list.remove(obj);
            }
        }
    }

    public ArrayList<Object> getElems() {
        return this.list;
    }

    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    public int amountOfItems() {
        return this.list.size();
    }
}
