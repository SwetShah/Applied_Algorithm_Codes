public class BinarySearch {

	public static int search(int A[], int key) {
		int start = 0;
		int end = A.length-1;
		return findKey(A, start, end, key);
	}
	
	public static int findKey(int A[], int start, int end, int key){
		
		

		if (A.length == 0 || (end < start)) {
			return -1;
		}

		int mid = (start + end) / 2;
		int midElement = A[mid];

		if (midElement == key) {
			return mid;
		}
		// Left Array
		if (key < midElement) {
			end = mid - 1;
			mid = findKey(A, start, end, key);
		}
		// Right Array
		else if (key > midElement) {
			start = mid + 1;
			mid = findKey(A, start, end, key);
		}
		else {
			return -1;
		}
		return mid;
	
	}
}