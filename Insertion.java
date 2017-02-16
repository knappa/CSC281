import java.util.Arrays;
import java.util.Scanner;

/**
 * Insertion sort demonstration
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
class Insertion {

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

    for (int i = 1; i < array.length; i++) {
      // let the next item "sink" down to it's appropriate level.
      for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
        int temp = array[j - 1];
        array[j - 1] = array[j];
        array[j] = temp;
      }
    }
  }

}
