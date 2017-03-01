import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GenericPointGenerator {

    /**
     * tests
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        ArrayList<Point2D> pts = GenericPointGenerator.getPoints(400);

        StdDraw.setScale(-3, 3);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(0.01);
        for (Point2D pt : pts) {
            StdDraw.point(pt.getX(), pt.getY());
        }
    }

    /**
     * @param numPoints number of points to return
     * @return an array list of points where no set of 3 are ever on the same line.
     */
    @SuppressWarnings("WeakerAccess")
    public static ArrayList<Point2D> getPoints(int numPoints) {
        ArrayList<Point2D> points = new ArrayList<>();

        Random rnd = new Random();

        do {
            Point2D nextPt = new Point2D.Double(rnd.nextGaussian(), rnd.nextGaussian());

            // check to see if it is generic. first, it must not
            if (points.contains(nextPt)) continue;

            Comparator<Point2D> slopeTo = new Comparator<Point2D>() {
                public int compare(Point2D pt1, Point2D pt2) {
                    // sign correction
                    int signCorr = 1;
                    if ((nextPt.getX() > pt1.getX() && nextPt.getX() < pt2.getX())
                            || (nextPt.getX() < pt1.getX() && nextPt.getX() > pt2.getX()))
                        signCorr = -1;
                    return signCorr * (int) Math.signum(
                            (nextPt.getY() - pt1.getY())
                                    * (nextPt.getX() - pt2.getX())
                                    - (nextPt.getY() - pt2.getY())
                                    * (nextPt.getX() - pt1.getX())
                    );

                }
            };
            points.sort(slopeTo);
            // if any two points with the same slope to our new point will be
            // colinear with it. by sorting, they will be side-by-side.
            boolean foundColinear = false;
            for (int index = 0;
                 !foundColinear && index < points.size() - 1;
                 index++) {
                if (slopeTo.compare(points.get(index), points.get(index + 1)) == 0)
                    foundColinear = true;
            }
            if (!foundColinear)
                points.add(nextPt);

        } while (points.size() < numPoints);

        return points;
    }

}
