/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: Vegetable.java
 * ASSIGNMENT: Programming assignment 5
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: This program implements a vegetable class for the Garden program,
 * 			and creates several useful methods to manipulate vegetable objects.
 */

/*
 * this class models a vegetable for the garden program.
 */
public class Vegetable extends Plant {
	private String name;

	/*
	 * Constructs a new instance of vegetable class with the specified name(or
	 * type).
	 * 
	 * @param type: A string, type of the tree.
	 */
	public Vegetable(String type) {
		super(type);
		name = type.toLowerCase();

	}

	/*
	 * this method creates a plot, a grid of 5x5 cells, for the vegetable.
	 */
	@Override
	public void plant() {
		plot[0][2] = type.charAt(0);

	}

	/*
	 * this method grows a vegetable the specified number of times.
	 * 
	 * @param num: an integer showing how many times a vegetable should grow.
	 */
	@Override
	public void grow(int num) {
		growthLevel += num;
		_grow(growthLevel);
	}

	/*
	 * this is a private recursive helper function for the above grow method.
	 * 
	 * @param num: an integer showing how many times a vegetable should grow.
	 */
	private void _grow(int num) {
		if (num == 0)
			return;
		if (num == 1)
			plot[1][2] = name.charAt(0);
		if (num == 2)
			plot[2][2] = name.charAt(0);
		if (num == 3)
			plot[3][2] = name.charAt(0);
		if (num == 4)
			plot[4][2] = name.charAt(0);
		_grow(num - 1);
	}

	/*
	 * A method that returns the name of the vegetable class.
	 */
	@Override
	public String getPlantClass() {
		return "vegetable";
	}

	/*
	 * A method that returns the name of the vegetable.
	 */
	@Override
	public String getPlantName() {
		return name;
	}

}
