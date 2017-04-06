/**
 * Created by knappa on 10/16/16.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class GuitarString {

  private static final double A_4 = 440.0; // hz
  private final int capacity;
  private int tics;
  private RingBuffer rb;

  // create a guitar string of the given frequency, using a sampling rate of 44,100
  public GuitarString(double frequency) {
    capacity = (int) Math.round(0.5 + (44100 / frequency));
    rb = new RingBuffer(capacity);
    tics = 0;
  }

  // create a guitar string whose size and initial values are given by the array
  public GuitarString(double[] init) {
    capacity = init.length;
    rb = new RingBuffer(capacity);
    for (double v : init) rb.enqueue(v);
    tics = 0;
  }

  public static void main(String[] args) {

    // create guitar strings
    double A_3 = A_4 * Math.pow(1.059463, -12.0);
    GuitarString stringA3 = new GuitarString(A_3);

    double B_3 = A_4 * Math.pow(1.059463, -10.0);
    GuitarString stringB3 = new GuitarString(B_3);

    double C_3 = A_4 * Math.pow(1.059463, -9.0);
    GuitarString stringC3 = new GuitarString(C_3);

    double D_3 = A_4 * Math.pow(1.059463, -7.0);
    GuitarString stringD3 = new GuitarString(D_3);

    double E_3 = A_4 * Math.pow(1.059463, -5.0);
    GuitarString stringE3 = new GuitarString(E_3);

    double F_3 = A_4 * Math.pow(1.059463, -4.0);
    GuitarString stringF3 = new GuitarString(F_3);

    double G_3 = A_4 * Math.pow(1.059463, -2.0);
    GuitarString stringG3 = new GuitarString(G_3);

    GuitarString stringA4 = new GuitarString(A_4);

    double B_4 = A_4 * Math.pow(1.059463, 2.0);
    GuitarString stringB4 = new GuitarString(B_4);

    double C_4 = A_4 * Math.pow(1.059463, 3.0);
    GuitarString stringC4 = new GuitarString(C_4);

    while (true) {
      // check if the user has typed a data; if so, process it
      if (StdDraw.hasNextKeyTyped()) {
        char key = StdDraw.nextKeyTyped();
        switch (key) {
          case 'a':
            stringA3.pluck();
            break;
          case 'b':
            stringB3.pluck();
            break;
          case 'c':
            stringC3.pluck();
            break;
          case 'd':
            stringD3.pluck();
            break;
          case 'e':
            stringE3.pluck();
            break;
          case 'f':
            stringF3.pluck();
            break;
          case 'g':
            stringG3.pluck();
            break;
          case 'A':
            stringA4.pluck();
            break;
          case 'B':
            stringB4.pluck();
            break;
          case 'C':
            stringC4.pluck();
            break;
          default:
            // no-op
        }
        //if(noteIndex != -1) strings[noteIndex].pluck();
      }

      // compute the superposition of samples
      double sample =
        stringA3.sample()
          + stringB3.sample()
          + stringC3.sample()
          + stringD3.sample()
          + stringE3.sample()
          + stringF3.sample()
          + stringG3.sample()
          + stringA4.sample()
          + stringB4.sample()
          + stringC4.sample();

      // play the sample on standard audio
      StdAudio.play(sample);

      // advance the simulation of each guitar string by one step
      stringA3.tic();
      stringB3.tic();
      stringC3.tic();
      stringD3.tic();
      stringE3.tic();
      stringF3.tic();
      stringG3.tic();
      stringA4.tic();
      stringB4.tic();
      stringC4.tic();

    }
  }

  // set the buffer to white noise
  private void pluck() {
    while (!rb.isEmpty()) rb.dequeue();

    for (int i = 0; i < capacity; i++) rb.enqueue(2.0 * (Math.random() - 0.5));


    // for (int i = 0; i < capacity; i++)
    //    rb.enqueue(Math.sin((2.0 * Math.PI * i) / capacity));

    // fundamental sinusoidal + noise
    // for (int i = 0; i < capacity; i++)
    //     rb.enqueue((0.5 - Math.random()) / 4 + Math.sin((2.0 * Math.PI * i) / capacity) / 2);

    // superposition of a bunch of stuff
    // for (int i = 0; i < capacity; i++) {
    //     double theta = (2.0 * Math.PI * i) / capacity;
    //     double phi = 0;
    //     int N = 10;
    //     for (int j = 1; j < N + 1; j++) {
    //         if (j % 2 == 0)
    //             phi += Math.random() * Math.sin(j * theta) / j;
    //         else
    //             phi += Math.random() * Math.cos(j * theta) / j;
    //     }
    //     rb.enqueue(phi / N);
    // }

    // sawtooth
    // for (int i = 0; i < capacity; i++) {
    //     rb.enqueue(-1.0 + 2.0 * (i / (double) capacity));
    // }

  }

  // return the current sample
  private double sample() {
    return rb.peek();
  }

  // advance the simulation one time step
  private void tic() {
    tics++;
    if (rb.isEmpty()) rb.enqueue(0);
    else rb.enqueue(0.996 * (rb.dequeue() + rb.peek()) / 2);
    //else rb.enqueue((rb.dequeue() + rb.peek()) / 2.0);
  }

}
