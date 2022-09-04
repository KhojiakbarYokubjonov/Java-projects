//student: Khojiakbar Yokubjonov
//class: CS345, Spring 2022
//project: implements a quicksort algorithm. It takes an Array parameter and sorts it.


//quickSort implementation
public class ArraySort {
	
	/*
	 * a wrapper method for the quickSort 
	 * @param a: an Array object containing values to be sorted
	 */
	public static void sort(Array a) {
		pivot(a, 0, a.length()-1);
		
	}
	
	/*
	 * sorts the Array using a quicksort method
	 * it accepts an Array object as well as the first and last indices
	 */
	private static void pivot(Array a, int start, int end) {
		if (start>= end)
			return;
		int left = start;
		int right = end;
		int pivot = a.getVal(end);
		while (left < right) {
			
			while (a.getVal(left)<=pivot && left < right) {
				left++;
			}
			
			while (a.getVal(right) >=pivot && right > left) {
				right --;
			}
			// swap the vals at left and right indexes
			swap(a, left, right);
		}
		swap(a, left, end);
		
		pivot(a, start, left-1);
		pivot(a, left+1, end);
		
	}
	
	/*
	 * a helper method to swap the two given values in the Array object.
	 * it accepts an Array object as well as the indices of the values which are to be swapped.
	 */
	private static void swap(Array a, int x,  int y) {
		int temp = a.getVal(x);
		a.setVal(x, a.getVal(y));
		a.setVal(y, temp);
	} 

}
