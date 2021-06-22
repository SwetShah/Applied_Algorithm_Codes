public class MergeSort {



	public static void sort(int A[], int p, int r) {
		mergeSort(A, p, r-1);
	}
	
	public static void mergeSort(int A[], int p, int r) {

		if (p < r) {
			int mid = (p + r) / 2;
			mergeSort(A, p, mid);
			mergeSort(A, mid + 1, r);
			merge(A, p, mid, r);
		}
	}

	public static void merge(int A[], int p, int q, int r) {

		int size1Array = q - p + 1;
		int size2Array = r - q;
		int k = p;
		
		int[] leftArray = new int[size1Array];
		int[] rightArray = new int[size2Array];

		// Copy elements in left Array
		for (int i = 0; i < size1Array; i++) {
			leftArray[i] = A[p ++];
		}

		// Copy elements in right Array
		for (int i = 0; i < size2Array; i++) {
			rightArray[i] = A[(q + 1)];
			q++;
		}

		int i = 0, j = 0;
		while (i < size1Array && j < size2Array) {
			if (leftArray[i] < rightArray[j]) {
				A[k++] = leftArray[i++];
			} else {
				A[k++] = rightArray[j++];
			}
		}
		while (i < size1Array) {
			A[k++] = leftArray[i++];
		}
		while (j < size2Array) {
			A[k++] = rightArray[j++];
		}
	}
}

