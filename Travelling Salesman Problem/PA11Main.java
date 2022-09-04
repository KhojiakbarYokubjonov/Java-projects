
/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: PA11Main.java
 * ASSIGNMENT: Programming assignment 11
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: the program accepts two command line arguments: .mtx file 
 * 	and the type of algorithms to be used, either time, heuristic, backtracking 
 * 	or the improved version.
 * 	The input files are in the matrix market (.mtx) format. 
 * 	An example input file is:
 * 
			%%MatrixMarket matrix coordinate real general
			3 3 6
			1 2 1.0
			2 1 2.0
			1 3 3.0
			3 1 4.0
			2 3 5.0
			3 2 6.0
		
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class PA11Main {

	public static void main(String[] args) throws FileNotFoundException {
		try {
			// reading the .mtx file
			Scanner file = new Scanner(new File(args[0]));

			String startLine = null;
			while (file.hasNextLine()) {
				startLine = file.nextLine();
				if (!startLine.startsWith("%"))
					break;
			}

			// identifying the # of vertices for the graphs
			int vertices = Integer.parseInt(startLine.split(" ")[0]);
			DGraph graph = new DGraph(vertices);

			// iterates through the file, and adds new edges using the file contents
			while (file.hasNext()) {
				String[] line;
				String line1 = file.nextLine();
				if (line1.split(" ").length > 3) {
					line = line1.split("  ");
				} else {
					line = line1.split(" ");
				}

				// extracting the labels and the weight for the adjacent vertices
				int label1 = Integer.parseInt(line[0]);
				int label2 = Integer.parseInt(line[1]);
				float weight = Float.parseFloat(line[2]);
				graph.addEdge(label1 - 1, label2 - 1, weight);

			}

			// reads the second command line input and identifies the command type
			String directive = new String(args[1]);
			if (directive.equals("HEURISTIC")) {
				Object[] solution = graph.heuristicT(0);
				System.out.print("cost = " + Math.round((double) solution[0] * 10.0) / 10.0 
						+ ", " + "visitOrder = "+ solution[1]);
			} else if (directive.equals("BACKTRACK")) {
				MinPath minPath = graph.backtrack(0);
				System.out.print("cost = " + Math.round((double) minPath.cost * 10.0) / 10.0 
						+ ", " + "visitOrder = "+ minPath.path);

			} else if (directive.equals("MINE")) {
				MinPath minPath = graph.backtrack(0);
				System.out.print("cost = " + Math.round((double) minPath.cost * 10.0) / 10.0 
						+ ", " + "visitOrder = "+ minPath.path);

			} else {
				reportTime(graph);
			}

			file.close();

		} catch (FileNotFoundException e) {
			System.out.println("The file could not be found!");
		} catch (Exception e) {
			System.out.println("An error occured when running the program!");
		}

	}

	/*
	 * reports the runtime costs of heuristic and backtracking approaches.
	 * 
	 * @param graph: a DGraph object, represents the cities and their distances
	 */
	public static void reportTime(DGraph graph) {

		// measures the runtime of the heuristic approach
		long startTime = System.nanoTime();
		Object[] retPath = graph.heuristicT(0);
		System.out.print("heuristic: cost = " + retPath[0] + ", ");
		String result = "heuristic: cost = " + retPath[0] + ", ";
		long endTime = System.nanoTime();
		long duration = (endTime - startTime); 
		System.out.print(duration / 1000000 + " milliseconds\n");
		result += duration / 1000000 + " milliseconds\n";

		// measures the runtime of the enhanced backtracking approach
		long startTime2 = System.nanoTime();
		MinPath minPath = graph.enhancedBacktrack(0);
		System.out.print("mine: cost = " + minPath.cost + ", ");
		result += "mine: cost = " + minPath.cost + ", ";
		long endTime2 = System.nanoTime();
		long duration2 = (endTime2 - startTime2); 
		System.out.print(duration2 / 1000000 + " milliseconds\n");
		result += duration2 / 1000000 + " milliseconds\n";

		// measures the runtime of the backtracking approach
		long startTime3 = System.nanoTime();
		MinPath path = graph.backtrack(0);
		System.out.print("backtrack: cost = " + path.cost + ", ");
		result += "backtrack: cost = " + path.cost + ", ";
		long endTime3 = System.nanoTime();
		long duration3 = (endTime3 - startTime3); 
		System.out.print(duration3 / 1000000 + " milliseconds\n");
		result += duration3 / 1000000 + " milliseconds\n";

		recordResults(result);

	}

	/*
	 * records the runtime costs of heuristic and backtracking methods in a
	 * README.md file.
	 * 
	 * @param results: A string, the runtime costs of those methods.
	 */
	public static void recordResults(String results) {
		String description = "************************README*************************\r\n"
				+ "The heuristic approach has the shortest runtime since it \r\n"
				+ "only finds one possible path while the other two functions \r\n"
				+ "find multiple paths and return the most efficient one. In my \r\n"
				+ "variation, I have improved the backtracking approach. Thus, \r\n"
				+ "it has a better performance since it stops the recursion \r\n"
				+ "when the current cost is higher than the existing minimum cost.";
		try {
			FileWriter file = new FileWriter("D:\\CSC_210\\Programming_assignments\\src\\PA11\\README.md", false);
			file.write(results);
			file.write(description);
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
