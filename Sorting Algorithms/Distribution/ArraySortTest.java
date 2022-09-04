public class ArraySortTest {
    private static int accessCount = 0;
    public static void main(String[] args) {
	double score = 0.0;
	for(int testnum = 1; testnum <= 5; testnum++)
	    score += test(testnum);
	System.out.println("Total Access Count = " + accessCount);
	score += checkAccessCounts();
	System.out.println("Total: " + score);
    }

    private static double test(int testNum) {
	double score = 0.0;
	System.out.println("***** BEGIN TEST " + testNum + "*****");
	Array array = new Array("array" + testNum + ".txt");
	ArraySort.sort(array);
	if(array.isSorted()) {
	    System.out.println("Array is sorted!");
	    score = 3.5;
	} else {
	    System.out.println("Array is not sorted.");
	    return 0.0;
	}
	int c = array.getAccessCount();
	if (c <= array.length()) {
	    System.out.println("Access count is less than or equal to the size of the array? Something isn't right!");
	    return 0.0;
	} else {
	    accessCount += c;
	}
	return score;
    }

    private static double checkAccessCounts() {
	double score = 0.0;
	//check that accessCount is better than bubbleSort
	System.out.println("Comparing your access count to Bubblesort...");
	if(accessCount < 6053312) {
	    System.out.println("Good, your access count is better than Bubble sort.");
	    score += 1.5;
	}
	//check that the accessCount is better than the next cutoff
	System.out.println("Comparing your access count to the average of mine...");
	if(accessCount <= 750000) {
	    System.out.println("Your access count was better!");
	    score += 1.0;
	}
	//check if you earned any extra credit with your access count
	System.out.println("Comparing your access count to various methods to check for extra credit...");
	if(accessCount <= 200000)
	    score += 0.5;
	if(accessCount <= 160000)
	    score += 0.5;
	if(accessCount <= 120000)
	    score += 0.5;
	if(accessCount <= 98052)
	    score += 0.5;
	return score;
    }
}
