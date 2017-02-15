import java.awt.*;
import java.util.ArrayList;

/**
 * A logo-like turtle
 *
 * @author <a href="mailto:adam.knapp@instanton.org">Adam Knapp</a>
 * @version 1.0
 */
public class Turtle {

  private static final double turtleSize = 5.0;
  private static ArrayList<Line> lines;
  private static ArrayList<Turtle> turtles;
  private static double minX, maxX;
  private static double minY, maxY;
  private static boolean rendering;
  private static boolean preserveAspectRatio = true;

  static {
    lines = new ArrayList<>();
    turtles = new ArrayList<>();
    StdDraw.enableDoubleBuffering();
    minX = -11.0;
    maxX = 11.0;
    minY = -11.0;
    maxY = 11.0;
    setBounds();
    rendering = true;
  }

  private double heading;
  private double x, y;
  private Color color;
  private boolean penDown;
  private boolean visible;

  /**
   * Creates a new <code>Turtle</code>
   *
   * @param color turtle's color
   */
  public Turtle(Color color) {
    this();
    this.color = color;
  }

  /**
   * Creates a new <code>Turtle</code>, with the default color; Black
   */
  public Turtle() {
    heading = Math.PI / 2.0;
    x = 0;
    y = 0;
    penDown = true;
    visible = true;
    color = Color.BLACK;
    turtles.add(this);
    render();
  }

  /**
   * re-render the scene
   */
  private static void render() {

    if (!rendering) return;

    StdDraw.clear();
    for (Turtle t : turtles) t.draw();
    for (Line l : lines) l.draw();
    StdDraw.show();
  }

  /**
   * draw the turtle
   */
  private void draw() {

    if (!visible) return;

    StdDraw.setPenColor(color);

    StdDraw.line(x + turtleSize * Math.cos(heading),
                 y + turtleSize * Math.sin(heading),
                 x + turtleSize * Math.cos(heading + 2 * Math.PI / 3) / 2.0,
                 y + turtleSize * Math.sin(heading + 2 * Math.PI / 3) / 2.0);

    StdDraw.line(x + turtleSize * Math.cos(heading + 2 * Math.PI / 3) / 2.0,
                 y + turtleSize * Math.sin(heading + 2 * Math.PI / 3) / 2.0,
                 x + turtleSize * Math.cos(heading + 4 * Math.PI / 3) / 2.0,
                 y + turtleSize * Math.sin(heading + 4 * Math.PI / 3) / 2.0);

    StdDraw.line(x + turtleSize * Math.cos(heading + 4 * Math.PI / 3) / 2.0,
                 y + turtleSize * Math.sin(heading + 4 * Math.PI / 3) / 2.0,
                 x + turtleSize * Math.cos(heading),
                 y + turtleSize * Math.sin(heading));
  }

  private static void setBounds() {
    if (preserveAspectRatio) {
      double max = Math.max(maxX + turtleSize, maxY + turtleSize);
      double min = Math.min(minX - turtleSize, minY - turtleSize);
      StdDraw.setXscale(min, max);
      StdDraw.setYscale(min, max);
    } else {
      StdDraw.setXscale(minX - turtleSize, maxX + turtleSize);
      StdDraw.setYscale(minY - turtleSize, maxY + turtleSize);
    }
  }

  /**
   * Removes all lines from screen
   */
  public static void clearScreen() {
    lines.clear();
    render();
  }

  public static void reset() {
    for (Turtle t : turtles) {t.setPosition(0, 0);}
    minX = -11.0;
    clearScreen();
    maxX = 11.0;
    minY = -11.0;
    maxY = 11.0;
    setBounds();
  }

  public static void disableProgressiveRendering() {
    rendering = false;
  }

  public static void enableProgressiveRendering() {
    rendering = true;
    render();
  }

  /**
   * Test code
   *
   * @param args ignored
   */
  public static void main(String[] args) {

    preserveAspectRatio(true);

    Turtle t1 = new Turtle();

    // square
    for (int i = 0; i < 4; i++) {
      t1.fd(10);
      t1.rtd(90);
    }

    // wait, then reset
    try { Thread.sleep(2000); } catch (InterruptedException ignored) { }
    reset();

    Turtle t2 = new Turtle(Color.RED);

    // pursuit on a line
    t2.setPosition(500, 0);
    for (int i = 0; i < 1000; i++) {
      t1.fd(1);

      t2.setHeadingTo(t1);
      t2.fd(1);
    }

    // wait, then reset
    try { Thread.sleep(2000); } catch (InterruptedException ignored) { }
    reset();

    // pursuit on a circle
    t1.setPosition(200, 0);
    for (int i = 0; i < 1000; i++) {
      t1.fd(2);
      t1.ltd(1);

      t2.setHeadingTo(t1);
      t2.fd(1);
    }

    // wait, then reset
    try { Thread.sleep(2000); } catch (InterruptedException ignored) { }
    reset();
    t2.hideTurtle();

    // spiraly flower thing
    for (int i = 0; i < 1000; i++) {
      t1.fd(1.0);
      t1.rtd(80 * Math.pow(Math.sin(i / 25.0), 2));
    }

    // wait, then reset
    try { Thread.sleep(2000); } catch (InterruptedException ignored) { }
    reset();

    // spirograph-like figure
    disableProgressiveRendering();
    for (int i = 0; i < 5000; i++) {
      t1.fd(4 + 2 * Math.sin(i / 40.0));
      t1.rtd(5 + 2 * Math.sin(i / 20.0));
    }
    enableProgressiveRendering();

    // wait, then reset
    try { Thread.sleep(2000); } catch (InterruptedException ignored) { }
    reset();

    // paired spirograph-like figures
    disableProgressiveRendering();
    for (int i = 0; i < 5000; i++) {
      t1.fd(4 + 2 * Math.sin(i / 40.0));
      t1.rtd(5 + 2 * Math.sin(i / 20.0));

      t2.fd(4 + 2 * Math.sin(i / 40.0));
      t2.ltd(5 + 2 * Math.sin(i / 20.0));
    }
    enableProgressiveRendering();

  }

