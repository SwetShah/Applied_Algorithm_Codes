
public class InversionCount {
	
	public static int[] count(int[] A) {
		int left = 0;
		int right = A.length - 1;
		int[] result = new int[A.length];
		int[] indexes = new int[A.length];

		for (int i = 0; i < A.length; i++) {
			indexes[i] = i;
		}

		mergeSort(indexes, A, left, right, result);
		return result;
	}

	public static void mergeSort(int[] indexes, int[] A, int p, int r, int[] result) {
		if (p < r) {
			int mid = (p + r) / 2;
			mergeSort(indexes, A, p, mid, result);
			mergeSort(indexes, A, mid + 1, r, result);
			merge(indexes, A, p, mid, r, result);
		}
	}

	public static void merge(int indexes[], int[] A, int p, int q, int r, int[] result) {

		int size1Array = q - p + 1;
		int size2Array = (r - q);
		int k = p;

		int[] leftArray = new int[size1Array];
		int[] rightArray = new int[size2Array];

		// Copy indexes in left Array
		for (int i = 0; i < size1Array; i++) {
			leftArray[i] = indexes[p];
			p++;
		}

		// Copy indexes in right Array
		for (int j = 0; j < size2Array; j++) {
			rightArray[j] = indexes[q + 1];
			q++;
		}

		int i = 0, j = 0;
		int count = 0;
		while (i < size1Array && j < size2Array) {
			if (A[leftArray[i]] > A[rightArray[j]]) {
				count++;
				indexes[k++] = rightArray[j++];

			} else {
				result[leftArray[i]] += count;
				indexes[k++] = leftArray[i++];
			}
		}
		while (i < size1Array) {
			result[leftArray[i]] += count;
			indexes[k++] = leftArray[i++];
		}
		while (j < size2Array) {
			indexes[k++] = rightArray[j++];
		}
	}
}
