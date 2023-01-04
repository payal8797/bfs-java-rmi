import java.rmi.Naming;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BFClient {
  public static void main(String[] args) {
    try {
	// look up the BFS object from the registry
	BFS bfs = (BFS) Naming.lookup("rmi://localhost/BFS");

	//create a blank list of list to store the edges
	List<List<Integer>> edgesList = new ArrayList<>();

	// input number of edges
	Scanner scanner = new Scanner(System.in);
	System.out.print("Enter edges: ");
	int edges = scanner.nextInt();
	
	//initialize the adjacency list
	bfs.adjacencyListInit(edges);

	//input nodes
	for (int i = 0; i < edges-1; i++) {
		List<Integer> temp = new ArrayList<>();
		System.out.print("For index " +i+ ":");
		System.out.println();
		System.out.print("Enter node1: ");
		int node1 = scanner.nextInt();
		System.out.print("Enter node2: ");
		int node2 = scanner.nextInt();
		temp.add(node1);
		temp.add(node2);
		edgesList.add(temp);
	}

	//add edges to the nodes
	List<List<Integer>> adjacencyList = bfs.addEdge(edges, edgesList);	
	
	//print nodes at each vertex
	System.out.print("Printing nodes at each vertex..");
	System.out.println();
	for (int i = 0; i < edges; i++) {
		System.out.print("Nodes at Vertex " + i + ": ");
		for (int j : adjacencyList.get(i)) {
		  System.out.print(j + " ");
		}
		System.out.println();
	  }


	//find shortest path and distance between 2 nodes
	System.out.print("Enter start node: ");
	int startNode = scanner.nextInt();
	System.out.print("Enter end node: ");
	int endNode = scanner.nextInt();
	List path = bfs.getShortestPath(startNode,endNode);

    // Print shortest path and length of path
    System.out.print("Shortest path is:");
    for (int i = path.size() - 1; i >= 0; i--) {
        System.out.print(path.get(i) + " ");
    }
	int sizePath= path.size() -1;
	System.out.printf("\nLength of path " + sizePath);
    } catch (Exception e) {
      System.out.println("BFSClient exception: " + e);
    }
  }
}