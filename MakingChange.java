import java.util.Arrays;

public class MakingChange {

//	public static void main(String[] args) {
//		int money = 11;
//		int []coins = {9, 6, 5, 1};
//		System.out.println(minimumCoins(money, coins));
//	}

	
	 public static int minimumCoins(int money, int[] coins) {
		
		 if(Arrays.asList(coins).contains(money)){
			 return 1;
		 }
		 
		 int []A = new int[money+1];
		 initializeArray(A);
		 
		 for(int i=1 ;i<A.length; i++) {
			 int min = Integer.MAX_VALUE;
			 for(int j=0; j<coins.length; j++) {
				 if(i>= coins[j]) {
					 min = findMinimum(i, j, min, A, coins);
					 A[i] = min;
				 }
			 }
		 }
		 if(A[money] == A.length) {
			 return -1;
		 }
		 return A[money];
	 }
	 
	 public static void initializeArray(int []A) {
		 A[0] = 0;
		 for(int i=1; i<A.length; i++) {
			 A[i] = A.length;
		 }
	 }
	 
	 public static int findMinimum(int i, int j, int min, int A[], int[] coins) {
		 int leftIndex = i - coins[j];
		 int left = A[leftIndex] + 1;
		 int right = A[i];
		 
		 if(left < right && left< min) {
			 min = left;
		 }
		 else if(left > right && right < min) {
			 min = right;
		 }
		 
		 return min;
	 }
}
