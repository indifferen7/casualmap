package se.cs.casualmap.generator;

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