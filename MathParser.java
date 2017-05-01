import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * A parser for mathematical expressions.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public class MathParser {

  private static final Set<String> variableNames = new HashSet<>();

  /**
   * characters which we will not allow in variable names
   */
  private static final char[] reservedCharacters =
    {'+', '-', '*', '/', '^', '=', '<', '>', '(', ')'};

  /**
   * registers a variable name with the parser. names cannot contain reserved characters
   *
   * @param name variable name
   * @return true/false as the variable was added or not
   */
  public static boolean registerVariableName(String name) {

    // name cannot contain math chars
    for (char c : reservedCharacters) {
      if (name.indexOf(c) != -1) {return false;}
    }

    // don't re-add names
    if (variableNames.contains(name)) { return false; }

    variableNames.add(name);
    return true;
  }

  /**
   * parses expression as a sum
   *
   * @param expression string to parse, contains a '+'
   * @return an optional containg a math expression, if the parse was successful
   */
  private static Optional<MathExpression> parseSum(String expression) {
    int sumIndex = expression.indexOf('+');
    assert sumIndex != -1;

    Optional<MathExpression> lhs = MathParser.parse(expression.substring(0, sumIndex));
    if (lhs.isPresent()) {
      Optional<MathExpression> rhs = MathParser.parse(expression.substring(sumIndex + 1));
      if (rhs.isPresent()) {
        return Optional.of(new Sum(lhs.get(), rhs.get()));
      }
    }

    return Optional.empty();
  }

  /**
   * parses expression as a difference
   *
   * @param expression string to parse, contains a '-'
   * @return an optional containg a math expression, if the parse was successful
   */
  private static Optional<MathExpression> parseDifference(String expression) {
    // TODO
    return Optional.empty();
  }

  /**
   * Parses a string into a mathematical expression
   *
   * @param expression string to parse
   * @return an optional containg a math expression, if the parse was successful
   */
  public static Optional<MathExpression> parse(String expression) {

    System.out.println("parsing: " + expression);

    // can't parse an empty string
    if (expression.length() == 0) { return Optional.empty(); }

    // look among variable names
    for (String varName : variableNames) {
      if (expression.equals(varName)) {
        return Optional.of(new Variable(varName));
      }
    }

    // determine if this is a number literal
    try {
      double value = Double.parseDouble(expression);
      return Optional.of(new Constant(value));
    } catch (NumberFormatException ignored) {}

    // attempt to parse as a sum or difference (left to right precedence)
    int sumIndex = expression.indexOf('+');
    int diffIndex = expression.indexOf('-');
    if (sumIndex != -1 && diffIndex == -1) {
      // have a '+' but no '-', so parse as a sum
      Optional<MathExpression> parseAttempt = MathParser.parseSum(expression);
      if (parseAttempt.isPresent()) { return parseAttempt; }
    } else if (sumIndex == -1 && diffIndex != -1) {
      // have a '-' but no '+', so parse as a difference
      Optional<MathExpression> parseAttempt = MathParser.parseDifference(expression);
      if (parseAttempt.isPresent()) { return parseAttempt; }
    } else if (sumIndex >= 0 && sumIndex < diffIndex) {
      // order of operations is left to right, so we the *leftmost* operator first: difference
      Optional<MathExpression> parseAttempt = MathParser.parseDifference(expression);
      if (parseAttempt.isPresent()) { return parseAttempt; }
      // if that fails, then try difference
      parseAttempt = MathParser.parseSum(expression);
      if (parseAttempt.isPresent()) { return parseAttempt; }
    } else if (diffIndex >= 0 && diffIndex < sumIndex) {
      // order of operations is left to right, so we the *leftmost* operator first: sum
      Optional<MathExpression> parseAttempt = MathParser.parseSum(expression);
      if (parseAttempt.isPresent()) { return parseAttempt; }
      // if that fails, then try sum
      parseAttempt = MathParser.parseDifference(expression);
      if (parseAttempt.isPresent()) { return parseAttempt; }
    }

    // attempt to parse as a multiplication or division (left to right precedence)
    // TODO

    // attempt to parse as exponentiation
    int expIndex = expression.indexOf('^');
    if (expIndex != -1) {
      Optional<MathExpression> base =
        MathParser.parse(expression.substring(0, expIndex));
      if (base.isPresent()) {
        Optional<MathExpression> exponent =
          MathParser.parse(expression.substring(expIndex + 1));
        if (exponent.isPresent()) {
          return Optional.of(new Exponential(base.get(), exponent.get()));
        }
      }
    }

    // attempt to parse parentheses
    if (expression.charAt(0) == '('
          && expression.charAt(expression.length() - 1) == ')') {
      Optional<MathExpression> subExpression =
        MathParser.parse(expression.substring(1, expression.length() - 1));
      if (subExpression.isPresent()) { return subExpression; }
    }

    // if we haven't returned by now, all known parses failed
    return Optional.empty();

  }

  public static void main(String[] args) {

    MathParser.registerVariableName("x");

    MathParser.parse("x^2+1")
      .ifPresent(me -> System.out.println(
        me.evaluate("x", 3)
          .getValue()));

    // won't parse as we haven't registered "t" as a variable name
    MathParser.parse("t^2+1")
      .ifPresent(me -> System.out.println(
        me.evaluate("t", 3)
          .getValue()));

    // won't parse until '*' is implemented
    MathParser.parse("(1+1)*(-1)")
      .ifPresent(me -> System.out.println(me.getValue()));
  }

//  private static class Multiplication implements MathExpression {
//    // TODO
//  }
//
//  private static class Division implements MathExpression {
//    // TODO
//  }
//
//  private static class Subtraction implements MathExpression {
//    // TODO
//  }

  /**
   * A class to encode an expression with an exponential as its top-most operation
   */
  private static class Exponential implements MathExpression {
    final MathExpression base, exponent;

    public Exponential(MathExpression base, MathExpression exponent) {
      this.base = base;
      this.exponent = exponent;
    }

    @Override
    public boolean isConstant() {
      return this.base.isConstant() && this.exponent.isConstant();
    }

    @Override
    public MathExpression evaluate(String variable, double value) {
      MathExpression baseEvald = this.base.evaluate(variable, value);
      MathExpression exponentEvald = this.exponent.evaluate(variable, value);
      // if we can simplify to a constant, do so.
      if (baseEvald.isConstant() && exponentEvald.isConstant()) {
        return new Constant(Math.pow(baseEvald.getValue(), exponentEvald.getValue()));
      } else {
        return new Exponential(baseEvald, exponentEvald);
      }
    }

    @Override
    public double getValue() {
      if (this.isConstant()) {
        return Math.pow(base.getValue(), exponent.getValue());
      } else {
        throw new NonConstantExpressionException();
      }
    }
  }

  /**
   * A class to encode an expression with a sum as its top-most operation
   */
  private static class Sum implements MathExpression {
    final MathExpression lhs, rhs;

    public Sum(MathExpression lhs, MathExpression rhs) {
      this.lhs = lhs;
      this.rhs = rhs;
    }

    @Override
    public boolean isConstant() {
      return this.lhs.isConstant() && this.rhs.isConstant();
    }

    @Override
    public MathExpression evaluate(String variable, double value) {
      MathExpression lhsEvald = this.lhs.evaluate(variable, value);
      MathExpression rhsEvald = this.rhs.evaluate(variable, value);
      if (lhsEvald.isConstant() && rhsEvald.isConstant()) {
        return new Constant(lhsEvald.getValue() + rhsEvald.getValue());
      } else {
        return new Sum(lhsEvald, rhsEvald);
      }
    }

    @Override
    public double getValue() {
      if (this.isConstant()) {
        return lhs.getValue() + rhs.getValue();
      } else {
        throw new NonConstantExpressionException();
      }
    }
  }

  /**
   * A class to encode an expression which is a named variable
   */
  private static class Variable implements MathExpression {

    final String name;

    public Variable(String name) {
      this.name = name;
    }

    @Override
    public boolean isConstant() {
      return false;
    }

    @Override
    public MathExpression evaluate(String variable, double value) {
      if (name.equals(variable))
        return new Constant(value);
      else
        return this;
    }

    @Override
    public double getValue() {
      throw new NonConstantExpressionException();
    }
  }

  /**
   * A class to encode an expression which is a constant
   */
  private static class Constant implements MathExpression {
    final double value;

    public Constant(double value) {
      this.value = value;
    }

    @Override
    public boolean isConstant() {
      return true;
    }

    @Override
    public MathExpression evaluate(String variable, double value) {
      return this;
    }

    @Override
    public double getValue() {
      return value;
    }
  }

  static class NonConstantExpressionException extends RuntimeException {}
}
