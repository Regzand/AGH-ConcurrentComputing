package zad1;

import org.javasimon.SimonManager;
import org.javasimon.Split;
import org.javasimon.Stopwatch;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {

    private static final int THREADS = 8;
    private static final int IMAGE_WIDTH = 800;
    private static final int IMAGE_HEIGHT = 600;
    private static final int ZOOM = 200;
    private static final int MAX_ITERATIONS = 1000;
    private static final int HORIZONTAL_TASKS_SIZE = 80;
    private static final int VERTICAL_TASKS_SIZE = 600;

    private static final int TEST_LOOPS = 10;

    public static void main(String[] args) throws Exception {

        // create image
        BufferedImage image = new BufferedImage(IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_RGB);

        // create timer
        Stopwatch stopwatch = SimonManager.getStopwatch("Test");

        // tests
        for (int i = 0; i < TEST_LOOPS; i++) {

            // start timer
            Split split = stopwatch.start();

            // execute and merge merge tasks
            for (Future<MandelbrotTask> future : Executors.newFixedThreadPool(THREADS).invokeAll(generateTasks()))
                future.get().copyTo(image);

            // stop timer
            split.stop();

        }

        // print results
        System.out.printf("Loops:\t\t%d\nAverage:\t%f\nDeviation:\t%f\n", stopwatch.getCounter(), stopwatch.getMean(), stopwatch.getStandardDeviation());

        // display image
        new Display("Multi-Thread Mandelbrot", image);
    }

    private static List<MandelbrotGenerator> generateTasks(){
        List<MandelbrotGenerator> tasks = new LinkedList<>();

        int offsetX = 0;
        while (offsetX < IMAGE_WIDTH) {
            int offsetY = 0;
            while (offsetY < IMAGE_HEIGHT) {
                tasks.add(
                        new MandelbrotGenerator(offsetX, offsetY, HORIZONTAL_TASKS_SIZE, VERTICAL_TASKS_SIZE, ZOOM, MAX_ITERATIONS, IMAGE_WIDTH, IMAGE_HEIGHT)
                );
                offsetY += VERTICAL_TASKS_SIZE;
            }
            offsetX += HORIZONTAL_TASKS_SIZE;
        }

        return tasks;
    }

}
