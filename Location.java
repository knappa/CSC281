/**
 * An immutable class encapsulating a named location on the Earth.
 * <p>
 * Created by knappa on 2/15/17.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class Location {

  /**
   * Constructs an instance of {@code Location}
   *
   * @param label        a name for this location
   * @param latitudeDeg  the latitude of this location in degrees
   * @param longitudeDeg the longitude of this location in degrees
   */
  public Location(String label, double latitudeDeg, double longitudeDeg) {
    // TODO: implement me
  }

  /**
   * Computes the distance between two {@code Location}s using the Haversine formula.
   *
   * @param other another location
   * @return the distance between {@code this} and {@code other} in miles
   */
  public double dist(Location other) {
    return 0; // TODO: implement me
  }

}
