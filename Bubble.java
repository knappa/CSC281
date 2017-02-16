import java.util.Arrays;
import java.util.Scanner;

/**
 * Bubble sort demonstration
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class Bubble {

  /**
   * Reads from stdin
   * 1) the number of integers we are about to receive
   * 2) that number of ints
   *
   * @param args ignored
   */
  public static void main(String[] args) {

    // read the array from stdin
    Scanner scanner = new Scanner(System.in);
    int arraySize = scanner.nextInt();
    int[] array = new int[arraySize];
    for (int i = 0; i < arraySize; i++)
      array[i] = scanner.nextInt();

    System.out.println("Array loaded, sorting");

    sort(array);

    System.out.println(Arrays.toString(array));

  }

  /**
   * Sorts the array {@code array}
   *
   * @param array an array of {@code int}s
   */
  public static void sort(int[] array) {

    for (int i = array.length - 1; i > 0; i--) {
      boolean madeSwap = false;
      for (int j = 0; j < i; j++) {
        if (array[j] > array[j + 1]) {
          // swap that element with the one at index i
          int temp = array[j];
          array[j] = array[j + 1];
          array[j + 1] = temp;
          madeSwap = true;
        }
      }
      // if we had a pass without any swap, the array must be sorted.
      if (!madeSwap) { return; }
    }
  }

}