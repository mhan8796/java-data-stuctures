// A battery of tests to exercise the DenseMatrix class of HW1
// NOTE: The public tests DO NOT exercise all required methods.
// NOTE: Add your own tests to ensure code quality.
// 
// Command line instructions
// 
// Compile
// lila [ckauffm2-hw1]% javac -cp .:junit-4.11.jar *.java
// 
// Run tests
// lila [ckauffm2-hw1]% java -cp .:junit-4.11.jar DenseMatrixTest
// 
// For IDEs, consult documentation to see how to run junit tests


// org.junit.Assert.assertEquals - statically imported
// assertEquals(message, expected, actual);
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class DenseMatrixTest
{
  // Compare a DenseMatrix to standard matrix for equal size and
  // elements
  private <T> void compareMatrices(DenseMatrix<T> x, T[][] y){
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
  // Checks that the fill element of a DenseMatrix has been correctly
  // set
  private <T> void checkFill(DenseMatrix<T> x, T e){
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
    DenseMatrix<Double> x = new DenseMatrix<Double>(0.0);
    DynamicMatrix dm = (DynamicMatrix) x;
  }

  @Test
  public void constructor1_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(0.0);
    checkFill(x, new Double(0.0));
    Double y[][] = new Double[0][0];
    compareMatrices(x,y);
  }
  @Test
  public void constructor1_2() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(1.0);
    checkFill(x, new Double(1.0));
    Double y[][] = new Double[0][0];
    compareMatrices(x,y);
  }
  @Test
  public void constructor1_3() throws Exception {
    DenseMatrix<String> x = new DenseMatrix<String>("Hi");
    checkFill(x, "Hi");
    String y[][] = new String[0][0];
    compareMatrices(x,y);
  }
  @Test
  public void constructor2_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 0.0);
    Double y[][] = new Double[][]{
 {0.0, 0.0, 0.0},
 {0.0, 0.0, 0.0}};
    compareMatrices(x,y);
  }
  @Test
  public void changeFill_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(0.0);
    x.setFillElem(1.0);
    checkFill(x, new Double(1.0));
  }
  @Test
  public void changeFill_2() throws Exception {
    DenseMatrix<String> x = new DenseMatrix<String>("Hi");
    x.setFillElem("Bye");
    checkFill(x, "Bye");
  }
  @Test
  public void insertRow_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 5.0);
    x.setFillElem(1.0);
    x.insertRow(1);
    Double y[][] = new Double[][]{
      {5.0, 5.0, 5.0},
      {1.0, 1.0, 1.0},
      {5.0, 5.0, 5.0}};
    compareMatrices(x,y);
  }    
  @Test
  public void insertRow_2() throws Exception {
    DenseMatrix<String> x = new DenseMatrix<String>(1,2, "Hi");
    x.setFillElem("Bye");
    x.insertRow(1);
    String y[][] = new String[][]{
      {"Hi", "Hi"},
      {"Bye", "Bye"}};
    compareMatrices(x,y);
  }    
  @Test(expected= IndexOutOfBoundsException.class) 
  public void insertRowException_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 5.0);
    x.setFillElem(1.0);
    x.insertRow(3);
  }    
  @Test(expected= IndexOutOfBoundsException.class) 
  public void insertRowException_2() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 5.0);
    x.setFillElem(1.0);
    x.insertRow(5);
  }    
  @Test
  public void removeRow_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,0, 5.0);
    x.removeRow(0);
    Double y[][] = new Double[][]{
      {}};
    compareMatrices(x,y);
  }    
  @Test
  public void removeRow_2() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,4, 5.0);
    x.removeRow(0);
    Double y[][] = new Double[][]{
      {5.0, 5.0, 5.0, 5.0}};
    compareMatrices(x,y);
  }    
  @Test
  public void removeRow_3() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(4,4, 5.0);
    x.removeRow(3);
    Double y[][] = new Double[][]{
      {5.0, 5.0, 5.0, 5.0},
      {5.0, 5.0, 5.0, 5.0},
      {5.0, 5.0, 5.0, 5.0}};
    compareMatrices(x,y);
  }    
  @Test(expected= IndexOutOfBoundsException.class) 
  public void removeRowException_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 5.0);
    x.removeRow(3);
  }    
  @Test
  public void set_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 0.0);
    x.set(0,1, 1.0);
    Double y[][] = new Double[][]{
      {0.0, 1.0, 0.0},
      {0.0, 0.0, 0.0}};
    compareMatrices(x,y);
  }
  @Test
  public void set_2() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 0.0);
    x.set(1,2, 1.0);
    Double y[][] = new Double[][]{
      {0.0, 0.0, 0.0},
      {0.0, 0.0, 1.0}};
    compareMatrices(x,y);
  }  
  @Test
  public void set_3() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(0.0);
    x.set(1,2, 1.0);
    Double y[][] = new Double[][]{
      {0.0, 0.0, 0.0},
      {0.0, 0.0, 1.0}};
    compareMatrices(x,y);
  }  
  @Test
  public void get_1() throws Exception {
    Double val = 1.0;
    DenseMatrix<Double> x = new DenseMatrix<Double>(10,2, val);
    assertEquals("Stored element not correct",val, x.get(0,0));
    assertEquals("Stored element not correct",val, x.get(0,1));
    assertEquals("Stored element not correct",val, x.get(9,0));
    assertEquals("Stored element not correct",val, x.get(9,1));
  }
  @Test(expected= IndexOutOfBoundsException.class) 
  public void get_2() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(10,2, 1.0);
    x.get(10,0);
  }    
  
  // Compare a TRANSPOSED DenseMatrix to standard matrix for equal
  // size and elements
  private <T> void compareTransposeMatrices(DenseMatrix<T> x, T[][] y, int r, int c){
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
    DenseMatrix<Double> x = new DenseMatrix<Double>(rows, cols, 0.0);
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
    DenseMatrix<Double> x = new DenseMatrix<Double>(rows, cols, 0.0);
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
    DenseMatrix<Double> x = new DenseMatrix<Double>(rows, cols, 0.0);
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
    DenseMatrix<Double> x = new DenseMatrix<Double>(rows, cols, 0.0);
    for(int i=0; i<rows; i++){
      for(int j=0; j<cols; j++){
 x.set(i,j, original[i][j]);
      }
    }
    x.transpose();
    compareTransposeMatrices(x,original, rows, cols);
  }

  @Test
  public void update_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(3,2, 0.0);
    Function1<Double,Double> inc = 
      new Function1<Double,Double>(){ public Double call(Double d){ return d+1.0; }};
    x.update(inc);
    Double y[][] = new Double[][]{
      {1.0, 1.0},
      {1.0, 1.0},
      {1.0, 1.0}};
    compareMatrices(x,y);
  }
  @Test
  public void map_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(3,2, 1.234);
    Function1<String,Double> sciString = 
      new Function1<String,Double>(){
 public String call(Double d){
   return String.format("%8.2e",d);
 }};
    DenseMatrix<String> sci = x.map(sciString);
    String y[][] = new String[][]{
      {"1.23e+00", "1.23e+00"},
      {"1.23e+00", "1.23e+00"},
      {"1.23e+00", "1.23e+00"}};
    compareMatrices(sci,y);
  }
  
  @Test
  public void reduce_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(3,2, 0.0);
    Double val1 = 1.5, val2 = 2.5;
    x.set(2,1, val1);
    x.set(1,0, val2);

    Function2<Double,Double,Double> add = 
      new Function2<Double,Double,Double>(){ 
        public Double call(Double x, Double y){ return x+y;} 
    };      
    Double sum = x.reduce(0.0, add);
    Double expect = val1 + val2;
    assertEquals("Summed element not correct",expect, sum);
  }

  @Test
  public void stress_test_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(1.0);
    x.insertRow(x.rows());
    x.insertRow(x.rows());
    x.insertColumn(x.cols());
    x.setFillElem(2.0);
    x.insertColumn(x.cols());
    x.insertColumn(x.cols());
    x.set(1,1,3.0);
    x.setFillElem(4.0);
    x.insertRow(1);
    x.insertColumn(1);
    x.removeRow(1);
    x.removeColumn(0);
    x.transpose();
    Function1<Double,Double> Poly = new Function1<Double,Double>(){ public Double call(Double d){return d*d-d*2.0+3.0;}};
    Function1<Double,Double> inc = new Function1<Double,Double>(){ public Double call(Double d){ return d+1.0; }};
    Function2<Double,Double,Double> add = new Function2<Double,Double,Double>(){ public Double call(Double x, Double y){ return x+y;} };  
    x.update(inc);
    DenseMatrix<Double> Pol = x.map(Poly);
    Double sum = Pol.reduce(1.0, add);
    Double expect = 66.0;
    assertEquals("Summed element not correct",expect, sum);
  }   
  
  //--------------------------------------------------------------
  @Test
  public void insertColumn_1() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,3, 5.0);
    x.setFillElem(1.0);
    x.insertColumn(1);
    Double y[][] = new Double[][]{
      {5.0, 1.0, 5.0, 5.0},
      {5.0, 1.0, 5.0, 5.0}};
    compareMatrices(x,y);
  }    
  @Test
  public void insertColumn_2() throws Exception {
    DenseMatrix<String> x = new DenseMatrix<String>(1,2, "Hi");
    x.setFillElem("Bye");
    x.insertColumn(1);
    String y[][] = new String[][]{
      {"Hi", "Bye", "Hi"}};
    compareMatrices(x,y);
  }    
  
  @Test
  public void removeColmun_1() throws Exception {
    DenseMatrix<String> x = new DenseMatrix<String>(1,4, "Hi");
    x.removeColumn(1);
    String y[][] = new String[][]{
      {"Hi", "Hi", "Hi"}};
    compareMatrices(x,y);
  }    
  @Test
  public void removeColumn_2() throws Exception {
    DenseMatrix<Double> x = new DenseMatrix<Double>(2,4, 5.0);
    x.removeColumn(0);
    Double y[][] = new Double[][]{
      {5.0, 5.0, 5.0},
      {5.0, 5.0, 5.0}
    };
    compareMatrices(x,y);
  }    
    //------------------------------------------------------------


  /*Main method runs tests in this file*/ 
  public static void main(String args[])
  {
    org.junit.runner.JUnitCore.main("DenseMatrixTest");
  } 
}

