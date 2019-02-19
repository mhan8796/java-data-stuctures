import java.util.*;
import java.io.*;

// Simple abstraction over Scanner to produce words. Reads whole file
// first to aid in timing comparisons
public class WordStream{
  private Scanner in = null;
  // Read whole file as a string
  private static String slurp(String fname) throws Exception{
    return new Scanner(new File(fname), "UTF-8").useDelimiter("\\Z").next();
  }
  // Create a word stream that reads from the named file
  public WordStream(String fname){
    try{
      String wholeDoc = slurp(fname);
      this.in = new Scanner(wholeDoc);
      this.in.useDelimiter("\\p{Punct}*(\\s+|\\z)(\\W)*");
      // this.in.useDelimiter("\\p{Punct}*(\\s+|\\z)(\\p{Punct}|\\s)*");
      // this.in.useDelimiter("\\W+");
    }
    catch(Exception e){
      String msg = String.format("Error: Couldn't open file %s",fname);
      throw new RuntimeException(msg+"\n"+e.toString());
    }
  }
  // Is there another word to read?
  public boolean hasWord(){
    return this.in.hasNext();
  }
  // Retrieve the next word from the stream
  public String nextWord(){
    return this.in.next().toLowerCase();
  }
  // Close the stream and any associated resources
  public void close(){
    this.in.close();
  }
  // Parse the command line file and print out its stream of words
  public static void main(String args[]){
    if(args.length < 1){
      System.out.println("usage: WordStream file\n");
      return;
    }
    WordStream ws = new WordStream(args[0]);
    while(ws.hasWord()){
      System.out.println(ws.nextWord());
    }
    ws.close();
  }
}
