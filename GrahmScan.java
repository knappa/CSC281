import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * A class to implement and visualize the Grahm Scan algorithm
 * <p>
 * Created by knappa on 2/28/17.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class GrahmScan {

  public static void main(String[] args) {
    ArrayList<Point2D> points = GenericPointGenerator.getPoints(500);

    // TODO: implement Grahm Scan algorithm
  }

  /**
   * Determine if the angle p1 -> p2 -> p3 is clockwise or counterclockwise
   *
   * @param p1 1st point
   * @param p2 2nd point
   * @param p3 3rd point
   * @return negative number if the angle p1 -> p2 -> p3 is counterclockwise, positive if clockwise, zero if the 3
   * points are colinear.
   */
  public static double ccw(Point2D p1, Point2D p2, Point2D p3) {
    // compute coordinates of vector from p1 to p2
    double v1x = p2.getX() - p1.getX();
    double v1y = p2.getY() - p1.getY();

    // compute coordinates of vector from p2 to p3
    double v2x = p3.getX() - p2.getX();
    double v2y = p3.getY() - p2.getY();

    // desired quantity is 3rd component of cross product (other
    // components are zero as these vectors are 2d)
    return v1x * v2y - v2x * v1y;
  }

}
