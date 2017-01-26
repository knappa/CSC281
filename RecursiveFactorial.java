/**
 * Prints the factorial of the integer given as a command line argument
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 1.0
 */
class RecursiveFactorial {

  /**
   * The classic recursive factorial function defined by 0!=1 and n! = n*(n-1)!
   *
   * @param n a non-negative integer value
   * @return value of n!
   */
  public static long compute(int n) {
    if (n == 0) { return 1;} else { return n * compute(n - 1);}
  }

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
      System.out.println("could not parse argument as an integer");

    }

  }

}
