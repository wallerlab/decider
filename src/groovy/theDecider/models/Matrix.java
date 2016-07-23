package theDecider.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Matrix
 * 
 * @author suzanne
 *
 */
public interface Matrix {

	/**
	 * Returns the matrix
	 * 
	 * @return matrix
	 */
	<T> T getMatrix();

	/**
	 * Returns the matrix values
	 * 
	 * @return matrix
	 */
	List<?> getMatrixValues();
	
	/**
	 * Returns the dimensionality of the matrix
	 * 
	 * @return numDimensions
	 */
	int getMatrixDimensions();
	
	/**
	 * Returns value in matrix at indices
	 * 
	 * @param indices
	 * @return value
	 */
	<T> T getAt(ArrayList<Integer> indices);
	
	/**
	 * Gets the number of elements at given matrix depth
	 * 
	 * @param depth
	 * @return size of matrix at depth
	 */
	int getSizeAtDepth(int depth);
	
	/**
	 * Returns true if matrix is empty
	 * 
	 * @return isEmpty
	 */
	boolean isEmpty();
	
	/**
	 * Adds value to matrix at indices
	 * 
	 * @param value
	 * @param indices
	 * @return confirmation
	 */
	<T> boolean addAt(T value, ArrayList<Integer> indices);
	
	/**
	 * Adds value to matrix at indices
	 * 
	 * @param value
	 * @param indices
	 * @return confirmation
	 */
	<T> boolean appendAt(T value, ArrayList<Integer> indices);
	
	/**
	 * Returns all instances of matrix corresponding to valueName
	 * 
	 * @param valueName
	 * @return List of all values
	 */
	List<?> getAll(String valueName);
	
}
