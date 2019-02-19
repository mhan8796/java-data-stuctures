import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;


public class TripleStoreTest {
  /*Main method runs tests in this file*/ 
  public static void main(String args[])
  {
    org.junit.runner.JUnitCore.main("TripleStoreTest");
  } 

  // Run before testing begins
  @BeforeClass
  public static void setUp() throws Exception {
    System.out.println("***NOTE: The public tests DO NOT exercise all required methods.\n***NOTE: Add your own tests for full credit.");
  }

  @Test
  public void constructor_1(){
    TripleStore t = new TripleStore();
  }

  @Test
  public void add_1(){
    TripleStore t = new TripleStore();
    boolean b;
    b = t.add("Alf","ISA","alien");
    assertTrue("add should return true",b);
  }
  @Test
  public void add_2(){
    TripleStore t = new TripleStore();

    boolean b;
    b = t.add("Alf","ISA","alien");
    assertTrue("add should return true",b);
    b = t.add("Lucky","ISA","cat");
    assertTrue("add should return true",b);
    b = t.add("Lucky","ISA","cat");
    assertFalse("add should return false",b);
  }

  public static void check_query(Record [] matches, Record [] extras, Record query){
    TripleStore t = new TripleStore();
    boolean b; String msg;
    for(Record r : matches){
      b = t.add(r.entity(), r.relation(), r.property());
    }
    for(Record r : extras){
      b = t.add(r.entity(), r.relation(), r.property());
    }
    ArrayList<Record> results = t.query(query.entity(), query.relation(), query.property());
    msg = String.format("Result should have %d items, got %d ", matches.length, results.size());
    assertEquals(msg, matches.length, results.size());
    for(int i=0; i<matches.length; i++){
      Record matchi = matches[i];
      boolean found = false;
      int j; Record reci;
      for(j=0; j<results.size(); j++){
 reci = results.get(j);
 if(recordsEqual(reci,matchi)){
   found = true;
   break;
 }
      }
      if(!found){
 msg = String.format("Query Results missing %s\nDB:\n%s\n", matchi,t);
 fail(msg);
      }
    }
  }
  

  @Test
  public void query_1(){
    Record matches[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Lucky","ISA","cat"),
    };
    Record query = Record.makeQuery("*","Alf","ISA","alien");
    check_query(matches,extras,query);
  }
  @Test
  public void query_2(){
    Record matches[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Alf","EATS","cat"),
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Lucky","ISA","cat"),
    };
    Record query = Record.makeQuery("*","Alf","*","*");
    check_query(matches,extras,query);
  }

  public static boolean recordsEqual(Record r, Record t){
    return
      r.entity().equals(t.entity()) &&
      r.relation().equals(t.relation()) &&
      r.property().equals(t.property());
  }

  public static void check_remove(Record [] matches, Record [] extras, Record query){
    TripleStore t = new TripleStore();
    boolean b; String msg;
    for(Record r : matches){
      b = t.add(r.entity(), r.relation(), r.property());
    }
    for(Record r : extras){
      b = t.add(r.entity(), r.relation(), r.property());
    }
    int nrm = t.remove(query.entity(), query.relation(), query.property());
    msg = String.format("Should have removed %d items, got %d ", matches.length, nrm);
    assertEquals(msg, matches.length, nrm);
    ArrayList<Record> remaining = t.query("*","*","*");
    msg = String.format("Remaining should have %d items, got %d ", extras.length, remaining.size());
    assertEquals(msg, extras.length, remaining.size());
    for(int i=0; i<extras.length; i++){
      Record extrai = extras[i];
      boolean found = false;
      int j; Record remaini;
      for(j=0; j<remaining.size(); j++){
 remaini = remaining.get(j);
 if(recordsEqual(remaini,extrai)){
   found = true;
   break;
 }
      }
      if(!found){
 msg = String.format("Remaining missing %s\nDB:\n%s\n", extrai,t);
 fail(msg);
      }
      remaini = remaining.get(j);
      msg = String.format("Remaining wrong:\nExpect: %s\nGot   : %s\n",
     extrai,remaini);
      assertTrue(msg,recordsEqual(remaini,extrai));
    }
  }

