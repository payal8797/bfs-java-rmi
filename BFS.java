import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface BFS extends Remote {
  public List<List<Integer>> addEdge(int edges, List<List<Integer>> edgesList) throws RemoteException;
  public List getShortestPath(int startNode, int endNode) throws RemoteException;
  public void adjacencyListInit(int numVertices) throws RemoteException;
}