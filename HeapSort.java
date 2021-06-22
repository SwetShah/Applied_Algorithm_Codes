
public class HeapSort {

	public static void heapify(int A[], int n, int i) {
		int maxIndex = i;
		int leftChild = 2 * i + 1;
		int rightChild = 2 * i + 2;

		if (leftChild < n && A[leftChild] > A[maxIndex])
			maxIndex = leftChild;

		if (rightChild < n && A[rightChild] > A[maxIndex])
			maxIndex = rightChild;

		if (maxIndex != i) {
			swap(A, i, maxIndex);
			heapify(A, n, maxIndex);
		}
	}

	public static void heapSort(int A[]) {

		for (int i = A.length / 2 - 1; i >= 0; i--) {
			heapify(A, A.length, i);
		}
		for (int i = A.length - 1; i >= 0; i--) {
			swap(A, i, 0);
			heapify(A, i, 0);
		}
	}

	static void swap(int[] A, int i, int j) {
		int temp = A[i];
		A[i] = A[j];
		A[j] = temp;
	}
}