package se.cs.casualmap.generator;

public class MapGeneratorArgs {

    private int width = 50;
    private int height = 50;

    private int minAreaHeight = 6;
    private int maxAreaHeight = 12;
    private int minAreaWidth = 6;
    private int maxAreaWidth = 12;
    private double maxTileDensity = .5;

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
        return maxTileDensity;
    }
}