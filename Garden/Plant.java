
/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: Plant.java
 * ASSIGNMENT: Programming assignment 5
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: This program implements an abstract Plant class for the Garden program,
 * 			and creates several useful methods.
 */
import java.util.ArrayList;

/*
 * this class models a Plant for the garden program.
 */
abstract class Plant {
	String type;
	int growthLevel;
	char[][] plot;

	/*
	 * Constructs the plant object and supplies it with a plot, 2D array of cells.
	 * 
	 * @param type: A string, a type of plant
	 */
	public Plant(String type) {

		this.type = type.toLowerCase();
		growthLevel = 0;
		plot = new char[5][5];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				plot[i][j] = '.';
			}
		}

	}

	/*
	 * this method creates a plot, a grid of 5x5 cells, for the plant.
	 */
	abstract void plant();

	/*
	 * this method grows a plant the specified number of times.
	 * 
	 * @param num: an integer showing how many times a plant should grow.
	 */
	abstract void grow(int num);

	/*
	 * * A method that returns the name of the Plant class.
	 */
	abstract String getPlantClass();

	/*
	 * A method that returns the name of the Plant.
	 */
	abstract String getPlantName();

	/*
	 * this method prints the plot of a plant object.
	 */
	public void print() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(plot[i][j]);
			}
			System.out.println();
		}
	}

}
