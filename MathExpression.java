/**
 * An interface specifying mathematical expressions.
 * <p>
 * Created by knappa on 4/24/17.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public interface MathExpression {

  boolean isConstant();

  MathExpression evaluate(String variable, double value);

  double getValue();

}
