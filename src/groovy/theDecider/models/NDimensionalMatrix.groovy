package theDecider.models

import java.util.stream.Stream
import javax.annotation.Resource

/**
 * N-Dimensional matrix
 * 
 * @author suzanne
 *
 */
class NDimensionalMatrix implements Matrix{
	
	@Resource
	def nameMap
	
	private Map<ArrayList<Integer>,?> matrix = [:]
	private int dimensions
	
	/**
	 * Constructs an empty NDimensionalMatrix
	 * 
	 */
	public NDimensionalMatrix(){
		this.matrix = [:]
		this.dimensions = 0
	}
	
	/**
	 * Constructs an NDimensionalMatrix from a Map<ArrayList<Integer>,?> 
	 * 
	 * @param payoffs
	 */
	public NDimensionalMatrix(Map<ArrayList<Integer>,?> payoffs){
		this.matrix = payoffs
		if(payoffs.isEmpty()){
			this.dimensions = 0
		}else{
			this.dimensions = payoffs.keySet()[0].size()
		}
	}
	
	/**
	 * Gets the matrix
	 *
	 */
	public <T> T getMatrix(){
		return this.matrix
	}
	
	/**
	 * Gets the matrix values
	 *
	 */
	public List<?> getMatrixValues(){
		return new ArrayList(this.matrix.values())
	}
	
	/**
	 * Returns the dimensionality of the matrix
	 * 
	 */
	public int getMatrixDimensions(){
		return this.dimensions
	}
	
	/**
	 * Gets matrix value at indices provided
	 *
	 * @param indices
	 * @return value at indices
	 */
	public <T> T getAt(ArrayList<Integer> indices){
		if(this.matrix[indices]){
			return this.matrix[indices]
		}
		return 0
	}
	
	/**
	 * Gets the number of elements at given matrix depth
	 * 
	 * @param depth
	 * @return size of matrix at depth
	 */
	public int getSizeAtDepth(int depth){
		Set keySet = this.matrix.keySet()
		Long numAtIndex = keySet.stream().mapToInt({entry -> entry[depth]})
			.distinct().count()
		return numAtIndex
	}
	
	/**
	 * Checks if the matrix is empty
	 * 
	 * @return empty
	 */
	public boolean isEmpty(){
		return this.matrix.isEmpty()
	}
	
	/**
	 * Adds payoff to matrix at indices
	 *
	 * @param value
	 * @param indices
	 * @return confirmation
	 */
	public <T> boolean addAt(T value, ArrayList<Integer> indices){
		
		if(this.dimensions == 0){
			this.dimensions = indices.size()
		}
		if(this.dimensions != indices.size()){
			return false
		}
		List<Integer> newIndices = new ArrayList<Integer>()
		for(int i = 0; i < indices.size(); i++){
			newIndices[i] = indices[i]
		}
		this.matrix[newIndices] = value
		return true
	}

	/**
	 * Appends to matrix at indices
	 * 
	 * @param value
	 * @param indices
	 * @return confirmation
	 */
	@Override
	public <T> boolean appendAt(T value, ArrayList<Integer> indices) {
		if(!this.matrix[indices]){
			this.addAt([value], indices)
		}
		else if(this.dimensions != indices.size()){
			return false;
		}
		else{
			this.matrix[indices].add(value)
			return true;
		}
		return false;
	}
	
	/**
	 * Returns all instances of matrix corresponding to valueName
	 * 
	 * @param valueName
	 * @return list of values
	 */
	public List<?> getAll(String valueName){
		
		int index = nameMap[valueName]
		List systemList = Collections.synchronizedList([])
		Stream stream = this.matrix.keySet().parallelStream().filter({key ->
				key[-1] == index})
		if(this.matrix.values()[0] instanceof String){
			stream.forEach({key -> systemList.add(this.matrix[key].toLowerCase())})
		}
		else{
			stream.forEach({key -> systemList.add(this.matrix[key])})
		}
		return systemList
	}
	
}
