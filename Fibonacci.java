import java.math.BigInteger;

/**
 * Created by <a href="mailto:knapp@american.edu">Adam Knapp</a> on 1/18/17.
 *
 * @version 0.1
 */
public class Fibonacci {

  /**
   * Computes the nth fibonacci number
   *
   * @param n index of fibonacci number
   * @return the nth fibonacci number
   */
  private static BigInteger compute(int n) {

    if (n == 0) {
      return BigInteger.ZERO;
    } else if (n == 1) {
      return BigInteger.ONE;
    } else {
      return compute(n - 2).add(compute(n - 1));
    }

  }

  /**
   * Computes the nth fibonacci number as specified by the command line argument
   *
   * @param args {@code args[0]} gives n
   */
  public static void main(String[] args) {
    System.out.println(compute(Integer.valueOf(args[0])));
  }

}
