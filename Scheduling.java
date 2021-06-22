import java.util.Arrays;
import java.util.Comparator;

public class Scheduling {

	static int TOTAL_SECONDS = 86400;

//	public static void main(String[] args) {
//
//		int[][] A = {  { 64800, 21600 },{ 75600, 14400 }, { 10800, 50400 }, { 46800, 68400 } };
////		int[][] A = { { 0, 3 }, {0,6}, {0,17}, {8,11},{19,23} };
////		int[][] A = {{100, 86000}, {86000, 100}};
////		int[][]A = {{1,2},{2,3},{3,4},{4,5},{5,6}};
//
//		System.out.println(schedule(A));
//	}

	public static int schedule(int[][] A) {
		// Sorting based on end time
		Arrays.sort(A, new Comparator<int[]>() {
			@Override
			public int compare(int[] element1, int[] element2) {
				int time1 = element1[1];
				int time2 = element2[1];
				if (time1 > time2) {
					return 1;
				} else if (time1 == time2) {
					return 0;
				}
				return -1;
			}
		});

		int previousStart = Integer.MAX_VALUE;
		int previousEnd = Integer.MIN_VALUE;
		boolean flag = true;
		int count = 0;

		for (int i = 0; i < A.length; i++) {
			int startTime = A[i][0];
			int endTime = A[i][1];

			if (startTime < endTime) {
				if (startTime >= previousEnd) {
					previousEnd = endTime;
					previousStart = startTime;
					count++;
				}
			} 
			else if (flag) {
				previousStart = startTime;
				previousEnd = endTime;
				flag = false;
			}
		}

		for (int i = 0; i < A.length; i++) {
			int startTime = A[i][0];
			int endTime = A[i][1];

			if (startTime > endTime) { 
				if (startTime >= previousEnd/* && endTime <= previousEnd*/) {
					count++;
					break;
				}
			}
		}
		return count;
	}
}
