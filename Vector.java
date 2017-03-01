/**
 * An interface for vectors.
 * <p>
 * Created by <a href="mailto:knapp@american.edu">Adam Knapp</a> on 2/22/17.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public interface Vector {

  /**
   * Accessor for vector entries
   *
   * @param index index to access
   * @return value of vector in index <code>index</code>
   */
  double getEntry(int index);

  /**
   * @return dimension of vector
   */
  int dimension();

  /**
   * Vector addition
   *
   * @param other a {@code Vector} to add to this one
   * @return result of addition
   */
  Vector add(Vector other);

  /**
   * scalar multiplication
   *
   * @param scalar scalar quantity
   * @return result of scalar multiplication
   */
  Vector mult(double scalar);

  /**
   * right multiplication by a matrix
   *
   * @param mat a <code>DenseMatrix</code> with compatible dimension
   * @return result of <code>this*mat</code>
   */
  Vector mult(Matrix mat);

  /**
   * @return L^2 length (square root of sum of squares) of <code>this</code>
   */
  default double len() {
    return Math.sqrt(lenSq());
  }

  /**
   * Return sum of squares of all entries. (equal to squared length)
   *
   * @return squared length of <code>this</code>
   */
  double lenSq();

  /**
   * computes the dot product
   *
   * @param other another <code>DenseVector</code> of the same dimension
   * @return dot product of <code>this</code> and <code>other</code>
   */
  double dot(Vector other);

}
