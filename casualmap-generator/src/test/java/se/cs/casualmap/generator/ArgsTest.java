package se.cs.casualmap.generator;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArgsTest {


    @Test(expected = IllegalArgumentException.class)
    public void widthTooLow() {
        Args.newBuilder()
                .minAreaWidth(4)
                .maxAreaWidth(4)
                .width(1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void heightTooLow() {
        Args.newBuilder()
                .minAreaHeight(4)
                .maxAreaHeight(4)
                .height(1).build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxWidthExceedsMinWidth() {
        Args.newBuilder()
                .minAreaWidth(10)
                .maxAreaWidth(9)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxHeightExceedsMinHeight() {
        Args.newBuilder()
                .minAreaHeight(10)
                .maxAreaHeight(9)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxHeightExceedsMapHeight() {
        Args.newBuilder()
                .maxAreaHeight(9)
                .height(6)
                .build();
    }

    @Test(expected = IllegalArgumentException.class)
    public void maxWidthExceedsMapWidth() {
        Args.newBuilder()
                .maxAreaWidth(9)
                .width(6)
                .build();
    }
}