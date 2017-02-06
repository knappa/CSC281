/**
 * <code>Circles</code> draws some pretty circles, recursively
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 1.0
 */
class Circles {

    /**
     * Draws a horizontal series of circles emanating from the given
     * central circle
     *
     * @param x x-coordinate
     * @param y y-coordinate
     * @param radius radius
     */
    public static void drawCircles(double x, double y, double radius) {
      // TODO: write this
    }

    /**
     * Runs a demonstration of <code>drawCircles</code>
     *
     * @param args ignored
     */
    public static void main(String[] args) {
	StdDraw.setXscale(-128.0,128.0);
	StdDraw.setYscale(-128.0,128.0);
	StdDraw.clear();
	drawCircles(0.0,0.0,128.0);
    }

}
