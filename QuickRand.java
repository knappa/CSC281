import java.util.Arrays;
import java.util.Scanner;

/**
 * Randomized Quick sort demonstration
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class QuickRand {

  /**
   * Sorts the array {@code array}
   *
   * @param array an array of {@code int}s
   */
  public static void sort(int[] array) {
    sort(array, 0, array.length - 1);
  }

  private static void sort(int[] array, int low, int high) {
    if (high <= low) return;
    int j = partition(array, low, high);
    sort(array, low, j - 1);
    sort(array, j + 1, high);
  }

  private static int partition(int[] array, int low, int high) {
    int i = low;
    int j = high + 1;

    // pick random pivot and swap to front
    int swapIndex = low + (int) (Math.random() * (high - low));
    int v = array[swapIndex];
    array[swapIndex] = array[low];
    array[low] = v;

    // continue as before

    while (true) {
      while (array[++i] < v) if (i == high) break;
      while (v < array[--j]) if (j == low) break;
      if (i >= j) break;

      // exchange
      int temp = array[i];
      array[i] = array[j];
      array[j] = temp;

    }

    // exchange
    int temp = array[low];
    array[low] = array[j];
    array[j] = temp;

    return j; // pivot's new location
  }

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

}
