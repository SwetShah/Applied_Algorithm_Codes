public class BinarySearch2D {
	
	public static int[] search(int[][] M, int q) {
		int[] result = { -1, -1 };
		if (M.length == 0) {
			return result;
		}

		int numberOfElements = M.length * M[0].length;
		int rowIndex = 0;
		int columnIndex = numberOfElements-1;
		return findKey(M, rowIndex, columnIndex, q);
	}

	public static int[] findKey(int[][] A, int start, int end, int key) {

		int[] result = new int[2];
		if (start > end) {
			result[0] = -1;
			result[1] = -1;
			return result;
		}

		int mid = (start + end) / 2;
		int row = mid / A[0].length;
		int column = mid % A[0].length;

		if (A[row][column] == key) {
			result[0] = row;
			result[1] = column;
			return result;
		} else if (key < A[row][column]) {
			end = mid - 1;
			return findKey(A, start, end, key);
		} else {
			start = mid + 1;
			return findKey(A, start, end, key);
		}
	}
}