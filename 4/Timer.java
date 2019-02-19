import java.util.*;

// A timing class for timing different parts of a program and printing
// out results
public class Timer{
  private long startTime;
  private long lastTime;
  private String name;
  private ArrayList<String> splitNames;
  private ArrayList<Long>   splitTimes;

  // Create a new Timer. Its starting time will be initialized as the
  // present moment. The name of the timer is appears on all lines of
  // its toString() string.
  public Timer(String name){
    this.startTime = now();
    this.lastTime  = now();
    this.name = name;
    this.splitNames = new ArrayList<String>();
    this.splitTimes = new ArrayList<Long>();
  }
  // Add a split time to the list of splits
  public void addSplit(String name){
    long time = this.elapsedAndReset();
    this.splitNames.add(name);
    this.splitTimes.add(time);
  }

  // Print a stringy representation of the timer which shows each
  // split time and the total time on its own line. Each line is
  // preceded by the name of the timer.
  public String toString(){
    long totalTime = this.totalElapsed();
    String fmt = "%s: %8d %s\n";
    StringBuilder sb = new StringBuilder();
    String line;
    for(int i=0; i<this.splitNames.size(); i++){
      line = String.format(fmt, this.name, this.splitTimes.get(i), this.splitNames.get(i));
      sb.append(line);
    }
    line = String.format(fmt, this.name, totalTime, "total time");
    sb.append(line);
    return sb.toString();
  }

  // Demonstrates the typical use of Timers by generating and sorting
  // an array of numbers
  public static void main(String args[]){
    Timer timer = new Timer("TIMERMAIN");

    System.out.println("Setting up a large array");
    int arraySize = 1024*512;
    double nums[] = new double[arraySize];
    timer.addSplit("allocation time");

    System.out.println("Generating random doubles");
    long seed = 713984371;
    Random rand = new Random(seed);
    for(int i=0; i<nums.length; i++){
      nums[i] = rand.nextDouble();
    }
    timer.addSplit("random generation time");

    System.out.println("Sorting numbers");
    Arrays.sort(nums);
    timer.addSplit("sorting time");

    System.out.println("Verifying sort");
    for(int i=0; i<nums.length-1; i++){
      if(nums[i] > nums[i+1]){
        System.out.printf("Error at %d vs %d: %f > %f",i,i+1,nums[i],nums[i+1]);
      }
    }
    timer.addSplit("verification time");

    System.out.println("Done. Performance Stats:");
    System.out.println(timer.toString());
  }

  // Report the curren time in milliseconds
  public static long now(){
    return System.currentTimeMillis();
  }
  // Reset the timer so that subsequent checks of elapsed will
  // consider now the star time.  Does not reset the timers start
  // time.
  public void reset(){ 
    this.lastTime = now();
  }
  // Returns time in millis since the last reset or split
  public long elapsed(){
    long elapsed = now() - this.lastTime;
    return elapsed;
  }
  // Returns time in millis since the Timer was created
  public long totalElapsed(){
    long elapsed = now() - this.startTime;
    return elapsed;
  }
  // Returns time in millis since the last reset or split and resets
  // the timer 
  public long elapsedAndReset(){
    long elapsed = this.elapsed();
    this.lastTime = now();
    return elapsed;
  }


}
