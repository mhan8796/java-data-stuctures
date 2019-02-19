// A battery of tests to exercise the SparseMatrix class of HW2
// NOTE: The public tests DO NOT exercise all required methods.
// NOTE: Add your own tests to ensure code quality.
// 
// Command line instructions
// 
// Compile
// lila [ckauffm2-hw1]% javac -cp .:junit-4.11.jar *.java
// 
// Run tests
// lila [ckauffm2-hw1]% java -cp .:junit-4.11.jar SparseMatrixTest
// 
// For IDEs, consult documentation to see how to run junit tests


// org.junit.Assert.assertEquals - statically imported
// assertEquals(message, expected, actual);
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class SparseMatrixTest
{
  /*Main method runs tests in this file*/ 
  public static void main(String args[])
  {
    org.junit.runner.JUnitCore.main("SparseMatrixTest");
  } 

  // Compare a SparseMatrix to standard matrix for equal size and
  // elements
  private <T> void compareMatrices(SparseMatrix<T> x, T[][] y){
    int r = y.length;
    int c = r > 0 ? y[0].length : 0;
    assertEquals("Row count wrong ",r,x.rows());
    assertEquals("Column count wrong",c,x.cols());
    for(int i=0; i<r; i++){
      for(int j=0; j<c; j++){
 assertEquals(String.format("Element (%d,%d) is wrong",i,j),
       y[i][j],x.get(i,j));
      }
    }
  }
  // Checks that the fill element of a SparseMatrix has been correctly
  // set
  private <T> void checkFill(SparseMatrix<T> x, T e){
    assertEquals("Fill element not correct",e,x.getFillElem());
  }
    
  // Run before testing begins
  @BeforeClass
  public static void setUp() throws Exception
  {
    System.out.println("***NOTE: The public tests DO NOT exercise all required methods.\n***NOTE: Add your own tests to ensure code quality.");
  }
  
  @Test
  public void implements_DynamicMatrix() throws Exception{
    SparseMatrix<Double> x = new SparseMatrix<Double>(0.0);
    DynamicMatrix dm = (DynamicMatrix) x;
  }

  @Test
  public void constructor1_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(0.0);
    checkFill(x, new Double(0.0));
    Double y[][] = new Double[0][0];
    compareMatrices(x,y);
  }
  @Test
  public void constructor1_2() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(1.0);
    checkFill(x, new Double(1.0));
    Double y[][] = new Double[0][0];
    compareMatrices(x,y);
  }
  @Test
  public void constructor1_3() throws Exception {
    SparseMatrix<String> x = new SparseMatrix<String>("Hi");
    checkFill(x, "Hi");
    String y[][] = new String[0][0];
    compareMatrices(x,y);
  }
  @Test
  public void constructor2_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 0.0);
    Double y[][] = new Double[][]{
 {0.0, 0.0, 0.0},
 {0.0, 0.0, 0.0}};
    compareMatrices(x,y);
  }
  @Test(expected=UnsupportedOperationException.class)
  public void cant_change_fill(){
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 0.0);
    x.setFillElem(5.0);
  }
  @Test
  public void insertRow_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 0.0);
    x.set(1,1,1.0);
    x.insertRow(1);
    Double y[][] = new Double[][]{
      {0.0, 0.0, 0.0,},
      {0.0, 0.0, 0.0,},
      {0.0, 1.0, 0.0,}
    };
    compareMatrices(x,y);
  }    
  @Test(expected= IndexOutOfBoundsException.class) 
  public void insertRowException_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 5.0);
    x.insertRow(3);
  }    
  @Test(expected= IndexOutOfBoundsException.class) 
  public void insertRowException_2() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 5.0);
    x.insertRow(5);
  }    
  @Test
  public void removeRow_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,0, 5.0);
    x.removeRow(0);
    Double y[][] = new Double[][]{
      {}};
    compareMatrices(x,y);
  }    
  @Test
  public void removeRow_2() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,4, 5.0);
    x.removeRow(0);
    Double y[][] = new Double[][]{
      {5.0, 5.0, 5.0, 5.0}};
    compareMatrices(x,y);
  }    
  @Test
  public void removeRow_3() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(4,4, 5.0);
    x.removeRow(3);
    Double y[][] = new Double[][]{
      {5.0, 5.0, 5.0, 5.0},
      {5.0, 5.0, 5.0, 5.0},
      {5.0, 5.0, 5.0, 5.0}};
    compareMatrices(x,y);
  }    
  @Test(expected= IndexOutOfBoundsException.class) 
  public void removeRowException_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 5.0);
    x.removeRow(3);
  }    
  @Test
  public void set_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 0.0);
    x.set(0,1, 1.0);
    Double y[][] = new Double[][]{
      {0.0, 1.0, 0.0},
      {0.0, 0.0, 0.0}};
    compareMatrices(x,y);
  }
  @Test
  public void set_2() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 0.0);
    x.set(1,2, 1.0);
    Double y[][] = new Double[][]{
      {0.0, 0.0, 0.0},
      {0.0, 0.0, 1.0}};
    compareMatrices(x,y);
  }  
  @Test
  public void set_3() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(0.0);
    x.set(1,2, 1.0);
    Double y[][] = new Double[][]{
      {0.0, 0.0, 0.0},
      {0.0, 0.0, 1.0}};
    compareMatrices(x,y);
  }  
  @Test
  public void set_4() throws Exception {
    int rows = 4, cols = 3;
    Double original[][] = new Double[][]{
      { 0.0, 4.0,  0.0,},
      { 1.0, 5.0,  9.0,},
      { 2.0, 0.0,  0.0,},
      { 3.0, 7.0,  0.0,},
    };
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, 0.0);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 x.set(i,j, original[i][j]);
      }
    }
    compareMatrices(x,original);
  }
  @Test
  public void set_5() throws Exception {
    double fill = 0.0;
    int rows = 4, cols = 3;
    Double original[][] = new Double[][]{
      { 0.0, 4.0,  0.0,},
      { 1.0, 1.0,  9.0,},
      { 2.0, 0.0,  1.0,},
      { 1.0, 7.0,  0.0,},
    };
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, fill);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 if(original[i][j] != fill){
   x.set(i,j, original[i][j]);
 }
      }
    }
    compareMatrices(x,original);
  }
  @Test
  public void set_6() throws Exception {
    double fill = 1.0;
    int rows = 4, cols = 3;
    Double original[][] = new Double[][]{
      { 0.0, 4.0,  0.0,},
      { 1.0, 1.0,  9.0,},
      { 2.0, 0.0,  1.0,},
      { 1.0, 7.0,  0.0,},
    };
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, fill);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 if(original[i][j] != fill){
   x.set(i,j, original[i][j]);
 }
      }
    }
    compareMatrices(x,original);
  }

  @Test
  public void get_1() throws Exception {
    Double val = 1.0;
    SparseMatrix<Double> x = new SparseMatrix<Double>(10,2, val);
    assertEquals("Stored element not correct",val, x.get(0,0));
    assertEquals("Stored element not correct",val, x.get(0,1));
    assertEquals("Stored element not correct",val, x.get(9,0));
    assertEquals("Stored element not correct",val, x.get(9,1));
  }
  @Test(expected= IndexOutOfBoundsException.class) 
  public void get_2() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(10,2, 1.0);
    x.get(10,0);
  }    
  
  // Compare a TRANSPOSED SparseMatrix to standard matrix for equal
  // size and elements
  private <T> void compareTransposeMatrices(SparseMatrix<T> x, T[][] y, int r, int c){
    assertEquals("Row count wrong ",c,x.rows());
    assertEquals("Column count wrong",r,x.cols());
    for(int i=0; i<r; i++){
      for(int j=0; j<c; j++){
 T ye = y[i][j];
 T xe = x.get(j,i);
 assertEquals(String.format("Element (%d,%d) is wrong",i,j),
       ye, xe);
      }
    }
  }
  @Test
  public void transpose_1() throws Exception {
    int rows = 2, cols = 3;
    Double original[][] = new Double[][]{
      {0.0, 0.0, 0.0},
      {1.0, 1.0, 1.0}};
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, 0.0);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 x.set(i,j, original[i][j]);
      }
    }
    x.transpose();
    compareTransposeMatrices(x,original, rows, cols);
  }
  @Test
  public void transpose_2() throws Exception {
    int rows = 1, cols = 4;
    Double original[][] = new Double[][]{
      {0.0, 0.0, 9.0, 0.0},
    };
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, 0.0);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 x.set(i,j, original[i][j]);
      }
    }
    x.transpose();
    compareTransposeMatrices(x,original, rows, cols);
  }
  @Test
  public void transpose_3() throws Exception {
    int rows = 0, cols = 5;
    Double original[][] = new Double[][]{
    };
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, 0.0);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 x.set(i,j, original[i][j]);
      }
    }
    x.transpose();
    compareTransposeMatrices(x,original, rows, cols);
  }
  @Test
  public void transpose_4() throws Exception {
    int rows = 4, cols = 4;
    Double original[][] = new Double[][]{
      {0.0, 0.0, 0.0, 6.0,},
      {1.0, 2.0, 1.0, 0.0,},
      {0.0, 1.0, 0.0, 0.0,},
      {3.0, 0.0, 1.0, 0.0,},
    };
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, 0.0);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 x.set(i,j, original[i][j]);
      }
    }
    x.transpose();
    compareTransposeMatrices(x,original, rows, cols);
  }
  @Test
  public void transpose_5() throws Exception {
    int rows = 4, cols = 3;
    Double original[][] = new Double[][]{
      { 0.0, 4.0,  8.0,},
      { 1.0, 5.0,  9.0,},
      { 2.0, 6.0, 10.0,},
      { 3.0, 7.0, 11.0,},
    };
    SparseMatrix<Double> x = new SparseMatrix<Double>(rows, cols, 0.0);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 x.set(i,j, original[i][j]);
      }
    }
    x.transpose();
    compareTransposeMatrices(x,original, rows, cols);
  }


  @SuppressWarnings("unchecked")
  @Test
  public void allElements_1() throws Exception {
    Element<String>[] tmp = new Element[]{
      new Element<String>(0,1, "Mal"),
      new Element<String>(1,0, "Kaylee"),
      new Element<String>(1,4, "Wash"),
      new Element<String>(4,6, "Jane"),
      new Element<String>(3,3, "River"),
    };
    List<Element<String>> expect = Arrays.asList(tmp);
    SparseMatrix<String> sm = new SparseMatrix<String>(5,7,"");
    for( Element<String> e : expect ){
      sm.set(e.i, e.j, e.data);
    }
    List<Element<String>> actual = sm.allElements();
    for( Element<String> e : expect ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, actual.contains(e));
    }
    for( Element<String> e : actual ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, expect.contains(e));
    }
  }

  // Ensure that removeElement actually removes an element
  @SuppressWarnings("unchecked")
  @Test
  public void removeElement_1() throws Exception {
    Element<String>[] tmp = new Element[]{
      new Element<String>(0,1, "Mal"),
      new Element<String>(1,0, "Kaylee"),
      new Element<String>(1,4, "Wash"),
      new Element<String>(4,6, "Jane"),
      new Element<String>(3,3, "River"),
    };
    ArrayList<Element<String>> expect = new ArrayList(Arrays.asList(tmp));
    String fill = "";
    SparseMatrix<String> sm = new SparseMatrix<String>(5,7,fill);
    for( Element<String> e : expect ){
      sm.set(e.i, e.j, e.data);
    }

    Element<String> remove = expect.get(0);
    expect.remove(0);
    sm.removeElement(remove.i, remove.j);

    List<Element<String>> actual = sm.allElements();
    for( Element<String> e : expect ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, actual.contains(e));
    }
    for( Element<String> e : actual ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, expect.contains(e));
    }
  }

  // Ensure setting to the fill element removes an element
  @SuppressWarnings("unchecked")
  @Test
  public void set_to_fill_removes_2() throws Exception {
    Element<String>[] tmp = new Element[]{
      new Element<String>(0,1, "Mal"),
      new Element<String>(1,0, "Kaylee"),
      new Element<String>(1,4, "Wash"),
      new Element<String>(4,6, "Jane"),
      new Element<String>(3,3, "River"),
    };
    ArrayList<Element<String>> expect = new ArrayList(Arrays.asList(tmp));
    String fill = "";
    SparseMatrix<String> sm = new SparseMatrix<String>(5,7,fill);
    for( Element<String> e : expect ){
      sm.set(e.i, e.j, e.data);
    }

    Element<String> remove = expect.get(0);
    expect.remove(0);
    sm.set(remove.i, remove.j,fill);

    List<Element<String>> actual = sm.allElements();
    for( Element<String> e : expect ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, actual.contains(e));
    }
    for( Element<String> e : actual ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, expect.contains(e));
    }
  }

  @SuppressWarnings("unchecked")
  @Test
  public void stress_test_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(0.0);
    x.insertRow(x.rows());
    x.insertRow(x.rows());
    x.insertColumn(x.cols());
    x.set(1,0,3.0);

    double n=0;
    for(int i=0; i<5; i+=2){
      for(int j=0; j<8; j+=3){
 x.set(i,j, n);
 n++;
      }
    }

    x.insertColumn(x.cols());
    x.insertColumn(2);
    x.insertColumn(x.cols());
    x.set(1,1,4.0);
    x.insertRow(1);
    x.insertColumn(1);
    x.set(0,0,10.0);
    x.removeRow(1);
    x.removeColumn(0);
    x.set(2,2,5.0);
    x.insertRow(2);
    x.set(8,6,7.0);
    x.transpose();

    Element<Double>[] tmp = new Element[]{
      new Element<Double>(1, 1, 4.0), 
      new Element<Double>(2, 3, 5.0), 
      new Element<Double>(4, 0, 1.0), 
      new Element<Double>(4, 3, 4.0), 
      new Element<Double>(4, 5, 7.0), 
      new Element<Double>(6, 8, 7.0), 
      new Element<Double>(7, 0, 2.0), 
      new Element<Double>(7, 3, 5.0), 
      new Element<Double>(7, 5, 8.0)
    };

    ArrayList<Element<Double>> expect = new ArrayList(Arrays.asList(tmp));
    List<Element<Double>> actual = x.allElements();
    for( Element<Double> e : expect ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, actual.contains(e));
    }
    for( Element<Double> e : actual ){
      String msg = String.format("Elements differ\nexpect: %s\nactual: %s\n", expect, actual);
      assertTrue(msg, expect.contains(e));
    }
  }
  
  //-------------------------------------------------------------------------------------------------------//
  
  
  @Test
  public void insertColumn_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,3, 0.0);
    x.set(1,1,1.0);
    x.insertColumn(1);
    Double y[][] = new Double[][]{
      {0.0, 0.0, 0.0, 0.0,},
      {0.0, 0.0, 1.0, 0.0,}
    };
    compareMatrices(x,y);
  }    

  @Test
  public void removeColumn_1() throws Exception {
    SparseMatrix<Double> x = new SparseMatrix<Double>(2,2, 5.0);
    x.removeColumn(0);
    Double y[][] = new Double[][]{
      {5.0,},
      {5.0,}};
    compareMatrices(x,y);
  }    

}

