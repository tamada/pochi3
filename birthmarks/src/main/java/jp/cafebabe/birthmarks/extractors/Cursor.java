package jp.cafebabe.birthmarks.extractors;

class Cursor {
    private int current;
    private int max;

    private Cursor(int max) {
        if(max < 0)
            throw new IllegalArgumentException(String.format("%d: must be positive value", max));
        this.current = 0;
        this.max = max;
    }

    public static Cursor of(int max) {
        return new Cursor(max);
    }

    public int increment() {
        if(current >= max)
            throw new IllegalStateException(String.format("current (%d) over max (%d)", current, max));
        current++;
        return current;
    }

    public boolean isLast() {
        return current == max;
    }

    public boolean incrementIsLast() {
        increment();
        return isLast();
    }
}
