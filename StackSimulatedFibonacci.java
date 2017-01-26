import java.util.Stack;

/**
 * An exercise in tree recursion via stack simulation. This is, of course, not how one should go about computing the
 * Fibonacci numbers.
 * <p>
 * The point of this is that the function-call stack is of limited size in Java. This program simulates the recursive
 * function calls, allowing a maximum depth equal to the maximum capacity of {@code Stack}.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 1.0
 */
public class StackSimulatedFibonacci {

  public static void main(String[] args) {

    System.out.println(compute(Integer.valueOf(args[0])));

  }

  private static int compute(int n) {

    Stack<State> stateStack = new Stack<>();
    stateStack.push(State.exitState());

    State state = State.call(n);
    int returnValue = 0; // dummy init

    do {

      System.out.println();
      System.out.println("current state = " + state);
      System.out.println("stack = " + stateStack);

      if (state.parameter == 0) {

        // compute return value fib(0)=0
        returnValue = 0;
        // return to caller
        System.out.println("returning " + returnValue);
        state = stateStack.pop();

      } else if (state.parameter == 1) {

        // compute return value fib(1)=1
        returnValue = 1;
        // return to caller
        System.out.println("returning " + returnValue);
        state = stateStack.pop();

      } else {
        switch (state.position) {

          case ENTRY:
            // store the state, with an update to position
            stateStack.push(state.nextPosition());
            // make recursive call to fib(n-1)
            state = State.call(state.parameter - 1);
            System.out.println("recursing, state = " + state);
            break;

          case RETURN_FROM_FIRST_RECURSION:
            // store return value
            state.setFirstValue(returnValue);
            System.out.println("setting fv=" + returnValue);
            // store the state, with an update to position
            stateStack.push(state.nextPosition());
            // make recursive call to fib(n-2)
            state = State.call(state.parameter - 2);
            System.out.println("recursing, state = " + state);
            break;

          case RETURN_FROM_SECOND_RECURSION:
            // store return value
            state.setSecondValue(returnValue);
            System.out.println("setting sv=" + returnValue);
            // compute return value
            returnValue = state.getFirstValue() + state.getSecondValue();
            System.out.println("returning " + returnValue);
            state = stateStack.pop(); // return to caller
            break;
        }
      }

    } while (state.position != Position.EXIT);

    System.out.println();

    return returnValue;
  }

  private enum Position {
    EXIT("exit"),
    ENTRY("entry"),
    RETURN_FROM_FIRST_RECURSION("recursing"),
    RETURN_FROM_SECOND_RECURSION("return");

    private final String name;

    Position(String s) {
      this.name = s;
    }

    @Override
    public String toString() {
      return this.name;
    }
  }

  private static class State {
    int parameter;
    Position position;
    boolean firstValueSet = false;
    boolean secondValueSet = false;
    private int firstValue;
    private int secondValue;

    State(int param,
          Position position,
          int first,
          boolean firstSet,
          int second,
          boolean secondSet) {
      this.parameter = param;
      this.position = position;
      this.firstValue = first;
      this.firstValueSet = firstSet;
      this.secondValue = second;
      this.secondValueSet = secondSet;
    }

    static State exitState() {
      return new State(
        0,
        Position.EXIT,
        0,
        false,
        0,
        false);
    }

    static State call(int parameter) {
      return new State(
        parameter,
        Position.ENTRY,
        0,
        false,
        0,
        false);
    }

    State nextPosition() {
      if (this.position == Position.ENTRY) {
        return new State(
          this.parameter,
          Position.RETURN_FROM_FIRST_RECURSION,
          this.firstValue,
          this.firstValueSet,
          this.secondValue,
          this.secondValueSet);
      } else if (this.position == Position.RETURN_FROM_FIRST_RECURSION) {
        return new State(
          this.parameter,
          Position.RETURN_FROM_SECOND_RECURSION,
          this.firstValue,
          this.firstValueSet,
          this.secondValue,
          this.secondValueSet);
      } else {
        System.err.println("not supposed to be called");
        return null;
      }
    }

    @Override
    public boolean equals(Object o) {
      if (!(o instanceof State)) return false;
      State other = (State) o;
      return this.parameter == other.parameter
        && this.position == other.position
        && this.firstValue == other.firstValue
        && this.firstValueSet == other.firstValueSet
        && this.secondValue == other.secondValue
        && this.secondValueSet == other.secondValueSet;
    }

    @Override
    public String toString() {
      if (this.position == Position.EXIT) {
        return Position.EXIT.toString();
      } else {
        StringBuilder sb = new StringBuilder();
        sb.append("(n=")
          .append(parameter)
          .append(",pos=")
          .append(position);

        if (this.firstValueSet) {
          sb.append(",fv=")
            .append(firstValue);
        }

        if (this.secondValueSet) {
          sb.append(",sv=")
            .append(secondValue);
        }

        sb.append(")");
        return sb.toString();
      }

    }

    int getFirstValue() {
      return firstValue;
    }

    void setFirstValue(int firstValue) {
      this.firstValue = firstValue;
      this.firstValueSet = true;
    }

    int getSecondValue() {
      return secondValue;
    }

    void setSecondValue(int secondValue) {
      System.out.println("setting sv=" + secondValue);
      this.secondValue = secondValue;
      this.secondValueSet = true;
    }
  }

}
