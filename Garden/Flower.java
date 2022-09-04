/*
 * AUTHOR: Khojiakbar Yokubjonov
 * FILE: Flower.java
 * ASSIGNMENT: Programming assignment 5
 * COURSE: CSc 210, Fall 2021
 * PURPOSE: This program implements a Flower class for the Garden program,
 * 			and creates several useful methods to manipulate flower objects.
 */

/*
 * this class models a flower for the garden program.
 */
public class Flower extends Plant {
	private char letter;
	private String name;

	/*
	 * A method that constructs a new instance of flower class.
	 * 
	 * @param type: A string, name of the flower.
	 */
	public Flower(String type) {
		super(type);
		name = type.toLowerCase();
		letter = name.charAt(0);

	}

	/*
	 * this method creates a plot, a grid of 5x5 cells, for the flower.
	 */
	@Override
	public void plant() {
		plot[2][2] = letter;
	}

	/*
	 * this method grows a flower the specified number of times.
	 * 
	 * @param num: an integer showing how many times a flower should grow.
	 */
	@Override
	public void grow(int num) {
		growthLevel += num;
		_grow(growthLevel);
	}

	/*
	 * this is a private recursive helper function for the above grow method.
	 * 
	 * @param num: an integer showing how many times a flower should grow.
	 */
	private void _grow(int num) {
		if (num == 0)
			return;
		else if (num == 1) {
			plot[1][2] = letter;
			plot[3][2] = letter;
			plot[2][1] = letter;
			plot[2][3] = letter;
		} else if (num == 2) {
			plot[0][2] = letter;
			plot[1][1] = letter;
			plot[1][3] = letter;
			plot[2][0] = letter;
			plot[2][4] = letter;
			plot[3][1] = letter;
			plot[3][3] = letter;
			plot[4][2] = letter;
		} else if (num == 3) {
			plot[0][1] = letter;
			plot[0][3] = letter;
			plot[1][0] = letter;
			plot[1][4] = letter;
			plot[3][0] = letter;
			plot[3][4] = letter;
			plot[4][1] = letter;
			plot[4][3] = letter;
		} else if (num == 4) {
			plot[0][0] = letter;
			plot[0][4] = letter;
			plot[4][0] = letter;
			plot[4][4] = letter;
		}

		_grow(num - 1);
	}

	/*
	 * A method that returns the class name.
	 */
	@Override
	public String getPlantClass() {
		return "flower";
	}

	/*
	 * A method that returns the name of the flower.
	 */
	@Override
	public String getPlantName() {
		return name;
	}

}
