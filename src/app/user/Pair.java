package app.user;

import java.util.ArrayList;

public class Pair<T, K> {
    public T user;
    public K degree;

    public Pair(T user, K degree) {
        this.user = user;
        this.degree = degree;
    }
}
