import java.util.Scanner;

import edu.princeton.cs.algs4.StdRandom;

public class TestGenerator {
	private int arr[][];
	private boolean opened[][];
	private int count;
	public int[][] getGen(int n, int countOpened) {
		arr = new int[countOpened][2];
		opened = new boolean[n][n];
		count = 0;
		while (count != countOpened) {
			int i = StdRandom.uniform(0, n);
			int j = StdRandom.uniform(0, n);
			if (!opened[i][j]) {
				arr[count][0] = i + 1;
				arr[count][1] = j + 1;
				opened[i][j] = true;
				count++;
			}
		}
		return arr;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = Integer.parseInt(sc.nextLine());
		
		int[][] arr = new int[n * n][2];
		int countOpen = 0;
		for (int i = 1; i <= n; i++) {
			String t = sc.nextLine();
			for (int j = 1; j <= n; j++) {
				if (t.charAt(j - 1) == 'O') {
					arr[countOpen][0] = i;
					arr[countOpen++][1] = j;
				}
			}
		}
		System.out.println(n + " " + countOpen);
		for (int i = 0; i < countOpen; i++) {
			System.out.println(arr[i][0] + " " + arr[i][1]);
		}
	}
}
