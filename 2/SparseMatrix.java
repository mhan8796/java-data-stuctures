import java.util.*;
// Fill in your class definition below.  Do not change the Head and
// Node classes.
public class SparseMatrix<T> implements DynamicMatrix<T>{

  // Row and Column Headers. Tracks indices of the col or row, pointer
  // to the start of row/column
  private static class Head<T> {
    public int index;  // Index of row/col
    public Node<T> nodes; // Dummy node at start of row/column; add after first
    public Head(int i){
      this.index = i;
      this.nodes = new Node<T>(); // Headed lists with dummy node first node
    }
  }

  // Singly linked nodes, accross to the right for rows, down for
  // columns.
  private static class Node<T>{
    public Head rowHead; // My row head
    public Head colHead; // My col head
    public Node<T> right;
    public Node<T> down;
    public T data;
  }
  
  
  ArrayList<Head<T>> rows;
  ArrayList<Head<T>> cols;
  int rownum=0;
  int colnum=0;
  T Elem;
  
  
//In addition to the fillElem, this constructor creates a matrix of the given number of rows and columns 
  //with each element implicitly defined as fillElem
  public SparseMatrix(int r, int c, T fillElem){
    rownum=r;
    colnum=c;
    rows=new ArrayList<Head<T>>();
    cols=new ArrayList<Head<T>>();
    this.Elem=fillElem;
    for(int i=0; i<rownum ;i++)
      rows.add(new Head<T>(i));
    for(int j=0; j<colnum ;j++)
      cols.add(new Head<T>(j));
  }
  //The constructor should result in a SparseMatrix of the appropriate dimensions with all elements set to fillElem.

  
  //A 1-parameter constructor which creates an empty (0 by 0) matrix. The fillElem is specified as the sole parameter.
  public SparseMatrix(T fillElem){
    this(0,0,fillElem);
  }
  //A zero by zero (empty) matrix should be constructed. The fillElem should be retained for when the matrix grows.


  //Return the number of rows
  public int rows(){
    return rownum;
  }
  
  
  //Return the number of columns
  public int cols(){
    return colnum;
  }
    
  
  public T getFillElem(){
    return Elem;
  }
  //Return the fill element specified at creation time for the matrix.


  
  //This method must be present but should throw an UnsupportedOperationException for efficiency reasons.
  public void setFillElem(T f){
    throw new UnsupportedOperationException();
  }


