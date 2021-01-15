package app.info;

public class Constraint<T> {
    // limita inferioara si superioara
    private T inf;
    private T sup;

    // constructor
    public Constraint(T inf, T sup) {
        this.inf = inf;
        this.sup = sup;
    }

    // getteri si setteri
    public T getInf() {
        return inf;
    }

    public void setInf(T inf) {
        this.inf = inf;
    }

    public T getSup() {
        return sup;
    }

    public void setSup(T sup) {
        this.sup = sup;
    }
}
