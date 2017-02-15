import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * Takes a single command line argument, an integer n, and prints
 * 1) n!
 * 2) the nth fibonacci number
 * <p>
 * Intended as an example as to how to implement recursion via lambdas. I'm not really in love
 * with suppressing warnings here, but there was no obvious way (to me) of giving the recursive
 * definitions a more informative type than {@code BiFunction<BiFunction, Integer, Integer>}.
 * The type {@code BiFunction<BiFunction<?, ?, Integer>, Integer, Integer>}, which at least
 * specifies the output type of the interior bifunction, is uncompilable.
 * <p>
 * Created by <a href="mailto:knapp@american.edu">Adam Knapp</a> on 1/27/17.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class RecursiveFunctionsViaLambdas {

  @SuppressWarnings("unchecked")
  public static void main(String[] args) {

    int n = Integer.parseInt(args[0]);

    // n!

    BiFunction<BiFunction, Integer, Integer> factorialRecursiveDefinition
        = (f, k) -> (k == 0) ? 1 : k * (int) f.apply(f, k - 1);

    Function<Integer, Integer> factorial
        = k -> factorialRecursiveDefinition.apply(factorialRecursiveDefinition, k);

    System.out.println(factorial.apply(n));

    // fib(n)

    BiFunction<BiFunction, Integer, Integer> fibonacciRecursiveDefinition
        = (f, k) -> {
      if (k == 0) { return 0; }
      if (k == 1) { return 1; }
      return (int) f.apply(f, k - 1) + (int) f.apply(f, k - 2);
    };

    Function<Integer, Integer> fibonacci
        = k -> fibonacciRecursiveDefinition.apply(fibonacciRecursiveDefinition, k);

    System.out.println(fibonacci.apply(n));

  }

}
