package se.cs.casualmap.api.shared;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction inverse() {
        if (this.equals(UP)) {
            return DOWN;
        }

        if (this.equals(RIGHT)) {
            return LEFT;
        }

        if (this.equals(DOWN)) {
            return UP;
        }

        return RIGHT;
    }

    public static Direction any() {
        List<Direction> directions = Arrays.asList(values());
        Collections.shuffle(directions);
        return directions.get(0);
    }

    public static List<Direction> scramble() {
        List<Direction> directions = Arrays.asList(values());
        Collections.shuffle(directions);
        return directions;
    }
}