  @Test
  public void remove_1(){
    Record matches[] = new Record[]{
      Record.makeRecord("Willie","ISA","human"),
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Lynn","ISA","human"),
      Record.makeRecord("Lucky","ISA","cat"),
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lynn","EATS","vegetable"),
      Record.makeRecord("Lucky","LIKESTO","purr"),
    };
    Record query = Record.makeQuery("*","*","ISA","*");
    check_remove(matches,extras,query);
  }
  @Test
  public void remove_2(){
    Record matches[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Alf","EATS","cat"),
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Lynn","ISA","human"),
      Record.makeRecord("Lucky","ISA","cat"),
      Record.makeRecord("Willie","ISA","human"),
      Record.makeRecord("Lynn","EATS","vegetable"),
      Record.makeRecord("Lucky","LIKESTO","purr"),
    };
    Record query = Record.makeQuery("*","Alf","*","*");
    check_remove(matches,extras,query);
  }
  
  
  //-------------------------------------------------------------------------------------------------------
  
  @Test
  public void add_3(){
    TripleStore t = new TripleStore();
    boolean b;
    t.add("Alf","ISA","alien");
    b = t.add("Alf","ISA","alien");
    assertFalse("add should return true",b);
  }
  
  @Test
  public void add_4(){
    TripleStore t = new TripleStore();

    boolean b;
    b = t.add("Alf","ISA","alien");
    assertTrue("add should return true",b);
    b = t.add("Lucky","ISA","cat");
    assertTrue("add should return true",b);
    t.remove("Lucky","ISA","cat");
    b = t.add("Lucky","ISA","cat");
    assertTrue("add should return false",b);
  }
  
  @Test
  public void remove_3(){
    Record matches[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lynn","ISA","human"),
      Record.makeRecord("Lucky","ISA","cat"),
      Record.makeRecord("Willie","ISA","human"),
      Record.makeRecord("Lynn","EATS","vegetable"),
      Record.makeRecord("Lucky","LIKESTO","purr"),
    };
    Record extras[] = new Record[]{
 
    };
    Record query = Record.makeQuery("*","*","*","*");
    check_remove(matches,extras,query);
  }
  
  @Test
  public void remove_4(){
    Record matches[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lynn","ISA","human"),
      Record.makeRecord("Lucky","ISA","cat"),
      Record.makeRecord("Willie","ISA","human"),
      Record.makeRecord("Lynn","EATS","vegetable"),
      Record.makeRecord("Lucky","LIKESTO","purr"),
    };
    Record query = Record.makeRecord("Alf","ISA","alien");
    check_remove(matches,extras,query);
  }
  
  @Test
  public void remove_5(){
    Record matches[] = new Record[]{
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lynn","ISA","human"),
      Record.makeRecord("Lucky","ISA","cat"),
      Record.makeRecord("Willie","ISA","human"),
      Record.makeRecord("Lynn","EATS","vegetable"),
      Record.makeRecord("Lucky","LIKESTO","purr"),
    };
    Record query = Record.makeRecord("Alddd","ISA","alien");

    check_remove(matches,extras,query);
  }
    
  @Test
  public void query_3(){
    Record matches[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lucky","ISA","cat"),
    };
    Record extras[] = new Record[]{   
    };
    Record query = Record.makeQuery("*","*","*","*");
    check_query(matches,extras,query);
  }
    
  @Test
  public void query_4(){
    Record matches[] = new Record[]{
    
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lucky","ISA","cat"),
    };
    Record query = Record.makeQuery("*","Ajvyuf","","*");
    check_query(matches,extras,query);
  }
  
  @Test
  public void query_5(){
    Record matches[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lucky","ISA","cat"),
    };
    Record query = Record.makeRecord("Alf","ISA","alien");
    check_query(matches,extras,query);
  }
  
  @Test
  public void query_6(){
    Record matches[] = new Record[]{
     
      
    };
    Record extras[] = new Record[]{
      Record.makeRecord("Alf","ISA","alien"),
      Record.makeRecord("Alf","EATS","cat"),
      Record.makeRecord("Lucky","ISA","cat"),
    };
    Record query = Record.makeRecord("Alf","ISA","alienssssssssssss");
    check_query(matches,extras,query);
  }
  
}
