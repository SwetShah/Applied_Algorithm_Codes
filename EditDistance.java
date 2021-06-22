
public class EditDistance {

	public static int editDistance(String word1, String word2) {

		
		char A[] = new char[word1.length()+1];
		char B[] = new char[word2.length()+1];
		
		for(int i=1;i<=word1.toCharArray().length; i++) {
			A[i] = word1.toCharArray()[i-1];
		}
		for(int i=1;i<=word2.toCharArray().length; i++) {
			B[i] = word2.toCharArray()[i-1];
		}
		
		int[][] result = new int[A.length][B.length];

		if (A.length == 0 && B.length == 0) {
			return 0;
		}
		
		if (A.length != 0 && B.length == 0) {
			return word2.length();
		}
		if (B.length != 0 && A.length == 0) {
			return word1.length();
		}

		for (int i = 0; i < A.length; i++) {
			result[i][0] = i;
		}

		for (int i = 0; i < B.length; i++) {
			result[0][i] = i;
		}

		for (int i = 1; i < A.length; i++) {
			for (int j = 1; j < B.length; j++) {
//				System.out.println("Row:"+A[i]+ " "+ "Column:"+B[j]);
				if(A[i] == B[j]) {
					result[i][j] = result[i-1][j-1];
				}
				else {
					result[i][j] = minimum(result[i-1][j-1], result[i-1][j], result[i][j-1]) + 1;
				}
			}
		}

		return result[A.length-1][B.length-1];
	}
	
	
	static int minimum(int a, int b, int c) {
		return a < b ? (a < c ? a : c) : (b < c ? b : c);
	}
}
