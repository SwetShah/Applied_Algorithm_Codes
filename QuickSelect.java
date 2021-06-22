import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuickSelect {
//	public static void main(String[] args) {
//		int[] A = { 10, 9, 8, 7,6,5,4,3,2,1,0};
//		int k = 10;
//		System.out.println(select(A, k));
//	}

	public static int select(int[] A, int k) {

		int low = 0;
		int high = A.length - 1;
		return search(A, low, high, k);
	}

	public static int search(int A[], int low, int high, int k) {

		int pivotIndex = partition(A, low, high);
		if (pivotIndex == k)
			return A[pivotIndex];
		else if (pivotIndex > k) {
			return search(A, low, pivotIndex - 1, k);
		} else {
			return search(A, pivotIndex + 1, high, k);
		}
	}

	private static int partition(int[] A, int low, int high) {
		int pivotElement = findPivotUsingMediansOfMedian(A, low, high);

		int pivotIndex = -1;
		for (int i = 0; i < A.length; i++) {
			if (pivotElement == A[i]) {
				pivotIndex = i;
			}
		}
		int temp1 = A[high];
		A[high] = A[pivotIndex];
		A[pivotIndex] = temp1;

		int right = high;
		high = high - 1;
		while (low <= high) {

			while (low < right && A[low] < pivotElement) {
				low++;
			}

			while (high >= low && A[high] >= pivotElement) {
				high--;
			}

			if (low < high) {
				int temp = A[high];
				A[high] = A[low];
				A[low] = temp;
			}
		}

		int temp = pivotElement;
		A[right] = A[low];
		A[low] = temp;

		return (high + 1);
	}

	public static int findPivotUsingMediansOfMedian(int[] A, int lo, int hi) {

		List<int[]> list = new ArrayList<>();
		list = splitArray(A, lo, hi);

		int median[] = new int[list.size()];
		median = calculateMedians(median, list);
		Arrays.sort(median);

		int pivot;
		if (median.length % 2 == 0)
			pivot = median[median.length / 2 - 1];
		else
			pivot = median[median.length / 2];

//		return select(median, median.length / 2);
		return pivot;
	}

	private static int[] calculateMedians(int[] median, List<int[]> list) {
		int i = 0;

		for (int[] A : list) {
			Arrays.sort(A);
			if (A.length % 2 == 0)
				median[i] = A[A.length / 2 - 1];
			else
				median[i] = A[A.length / 2];
			i++;
		}

		return median;
	}

	public static List<int[]> splitArray(int[] A, int low, int high) {

		int M[];
		int length = high - low + 1;
		if (length < 5) {
			M = new int[length];
		} else {
			M = new int[5];
		}

		List<int[]> list = new ArrayList<>();

		int j = 0;
		while (low <= high) {
			if ((j + 1) % 5 == 0) {
				M[j] = A[low];
				list.add(M);
				int difference = high - low;
				if (difference >= 5) {
					M = new int[5];
				} else {
					M = new int[difference];
				}
				low++;
				j = 0;
			} else if (low == high) {
				M[j] = A[low];
				list.add(M);
				return list;
			} else {
				M[j] = A[low];
				low++;
				j++;
			}
		}
		return list;
	}
}
