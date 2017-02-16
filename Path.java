/**
 * An immutable class which encapsulates a circular path of {@code Location}s. Two paths are considered to be
 * equivalent if they are the same up to rotations and reversals.
 * <p>
 * Created on 2/15/17
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class Path implements Comparable<Path> {

  /**
   * Creates a {@code Path} based on the array {@code locations}.
   *
   * @param locations an array of locations in order of visitation
   */
  public Path(Location[] locations) {
    // TODO: implement
  }

  /**
   * Computes the length of this path
   *
   * @return the length of {@code this} in miles
   */
  public double length() {
    return 0.0; // TODO: implement
  }

  /**
   * Compares the lengths of {@code this} and {@code other}
   *
   * @param other another path
   * @return -1 if the length of {@code this} if less than the length of {@code other}, 0 if the lengths agree, or -1 if
   * the length of {@code this} is larger.
   */
  @Override
  public int compareTo(Path other) {
    return 0; // TODO: implement
  }

  /**
   * Produces a path with a random swap mutation
   *
   * @return a path, different from {@code this} by a single swap of two randomly chosen locations
   */
  public Path getMutant() {
    return null; // TODO: implement
  }

  @Override
  public boolean equals(Object obj) {
    return obj != null
      && obj instanceof Path
      && this.equals((Path) obj);
  }

  /**
   * Compares paths for equality
   *
   * @param other another path
   * @return {@code true} if {@code this} represents the same path as {@code other}, {@code false} otherwise
   */
  public boolean equals(Path other) {
    // Easy case: if instances are literally the same, they must be equal
    if (this == other) { return true; }

    // TODO: implement actual test when the instances are different
    return false;
  }

  /**
   * Draw path to StdDraw
   */
  public void draw() {
    // TODO: (optional) implement drawing to StdDraw
  }

}
