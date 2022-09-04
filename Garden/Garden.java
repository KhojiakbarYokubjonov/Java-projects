/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: Garden.java
 * ASSIGNMENT: Programming assignment 5
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: This program implements a garden class for the Garden program,
 * 			and creates several useful methods to manipulate plant objects.
 */

import java.util.Arrays;

/*
 * this class models a garden for the garden program
 */
public class Garden {

	private int row;
	private int column;
	private Plant[][] plants;
	private Plant newPlant;
	private String[] flowers = { "iris", "lily", "rose", "daisy", "tulip", "sunflower" };
	private String[] trees = { "oak", "willow", "banana", "coconut", "pine" };
	private String[] vegetables = { "garlic", "zucchini", "tomato", "yam", "lettuce" };

	/*
	 * Initializes the garden object and creates a 2D array with the lengths
	 * specified by the inputs.
	 * 
	 * @param row: an integer representing the lenght of the garden.
	 * 
	 * @param column: an integer representing the width of the garden.
	 */
	public Garden(int row, int column) {
		this.row = row;
		this.column = column;
		plants = new Plant[row][column];

	}

	/*
	 * this method plants a specified plant in the garden at given plot coordinates.
	 * 
	 * @param x,y: a pair of integers representing plot coordinates.
	 */
	public void plant(int x, int y, String type) {
		boolean foundInFlowers = Arrays.asList(flowers).contains(type);
		boolean foundInTrees = Arrays.asList(trees).contains(type);
		boolean foundInVegetables = Arrays.asList(vegetables).contains(type);

		if (foundInFlowers) {
			this.newPlant = new Flower(type);
		} else if (foundInTrees) {
			this.newPlant = new Tree(type);
		} else if (foundInVegetables) {
			this.newPlant = new Vegetable(type);
		}

		newPlant.plant();
		if (plants[x][y] != null) {
			System.out.println("Can't plant there.\n");
			return;
		}
		plants[x][y] = newPlant;
	}

