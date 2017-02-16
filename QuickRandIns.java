import java.util.Arrays;
import java.util.Scanner;

/**
 * Randomized Quick/Insertion Hybrid sort demonstration.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class QuickRandIns {

  // when the size of the partition drops below N, use insertion sort
  private static final int N = 3;

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

    if (j - 1 - low >= N) sort(array, low, j - 1);
    else insSort(array, low, j - 1);

    if (high - (j + 1) >= N) sort(array, j + 1, high);
    else insSort(array, j + 1, high);
  }

  private static void insSort(int[] a, int low, int high) {
    for (int i = low + 1; i <= high; i++) {
      // let the next item "sink" down to it's appropriate level.
      for (int j = i; j > low && a[j] < a[j - 1]; j--) {
        int temp = a[j - 1];
        a[j - 1] = a[j];
        a[j] = temp;
      }
    }
  }

  private static int partition(int[] a, int low, int high) {
    int i = low;
    int j = high + 1;

    // pick random pivot and swap to front
    int swapIndex = low + (int) (Math.random() * (high - low));
    int v = a[swapIndex];
    a[swapIndex] = a[low];
    a[low] = v;

    // continue as before

    while (true) {
      while (a[++i] < v) if (i == high) break;
      while (v < a[--j]) if (j == low) break;
      if (i >= j) break;

      // exchange
      int temp = a[i];
      a[i] = a[j];
      a[j] = temp;

    }

    // exchange
    int temp = a[low];
    a[low] = a[j];
    a[j] = temp;

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
