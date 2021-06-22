import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class CriticalLink {

	private static Map<Integer, List<Integer>> map = new HashMap<>();
	private static List<Integer> visited = new ArrayList<>();
	private static Map<Integer, Integer> labels = new HashMap<>();
	private static int count = 0;
	private static int counter = 0;

//	public static void main(String[] args) {
//
//		int[][] links = { { 0, 1 }, { 1, 2 }, { 2, 0 }, {1,3} };
//		int n = 4;
//
//		System.out.println(criticalLink(n, links));
//	}

	public static int criticalLink(int n, int[][] links) {

//		System.out.println(links);
//		print(links);
		for (int i = 0; i < links.length; i++) {

			int first = links[i][0];
			int second = links[i][1];

			if (map.get(first) == null) {
				map.put(first, new ArrayList<>(Arrays.asList(second)));
			} else {
				map.get(first).add(second);
			}

			if (map.get(second) == null) {
				map.put(second, new ArrayList<>(Arrays.asList(first)));
			} else {
				map.get(second).add(first);
			}

//			labels.put(first, first);
//			labels.put(second, second);
		}

		for (int i = 0; i < n; i++) {
			if (!map.containsKey(i)) {
				map.put(i, new ArrayList<>());
			}
		}
		DFS(0, -1);
		return count;
	}

	public static void DFS(int n, int parent) {

		if (!visited.contains(n)) {
			labels.put(n, counter++);
			visited.add(n);
			List<Integer> neighbours = map.get(n);

			for (int neigbour : neighbours) {
				if (parent == neigbour) {
					continue;
				}
				DFS(neigbour, n);
				if (labels.get(neigbour) <= labels.get(n)) {
					int min = Math.min(labels.get(neigbour), labels.get(n));
					labels.put(n, min);
//					 labels.put(neigbour, min);
				} else {
					count++;
				}
			}
		} else {
			labels.put(n, Math.min(labels.get(parent), labels.get(n)));
		}

	}

//	public static void print(int[][] links) {
//
//		for (int i = 0; i < links.length; i++) {
//			System.out.println(links[i][0] + " " + links[i][1]);
//		}
//	}
}
