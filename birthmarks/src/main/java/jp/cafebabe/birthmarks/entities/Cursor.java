package jp.cafebabe.birthmarks.entities;

public class Cursor {
    private int current = 0;
    private long max;

    private Cursor(long max) {
        if(max < 0)
            throw new IllegalArgumentException(String.format("%d: must be positive value", max));
        this.max = max;
    }

    public static Cursor of(long max) {
        return new Cursor(max);
    }

    public long max() {
        return max;
    }

    public int increment() {
        if(current >= max)
            throw new IllegalStateException(String.format("current (%d) over max (%d)", current, max));
        current++;
        return current;
    }

    public boolean incrementIsFirst() {
        var returnValue = isFirst();
        increment();
        return returnValue;
    }

    public boolean isFirst() {
        return current == 0;
    }

    public boolean isLast() {
        return current == max;
    }

    public boolean incrementIsLast() {
        increment();
        return isLast();
    }
}
