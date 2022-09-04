/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: DGraph.java
 * ASSIGNMENT: Programming assignment 11
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: this program implements a weighted graph that represents a network of cities
 * 			in a TSP. it also has two other helper classes to represent the minimum path 
 * 			and the edges.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/*creates a minimum path class that has 2 fields:
 * @field cost: the float, distance between two edges.
 * @field path: an array representing the traversal order of edges.
 */
class MinPath {
	double cost;
	List<Integer> path;

	// initializes the minimum path class
	public MinPath(double cost, List<Integer> path) {
		this.cost = cost;
		this.path = path;
	}
}

/*
 * implements a directed weighted graph to represent the cities and their
 * distances.
 * 
 */
public class DGraph {
	private int numVertices;

	/*
	 * implements an Edge class for the directed weighted class
	 * 
	 * @field label:an integer, representing an edge label.
	 * 
	 * @field weight: a double, the distance between the previous edge and this one.
	 */
	private class Edge {
		int label;
		double weight;

		// initializes a new instance of Edge class
		public Edge(int v, double w) {
			label = v;
			weight = w;
		}
	}

	MinPath minPath = new MinPath(1000, new ArrayList<>());
	private ArrayList<LinkedList<Edge>> adjList = new ArrayList<>();

	/*
	 * initializes a new instance of an DGraph
	 * 
	 * @param numVertices: the # of edges >=1
	 */
	DGraph(int numVertices) {
		this.numVertices = numVertices;
		for (int i = 0; i < numVertices; i++)
			adjList.add(new LinkedList<Edge>());
	}

	/*
	 * adds a new edge to the directed weighted graph.
	 * if this edge already exist, it will just update its weight.
	 * 
	 * @param u: an index in an array of linkedlists
	 * 
	 * @param v: an integer label for the new edge.
	 * 
	 * @param w: a float weight between the two adjacent edges.
	 */
	void addEdge(int u, int v, double w) {
		if(getEdge(u,v)!= null) {
			Edge edge = getEdge(u,v);
			edge.weight = w;
			return;
		}
		adjList.get(u).add(new Edge(v, w));
		// adjList.get(v).add(new Edge(u,w));
	}

	/*
	 * returns the edge with the specified label.
	 * 
	 * @param u: an integer, index in the array.
	 * 
	 * @param v: an integer, label for an edge.
	 */
	Edge getEdge(int u, int v) {
		for (int i = 0; i < adjList.get(u).size(); i++)
			if (adjList.get(u).get(i).label == v)
				return adjList.get(u).get(i);
		return null;
	}

	/*
	 * traverses the graph through the incident edges with the lowest weight.
	 * 
	 * @param start: an integer, the starting edge. returns: an array - {total cost,
	 * path}
	 */
	Object[] heuristicT(int start) {
		boolean visited[] = new boolean[numVertices];
		ArrayList<Integer> visitOrder = new ArrayList<>();
		visited[start] = true;
		int nextVertex = start;
		int lb = adjList.get(start).get(0).label;
		double total = 0;
		visitOrder.add(start + 1);

		while (visitOrder.size() != numVertices) {
			double lowest = 777;
			for (Edge edge : adjList.get(nextVertex)) {
				if (!visited[edge.label]) {
					if (edge.weight < lowest) {
						lowest = edge.weight;
						lb = edge.label;
					}
				}
			}
			total += lowest;
			visitOrder.add(lb + 1);
			visited[lb] = true;
			nextVertex = lb;
		}
		total += getEdge(nextVertex, start).weight;
		Object[] retValue = { total, visitOrder };
		return retValue;
	}

	/*
	 * finds a possible traversing solution for the graph recursively, recording its
	 * value, and then backing up to the previous vertex to explore other solutions.
	 * 
	 * @param start: an integer, the starting edge. returns the path with the
	 * minimum cost.
	 */
	public MinPath backtrack(int start) {
		boolean[] visited = new boolean[numVertices];
		List<Integer> path = new ArrayList<>();
		path.add(start + 1);
		double total = 0;
		backtrack(start, visited, path, total, minPath);

		return minPath;

	}

