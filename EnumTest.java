import java.util.Scanner;

/**
 * An example of an enum type and switch statement.
 * <p>
 * Created on 2/2/17
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class EnumTest {

  /**
   * reads a cardinal direction from stdin and prints it rotated clockwise
   *
   * @param args ignored
   */
  public static void main(String[] args) {

    Direction dir;

    Scanner scanner = new Scanner(System.in);

    String directionString = scanner.nextLine();

    dir = Direction.valueOf(directionString);

    System.out.println(rotateCW(dir));

  }

  /**
   * rotates directions clockwise
   *
   * @param dir a cardinal direction
   * @return {@code dir} rotated one tick clockwise
   */
  private static Direction rotateCW(Direction dir) {
    switch (dir) {
      case N:
        return Direction.E;
      case E:
        return Direction.S;
      case S:
        return Direction.W;
      case W:
        return Direction.N;
      case NONE:
      default:
        return Direction.NONE;
    }
  }

  /**
   * Cardinal directions
   */
  enum Direction {
    NONE, N, S, E, W
  }

}