import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Array {
    private int[] array;//the array that will be sorted
    private int[] copy;//a copy of the array to compare after sorting--you should never access this in your code!
    private int[] temp;//a temporary array in case you use some version of mergesort
    private int accessCount;


    //constructor that reads in the integers from a file and creates the array
    public Array (String fn) {
	this.accessCount = 0;
	int n = 0;
	BufferedReader br;
	try {
	    br = new BufferedReader(new FileReader(fn));
	    String line = br.readLine();
	    if(line != null) {
		n = Integer.parseInt(line);
	    }
	    line = br.readLine();
	    int i = 0;
	    array = new int[n];
	    copy = new int[n];
	    while(line != null && i < n) {
		int num = Integer.parseInt(line);
		array[i] = num;
		copy[i] = num;
		line = br.readLine();
		i++;
	    }
	    br.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	Arrays.sort(copy);//this is just for testing--you should never access copy at all in your code!
	temp = new int[n];//provided in case you use some version of mergesort 
    }

    //gets a String representation of the array
    public String toString() {
	return Arrays.toString(array);
    }

    //gets the length of the array
    public int length() {
	return array.length;
    }

    //gets the value at index i
    public int getVal(int i) {
	if(!checkIndex(i))
	    return -9999;
	accessCount++;
	return array[i];
    }

    //sets the value at index i to val
    public void setVal(int i, int val) {
	array[i] = val;
	accessCount++;
    }

    //swaps the values at i and j
    public void swap(int i, int j) {
	int temp = array[i];
	array[i] = array [j];
	array[j] = temp;
	accessCount+=4;
    }

    //returns the accessCount
    public int getAccessCount() {
	return accessCount;
    }

    //sets the temp value at index i to val
    public void setTempVal(int i, int val) {
	temp[i] = val;
	accessCount++;
    }

    //gets the temp value at index i
    public int getTempVal(int i) {
	if(!checkIndex(i))
	    return -9999;
	accessCount++;
	return temp[i];
    }

    //checks that the array is sorted
    public boolean isSorted() {
	for(int i = 0; i < array.length; i++) {
	    if (array[i] != copy[i])
		return false;
	}
	return true;
    }

    //checks that the index is in bounds
    private boolean checkIndex(int i) {
	if (i < 0 || i > array.length) {
	    System.err.println("Index " + i + " is out of bounds.");
	    return false;
	}
	return true;
    }
}
