//student: Khojiakbar Yokubjonov
//class: CS345, Spring 2022
//project: implements a shellsort algorithm. It takes an Array parameter and sorts it.

public class Shellsort {
	/*
	 * accepts an Array object and sorts it using a shellsort method
	 */
	public static void sort(Array a) {
		int h = 1;

		int size = a.length();

		/*
		 * finds the starting val for h
		 */
		;
		while (h < size / 3) {

			h = 3 * h + 1;
		}

		while (h >= 1) {
			if (h == 1)
				insertionSort(a);

			else {
				// h-sorts the array
				int i = 0;
				while (h + i < a.length()) {
					if (a.getVal(h + i) < a.getVal(i)) {
						swap(a, h + i, i);
					}
					i += 1;
				}
			}

			h = h / 3;

		}

	}

	/*
	 * swaps the two values in Array object. it accepts an Array object as well as
	 * the indices of the two values which are to be swapped
	 */
	private static void swap(Array a, int x, int y) {
		int temp = a.getVal(x);
		a.setVal(x, a.getVal(y));
		a.setVal(y, temp);
	}

	/*
	 * a helper method sorts the Array object using an insertion sort
	 */
	private static void insertionSort(Array a) {

		for (int i = 1; i < a.length(); i++) {

			int j = i;

			while (j > 0 && a.getVal(j) < a.getVal(j - 1)) {
				swap(a, j, j - 1);
				j--;
			}
		}

	}

	/*
	 * a helper method to print the Array object
	 */
	private static void printList(Array a) {
		for (int i = 0; i < a.length(); i++) {
			System.out.print(a.getVal(i) + " ");
		}
		System.out.println();
	}

}
