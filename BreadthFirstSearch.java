import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class BreadthFirstSearch {

//	public static void main(String[] args) {
//
//		List<List<Integer>> input = new ArrayList<>();
//
//		List<Integer> lis1 = new ArrayList<>(Arrays.asList(0, 1));
//		List<Integer> lis2 = new ArrayList<>(Arrays.asList(1, 0));
//		List<Integer> lis3 = new ArrayList<>(Arrays.asList(0, 4));
//		List<Integer> lis4 = new ArrayList<>(Arrays.asList(3, 4));
//		List<Integer> lis5 = new ArrayList<>(Arrays.asList(2, 4));
//
//		input.add(lis1);
//		input.add(lis2);
//		input.add(lis3);
//		input.add(lis4);
//		input.add(lis5);
//		input.add(lis6);
//		input.add(lis7);
//		System.out.println(breadthFirstSearch(input, 6));
//
//	}

	public static int[] breadthFirstSearch(List<List<Integer>> edges, int vertices) {

		Map<Integer, List<Integer>> map = new HashMap<>();
		Queue<Integer> queue = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();

		int result[] = new int[vertices];

		for (List<Integer> li : edges) {
			int source = li.get(0);
			int destination = li.get(1);

			if (map.get(source) == null) {
				map.put(source, new ArrayList<>(Arrays.asList(destination)));
			} else {
				map.get(source).add(destination);
			}
		}

		// Assigning empty arraylist to the vertices which is atomic
		for (int i = 0; i < vertices; i++) {
			if (!map.containsKey(i)) {
				map.put(i, new ArrayList<>());
			}
		}

		// Initializing the result array
		if (result.length > 0) {
			result[0] = 0;
			for (int i = 1; i < vertices; i++) {
				result[i] = -1;
			}

			bfs(0, result, map, queue, visited);
		}

		return result;
	}

	public static void bfs(int i, int[] result, Map<Integer, List<Integer>> map, Queue<Integer> queue,
			Set<Integer> visited) {
		queue.add(i);
		visited.add(i);

		while (!queue.isEmpty()) {
			int value = queue.poll();
//			visited.add(value);
			for (Integer neighbour : map.get(value)) {
				if (!visited.contains(neighbour)) {
					compare(result, neighbour, value);
					visited.add(neighbour);
					queue.add(neighbour);
				}
			}
		}
	}

	public static void compare(int[] result, int neighbour, int val) {
		if (result[neighbour] == -1) {
			result[neighbour] = result[val] + 1;
		} else {
			int temp = result[neighbour] + result[val];
			if (temp < result[neighbour]) {
				result[neighbour] = temp;
			}
		}
	}
}
