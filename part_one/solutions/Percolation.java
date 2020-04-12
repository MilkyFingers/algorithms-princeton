import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/* *****************************************************************************
 *  Name: Micah Bassett
 *  Date: 05/04/2020
 *  Description: Princeton algorithm course Part 1, week 1 solutions
 **************************************************************************** */
public class Percolation {

    /*
    This API assumes that positions in the locations array corresponding to 0
    are closed, 1 is open. A site is full when it can be connected to an open
    site in the top row.
     */

    private int[][] locations;
    private int noOpenSites = 0;
    private int size;
    private WeightedQuickUnionUF ds;

    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("n must be natural!");
        }
        locations = new int[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                locations[x][y] = 0;
            }
        }
        size = (n * n) + 2;
        ds = new WeightedQuickUnionUF(size);
        // connecting the virtual nodes
        for (int i = 1; i <= locations.length; i++) {
            ds.union(0, i);
            ds.union((size - 1), ((size - 1) - i));
        }
    }

    // We traditionally reference by 1-based indexing, so we subtract one
    // from row and col to account for this
    public void open(int row, int col) {
        if (notValid(row, col)) {
            throw new IllegalArgumentException("Must be 1 to n!");
        }
        if (!isOpen(row, col)) {
            locations[row - 1][col - 1] = 1;
            noOpenSites += 1;
            unionNeighbours(row - 1, col - 1);
        }
    }

    // Returns true if index is open
    public boolean isOpen(int row, int col) {
        if (notValid(row, col)) {
            throw new IllegalArgumentException("Must be 1 to n!");
        }
        row -= 1;
        col -= 1;
        return (locations[row][col] == 1);
    }

    public boolean isFull(int row, int col) {
        if (notValid(row, col)) {
            throw new IllegalArgumentException("Must be 1 to n!");
        }
        if (!isOpen(row, col)) {
            return false;
        }
        else {
            int element = twoDToOneD(row - 1, col - 1);
            if (ds.find(element) == ds.find(0)) {
                return true;
            }
        }
        return false;
    }

    public int numberOfOpenSites() {
        return noOpenSites;
    }

    public boolean percolates() {
        return (ds.find(size - 1) == ds.find(0));
    }

    /*
    Private Functions
     */

    // Returns true if position is NOT valid
    private boolean notValid(int row, int col) {
        int length = locations.length;
        return (row <= 0 || row > length || col <= 0 || col > length);
    }

    private int twoDToOneD(int x, int y) {
        return (x * locations.length + y) + 1;
    }

    private void unionNeighbours(int x, int y) {
        int oneDLoc = twoDToOneD(x, y);
        if (y > 0 && locations[x][y - 1] == 1) {
            ds.union(oneDLoc, oneDLoc - 1);
        }
        if (y < locations.length - 1 && locations[x][y + 1] == 1) {
            ds.union(oneDLoc, oneDLoc + 1);
        }
        if (x > 0 && locations[x - 1][y] == 1) {
            ds.union(oneDLoc, oneDLoc - locations.length);
        }
        if (x < locations.length - 1 && locations[x + 1][y] == 1) {
            ds.union(oneDLoc, oneDLoc + locations.length);
        }
    }

}
