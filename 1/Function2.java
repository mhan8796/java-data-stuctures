// Function of 2 parameters
// Allows 2-parameter reductions to happen
public interface Function2<Q,R,S>{
  public Q call(R x, S y);
}
