package com.mycompany.quickCalculations;

import com.mycompany.quickCalculations.model.FixedSizePriorityQueue;
import com.mycompany.quickCalculations.model.PointWithDistance;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class Main {

    public static final String PATH = "points";

    private static final short START_X = -200;
    private static final short START_Y = 300;

    private static final short START_X1 = 1000;
    private static final short START_Y1 = 25;

    private static final short QUEUE_SIZE_CLOSEST = 10;
    private static final short QUEUE_SIZE_FURTHEST = 20;

    public static void main(String[] args) throws FileNotFoundException, IOException {
        long start = System.currentTimeMillis();
        Main m = new Main();
        StringBuilder a = m.startProcessing();
        Float elapsedTime;
        elapsedTime = (System.currentTimeMillis() - start) / 1000F;
        System.out.println(String.join(a.toString(), "Execution time: ", elapsedTime.toString()));
    }

    private StringBuilder startProcessing() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();

        FixedSizePriorityQueue queueClosest = FixedSizePriorityQueue.getNormalQueue(QUEUE_SIZE_CLOSEST);
        FixedSizePriorityQueue queueFurtherst = FixedSizePriorityQueue.getReversedQueue(QUEUE_SIZE_FURTHEST);

        File file = new File(classLoader.getResource(PATH).getFile());
        BufferedInputStream input;
        input = new BufferedInputStream(new FileInputStream(file));
        try {
            byte[] coordinate = new byte[4];

            while (input.read(coordinate) != -1) {
                short x = getCoordinate(Arrays.copyOfRange(coordinate, 0, 2));
                short y = getCoordinate(Arrays.copyOfRange(coordinate, 2, coordinate.length));
                PointWithDistance p = new PointWithDistance(x, y, START_X, START_Y);
                PointWithDistance q = new PointWithDistance(x, y, START_X1, START_Y1);
                queueClosest.add(p);
                queueFurtherst.add(q);
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("No file found to be processed", ex);
        } finally {
            input.close();
        }

        StringBuilder sb = new StringBuilder()
                .append("Closest points:\n")
                .append(queueClosest.toString())
                .append("Furthest points:\n")
                .append(queueFurtherst.toString()).append("\n");
        return sb;
    }

    public short getCoordinate(byte[] bytearray) {
        ByteBuffer wrapped = ByteBuffer.wrap(bytearray); // big-endian by default
        return wrapped.getShort();
    }

}