  /**
   * @param b true if we want to preserve a 1:1 aspect ration
   */
  public static void preserveAspectRatio(boolean b) {
    preserveAspectRatio = b;
  }

  public void setPosition(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Points {@code this} turtle in the direction of {@code other}
   *
   * @param other another turtle
   */
  public void setHeadingTo(Turtle other) {
    double dx = other.x - this.x;
    double dy = other.y - this.y;

    if (dy == 0) {
      if (dx > 0) heading = 0.0;
      else heading = Math.PI;
    } else if (dx == 0) {
      if (dy > 0) heading = Math.PI / 2;
      else heading = 3 * Math.PI / 2;
    } else if (dx > 0) heading = Math.atan(dy / dx);
    else heading = Math.PI + Math.atan(dy / dx); // dx < 0

  }

  /**
   * show's turtle
   */
  public void showTurtle() {
    visible = true;
    render();
  }

  /**
   * hide's turtle
   */
  public void hideTurtle() {
    visible = false;
    render();
  }

  /**
   * right turn (in degrees)
   *
   * @param theta angle, in degrees
   */
  public void rtd(double theta) {
    rightTurnDeg(theta);
  }

  /**
   * right turn (in degrees)
   *
   * @param theta angle, in degrees
   */
  public void rightTurnDeg(double theta) {
    rightTurn(theta * Math.PI / 180.0);
    render();
  }

  /**
   * right turn (in radians)
   *
   * @param theta angle, in radians
   */
  public void rightTurn(double theta) {
    heading -= theta;
    heading = heading % (2 * Math.PI);
    render();
  }

  /**
   * left turn (in degrees)
   *
   * @param theta angle, in degrees
   */
  public void ltd(double theta) {
    leftTurnDeg(theta);
  }

  /**
   * left turn (in degrees)
   *
   * @param theta angle, in degrees
   */
  public void leftTurnDeg(double theta) {
    leftTurn(theta * Math.PI / 180.0);
    render();
  }

  /**
   * left turn (in radians)
   *
   * @param theta angle, in radians
   */
  public void leftTurn(double theta) {
    heading += theta;
    heading = heading % (2 * Math.PI);
    render();
  }

  /**
   * move forward
   *
   * @param dist distance
   */
  public void fd(double dist) {
    forward(dist);
  }

  /**
   * move forward
   *
   * @param dist distance
   */
  public void forward(double dist) {
    double newX = x + dist * Math.cos(heading);
    double newY = y + dist * Math.sin(heading);

    //if(penDown) StdDraw.line(x,y,newX,newY);
    if (penDown) lines.add(new Line(x, y, newX, newY, color));

    x = newX;
    y = newY;

    render();
  }

  /**
   * move backwards
   *
   * @param dist distance
   */
  public void bk(double dist) {
    backward(dist);
  }

  /**
   * move backwards
   *
   * @param dist distance
   */
  public void backward(double dist) {
    double newX = x - dist * Math.cos(heading);
    double newY = y - dist * Math.sin(heading);

    if (penDown) lines.add(new Line(x, y, newX, newY, color));

    x = newX;
    y = newY;
  }

  /**
   * A container for lines
   */
  private class Line {
    final double x1, y1, x2, y2;
    final Color color;

    /**
     * Creates a new <code>Line</code> instance, from (x1,y1) to
     * (x2,y2) with the default color, Blue
     *
     * @param x1 first x-coord
     * @param y1 first y-coord
     * @param x2 second x-coord
     * @param y2 second y-coord
     */
    public Line(double x1, double y1, double x2, double y2) {
      this(x1, y1, x2, y2, Color.BLUE);
    }

    /**
     * Creates a new <code>Line</code> instance, from (x1,y1) to
     * (x2,y2) with the default color, Blue
     *
     * @param x1    first x-coord
     * @param y1    first y-coord
     * @param x2    second x-coord
     * @param y2    second y-coord
     * @param color the line's color
     */
    public Line(double x1, double y1, double x2, double y2, Color color) {
      this.x1 = x1;
      this.y1 = y1;
      this.x2 = x2;
      this.y2 = y2;
      this.color = color;

      double lineMinX = Math.min(x1, x2);
      if (minX > lineMinX) {
        minX = lineMinX;
        setBounds();
      }

      double lineMinY = Math.min(y1, y2);
      if (minY > lineMinY) {
        minY = lineMinY;
        setBounds();
      }

      double lineMaxX = Math.max(x1, x2);
      if (maxX < lineMaxX) {
        maxX = lineMaxX;
        setBounds();
      }

      double lineMaxY = Math.max(y1, y2);
      if (maxY < lineMaxY) {
        maxY = lineMaxY;
        setBounds();
      }

    }

    /**
     * draw the line
     */
    private void draw() {
      StdDraw.setPenColor(color);
      StdDraw.line(x1, y1, x2, y2);
    }
  }

}
