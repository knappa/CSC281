/**
 * An interface for matrices.
 * <p>
 * Created by <a href="mailto:knapp@american.edu">Adam Knapp</a> on 2/22/17.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 0.1
 */
public interface Matrix {

  /**
   * Accessor for matrix entries
   *
   * @param row row of desired entry
   * @param col column of desired entry
   * @return value in desired entry
   * @throws IllegalArgumentException if the caller requests an out of bound row or column
   */
  double getEntry(int row, int col);

  /**
   * @return number of rows in this matrix
   */
  int rows();

  /**
   * @return number of columns in this matrix
   */
  int cols();

  /**
   * perform matrix addition, if well-defined
   *
   * @param other another matrix
   * @return result of addition
   * @throws IllegalArgumentException is {@code other} is null, or if the dimensions of {@code this} and {@code other}
   *                                  do not match.
   */
  Matrix add(Matrix other);

  /**
   * perform scalar multiplication
   *
   * @param scalar scalar value
   * @return <code>scalar*this</code>
   */
  Matrix mult(double scalar);

  /**
   * perform matrix-matrix multiplication
   *
   * @param other another matrix
   * @return <code>this*other</code>
   * @throws IllegalArgumentException is {@code other} is null, or if the number of columns of {@code this} and the rows
   *                                  of {@code other} do not match.
   */
  Matrix mult(Matrix other);

  /**
   * perform matrix-vector multiplication
   *
   * @param vec a vector
   * @return <code>this*vec</code>
   * @throws IllegalArgumentException is {@code vec} is null, or if the number of columns of {@code this} and the
   *                                  dimension of {@code vec} do not match.
   */
  Vector mult(Vector vec);

}
