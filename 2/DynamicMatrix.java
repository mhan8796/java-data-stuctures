// DO NOT MODIFY
// Methods of a Dynamic 2D matrix
// functional methods (map/reduce/update) are omitted
public interface DynamicMatrix<T>{
  public T getFillElem();	      // Retrieve the fill element
  public void setFillElem(T f);	      // Change the fill element
  public int rows();		      // Retrieves number of rows
  public int cols();		      // Retrieves number of columns
  public T get(int i, int j);	      // Retrive an element
  public void set(int i, int j, T x); // Set an element, grow dynamically
  public void insertRow(int i);	      // Insert row at given index
  public void insertColumn(int j);    // Insert column at given index
  public void removeRow(int i);	      // Remove the specified row
  public void removeColumn(int j);    // Remove the specified column
  public void transpose();	      // Transpose the matrix
}
