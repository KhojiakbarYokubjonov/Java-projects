public class ShellsortTest {
    private static int[] bookCounts = new int[]{2436, 18906, 46180, 10914, 24692};
    public static void main(String[] args) {
	double score = 0.0;
	for(int testnum = 1; testnum <= 5; testnum++)
	    score += test(testnum);
	System.out.println("Total: " + score);
    }

    private static double test(int testNum) {
	double score = 0.0;
	System.out.println("***** BEGIN TEST " + testNum + "*****");
	Array array = new Array("array" + testNum + ".txt");
	Shellsort.sort(array);
	if(array.isSorted()) {
	    System.out.println("Array is sorted!");
	    score = 4.0;
	} else {
	    System.out.println("Array is not sorted.");
	    return 0.0;
	}
	int c = array.getAccessCount();
	if (c <= array.length()) {
	    System.out.println("Access count is less than or equal to the size of the array? Something isn't right!");
	    return 0.0;
	} else {
	    if (c < bookCounts[testNum-1]) {
		System.out.println("Congratulations! You beat the access count from the h-sequence presented in class!");
		score += 0.5;
	    }
	}
	System.out.println("Your access count: " + c);
	return score;
    }
}
