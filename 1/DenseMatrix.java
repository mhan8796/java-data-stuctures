import java.io.*; 
import java.util.*;

public class DenseMatrix<T> implements DynamicMatrix<T> {
  int rownum;
  int colnum;
  ArrayList<ArrayList <T>> matrix;
  T elem;
  
  //The number of initial rows r and columns c the matrix should contain.
  //Each element should be set to fillElem. Track this element as whenever rows/columns are added, new elements
  //will be set to fillElem.
  public DenseMatrix(int r, int c, T fillElem){
    matrix = new ArrayList<ArrayList<T>>();
    for(int i=0;i<r;i++){
      ArrayList<T> row = new ArrayList<T>();
      for(int j=0;j<c;j++){
      row.add(fillElem);
      }
    matrix.add(row);
    }
    rownum=r;
    colnum=c;
    elem=fillElem;
  }
  //The constructor should result in a DenseMatrix of the appropriate dimensions with all elements set to fillElem.
  
  public DenseMatrix(T fillElem){
   this(0,0,fillElem);
  }
  //A zero by zero (empty) matrix should be constructed. The fillElem should be retained for when the matrix grows.
  
  //These two methods return the number of rows and columns respectively in the matrix. An empty matrix has 
  //either 0 rows or 0 columns or both.
  public int rows(){
    return rownum;
  }
  public int cols(){
    return colnum;
  }
  
  //These two methods allow retrieval and alteration of the fillElem.
  public T getFillElem(){
    return elem;
  }
  
  public void setFillElem(T f){
    elem=f;
  }
  
  //Insert a row at the given row location. If the position is before the last row, rows i and below must
  //be shifted down.
  public void insertRow(int i){   
    if(i>rownum){
      throw new IndexOutOfBoundsException();
    }
    ArrayList<T> newrow = new ArrayList<T>();
    for(int n=0;n<colnum;n++)
      newrow.add(elem);
    matrix.add(i,newrow);
    rownum=rownum+1;
  }
  
  //Insert a column at the given position. If the position is before the last column, columns j 
  //and after must be shifted right.
  public void insertColumn(int j){
     if(j>colnum){
      throw new IndexOutOfBoundsException();
    }
     for(int n=0;n<rownum;n++){ 
       matrix.get(n).add(null);
       for(int m=colnum;m>j;m--){
      matrix.get(n).set(m,matrix.get(n).get(m-1));
       }
       matrix.get(n).set(j,elem);
     }
    colnum=colnum+1;
  }

  //Delete the inidicated row from the matrix. Rows below row i are shifted up. 
  //An IndexOutOfBounds exception is thrown if i is larger than rows()-1.
  public void removeRow(int i){
    if(i>(rownum-1)){
      throw new IndexOutOfBoundsException();
    }
    matrix.remove(i);
    rownum=rownum-1;
  }
  
  //check
  //Delete the inidicated column from the matrix. Columns right of column j are shifted left. 
  //An IndexOutOfBounds exception is thrown if j is larger than cols()-1.
  public void removeColumn(int j){                              
    if(j>(colnum-1)){
      throw new IndexOutOfBoundsException();
    }
    for(int n=0;n<rownum;n++){
      matrix.get(n).remove(j);
    }
    colnum--;
  }
  
  //The method simply returns the item at that pair of indices in the matrix or throws an IndexOutOfBounds exception 
  //if i exceeds rows()-1 or j exceeds cols()-1
  public T get(int i, int j){
    if(i>(rownum-1)||j>(colnum-1)){
      throw new IndexOutOfBoundsException();
    }
    return matrix.get(i).get(j);
  }
  
  //int i and int j, The row and column index where the element should be added.
  //T x, The value to be set at the given indices.
  //No exceptions based on the matrix being too small should be thrown by set. If the matrix is too small, 
  //expand it to the minimum dimension required to accommodate the new element.
  //Calls to addRow and addColumn may be useful here.
  public void set(int i, int j, T x){                           
    if(i+1>rownum)
      for(int m=rownum;m<i+1;m++){
      insertRow(m);
    }
    if(j+1>colnum)
     for(int m=colnum;m<j+1;m++){
     insertColumn(m);
    }
    matrix.get(i).set(j,x);
  }
  
  //The transpose() method is a mutator in that it changes the calling DenseMatrix just 
  //as set and insertRows/insertCols do.
  public void transpose(){
    ArrayList<ArrayList <T>> tmp = new ArrayList<ArrayList<T>>();
    int t;
    for(int m=0;m<colnum;m++){
      ArrayList<T> row=new ArrayList<T>();
      for (int n=0;n<rownum;n++){
        row.add(matrix.get(n).get(m));
      }
      tmp.add(row);
    }
    matrix=tmp;
    t=colnum;
    colnum=rownum;
    rownum=t;
  }
  //After calling, the matrix should be transposed with the number of rows and columns swapped 
  //and the elements rearranged

  //update: change the elements of a matrix by applying a function such as adding 1, doubling, or concatenating.
  public void update(Function1<T,T> f){
    for(int m=0; m<rownum; m++)
      for(int n=0; n<colnum;n++){
      T updatedVal = f.call(matrix.get(m).get(n));
      set(m,n,updatedVal);
    }}
  
  // create another matrix which has elements which are a function of the given matrix elements 
  //such as conversion to strings or doubling.
  public <Q> DenseMatrix<Q> map(Function1<Q,T> f){
    DenseMatrix<Q> mat = new DenseMatrix<Q>(rownum,colnum,f.call(matrix.get(0).get(0)));
    for(int i=0;i<rownum;i++){
      for(int j=0;j<colnum;j++){
        mat.set(i,j,f.call(matrix.get(i).get(j)));
      }}
    return mat;
  }
  
  //reduce: compute a function of all elements of the matrix such as sum or product
  public <W> W reduce(W init, Function2<W,W,T> f){
    for(int m=0; m<rownum; m++)
      for(int n=0; n<colnum;n++)
      init=f.call(init,matrix.get(m).get(n));
   return init;
  }     
}
    