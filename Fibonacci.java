
public class Fibonacci {
	public static int fibonacci(int n) {
		int firstElement = 0;
		int secondElement = 1;
		int nThElement = 0;

		if (n == 0) {
			return 0;
		}
		if (n == 1 || n == 2) {
			return 1;
		}
		while (n - 2 >= 0) {
			nThElement = firstElement + secondElement;
			firstElement = secondElement;
			secondElement = nThElement;
			n--;
		}
		return nThElement;
	}
}