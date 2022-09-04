//student: Khojiakbar Yokubjonov
//class: CS345, Spring 2022
//project: implements a sorting method for a grid. it uses a quicksort to sort the grid.

public class SortGrid {

	/*
	 * sorts an Grid object
	 */
	public static void sort(Grid g) {
		quickSort(g, 0, g.size() * g.size() - 1);
	}

	/*
	 * a helper method sorts a Grid object using the quicksort method. it accepts a
	 * Grid obj as well as the first and last indices (it treats the 2D grid like it
	 * is a 1D array.)
	 */
	private static void quickSort(Grid a, int start, int end) {
		if (start >= end)
			return;
		int left = start;
		int right = end;
		int pivot = getVal(a, end);
		while (left < right) {

			while (getVal(a, left) <= pivot && left < right) {
				left++;
			}

			while (getVal(a, right) >= pivot && right > left) {
				right--;
			}
			// swap the vals at left and right indexes
			swap(a, left, right);
		}
		swap(a, left, end);

		quickSort(a, start, left - 1);
		quickSort(a, left + 1, end);

	}

	/*
	 * a helper method accepts a grid object and returns the value at a given index.
	 * (it treats the 2D grid like it is a 1D array.)
	 */
	private static int getVal(Grid g, int i) {
		int width = g.size();
		int r = i / width;
		int c = i % width;
		return g.getIntVal(r, c);
	}

	/*
	 * a helper method accepts a Grid obj and returns the row and col. indices for
	 * the given the index (it treats the grid as a 1D array.)
	 */
	private static int[] getIndices(Grid g, int i) {
		;
		int width = g.size();
		int r = i / width;
		int c = i % g.size();
		return new int[] { r, c };
	}

	/*
	 * returns the length of the Grid obj
	 */
	private static int getLength(Grid g) {
		return g.size() * g.size();
	}

	/*
	 * swaps the two given values in the Grid obj it accepts a Grid obj as well as
	 * the indices of the two values which are to be swapped. (it treats the 2D grid
	 * as a 1D array.)
	 */
	private static void swap(Grid g, int x, int y) {
		int[] inds = getIndices(g, x);
		String temp = g.getVal(inds[0], inds[1]);
		int[] inds2 = getIndices(g, y);
		g.setVal(inds[0], inds[1], g.getVal(inds2[0], inds2[1]));
		g.setVal(inds2[0], inds2[1], temp);
	}

}
