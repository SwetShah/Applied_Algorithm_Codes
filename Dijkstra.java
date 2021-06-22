import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Dijkstra {

//	public static void main(String[] args) {
//
//		Map<Integer, Integer> map = new HashMap<>();
//		map.put(3, 4);
//		map.put(5, 1);
//		map.put(1, 2);
//
//		List<List<Integer>> input = new ArrayList<>();
//
//		List<Integer> lis1 = new ArrayList<>(Arrays.asList(0, 1, 4));
//		List<Integer> lis2 = new ArrayList<>(Arrays.asList(0, 2, 2));
//		List<Integer> lis3 = new ArrayList<>(Arrays.asList(1, 3, 2));
//		List<Integer> lis4 = new ArrayList<>(Arrays.asList(1, 4, 3));
//		List<Integer> lis5 = new ArrayList<>(Arrays.asList(2, 1, 1));
//		List<Integer> lis6 = new ArrayList<>(Arrays.asList(2, 3, 2));
//		List<Integer> lis7 = new ArrayList<>(Arrays.asList(2, 4, 5));
//		List<Integer> lis8 = new ArrayList<>(Arrays.asList(4, 3, 1));
//		List<Integer> lis9 = new ArrayList<>(Arrays.asList(5, 2, 2));
//
//		input.add(lis1);
//		input.add(lis2);
//		input.add(lis3);
//		input.add(lis4);
//		input.add(lis5);
//		input.add(lis6);
//		input.add(lis7);
//		input.add(lis8);
//		input.add(lis9);
//		System.out.println(shortestDistance(input, 6));
//	}

	public static int[] shortestDistance(List<List<Integer>> edges, int vertices) {

		Map<Integer, List<Integer>> linkMap = new HashMap<>();
		Map<String, Integer> weightMap = new HashMap<>();
		PriorityQueue<Node> queue = new PriorityQueue<>();
		Set<Integer> visited = new HashSet<>();
		Map<Integer, Node> updateMap = new HashMap<>();
		int result[] = new int[vertices];
		if (vertices == 0) {
			return result;
		}

		try {
			for (List<Integer> li : edges) {

				int source = li.get(0);
				int destination = li.get(1);
				int weight = li.get(2);

				if (linkMap.get(source) == null) {
					linkMap.put(source, new ArrayList<>(Arrays.asList(destination)));
				} else {
					linkMap.get(source).add(destination);
				}

				String s = String.valueOf(source) + String.valueOf(destination);

				weightMap.put(s, weight);
			}

			for (int i = 0; i < vertices; i++) {
				if (!linkMap.containsKey(i)) {
					linkMap.put(i, new ArrayList<>());
				}
				Node n = new Node(i, Integer.MAX_VALUE);
				updateMap.put(i, n);
			}
			updateMap.get(0).weight = 0;

			queue.add(updateMap.get(0));
//		visited.add(0);

			dijaktra(queue, linkMap, updateMap, weightMap, visited);

			for (int i = 0; i < vertices; i++) {
				if (updateMap.get(i) == null) {
					result[i] = -1;
				} else {
					result[i] = updateMap.get(i).weight == Integer.MAX_VALUE ? -1 : updateMap.get(i).weight;
				}
			}
		} catch (Exception e) {
			System.out.println("Edges: " + edges);
			System.out.println("Vertices: " + edges);
			e.printStackTrace();
		}
		result[0] = 0;
		return result;
	}

	public static void dijaktra(PriorityQueue<Node> queue, Map<Integer, List<Integer>> linkMap,
			Map<Integer, Node> updateMap, Map<String, Integer> weightMap, Set<Integer> visited) {

		while (!queue.isEmpty()) {
			Node n = queue.poll();

			int vertex = n.vertex;
			if (!visited.contains(vertex)) {
				int weight = n.weight;
				for (Integer neighbour : linkMap.get(vertex)) {
					Node node = updateMap.get(neighbour);
					int existingWeight = weightMap.get(String.valueOf(vertex) + String.valueOf(neighbour));
					int total = existingWeight + updateMap.get(vertex).weight;
					if (total < node.weight) {
						node.weight = total;
					}
					queue.add(node);
				}
				visited.add(vertex);
			}
		}

	}

	public static class Node implements Comparable<Node> {
		public int vertex;
		public int weight;

		public Node(int vertex, int weight) {
			super();
			this.vertex = vertex;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			if (this.weight == o.weight) {
				return 0;
			} else if (this.weight > o.weight)
				return 1;
			else
				return -1;
		}
	}

}