//student: Khojiakbar Yokubjonov
//class: CS345, Spring 2022
//description: this project solves a word search using a variation of DFS and 
//BFS with a Deque data structure. It accepts a grid that will contain individual 
//letters represented as Strings. A word can occur as any path from the first letter 
//to the last where a step in the path can go up, right, down, or left. (No diagonals). 
//Also, it is possible for the same letter to be used more than once in the same word. 
//The output for this search will be a String representation of the locations in the path.

/*
 * implements a Puzzle class that will do a word search
 * @fields:
 * 		grid - a Grid structure that contain individual letters represented as Strings
 * 		path - a string representing the location of the word being searched
 * 		usedLocs - a Deque structure storing the used locations from grid
 */
public class Puzzle {
	private Grid grid;
	String path;
	Deque usedLocs;

	/*
	 * creates an instance of a Puzzle class grid: a Grid sturcture containing
	 * individual letters represented as Strings.
	 */
	public Puzzle(Grid grid) {
		this.grid = grid;

	}

	/*
	 * find and return (as a String) the path containing word; 
	 * starts the search at (r, c)
	 * word - a str, word being searched.
	 */
	public String find(String word, int r, int c) {
		path = "";
		usedLocs = new Deque();

		if (searchAtIndex(word, r, c)) {

			resetUsedLocs(usedLocs);
			return path.substring(1);
		}

		resetUsedLocs(usedLocs);
		return path;

	}

	/*
	 * a method to reset the locations used during the word search
	 * usedLocs - a Deque structure storing the used locations from grid
	 * 
	 */
	private void resetUsedLocs(Deque usedLocs){
		while (!usedLocs.isEmpty()) {
			
			try {
				Loc coord;
				coord = (Loc) usedLocs.getFirst();
			
			
			coord.visited = false;
			} catch (EmptyDequeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * a helper method for the find method.
	 * r, c - starting coords for the word search
	 * word - str, word being searched
	 * count - int, index for the letters in the word
	 * path - a str representing the location of the word
	 */
	private boolean _find(String word, int r, int c, int count, String path) {
		String val = grid.getVal(r, c);
		if (count == word.length() - 1 && word.substring(count, count + 1).equals(val)) {
			this.path = path;
			return true;
		}

		if (r < 0 || r >= grid.size() || c < 0 || c >= grid.size() || !val.equals(word.substring(count, count + 1))) {
			return false;

		}

		boolean found = _find(word, r - 1, c, count + 1, path + "(" + (r - 1) + ", " + c + ")")
				|| _find(word, r, c + 1, count + 1, path + "(" + r + ", " + (c + 1) + ")")
				|| _find(word, r + 1, c, count + 1, path + "(" + (r + 1) + ", " + c + ")")
				|| _find(word, r, c - 1, count + 1, path + "(" + r + ", " + (c - 1) + ")");
		return found;

	}

	/*
	 * does a BFS to find the coordinate of the first letter in the word.
	 * r, c - starting coords for the word search
	 * word - str, word being searched
	 */
	@SuppressWarnings("unchecked")
	private boolean searchAtIndex(String word, int r, int c) {
		boolean found = false;
		Deque q = new Deque();
		Loc loc = grid.getLoc(r, c);
		q.addToBack(loc);
		loc.visited = true;
		usedLocs.addToBack(loc);
		try {
		while (!q.isEmpty()) {
			Loc coords;
			
				coords = (Loc) q.getFirst();
			
			int x = coords.row;
			int y = coords.col;
			if (coords.getVal().equals(word.substring(0, 1))
					&& _find(word, x, y, 0, path + " (" + x + ", " + y + ")")) {
				return true;
			}

			Loc up = grid.getLoc(x - 1, y);
			if (x - 1 >= 0 && x - 1 < grid.size() && y >= 0 && y < grid.size() && up.visited == false) {
				q.addToBack(up);
				up.visited = true;
				usedLocs.addToBack(up);

			}
			Loc right = grid.getLoc(x, y + 1);
			if (x >= 0 && x < grid.size() && y + 1 >= 0 && y + 1 < grid.size() && right.visited == false) {
				q.addToBack(right);
				right.visited = true;
				usedLocs.addToBack(right);

			}
			Loc below = grid.getLoc(x + 1, y);
			if (x + 1 >= 0 && x + 1 < grid.size() && y >= 0 && y < grid.size() && below.visited == false) {
				q.addToBack(below);
				below.visited = true;
				usedLocs.addToBack(below);

			}
			Loc left = grid.getLoc(x, y - 1);
			if (x >= 0 && x < grid.size() && y - 1 >= 0 && y - 1 < grid.size() && left.visited == false) {
				q.addToBack(left);
				left.visited = true;
				usedLocs.addToBack(left);

			}

		}} catch (EmptyDequeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return found;
	}

}
