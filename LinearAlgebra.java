import java.util.Arrays;

/**
 * <code>LinearAlgebra</code> performs basic linear algebra routines.
 *
 * @author <a href="mailto:knapp@american.edu">Adam Knapp</a>
 * @version 1.0
 */
class LinearAlgebra {

    /**
     * Adds two vectors
     *
     * @param vec1 <code>double</code> vector
     * @param vec2 <code>double</code> vector
     * @return a new <code>double</code> array containing the result
     * of vector addition
     * @exception IllegalArgumentException if vectors are not of the
     * same length or are <code>null</code>
     */
    public static double[] vecAdd(double[] vec1, double[] vec2) {
	// TODO: implement
	return null;
    }

    /**
     * performs scalar multiplaction on a vector
     *
     * @param scalar <code>double</code> scalar
     * @param vec <code>double</code> vector
     * @return a new <code>double</code> array containing the result of scalar mult
     * @exception IllegalArgumentException if <code>vec</code> is
     * <code>null</code>
     */
    public static double[] scalarMult(double scalar, double[] vec) {
	// TODO: implement
	return null;
    }

    /**
     * adds two matrices
     *
     * @param mat1 <code>double</code> matrix
     * @param mat2 <code>double</code> matrix
     * @return a new <code>double</code> matrix, the result of matrix
     * addition
     * @exception IllegalArgumentException if matrices are wrong size
     * or <code>null</code>
     */
    public static double[][] matAdd(double[][] mat1, double[][] mat2) {
	// TODO: implement
	return null;
    }

    /**
     * performs scalar multiplication on a matrix
     *
     * @param scalar a <code>double</code> scalar value
     * @param mat a <code>double</code> matrix
     * @return a new <code>double</code> matrix
     * @exception IllegalArgumentException if <code>mat</code> or any
     * of its columns is <code>null</code>
     */
    public static double[][] scalarMult(double scalar, double[][] mat) {
	// TODO: implement
	return null;
    }

    /**
     * performs matrix-vector multiplication
     *
     * @param mat a <code>double</code> matrix
     * @param vec a <code>double</code> vector
     * @return a <code>double[]</code> vector, the result of
     * multiplication
     * @exception IllegalArgumentException if the number of columns of
     * <code>mat</code> is not equal to the number of rows of
     * <code>vec</code>, or either is <code>null</code>
     */
    public static double[] matVecMult(double[][] mat, double[] vec) {
	// TODO: implement
	return null;
    }

    /**
     * Unit tests
     *
     * @param args ignored
     */
    public static void main(String[] args) {

	double[] vecA = { 1.0, -1.0 };
	double[] vecB = { 0.7071, 0.7071 };
	double[][] matA = { {0.7071, -0.7071}, {0.7071,0.7071} };
	double[][] matB = { {0.0, -1.0}, {1.0, 0.0} };

	// should print: [1.7071, -0.29290000000000005]
	System.out.println(Arrays.toString(vecAdd(vecA,vecB)));

	// should print: [0.9999808199999999, 0.9999808199999999]
	System.out.println(Arrays.toString(scalarMult(1.4142,vecB)));

	// should print: [[0.7071, -1.7071], [1.7071, 0.7071]]
	System.out.println(Arrays.deepToString(matAdd(matA,matB)));

	// should print: [[0.0, 1.0], [-1.0, 0.0]]
	System.out.println(Arrays.deepToString(scalarMult(-1.0,matB)));
	
	// should print: [1.1412, 0.0]
	System.out.println(Arrays.toString(matVecMult(matA,vecA)));	

    }
    
}
