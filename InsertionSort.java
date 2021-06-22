public class InsertionSort {
	public static void sort(int[] A) {
		for (int i = 1; i < A.length; i++) {
			int x = A[i];
			int j = i;
			int k = i;
			while (j > 0) {
				int y = A[j - 1];
				if (x < y) {
					A[k] = A[k] + A[j - 1];
					A[j - 1] = A[k] - A[j - 1];
					A[k] = A[k] - A[j - 1];
				}
				j--;
				k--;
			}
		}
	}
}