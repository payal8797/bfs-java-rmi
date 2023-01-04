import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

public class BFSImpl extends UnicastRemoteObject implements BFS {
  
  private int numVertices;
  private List<List<Integer>> adjacencyList;
  
  public BFSImpl( ) throws RemoteException { 
    this.adjacencyList = new ArrayList<>(this.numVertices);
  }
  
  //function to create empty adjacency list
  public void adjacencyListInit(int numVertices) throws RemoteException {
    this.numVertices = numVertices;
    for (int i = 0; i < numVertices; i++) {
      this.adjacencyList.add(new ArrayList<>());
    }  
  }

  //function to add edges between 2 vertices
  public List<List<Integer>> addEdge(int numVertices, List<List<Integer>> edgesList) {
    edgesList.forEach((list) ->
    {
      this.adjacencyList.get(list.get(0)).add(list.get(1));
      this.adjacencyList.get(list.get(1)).add(list.get(0));
    }
    );
    return adjacencyList;
  }

  //find shortest path
  public List getShortestPath(int source, int destination) {
    //predecessor array stores predecessor of each element
    int predecessor[] = new int[this.numVertices];
    if (checkPath(adjacencyList, source, destination, this.numVertices, predecessor) == false) {
      System.out.println("Given source and destination" +"are not connected!!");
      return List.of();
    }

    // create linkedList to store path
    LinkedList<Integer> path = new LinkedList<Integer>();
    path.add(destination);
    
    while (predecessor[destination] != -1) {
        path.add(predecessor[destination]);
        destination = predecessor[destination];
    }
    adjacencyList.clear();
    return path;
  }

  public boolean checkPath(List<List<Integer>> adjacencyList, int source, int destination, int v, int predecessor[])
    {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        boolean visited[] = new boolean[v];

        //set all vertices as not visited and distance as -1
        Arrays.fill(visited, false);
        Arrays.fill(predecessor, -1);

        // set source as visited and add visited nodes to queue
        visited[source] = true;
        queue.add(source);

        //bfs logic
        while (!queue.isEmpty()) {
            int u = queue.remove();
            for (int i = 0; i < adjacencyList.get(u).size(); i++) {
                int neighbor = adjacencyList.get(u).get(i);
                if (visited[neighbor] == false) {
                    visited[neighbor] = true;
                    predecessor[neighbor] = u;
                    queue.add(neighbor);

                    //if destination is found, return true
                    if (neighbor == destination)
                        return true;
                }
            }
        }
        //if destination is not found, return false
        return false;
    }

  }
