import java.util.*;
import java.io.*;

public class SortGridTest {
    private static int[] c1 = new int[]{3918, 20456, 101000, 480904, 789926};
    private static int count = 0;
    
    public static void main(String[] args) {
	double score = 0.0;
	
	for(int testNum = 1; testNum <= 5; testNum++) 
	    score += test(testNum);
	System.out.println("Total Access Count = " + count);
	System.out.println("SortGrid Total: " + score);
    }

    private static double test(int testNum) {
	double score = 0;
	System.out.println("***** BEGIN TEST " + testNum + "*****");
	Grid grid = new Grid("testGrid" + testNum + ".txt", false);
	SortGrid.sort(grid);
	score += checkAccuracy(grid);
	score += checkAccessCount(grid.size(), grid.getAccessCount(), c1[testNum-1]);
	return score;
    }
   
    private static double checkAccessCount(int n, int c, int c1) {
	System.out.println("Checking access count...");
	int lt = n*n;
	count += c;
	if(c < lt) {
	    System.out.println("Access count is less than the grid size? Something isn't right...");
	    return 0.0;
	}    
	if(c <= c1) {
	    System.out.println("Access count is below the first cutoff!");
	    return 1.5;
	}
	else if(c <= c1*3) {
	    System.out.println("Access count is below the second cutoff!");
	    return 1.0;
	}
	else {
	    System.out.println("Access count is too high...");
	}
	return 0.0;
    }

    private static double checkAccuracy(Grid grid) {
	//System.out.println(grid);
	if(grid.isSorted()) {
	    System.out.println("The grid is sorted!");
	    return 3.0;
	}
	System.out.println("The grid is not sorted. Use the toString method to print the grid before and after sorting.");
	return 0.0;
    }
}
 
