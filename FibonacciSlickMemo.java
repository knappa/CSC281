import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

/**
 * Created on 1/23/17
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class FibonacciSlickMemo {

  /**
   * memoization cache for fibonacci numbers
   */
  private static Map<Integer, BigInteger> memos = new HashMap<>();

  // static initializer
  static {
    memos.put(0, BigInteger.ZERO);
    memos.put(1, BigInteger.ONE);
  }

  /**
   * Computes the nth fibonacci number
   *
   * @param n index of fibonacci number
   * @return the nth fibonacci number
   */
  public static BigInteger compute(int n) {
    return memos.computeIfAbsent(n, k -> compute(k - 1).add(compute(k - 2)));
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
