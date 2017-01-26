import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by <a href="mailto:knapp@american.edu">Adam Knapp</a> on 1/18/17.
 *
 * @version 0.1
 */
public class FibonacciMemoized {

  /**
   * memoization cache for fibonacci numbers
   */
  private static Map<Integer, BigInteger> memos = new HashMap<>();

  /**
   * Computes the nth fibonacci number
   *
   * @param n index of fibonacci number
   * @return the nth fibonacci number
   */
  public static BigInteger compute(int n) {

    if (n == 0) { return BigInteger.ZERO; }
    if (n == 1) { return BigInteger.ONE; }

    // we may have already memoized this value
    if (memos.containsKey(n)) return memos.get(n);

    BigInteger fibonacci = compute(n - 2).add(compute(n - 1));

    memos.put(n, fibonacci);
    return fibonacci;

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
