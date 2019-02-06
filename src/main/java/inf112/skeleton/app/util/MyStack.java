package inf112.skeleton.app.util;

import java.lang.reflect.Array;

/**
 * Created by Martin on 01/02/2019.
 */
public class MyStack<T>  {

    private int maxSize;
    private T[] array;
    private int head;

    public MyStack(int maxSize) {
        this.maxSize = maxSize;
//        @SuppressWarnings("unchecked")
        this.array = (T[]) Array.newInstance(MyStack.class, maxSize);
        this.head = 0;
    }

    /**public MyStack (int maxSize) {
        array = new T[maxSize];
        head = 0;
    }*/

    public void push(T elem) {
        if (isFull()) {
            return;
        }
        array[head] = elem;
        head++;
    }

    public T peek() {
        if (!isEmpty()) {
            return array[head - 1];
        }
        return null;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        head--;
        T temp = array[head];
        array[head] = null;
        return temp;
    }

    public boolean isEmpty() {
        return head == 0;
    }

    public boolean isFull() {
        return head == maxSize;
    }

}
