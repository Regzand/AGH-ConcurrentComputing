package zad1;

import java.util.concurrent.Callable;

public class MandelbrotGenerator implements Callable<MandelbrotTask> {

    private final MandelbrotTask task;

    private final double zoom;
    private final int max_iterations;
    private final int imageWidth;
    private final int imageHeight;

    public MandelbrotGenerator(int offsetX, int offsetY, int width, int height, int zoom, int max_iter, int imageWidth, int imageHeight) {
        this.task = new MandelbrotTask(offsetX, offsetY, width, height);
        this.zoom = zoom;
        this.max_iterations = max_iter;
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
    }

    @Override
    public MandelbrotTask call() {
        for (int y = 0; y < task.height; y++){
            for (int x = 0; x < task.width; x++){
                double zx = 0;
                double zy = 0;
                double cX = (x + task.offsetX - imageWidth/2.0) / zoom;
                double cY = (y + task.offsetY - imageHeight/2.0) / zoom;

                int i = max_iterations;
                while (zx * zx + zy * zy < 4 && i > 0){
                    double tmp = zx * zx - zy * zy + cX;
                    zy = 2.0 * zx * zy + cY;
                    zx = tmp;
                    i--;
                }

                task.data[x][y] = (i << 16) | 0x00ffff;
            }
        }

        return task;
    }
}
