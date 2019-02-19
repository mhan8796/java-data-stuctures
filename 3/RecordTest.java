import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;


public class RecordTest {
  /*Main method runs tests in this file*/ 
  public static void main(String args[])
  {
    org.junit.runner.JUnitCore.main("RecordTest");
  } 

  // Run before testing begins
  @BeforeClass 
  public static void setUp() throws Exception {
    System.out.println("***NOTE: The public tests DO NOT exercise all required methods.\n***NOTE: Add your own tests for full credit.");
  }

  @Test
  public void matches_1(){
    Record r = Record.makeRecord("Alf","ISA","alien");
    Record q = Record.makeRecord("Alf","ISA","alien");
    assertTrue(r.matches(q));
    assertTrue(q.matches(r));
  }    
  @Test
  public void matches_2(){
    Record r = Record.makeQuery("*","Alf","ISA","*");
    Record q = Record.makeRecord("Alf","ISA","alien");
    assertTrue(r.matches(q));
    assertTrue(q.matches(r));
  }    
  @Test
  public void matches_3(){
    Record r = Record.makeQuery("WILD!","WILD!","ISA","WILD!");
    Record q = Record.makeRecord("Alf","ISA","alien");
    assertTrue(r.matches(q));
    assertTrue(q.matches(r));
  }    

  // Compare two records
  public static void
    doCompare(Record small, Record big, 
       Comparator<Record> comp, int sign){
    int cmp = comp.compare(small,big);
    if((sign != 0 && cmp*sign < 0) ||
       (sign ==0 && cmp != 0)){
      String msg = String.format("%d: [%s] should be smaller than [%s]",
     cmp,small,big);
      fail(msg);
    }
  }    

  @Test
  public void ERPCompare_1(){
    doCompare(Record.makeQuery("*","*","*","*"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.ERPCompare, -1);
  }
  @Test
  public void ERPCompare_2(){
    doCompare(Record.makeQuery("*","Alf","*","*"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.ERPCompare, -1);
  }
  @Test
  public void ERPCompare_3(){
    doCompare(Record.makeQuery("*","Alf","ISA","*"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.ERPCompare, -1);
  }
  
  
  //-------------------------------------------------------------------------------------------------------------------
  @Test
  public void makeRecord_1(){
    Record q = Record.makeRecord("Alf","ISA","alien");
    assertTrue(q.toString().equals("     Alf     ISA     alien"));
  }   
  
  @Test
  public void makeQuery_1(){
    Record q = Record.makeQuery("*","Alf","ISA","alien");
    assertTrue(q.toString().equals("     Alf     ISA     alien"));
  }   
  
  @Test
  public void containsWild_1(){
    Record q = Record.makeQuery("Alf","Alf","eee","alien");
    assertTrue(q.containsWild());
  }   
  
  @Test
  public void containsWild_2(){
    Record q = Record.makeQuery("Alf","bbb","eee","alien");
    assertFalse(q.containsWild());
  }  
      
  @Test
  public void ERPCompare_4(){
    doCompare(Record.makeRecord("Alf","ISA","blien"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.ERPCompare, 1);
  }
  @Test
  public void ERPCompare_5(){
    doCompare(Record.makeRecord("Alf","JSA","alien"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.ERPCompare, 1);
  }
  @Test
  public void ERPCompare_6(){
    doCompare(Record.makeRecord("Blf","ISA","sdfs"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.ERPCompare, 1);
  }
  @Test
  public void RPECompare_1(){
    doCompare(Record.makeRecord("AAf","ISA","blien"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.RPECompare, 1);
  }
  @Test
  public void RPECompare_2(){
    doCompare(Record.makeRecord("Alf","Alf","AAA"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.RPECompare, -1);
  }
  @Test
  public void RPECompare_3(){
    doCompare(Record.makeQuery("*","Alf","ISA","*"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.RPECompare, -1);
  }
  @Test
  public void PERCompare_1(){
    doCompare(Record.makeQuery("*","Alf","*","*"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.PERCompare, -1);
  }
  @Test
  public void PERCompare_2(){
    doCompare(Record.makeRecord("Alf","ISB","alien"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.PERCompare, 1);
  }
  @Test
  public void PERCompare_3(){
    doCompare(Record.makeRecord("Alf","ISA","aaien"),
       Record.makeRecord("Alf","ISA","alien"),
       Record.PERCompare, -1);
  }
  @Test
  public void matches_4(){
    Record r = Record.makeRecord("Alu","ISA","alien");
    Record q = Record.makeRecord("Alf","ISA","alien");
    assertFalse(r.matches(q));
    assertFalse(q.matches(r));
  }    
  @Test
  public void matches_5(){
    Record r = Record.makeQuery("*","Alf","ISA","*");
    Record q = Record.makeRecord("Alf","IFA","alien");
    assertFalse(r.matches(q));
    assertFalse(q.matches(r));
  }    
  @Test
  public void matches_6(){
    Record r = Record.makeQuery("WILD!","WILD!","ISA","WILD!");
    Record q = Record.makeRecord("DHFHGFUDJHD","ISA","BBBBB");
    assertTrue(r.matches(q));
    assertTrue(q.matches(r));
      System.out.println(r.toString());
  }    
  
  
  

}