	/*
	 * this method grows all the plants in the garden the specified number of times
	 * based on the input command.
	 * 
	 * @param num: an integer that specifies the number of times that the plants
	 * will grow.
	 */
	public void grow(int num) {
		System.out.println("> GROW " + num + "\n");
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Plant plant = plants[i][j];
				if (plant != null)
					plant.grow(num);

			}
		}
	}

	/*
	 * this method grows the specified plants a number of times based on the input
	 * command.
	 * 
	 * @param num:an integer that specifies the number of times that the plants will
	 * grow.
	 * 
	 * @param plantType:A string, a certain type of plant.
	 */
	public void grow(int num, String plantType) {
		System.out.println("> GROW " + num + " " + plantType + "\n");

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < column; j++) {
				Plant plant = plants[i][j];
				if (plant != null)
					if (plant.getPlantClass().equals(plantType))
						plant.grow(num);
			}
		}
		for (int i = 0; i < row; i++) {

			for (int j = 0; j < column; j++) {
				Plant plant = plants[i][j];
				if (plant != null)
					if (plant.getPlantName().equals(plantType))
						plant.grow(num);
			}
		}
	}

	/*
	 * This method grows the plant at given plot coordinates the specified number of
	 * times. If there is nothing at this position or the position is outside the
	 * size of the garden, it prints, Cant grow there..
	 * 
	 * @param num: an integer that specifies the number of times that the plants
	 * will grow.
	 * 
	 * @param x,y: a pair of integers representing plot coordinates.
	 */
	public void grow(int num, int x, int y) {
		System.out.println("> GROW " + num + " (" + x + "," + y + ")\n");
		if (x >= plants.length || x < 0 || y >= plants[0].length || y < 0) {
			System.out.println("Can't grow there.\n");
			return;
		}
		if (plants[x][y] == null) {
			System.out.println("Can't grow there.\n");
			return;
		}
		plants[x][y].grow(num);

	}

	/*
	 * this method removes all the vegetables in the garden
	 */
	public void harvest() {
		System.out.println("> HARVEST\n");
		for (int i = 0; i < row; i++) {
			for (int n = 0; n < 5; n++) {
				for (int j = 0; j < column; j++) {
					Plant plant = plants[i][j];
					if (plant != null)
						if (plant.getPlantClass() == "vegetable")
							plants[i][j] = null;

				}
			}
		}

	}

	/*
	 * this method removes all existing vegetables of the specified type.
	 * 
	 * @param vegetableType: a String, a certain type of vegetable.
	 */
	public void harvest(String vegetableType) {
		System.out.println("> HARVEST " + vegetableType + "\n");
		for (int i = 0; i < row; i++) {
			for (int n = 0; n < 5; n++) {
				for (int j = 0; j < column; j++) {
					Plant plant = plants[i][j];
					if (plant != null)
						if (plant.getPlantName().equals(vegetableType))
							plants[i][j] = null;

				}
			}
		}

	}

	/*
	 * this method harvests vegetables at location (x,y). If it's not a vegetable or
	 * the location is outside of garden, it will continue with a print statement.
	 * 
	 * @param x,y: a pair of integers representing plot coordinates.
	 */
	public void harvest(int x, int y) {
		System.out.println("> HARVEST " + "(" + x + "," + y + ")\n");

		if (x >= plants.length || x < 0 || y >= plants[0].length || y < 0) {
			System.out.println("Can't harvest there.\n");
			return;
		}
		if (!plants[x][y].getPlantClass().equals("vegetable")) {
			System.out.println("Can't harvest there.\n");
			return;
		}

		plants[x][y] = null;

	}

	/*
	 * this method removes all flowers from the garden
	 */
	public void pick() {
		System.out.println("> PICK\n");
		for (int i = 0; i < row; i++) {
			for (int n = 0; n < 5; n++) {
				for (int j = 0; j < column; j++) {
					Plant plant = plants[i][j];
					if (plant != null)
						if (plant.getPlantClass().equals("flower"))
							plants[i][j] = null;

				}
			}
		}

	}

	/*
	 * this method picks flowers of the specified type. if there're no flowers, it
	 * won't do anything.
	 * 
	 * @param type: a String, a certain type of flower.
	 */
	public void pick(String type) {
		System.out.println("> PICK " + type + "\n");

		for (int i = 0; i < row; i++) {
			for (int n = 0; n < 5; n++) {
				for (int j = 0; j < column; j++) {
					Plant plant = plants[i][j];
					if (plant != null)
						if (plant.getPlantName().equals(type.toLowerCase()))
							plants[i][j] = null;

				}
			}
		}

	}

	/*
	 * this method picks the flower at location (x,y). if it is not a flower, or
	 * outside of garden, it will move on with a print statement.
	 * 
	 * @param x,y: a pair of integers representing plot coordinates.
	 */
	public void pick(int x, int y) {
		System.out.println("> PICK " + "(" + x + "," + y + ")\n");
		if (x >= plants.length || x < 0 || y >= plants[0].length || y < 0) {
			System.out.println("Can't pick there.\n");
			return;
		}

		if (plants[x][y] == null) {
			System.out.println("Can't pick there.");
			return;
		}

		if (!plants[x][y].getPlantClass().equals("flower")) {
			System.out.println("Can't pick there.\n");
			return;
		}

		plants[x][y] = null;

	}

	/*
	 * this method removes all Trees from the Garden.
	 */
	public void cut() {
		System.out.println("> CUT\n");
		for (int i = 0; i < row; i++) {
			for (int n = 0; n < 5; n++) {
				for (int j = 0; j < column; j++) {
					Plant plant = plants[i][j];
					if (plant != null)
						if (plant.getPlantClass().equals("tree"))
							plants[i][j] = null;

				}
			}
		}

	}

	/*
	 * this method cuts tree at location (x,y). if it's not a tree or outside of
	 * garden, it will move on with a print statement.
	 * 
	 * @param x,y: a pair of integers representing plot coordinates.
	 * 
	 */
	public void cut(int x, int y) {
		System.out.println("> CUT " + "(" + x + "," + y + ")\n");
		if (x >= plants.length || x < 0 || y >= plants[0].length || y < 0) {
			System.out.println("Can't cut there.\n");
			return;
		}
		if (!plants[x][y].getPlantClass().equals("tree")) {
			System.out.println("Can't cut there.\n");
			return;
		}

		plants[x][y] = null;

	}

	/*
	 * this method cuts all Trees of the specified type. If there are no Trees of
	 * that type, it won't do anything.
	 * 
	 * @param type: a String, a certain type of tree.
	 */
	public void cut(String type) {
		System.out.println("> CUT " + type + "\n");
		for (int i = 0; i < row; i++) {
			for (int n = 0; n < 5; n++) {
				for (int j = 0; j < column; j++) {
					Plant plant = plants[i][j];
					if (plant != null)
						if (plant.getPlantName().equals(type.toLowerCase()))
							plants[i][j] = null;
				}
			}
		}

	}

	/*
	 * this method prints garden by looping through all existing plant objects.
	 */
	public void print() {
		System.out.println("> PRINT");
		for (int i = 0; i < row; i++) {
			for (int n = 0; n < 5; n++) {
				for (int j = 0; j < column; j++) {
					Plant plant = plants[i][j];
					if (plant != null)
						System.out.print(plant.plot[n]);
					else
						System.out.print(".....");

				}
				System.out.println();
			}
		}
		System.out.println();
	}

}
