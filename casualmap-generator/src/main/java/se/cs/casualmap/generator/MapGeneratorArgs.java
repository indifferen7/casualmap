package se.cs.casualmap.generator;

/**
 * Map generator args, not very flexible at the moment.. ;)
 */
public class MapGeneratorArgs {

    private final int width;
    private final int height;
    private final int minAreaHeight;
    private final int maxAreaHeight;
    private final int minAreaWidth;
    private final int maxAreaWidth;
    private final double tileDensityThreshold;

    private MapGeneratorArgs(int width, int height, int minAreaHeight,
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

        public Builder setMinAreaWidth(int minAreaWidth) {
            this.minAreaWidth = minAreaWidth;
            return this;
        }

        public Builder setMaxAreaWidth(int maxAreaWidth) {
            this.maxAreaWidth = maxAreaWidth;
            return this;
        }

        public Builder setTileDensityThreshold(double tileDensityThreshold) {
            this.tileDensityThreshold = tileDensityThreshold;
            return this;
        }

        public MapGeneratorArgs build() {
            return new MapGeneratorArgs(width, height, minAreaHeight, maxAreaHeight,
                    minAreaWidth, maxAreaWidth, tileDensityThreshold);
        }
    }
}