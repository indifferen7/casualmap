package se.cs.casualmap.api.shared;

public class Tile {

    private final int x;
    private final int y;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Tile add(Tile tile) {
        return new Tile(x + tile.getX(), y + tile.getY());
    }

    public Tile relativeTo(Direction direction) {
        return relativeTo(direction, 1);
    }

    public Tile relativeTo(Direction direction, int steps) {
        if (direction.equals(Direction.UP))
            return new Tile(this.getX(), this.getY() - steps);
        if (direction.equals(Direction.RIGHT))
            return new Tile(this.getX() + steps, this.getY());
        if (direction.equals(Direction.DOWN))
            return new Tile(this.getX(), this.getY() + steps);
        if (direction.equals(Direction.LEFT))
            return new Tile(this.getX() - steps, this.getY());

        throw new RuntimeException(String.format("Unknown direction: %s ", direction));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tile tile = (Tile) o;

        if (x != tile.x) return false;
        return y == tile.y;

    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
