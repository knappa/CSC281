import java.util.Arrays;
import java.util.Scanner;

/**
 * Selection sort demonstration
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class Selection {


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

    for (int i = 0; i < array.length; i++) {

      // find the smallest element with index >= i
      int min = array[i];
      int minLocation = i;
      for (int j = i + 1; j < array.length; j++) {
        if (min > array[j]) {
          minLocation = j;
          min = array[j];
        }
      }

      // swap that element with the one at index i
      int temp = array[i];
      array[i] = array[minLocation];
      array[minLocation] = temp;

    }

  }

}
