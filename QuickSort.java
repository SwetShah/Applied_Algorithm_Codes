import java.util.Random;

public class QuickSort {

	static Random random = new Random();

	public static void sort(int A[], int p, int r) {
		quickSort(A, p, r - 1);
	}

	public static void quickSort(int[] A, int p, int r) {

		int start = p;
		int end = r;

		if (start < end) {
			int pivotIndex = partition(A, start, end);
			quickSort(A, start, pivotIndex - 1);
			quickSort(A, pivotIndex + 1, end);
		}

	}

	public static int partition(int A[], int p, int r) {

		// Randomized selection of pivot
		int pivotIndex = p + random.nextInt(r - p);
		int pivotElement = A[pivotIndex];

		// Swapping and making last index as pivot, always
		if (pivotIndex != r) {
			int temp = A[r];
			A[r] = A[pivotIndex];
			A[pivotIndex] = temp;
		}

		int right = r;
		r = r - 1;
		while (p <= r) {

			while (p < right && A[p] < pivotElement) {
				p++;
			}

			while (r >= p && A[r] >= pivotElement) {
				r--;
			}

			if (p < r) {
				int temp = A[r];
				A[r] = A[p];
				A[p] = temp;
			}
		}

		int temp = pivotElement;
		A[right] = A[p];
		A[p] = temp;

		return (r + 1);
	}

}
