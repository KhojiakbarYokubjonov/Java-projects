/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: PA5Main.java
 * ASSIGNMENT: Programming assignment 5
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: This program is a text-based simulator of garden. it reads
 * 			commands from a txt file and executes them.The garden
			will consist of a number of rows and columns of plots.
			Within each plot there can exist a single plant, which is 
			represented with a 5x5 grid of cells. Plants are divided up 
			into three different categories: trees, flowers, and vegetables, 
			all of which have unique characteristics. For example, trees grow up, 
			vegetables grow down, and flowers bloom as they grow.
			
 The input file can have the following format:
* -------------------------------------------
* | rows: 3
* | cols: 10
* |
* | PLANT (0, 0) banana
* | PRINT
* | GROW 2
* | GROW 4 (1, 3)
* | GROW 1 vegetable
* | GROW 1 lily
* | HARVEST
* | HARVEST (2, 3)
* | HARVEST garlic
* | PICK 
* | PICK (4, 3) 
* | PICK rose
* | CUT 
* | CUT (5, 2) 
* | CUT pine 
* -------------------------------------------
*
* The commands shown above are all of the commands that are supported
* by this program. It is assumed that (except for some specific errors), 
* the input is well-formed, and matches the format shown above.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/*
 * this class models a text-based version of a garden.
 */
public class PA5Main {
	public static void main(String[] args) throws FileNotFoundException {

		// reading the text file
		Scanner scanner = new Scanner(new File(args[0]));
		int rows = Integer.parseInt(scanner.nextLine().split(" ")[1]);
		int cols = Integer.parseInt(scanner.nextLine().split(" ")[1]);
		if (cols > 16) {
			System.out.println("Too many plot columns.");
			return;
		}
		Garden garden = new Garden(rows, cols);

		while (scanner.hasNextLine()) {
			String line = scanner.nextLine().toLowerCase();
			String[] command = line.split(" ");
			convertToLowerCase(command);

			if (command[0].equals("plant")) {
				gardenPlant(command, garden);

			} else if (command[0].equals("print")) {
				garden.print();

			} else if (command[0].equals("grow")) {
				growPlants(command, garden);

			} else if (command[0].equals("pick")) {
				pickFlowers(command, garden);

			} else if (command[0].equals("cut")) {
				cutTrees(command, garden);

			} else if (command[0].equals("harvest")) {
				harvestVeggies(command, garden);
			}
		}
	}

	/*
	 * this function accepts an array of String commands and converts all the
	 * characters in them to lower case.
	 * 
	 * @param command: an array of String commands.
	 */
	public static void convertToLowerCase(String[] command) {
		for (int i = 0; i < command.length; i++) {
			String part = command[i];
			command[i] = part.toLowerCase();
		}
	}

	/*
	 * this function accepts a String command and based on that, it will grow the
	 * specified plant(s) the specified number of times.
	 * 
	 * @param command: an array of String commands.
	 * 
	 * @param garden: A garden object that can store and manipulate plant objects.
	 */
	public static void growPlants(String[] command, Garden garden) {
		int num = Integer.parseInt(command[1]);
		if (command.length == 2) {
			garden.grow(num);
		} else if (command.length == 3) {
			if (command[2].charAt(0) == '(') {
				int[] coords = getCoords(command[2]);
				int x = coords[0];
				int y = coords[1];
				garden.grow(num, x, y);
			} else {
				garden.grow(num, command[2].toLowerCase());
			}
		}

	}

	/*
	 * the purpose of this function is to plant the specifies plants in the garden.
	 * 
	 * @param command: an array of String commands.
	 * 
	 * @param garden: A garden object that can store and manipulate plant objects.
	 */
	public static void gardenPlant(String[] command, Garden garden) {
		int[] coords = getCoords(command[1]);
		int x = coords[0];
		int y = coords[1];
		String type = command[2];
		garden.plant(x, y, type);
	}

	/*
	 * The purpose of this function is to pick flowers from the garden. Based on the
	 * command type, it will either pick all flowers or the specified ones only.
	 * 
	 * @param command: an array of String commands.
	 * 
	 * @param garden: A garden object that can store and manipulate plant objects.
	 */
	public static void pickFlowers(String[] command, Garden garden) {
		if (command.length == 1)
			garden.pick();
		else if (command.length == 2) {
			if (command[1].charAt(0) == '(') {
				int[] coords = getCoords(command[1]);
				int x = coords[0];
				int y = coords[1];
				garden.pick(x, y);
			} else {
				garden.pick(command[1].toLowerCase());
			}
		}
	}

	/*
	 * The purpose of this function is to cut trees in the garden. Based on the
	 * command type, it will either cut all trees or the specified ones only.
	 * 
	 * @param command: an array of String commands.
	 * 
	 * @param garden: A garden object that can store and manipulate plant objects.
	 */
	public static void cutTrees(String[] command, Garden garden) {
		if (command.length == 1)
			garden.cut();
		else if (command.length == 2) {
			if (command[1].charAt(0) == '(') {
				int[] coords = getCoords(command[1]);
				int x = coords[0];
				int y = coords[1];
				garden.cut(x, y);

			} else {
				garden.cut(command[1].toLowerCase());
			}
		}
	}

	/*
	 * The purpose of this function is to harvest vegetables in the garden. Based on
	 * the command type, it will either harvest all vegetables or the specified ones
	 * only.
	 * 
	 * @param command: an array of String commands.
	 * 
	 * @param garden: A garden object that can store and manipulate plant objects.
	 */

	public static void harvestVeggies(String[] command, Garden garden) {
		if (command.length == 1)
			garden.harvest();
		else if (command.length == 2) {
			if (command[1].charAt(0) == '(') {
				int[] coords = getCoords(command[1]);
				int x = coords[0];
				int y = coords[1];
				garden.harvest(x, y);
			} else {
				garden.harvest(command[1].toLowerCase());
			}
		}
	}

	/*
	 * This function accepts a String representing the xy location of a plant object
	 * in the garden and extracts these (x,y) coords, converts them to integers and
	 * returns them in an array.
	 * 
	 * @param location: A numeric String representing the xy coords.
	 */
	public static int[] getCoords(String location) {
		String[] coords = location.substring(1, 4).split(",");
		int x = Integer.parseInt(coords[0]);
		int y = Integer.parseInt(coords[1]);
		int[] xyCoords = { x, y };
		return xyCoords;
	}
}