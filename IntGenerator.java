/**
 * Simple program to generate a quantity of random integers. First argument is the quantity of integers. The second
 * argument, if present and parsable as an int, gives the strict upper bound of the values we should generate. If not
 * present, this is set to Integer.MAX_VALUE.
 * <p>
 * Note: The first value printed will be the number of ints it will produce.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
class IntGenerator {

  /**
   * @param args {@code args[0]} = number of ints to generate, {@code args[1]} = strict upper bound, if present.
   */
  public static void main(String[] args) {

    int numInts = Integer.parseInt(args[0]);

    int maxValue = Integer.MAX_VALUE;
    try {
      maxValue = Integer.valueOf(args[1]);
    } catch (Exception ignored) {}

    System.out.println(numInts);

    for (int i = 0; i < numInts; i++)
      System.out.println((int) (maxValue * Math.random()));

  }

}
