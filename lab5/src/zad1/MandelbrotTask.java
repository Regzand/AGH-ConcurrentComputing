package zad1;

import java.awt.image.BufferedImage;

public class MandelbrotTask {

    public final int offsetX;
    public final int offsetY;
    public final int width;
    public final int height;

    public final int[][] data;

    public MandelbrotTask(int offsetX, int offsetY, int width, int height) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;
        this.data = new int[width][height];
    }

    public void copyTo(BufferedImage image) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (offsetX+x < image.getWidth() && offsetY+y < image.getHeight()){
                    image.setRGB(offsetX+x, offsetY+y, data[x][y]);
                }
            }
        }
    }

}
