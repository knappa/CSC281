/**
 * Created on 1/23/17
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class PrisonersAndBoxes {

  /**
   * Creates a randomized set of boxes
   *
   * @return int array of size 100
   */
  public static int[] setupBoxes() {

    // create boxes
    int[] boxes = new int[100];

    // put numbers in the boxes
    for (int i = 0; i < 100; i++) {
      boxes[i] = i;
    }

    // shuffle numbers via the Fisher-Yates/Knuth shuffle
    for (int i = 0; i < 99; i++) {
      // generate j s.t. i ≤ j < 100
      int j = (int) (i + Math.random() * (100 - i));

      // swap
      int temp = boxes[j];
      boxes[j] = boxes[i];
      boxes[i] = temp;
    }

    return boxes;
  }

  /**
   * Run a trial of the prisoner's box strategy
   *
   * @return {@code true} if the prisoners live, {@code false} if they die
   */
  public static boolean runTrial() {
    int[] boxes = setupBoxes();

    // now run the algorithm

    // TODO: run the algorithm

  }

  public static void main(String[] args) {
    try {
      int numTrials = Integer.parseInt(args[0]);

      int sucesses = 0;
      for (int i = 0; i < numTrials; i++) {
        if (runTrial()) { sucesses++; }
      }
      System.out.println((100.0 * sucesses / (double) numTrials) + "% survival rate");
    } catch (Exception e) {
      System.err.println("expecting an integer number of trials as parameter");
    }
  }

}
