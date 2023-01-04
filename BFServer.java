import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class BFServer {
  public static void main(String[] args) {
    try {
      // create the BFS object
      BFS bfs = new BFSImpl();
      // create the RMI registry
      LocateRegistry.createRegistry(1099);
      // bind the BFS object to the registry
      Naming.rebind("rmi://localhost/BFS", bfs);
      System.out.println("BFS Server is ready.");
    } catch (Exception e) {
      System.out.println("BFS Server failed: " + e);
    }
  }
}