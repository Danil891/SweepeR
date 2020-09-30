package elementsForStream;

public class Coordinates {
    public int x;
    public int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coordinates) {
            Coordinates to = (Coordinates) o;
            return to.x == x && to.y == y;
        }
        return super.equals(o);
    }
}