  //Inserts a row, shift rows down if interior
  public void insertRow(int i){
    for(int num=i;num<rownum;num++){
      rows.get(num).index=num+1; 
    }
    rows.add(i,new Head<T>(i));    
    rownum++;
  }
  
  
  //Inserts a column, shift columns if interior
  public void insertColumn(int j){
     for(int num=j;num<colnum;num++){
      cols.get(num).index=num+1; 
    }
    cols.add(j,new Head<T>(j));    
    colnum++;
  }
  
  
  //Set a specific element to a value; grow the matrix if required. 
  //If the e is the fill element, ensure the node is deleted.
  public void set(int i, int j, T x){
    if(i+1>rownum)
      for(int num=rownum;num<i+1;num++)
      insertRow(num);
    if(j+1>colnum)
      for(int num=colnum;num<j+1;num++)
      insertColumn(num);
    
    if(x==Elem)
      this.removeElement(i,j);
    
    else{
      Node<T> tmp=new Node<T>();
      tmp=rows.get(i).nodes.right;
      while(tmp!=null){
        if(tmp.colHead.index==j){
          tmp.data=x;
          return;}
        tmp=tmp.right;}
    
      Node<T> node=new Node<T>();
      node.data=x;
      node.rowHead=rows.get(i);
      node.colHead=cols.get(j);
    
      Node<T> tmp2=new Node<T>();
      tmp2=rows.get(i).nodes;
      tmp=rows.get(i).nodes.right;
      while(tmp!=null){
        if(tmp.colHead.index>j){
          tmp2.right=node;
          node.right=tmp;
          break;}
        tmp2=tmp;
        tmp=tmp.right;}
      if(tmp==null)
        tmp2.right=node;
    
      tmp2=cols.get(j).nodes;
      tmp=cols.get(j).nodes.down;
      while(tmp!=null){
        if(tmp.rowHead.index>i){
          tmp2.down=node;
          node.down=tmp;
          break;}
      tmp2=tmp;
      tmp=tmp.down;}
    if(tmp==null)
      tmp2.down=node;
  }}
  
  
  //Retrieve an element; out of bounds possible. If the element is not explicitly defined, return the fillElement, 
  //otherwise return the found data found at the Node.
  public T get(int i, int j){
    if(i>rownum-1||j>colnum-1)
      throw new IndexOutOfBoundsException();
    Node<T> tmp=null;
    tmp=rows.get(i).nodes.right;
    while(tmp!=null){
      if(tmp.colHead.index==j)
        return tmp.data;
      else
        tmp=tmp.right;}
    return Elem;
  }
  
  
  //Make sure that the specified element is no longer present and is thus yields the fill element on subsequent gets.
  public void removeElement(int i, int j){
    if(i>rownum-1||j>colnum-1)
      throw new IndexOutOfBoundsException();
    Node<T> tmp1=null;
    Node<T> tmp2=null;
    tmp1=rows.get(i).nodes;
    tmp2=rows.get(i).nodes.right;
    while(tmp2!=null){
      if(tmp2.colHead.index==j){
        tmp1.right=tmp2.right;
      }
      tmp1=tmp2;
      tmp2=tmp2.right;
    }
    
    tmp1=cols.get(j).nodes;
    tmp2=cols.get(j).nodes.down;
    while(tmp2!=null){
      if(tmp2.rowHead.index==i){
        tmp1.down=tmp2.down;
      }
      tmp1=tmp2;
      tmp2=tmp2.down;
    }}   
  
  
  //Removes a row, shift rows down if interior
  public void removeRow(int i){
    Node<T> tmp1=null;
    Node<T> tmp2=null;
    for(int num=0;num< colnum;num++){
    tmp1=cols.get(num).nodes;
    tmp2=cols.get(num).nodes.down;
    while(tmp2!=null){
      if(tmp2.rowHead.index>=i){
        tmp1.down=tmp2;
      }
      tmp1=tmp2;
      tmp2=tmp2.down;
    }} 
    rows.remove(i);
    rownum--;
    for(int num=i;num<rownum;num++)
      rows.get(num).index--;
  }
  
  
  //Removes a column, shift columns if interior
  public void removeColumn(int i){
    Node<T> tmp1=null;
    Node<T> tmp2=null;
    for(int num=0;num< rownum;num++){
    tmp1=rows.get(num).nodes;
    tmp2=rows.get(num).nodes.right;
    while(tmp2!=null){
      if(tmp2.colHead.index>=i){
        tmp1.right=tmp2;
      }
      tmp1=tmp2;
      tmp2=tmp2.right;
    }} 
    cols.remove(i);
    colnum--;
    for(int num=i;num<colnum;num++)
      cols.get(num).index--;
  }
  
  
  //This method will produce an ArrayList of all elements in the matrix along with their row/column indices.
  public ArrayList<Element<T>> allElements(){
    ArrayList<Element<T>> al=new ArrayList<Element<T>>(); 
    Node<T> tmp =null;
    for(int num=0;num<rownum;num++){
      tmp=rows.get(num).nodes.right;
      while(tmp!=null){
        al.add(new Element<T>(tmp.rowHead.index,tmp.colHead.index, tmp.data));
        tmp=tmp.right;
      }}  
      return al;}
  //for the matrix which will return and ArrayList of Element objects populated from the matrix. 
  //Only non-fill elements that are explicitly defined (using set) should be returned in the ArrayList.


  //Transpose the matrix
  public void transpose(){
    int tm1;
    int tm3;
    ArrayList<Head<T>> tm2 = new ArrayList<Head<T>>();
    Node<T> tmp=null;
    Node<T> tm=null;
    for(int i=0;i<rownum;i++){
     tmp=rows.get(i).nodes.right;
     while(tmp!=null){
       tm=tmp.right;
       tmp.right=tmp.down;
       tmp.down=tm;
       tm3=tmp.rowHead.index;
       tmp.rowHead=cols.get(tmp.colHead.index);
       tmp.colHead=rows.get(tm3);
       tmp=tmp.down;
     }}
    for(int i=0;i<rownum;i++){
      rows.get(i).nodes.down=rows.get(i).nodes.right;
      rows.get(i).nodes.right=null;}
    for(int i=0;i<colnum;i++){
      cols.get(i).nodes.right=cols.get(i).nodes.down;
      cols.get(i).nodes.down=null;}
    
   tm2=rows;
   rows=cols;
   cols=tm2;
   tm1=rownum;
   rownum=colnum;
   colnum=tm1;}
  //It can also be done in place: only a constant amount of additional memory is required to finish the 
  //transposition regardless of how big the matrix is. Implement this version of transposition which is 
  //far more efficient than the above slow approach.
}