	/*
	 * overloads the previous method.
	 * 
	 * @param u: an index in an array of linkedlists
	 * 
	 * @param visited: an array that keeps track of the visited edges
	 * 
	 * @param path: an array that shows the visited order of the vertices.
	 * 
	 * @param total: a double type, the total cost of traversing the graph.
	 * 
	 * @param minPath: an array that shows the visited order for the most efficient
	 * path.
	 */
	public void backtrack(int u, boolean[] visited, List<Integer> path, double total, MinPath minPath) {
		visited[u] = true;

		// if all the vertices are visited, then the Hamiltonian cycle exists
		if (path.size() == numVertices) {
			for (Edge edge : adjList.get(u)) {
				if (edge.label + 1 == path.get(0)) {
					total += edge.weight;
				}

			}

			if (total < minPath.cost) {
				minPath.cost = total;
				List<Integer> copy = new ArrayList<>();
				for (int num : path) {
					copy.add(num);
				}
				minPath.path = copy;
			}

			return;
		}

		// Check if every edge starting from vertex `u` leads to a solution or not
		for (int i = 0; i < adjList.get(u).size(); i++) {
			Edge edge = adjList.get(u).get(i);
			if (!visited[edge.label]) {
				path.add(edge.label + 1);
				backtrack(edge.label, visited, path, total += edge.weight, minPath);
				// backtrack for the path
				visited[edge.label] = false; // so v could be used in another path
				path.remove(path.size() - 1);
				total -= edge.weight;
			}
		}

	}

	/*
	 * finds a possible traversing solution for the graph recursively, recording its
	 * value, and then backing up to the previous vertex to explore other solutions.
	 * IT STOPS THE RECURSION WHEN THE CURRENT COST BECOMES GREATER THAN THE
	 * EXISTING MINIMUM COST.
	 * 
	 * @param start: an integer, the starting edge. returns the path with the
	 * minimum cost.
	 */
	public MinPath enhancedBacktrack(int start) {
		boolean[] visited = new boolean[numVertices];
		List<Integer> path = new ArrayList<>();
		path.add(start + 1);
		double total = 0;
		enhancedBacktrack(start, visited, path, total, minPath);
		return minPath;

	}

	/*
	 * overloads the previous method.
	 * 
	 * @param u: an index in an array of linkedlists
	 * 
	 * @param visited: an array that keeps track of the visited edges
	 * 
	 * @param path: an array that shows the visited order of the vertices.
	 * 
	 * @param total: a double type, the total cost of traversing the graph in a
	 * certain order.
	 * 
	 * @param minPath: an array that shows the visited order for the most efficient
	 * path.
	 */
	public void enhancedBacktrack(int u, boolean[] visited, List<Integer> path, double total, MinPath minPath) {
		visited[u] = true;

		// if all the vertices are visited, then the Hamiltonian cycle exists
		if (path.size() == numVertices) {
			for (Edge edge : adjList.get(u)) {
				if (edge.label + 1 == path.get(0)) {
					total += edge.weight;
				}

			}


			if (total < minPath.cost) {
				minPath.cost = total;
				List<Integer> copy = new ArrayList<>();
				for (int num : path) {
					copy.add(num);
				}
				minPath.path = copy;
			}

			return;
		}
		if (total > minPath.cost) {
			return;
		}

		// Check if every edge starting from vertex `u` leads to a solution or not
		for (int i = 0; i < adjList.get(u).size(); i++) {
			Edge edge = adjList.get(u).get(i);
			if (!visited[edge.label]) {
				path.add(edge.label + 1);
				enhancedBacktrack(edge.label, visited, path, total += edge.weight, minPath);
				// backtrack for the path
				visited[edge.label] = false; // so v could be used in another path
				path.remove(path.size() - 1);
				total -= edge.weight;
			}
		}
	}

	// returns the format of the directed weighted graph
	public String toString() {
		String str = "";
		for (int i = 0; i < numVertices; i++) {
			str += i + 1 + ": [";
			LinkedList<Edge> list = adjList.get(i);

			for (int j = 0; j < list.size(); j++)
				str += list.get(j).label + 1 + "/" + list.get(j).weight + ", ";

			str += "]\n";
		}
		return str;
	}
}