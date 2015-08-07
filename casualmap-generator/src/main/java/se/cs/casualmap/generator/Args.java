package se.cs.casualmap.generator;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Map generator args.
 */
public class Args {

    private final int width;
    private final int height;
    private final int minAreaHeight;
    private final int maxAreaHeight;
    private final int minAreaWidth;
    private final int maxAreaWidth;
    private final double tileDensityThreshold;

    private Args(int width, int height, int minAreaHeight,
                 int maxAreaHeight, int minAreaWidth,
                 int maxAreaWidth, double tileDensityThreshold) {

        checkArgument(width > 1, "Expected map to be at least 2 tiles wide, got %s", width);
        checkArgument(height > 1, "Expected map to be at least 2 tiles high, got %s", height);
        checkArgument(minAreaWidth <= maxAreaWidth,
                "Minimum area width (%s) exceeds maximum area width (%s)", minAreaWidth, maxAreaWidth);
        checkArgument(minAreaHeight <= maxAreaHeight,
                "Minimum area height (%s) exceeds maximum area height (%s)", minAreaHeight, maxAreaHeight);
        checkArgument(width >= maxAreaWidth,
                "Map width (%s) must be greater than the maximum area width (%s)", width, maxAreaWidth);
        checkArgument(height >= maxAreaHeight,
                "Map height (%s) must be greater than the maximum area height (%s)", height, maxAreaHeight);
        checkArgument(tileDensityThreshold > 0 && tileDensityThreshold <= 1,
                "Tile density threshold must be somewhere between 0 and 1");

        this.width = width;
        this.height = height;
        this.minAreaHeight = minAreaHeight;
        this.maxAreaHeight = maxAreaHeight;
        this.minAreaWidth = minAreaWidth;
        this.maxAreaWidth = maxAreaWidth;
        this.tileDensityThreshold = tileDensityThreshold;
    }

    public static Builder newBuilder() {
        return new Args.Builder();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getMinAreaHeight() {
        return minAreaHeight;
    }

    public int getMaxAreaHeight() {
        return maxAreaHeight;
    }

    public int getMinAreaWidth() {
        return minAreaWidth;
    }

    public int getMaxAreaWidth() {
        return maxAreaWidth;
    }

    public double tileDensityThreshold() {
        return tileDensityThreshold;
    }

    public static class Builder {
        private int width = 50;
        private int height = 50;
        private int minAreaHeight = 6;
        private int maxAreaHeight = 12;
        private int minAreaWidth = 6;
        private int maxAreaWidth = 12;
        private double tileDensityThreshold = .5;

        private Builder() {}

        public Builder width(int width) {
            this.width = width;
            return this;
        }

        public Builder height(int height) {
            this.height = height;
            return this;
        }

        public Builder minAreaHeight(int minAreaHeight) {
            this.minAreaHeight = minAreaHeight;
            return this;
        }

        public Builder maxAreaHeight(int maxAreaHeight) {
            this.maxAreaHeight = maxAreaHeight;
            return this;
        }

        public Builder minAreaWidth(int minAreaWidth) {
            this.minAreaWidth = minAreaWidth;
            return this;
        }

        public Builder maxAreaWidth(int maxAreaWidth) {
            this.maxAreaWidth = maxAreaWidth;
            return this;
        }

        public Builder tileDensityThreshold(double tileDensityThreshold) {
            this.tileDensityThreshold = tileDensityThreshold;
            return this;
        }

        public Args build() {
            return new Args(width, height, minAreaHeight, maxAreaHeight,
                    minAreaWidth, maxAreaWidth, tileDensityThreshold);
        }
    }
}