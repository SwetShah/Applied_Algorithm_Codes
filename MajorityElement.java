import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

//	public static void main(String[] args) {
//		int[] A = { 1};
//		System.out.println(majority(A));
//
//	}

	public static int majority(int[] A) {

		Map<Integer, Integer> map = new HashMap<>();

		for (int a : A) {
			if (map.get(a) != null) {
				int count = map.get(a);
				count += 1;
				if (count > A.length / 2)
					return a;
				map.put(a, count);
			} else {
				map.put(a, 1);
				if (1 > A.length / 2)
					return a;
			}
		}
		return -1;
	}
}
