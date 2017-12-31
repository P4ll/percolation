
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private static final int[] DETERMINED_MOVES_X = {1, -1, 0, 0};
	private static final int[] DETERMINED_MOVES_Y = {0, 0, 1, -1};
	private WeightedQuickUnionUF qu;
	private boolean opened[][];
	private int amtOFOpened;
	private int n;
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException("Can't create Percolation with zero size.");
		}
		this.n = n;
		amtOFOpened = 0;
		opened = new boolean[n][n];
		qu = new WeightedQuickUnionUF(n * n + 2);
	}
	public void open(int row, int col) {
		if (row > n || col > n || row <= 0 || col <= 0) {
			throw new IllegalArgumentException("Can't open!");
		}
		if (isOpen(row, col)) {
			return;
		}
		opened[row - 1][col - 1] = true;
		if (row == 1) {
			qu.union(col, 0);
		}
		if (row == n) {
			qu.union((row - 1) * n + col, n * n + 1);
		}
		amtOFOpened++;
		for (int i = 0; i < DETERMINED_MOVES_X.length; i++) {
			int sucX = col + DETERMINED_MOVES_X[i];
			int sucY = row + DETERMINED_MOVES_Y[i];
			boolean mayGo = sucX <= n && sucX > 0 && sucY <= n && sucY > 0;
			if (mayGo) {
				if (isOpen(sucY, sucX)) {
					qu.union((row - 1) * n + col, (sucY - 1) * n + sucX);
				}
			}
		}
	}
	public boolean isOpen(int row, int col) {
		if (row > n || col > n || row <= 0 || col <= 0) {
			throw new IllegalArgumentException("Can't check this!");
		}
		return opened[row - 1][col - 1];
	}
	public boolean isFull(int row, int col) {
		if (row > n || col > n || row <= 0 || col <= 0) {
			throw new IllegalArgumentException("Can't check this!");
		}
		return qu.connected(0, (row - 1) * n + col);
	}
	public int numberOfOpenSites() {
		return amtOFOpened;
	}
	public boolean percolates() {
		return qu.connected(0, n * n + 1);
	}
	public static void main(String[] args) {
		Percolation perc = new Percolation(10);
		int n = StdIn.readInt();
		int m = StdIn.readInt();
		for (int i = 0; i < m; i++) {
			perc.open(StdIn.readInt(), StdIn.readInt());
		}
		
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				if (perc.isFull(i, j)) {
					StdOut.print("*");
				}
				else if (perc.isOpen(i, j)) {
					StdOut.print("O");
				}
				else {
					StdOut.print("C");
				}
			}
			StdOut.println();
		}
		StdOut.println(perc.isFull(10, 1));
	}
}
