import java.util.Arrays;
import java.util.Scanner;

/**
 * Merge sort demonstration
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class Merge {

  private static int[] aux;

  /**
   * Sorts the array {@code array}
   *
   * @param array an array of {@code int}s
   */
  public static void sort(int[] array) {
    aux = new int[array.length];
    sort(array, 0, array.length - 1);
  }

  private static void sort(int[] array, int low, int high) {
    if (high <= low) return;

    // numerically same as (high+low)/2, but works better for int overflow
    int mid = low + (high - low) / 2;

    sort(array, low, mid);
    sort(array, mid + 1, high);
    merge(array, low, mid, high);
  }

  private static void merge(int[] array, int low, int mid, int high) {
    int i = low, j = mid + 1;

    for (int k = low; k <= high; k++)
      aux[k] = array[k];

    for (int k = low; k <= high; k++) {
      if (i > mid) array[k] = aux[j++];
      else if (j > high) array[k] = aux[i++];
      else if (aux[j] < aux[i]) array[k] = aux[j++];
      else array[k] = aux[i++];
    }
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
