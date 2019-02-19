import java.util.*;


public class TopKWords{
  
  static Set<String> stopwords=new HashSet<String>();
  static TreeMap<String, Integer> mtree;
  static HashMap<String, Integer> mhash;
  static Set<Map.Entry<String, Integer>> both;
  static int num,vnum=0;
  static Timer timer = new Timer("TOPK");
  
  public static void main(String args[]) throws Exception{
    num=Integer.parseInt(args[1]);
    if(args.length==5)
      getStopWords(args[4]);
      timer.addSplit("stopwords time");
    if(args[2].equals("tree")){
      treeCount(args[0]);
      timer.addSplit("wordcount time");
    }
    if(args[2].equals("hash")){
      hashCount(args[0]);
      timer.addSplit("wordcount time");
    }
    if(args[3].equals("pq")){
      topkpq();
      timer.addSplit("topk calculation time");
    }
    if(args[3].equals("fullsort")){
      topkfull();
      timer.addSplit("topk calculation time");
    }
    System.out.println("TOPK:   "+args[0]);
    if(args.length==5)
      System.out.println("TOPK:   "+args[4]);
    System.out.println("TOPK:   "+args[2]+" count method");
    System.out.println("TOPK:   "+args[3]+" topk method");
    System.out.println("TOPK:   "+vnum+" document words");
    System.out.println("TOPK:   "+stopwords.size()+" stop words");
    System.out.println("TOPK:   "+both.size()+" unique words");
    System.out.println("TOPK:   "+num+" topk words");
    System.out.println(timer.toString());
  }
  
  
  static void getStopWords(String fname){
    Set<String> words= new HashSet<String>(); 
    WordStream ws = new WordStream(fname);
    timer.addSplit("stopwords setup time"); //Sorry for two set up time because the structure is different
    while(ws.hasWord()){
      words.add(ws.nextWord());
    }
    ws.close();
    stopwords=words;
  }  
  
  
  //Your program is required to allow the user to select between using maps and sets that are either tree-based or hash table-based
  //the obvious way to filter stop words is to build a set of the stop words and 
  //if a main document word is in the set of stop words, ignore it. 
  //Trees and hash tables both also provide a means to build set-like data structures.
  static void hashCount(String fname){
    HashMap<String,Integer> hash= new HashMap<String, Integer>();
    String word=new String();
    WordStream ws= new WordStream(fname);
    timer.addSplit("setup time"); //Sorry for two set up time because the structure is different
    while(ws.hasWord()){
    	vnum++;
      word=ws.nextWord();
      if(stopwords.contains(word))
        continue;
      else if(hash.containsKey(word)){
        hash.put(word,hash.get(word)+1);
      }
      else{
      hash.put(word,1);
      }
    }
    ws.close();
    mhash=hash;
    both=hash.entrySet();
  }
    
  
  static void treeCount(String fname){
    TreeMap<String, Integer> tree= new TreeMap<String, Integer>();
    String word=new String();
    WordStream ws= new WordStream(fname);
    timer.addSplit("setup time"); //Sorry for two set up time because the structure is different
    while(ws.hasWord()){
    	vnum++;
      word=ws.nextWord();
      if(stopwords.contains(word)){
        continue;
      }
      else if(tree.containsKey(word)){
        tree.put(word,tree.get(word)+1);
      }
      else{
      tree.put(word,1);
      }
    }
    mtree=tree;
    both=tree.entrySet();
  }
  
  
  //After all words counts are inserted, the most frequent K can be extracted with calls to retrieve the highest priority item.
  static void topkpq(){
    PriorityQueue<Integer> values=new PriorityQueue<Integer>(both.size(), Collections.reverseOrder());
    Set<String> keys=new HashSet<String>();
    for(Map.Entry<String, Integer> pairs :both){
      values.add(pairs.getValue());
    }
    for(int i =0; i<Math.min(num,both.size());i++){
      int va=values.poll();
      for(Map.Entry<String, Integer> pairs :both){
        if(pairs.getValue()==va&&keys.add(pairs.getKey()))
          System.out.println(va+"     "+pairs.getKey());
        }
    }
  }


  //"full sort" of an array of word counts
  static void topkfull(){
    Integer[] values=new Integer[both.size()];
    Set<String> keys=new HashSet<String>();
    int l=0;
    for(Map.Entry<String, Integer> pairs :both){
      values[l]=pairs.getValue();
      l++;
    }
    Arrays.sort(values, Collections.reverseOrder());
    for(int i =0; i< Math.min(num,both.size());i++){
      int va=values[i];
      for(Map.Entry<String, Integer> pairs :both){
        if(pairs.getValue()==va&&keys.add(pairs.getKey()))
        	System.out.println(va+"   "+pairs.getKey());
      	}  
    }
  }
}