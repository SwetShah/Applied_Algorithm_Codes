import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class MinimumSpanningTree {

//	public static void main(String[] args) {
//
//		ArrayList<ArrayList<Integer>> input = new ArrayList<>();
//
//		ArrayList<Integer> lis1 = new ArrayList<>(Arrays.asList(0, 1, 3));
//		ArrayList<Integer> lis2 = new ArrayList<>(Arrays.asList(1, 2, 1));
//		ArrayList<Integer> lis3 = new ArrayList<>(Arrays.asList(2, 0, 2));
//		ArrayList<Integer> lis4 = new ArrayList<>(Arrays.asList(1, 2, 2));
//		ArrayList<Integer> lis5 = new ArrayList<>(Arrays.asList(2, 3, -1));
//		ArrayList<Integer> lis6 = new ArrayList<>(Arrays.asList(2, 4, 0));
//		ArrayList<Integer> lis7 = new ArrayList<>(Arrays.asList(3, 4, -2));
//		ArrayList<Integer> lis8 = new ArrayList<>(Arrays.asList(5, 4, 6));
//		ArrayList<Integer> lis9 = new ArrayList<>(Arrays.asList(5, 6, 1));
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
//		System.out.println(mst(input, 3));
//	}

	public static int mst(ArrayList<ArrayList<Integer>> edges, int vertices) {

//		System.out.println("Vertices : " + vertices);
//		System.out.println("Edges : " + edges);

		Map<Integer, HashSet<Integer>> unionMap = new HashMap<>();
		MinimumSpanningTree m = new MinimumSpanningTree();
		Queue<Node> queue = new PriorityQueue<>();
		int count = 0;
		int totalWeight = 0;

		for (int i = 0; i < vertices; i++) {
			unionMap.put(i, new HashSet<>(Arrays.asList(i)));
		}

		if (vertices == 0 || vertices == 1) {
			return 0;
		}
		if (vertices == 2) {
			return edges.get(0).get(2);
		}

		for (ArrayList<Integer> edge : edges) {
			int vertex1 = edge.get(0);
			int vertex2 = edge.get(1);
			int weight = edge.get(2);
			Node node = m.new Node(vertex1, vertex2, weight);
			queue.add(node);
		}

		while (count != vertices - 1) {
			Node n = queue.poll();
			Integer v1 = n.vertex1;
			Integer v2 = n.vertex2;
			int weight = n.weight;

			if (find(unionMap, v1) != find(unionMap, v2)) {
				HashSet<Integer> set1 = find(unionMap, v1);
				HashSet<Integer> set2 = find(unionMap, v2);
				set1.addAll(set2);

				for (Integer i : set1) {
					unionMap.put(i, set1);
				}
				totalWeight = totalWeight + weight;
				count++;
			}
		}

		return totalWeight;
	}

	public static HashSet<Integer> find(Map<Integer, HashSet<Integer>> unionMap, Integer s) {
		return unionMap.get(s);
	}

	public class Node implements Comparable<Node> {
		int vertex1;
		int vertex2;
		int weight;

		public Node(int vertex1, int vertex2, int weight) {
			super();
			this.vertex1 = vertex1;
			this.vertex2 = vertex2;
			this.weight = weight;
		}

		public int getVertex1() {
			return vertex1;
		}

		public void setVertex1(int vertex1) {
			this.vertex1 = vertex1;
		}

		public int getVertex2() {
			return vertex2;
		}

		public void setVertex2(int vertex2) {
			this.vertex2 = vertex2;
		}

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		@Override
		public int compareTo(MinimumSpanningTree.Node o) {
			if (this.weight > o.weight) {
				return 1;
			} else if (this.weight < o.weight) {
				return -1;
			}
			return 0;
		}

	}

}
