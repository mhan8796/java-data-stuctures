// Used only to return all elements in a sparse matrix using its
// .allElements method
public class Element<T>{
  public int i, j;
  public T data;
  public Element(int i, int j, T d){
    this.i = i; this.j = j; this.data = d;
  }
  public boolean equals(Object o){
    if(o instanceof Element){
      Element e = (Element) o;
      return
	this.i == e.i && 
	this.j == e.j &&
	this.data.equals(e.data);
    }
    else{
      return false;
    }
  }
  public String toString(){
    return String.format("(%d, %d, %s)", this.i,this.j,this.data);
  }
      
}
