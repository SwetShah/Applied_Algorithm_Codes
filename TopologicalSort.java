import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TopologicalSort {

//	public static void main(String[] args) {
//		List<List<Integer>> input = new ArrayList<>();
//
//		List<Integer> lis1 = new ArrayList<>(Arrays.asList(0, 1));
//		List<Integer> lis2 = new ArrayList<>(Arrays.asList(0, 2));
//		List<Integer> lis3 = new ArrayList<>(Arrays.asList(1, 2));
//		List<Integer> lis4 = new ArrayList<>(Arrays.asList(1, 3));
//		List<Integer> lis5 = new ArrayList<>(Arrays.asList(2, 4));
//		List<Integer> lis6 = new ArrayList<>(Arrays.asList(3, 4));
//
//		input.add(lis1);
//		input.add(lis2);
//		input.add(lis3);
//		input.add(lis4);
//		input.add(lis5);
//		input.add(lis6);
//		topoSort(input, 5);
//
//	}

	

	public static List<Integer> topoSort(List<List<Integer>> pre_requisites, int total_courses) {

		
		Map<Integer, Integer> map = new HashMap<>();
		Map<Integer, List<Integer>> neigbnours = new HashMap<>();
		Set<Integer> set = new HashSet<>();
		for (List<Integer> list : pre_requisites) {
			int key = list.get(0);
			int value = list.get(1);

			set.add(key);
			set.add(value);

			if (map.get(value) != null) {
				map.put(value, map.get(value) + 1);
			} else {
				map.put(value, 1);
			}

			if (neigbnours.get(key) != null) {
				neigbnours.get(key).add(value);
			} else {
				neigbnours.put(key, new ArrayList<>(Arrays.asList(value)));
			}
		}

		List<Integer> numbers = new ArrayList<>();
		for(int i=0; i<total_courses; i++) {
			numbers.add(i);
		}
		
		for (Integer i : numbers) {
			if (map.get(i) == null) {
				map.put(i, 0);
			}
		}

		
		
		List<Integer> list = new LinkedList<>();
		Queue<Integer> queue = new LinkedList<>();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getValue() == 0) {
				queue.add(entry.getKey());
			}
		}
		while (!queue.isEmpty()) {
			int val = queue.poll();
			list.add(val);
			List<Integer> neigh = neigbnours.get(val);
			if (neigh != null) {
				for (Integer n : neigh) {
					map.put(n, map.get(n) - 1);
					if (map.get(n) == 0) {
						queue.add(n);
					}
				}
			}

		}
		return list;
	}
}