/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: Tree.java
 * ASSIGNMENT: Programming assignment 5
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: This program implements a Tree class for the Garden program,
 * 			and creates several useful methods to manipulate Tree objects.
 */

/*
 * this class models a Tree for the garden program.
 */
public class Tree extends Plant {
	private String name;

	/*
	 * a method that constructs a new instance of a tree class with the specified
	 * name.
	 * 
	 * @param type: A string, type of the tree.
	 */
	public Tree(String type) {
		super(type);
		name = type.toLowerCase();
	}

	/*
	 * A method that returns the name of the tree.
	 */
	public String getName() {
		return name;
	}

	/*
	 * A method that creates a plot, a grid of 5x5 cells, for the tree.
	 */
	@Override
	public void plant() {
		plot[4][2] = name.charAt(0);
	}

	/*
	 * this method grows a tree the specified number of times.
	 * 
	 * @param num: an integer showing how many times a tree should grow.
	 */
	@Override
	public void grow(int num) {
		growthLevel += num;
		_grow(growthLevel);
	}

	/*
	 * this is a private recursive helper function for the above grow method.
	 * 
	 * @param num: an integer showing how many times a tree should grow.
	 */
	private void _grow(int num) {
		if (num == 0)
			return;
		if (num == 1)
			plot[3][2] = name.charAt(0);
		if (num == 2)
			plot[2][2] = name.charAt(0);
		if (num == 3)
			plot[1][2] = name.charAt(0);
		if (num == 4)
			plot[0][2] = name.charAt(0);
		_grow(num - 1);
	}

	/*
	 * A method that returns the class name.
	 * 
	 */
	@Override
	public String getPlantClass() {
		return "tree";
	}

	/*
	 * A method that returns the name of the tree.
	 */
	@Override
	public String getPlantName() {
		return name;
	}
}