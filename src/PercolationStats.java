
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double results[];
	private int trials;
	private double mean;
	private double stddev;
	public PercolationStats(int n, int trials) {
		if (n <= 0 || trials <= 0) {
			throw new IllegalArgumentException("Can't create PercolationState with zero size or trials.");
		}
		results = new double[trials];
		this.trials = trials;
		for (int i = 0; i < trials; i++) {
			Percolation perc = new Percolation(n);
			while (!perc.percolates()) {
				int x = StdRandom.uniform(1, n + 1);
				int y = StdRandom.uniform(1, n + 1);
				perc.open(y, x);
			}
			results[i] = (double)perc.numberOfOpenSites() / (n * n);
		}
		mean = StdStats.min(results);
		stddev = StdStats.stddev(results);
	}
	public double mean() {
		return mean;
	}
	public double stddev() {
		return stddev;
	}
	public double confidenceLo() {
		return mean() - 1.96 * stddev() / Math.sqrt(trials);
	}
	public double confidenceHi() {
		return mean() + 1.96 * stddev() / Math.sqrt(trials);
	}
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int countExp = Integer.parseInt(args[1]);
//		int n = StdIn.readInt();
//		int countExp = StdIn.readInt();
		PercolationStats perStat = new PercolationStats(n, countExp);
		StdOut.println("mean\t\t\t= " + perStat.mean() + "\nstddev\t\t\t= " + perStat.stddev() + 
							"\n95% confidence interval\t= [" + perStat.confidenceLo() + ", " + perStat.confidenceHi() + "]");
	}
}
