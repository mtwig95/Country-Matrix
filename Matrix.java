/**
 * Represents information about a city Maman13 question2 2019a
 * @author Maytal Twig
 *version 07/12/18
 */
public class Matrix {

	private int[][] _matrix;
	final int maxVal=255;
	final int DEFAULT_VAL=0;

	/**
	 * Constructs a new Matrix from a two dimensional array.
	 * @param array the array from which to create the Matrix.
	 */

	public Matrix(int[][]array) {
		_matrix=new int[array.length][array[0].length];
		for (int i=0;i<array.length;i++){
			for(int j=0;j<array[0].length;j++){
				_matrix[i][j]=array[i][j];
			}
		}
	}
	/**
	 * Constructs a size1 by size2 Matrix of zero.
	 * @param size1 - rows 
	 * @param size2 - columns
	 */

	public Matrix(int size1,int size2) {
		_matrix=new int[size1][size2];
	}
	
	/**
	 * Flip the matrix horizontal
	 */
	
	public Matrix flipHorizontal(){
		int[][] newMat=new int [_matrix.length][_matrix[0].length];
		for (int i=0;i<_matrix.length;i++)
			for(int j=0;j<_matrix[0].length;j++)
				newMat[i][j]=_matrix[i][_matrix[0].length-j-1];
		return new Matrix(newMat);
	}
	
	/**
	 * Flip the matrix vertical
	 */

	public Matrix flipVertical(){
		int[][] newMat=new int [_matrix.length][_matrix[0].length];
		for (int i=0;i<_matrix.length;i++)
			for(int j=0;j<_matrix[0].length;j++)
				newMat[i][j]=_matrix[_matrix.length-i-1][j];
		return new Matrix(newMat);
	}
	/**
	 * rotate the matrix Clockwise.
	 */

	public Matrix rotateClockwise(){
		int[][] mat=new int [_matrix[0].length][_matrix.length];//Switches between row and column
		for (int i=0;i<_matrix.length;i++)
			for(int j=_matrix[0].length-1;j>=0;j--)
				mat[j][i]=_matrix[_matrix.length-1-i][j];
		Matrix newMat=new Matrix(mat);
		return newMat;
	}
	/**
	 * rotate the matrix Counter Clockwise.
	 */

	public Matrix rotateCounterClockwise(){
		int[][] mat=new int [_matrix[0].length][_matrix.length];
		for (int i=0;i<_matrix.length;i++)
			for(int j=_matrix[0].length-1;j>=0;j--)
				mat[j][i]=_matrix[i][_matrix[0].length-1-j];
		Matrix newMat=new Matrix(mat);
		return newMat;
	}
	public String toString() {
		String print="";
		for (int i=0;i<_matrix.length;i++){
			for(int j=0;j<_matrix[0].length;j++)
				print+=_matrix[i][j]+"\t";
			print=(print+"\n");
		}

		return print;
	}
}