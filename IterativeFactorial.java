import java.math.BigInteger;

/**
 * Prints the factorial of the integer given as a command line argument
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 1.0
 */
public class IterativeFactorial {

  /**
   * @param args command line arguments
   */
  public static void main(String[] args) {
    try {
      try {
        int n = Integer.parseInt(args[0]);
        System.out.println(compute(n));
      } catch (ArrayIndexOutOfBoundsException e) {
        System.out.println("what's the number?");
      }
    } catch (NumberFormatException e) {
      System.out.println("could not parse argument as Integer");

    }

  }

  /**
   * The classic iterative factorial function defined by 0!=1 and n! = n*(n-1)!
   *
   * @param n a non-negative integer value
   * @return value of n!
   */
  public static BigInteger compute(int n) {
    BigInteger value = BigInteger.ONE;
    for (int i = 1; i <= n; i++) {
      value = value.multiply(BigInteger.valueOf(i));
    }
    return value;
  }

}
