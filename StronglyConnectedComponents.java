import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class StronglyConnectedComponents {

//	public static void main(String[] args) {
//		List<List<Integer>> input = new ArrayList<>();
//		List<Integer> lis1 = new ArrayList<>(Arrays.asList(0, 1));
//		List<Integer> lis2 = new ArrayList<>(Arrays.asList(1, 2));
//		List<Integer> lis3 = new ArrayList<>(Arrays.asList(2, 3));
//		List<Integer> lis4 = new ArrayList<>(Arrays.asList(3, 4));
//		List<Integer> lis5 = new ArrayList<>(Arrays.asList(4, 5));
//		List<Integer> lis6 = new ArrayList<>(Arrays.asList(5, 6));
//		List<Integer> lis7 = new ArrayList<>(Arrays.asList(6, 5));
//		List<Integer> lis8 = new ArrayList<>(Arrays.asList(5, 4));
//		List<Integer> lis9 = new ArrayList<>(Arrays.asList(4, 3));
//		List<Integer> lis10 = new ArrayList<>(Arrays.asList(3, 2));
//		List<Integer> lis11 = new ArrayList<>(Arrays.asList(2, 1));
//		List<Integer> lis12 = new ArrayList<>(Arrays.asList(1, 0));
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
//		input.add(lis10);
//		input.add(lis11);
//		input.add(lis12);
//
////		scc(8, input);
//		System.out.println(scc(7, input));
//	}

	public static List<List<Integer>> scc(int students, List<List<Integer>> knows) {

		int[][] matrix = new int[students][students];
		boolean[] visited = new boolean[matrix.length];
		Stack<Integer> stack = new Stack<>();
		List<List<Integer>> result = new ArrayList<>();

		for (List<Integer> list : knows) {
			matrix[list.get(0)][list.get(1)] = 1;
		}

		for (int i = 0; i < matrix.length; i++) {
			if (!visited[i]) {
				applyDFS(matrix, visited, stack, i);
			}
		}

		int[][] reverseGraph = getReverseGraph(matrix);
		visited = new boolean[matrix.length];

		while (!stack.isEmpty()) {
			int vertex = stack.pop();
			if (!visited[vertex]) {
				List<Integer> groups = new ArrayList<>();
				transpose(reverseGraph, visited, groups, vertex);
				result.add(groups);
			}
		}
		return result;
	}

	public static void applyDFS(int[][] graph, boolean[] visitedVertices, Stack<Integer> stack, int vertex) {
		visitedVertices[vertex] = true;

		for (Integer adjacentVertex : getNeighbours(graph, vertex)) {
			if (!visitedVertices[adjacentVertex]) {
				applyDFS(graph, visitedVertices, stack, adjacentVertex);
			}
		}
		stack.push(vertex);
	}

	public static void transpose(int[][] reverseGraph, boolean[] visited, List<Integer> groups, int vertex) {
		visited[vertex] = true;
		groups.add(vertex);

		List<Integer> neighbours = getNeighbours(reverseGraph, vertex);

		for (Integer adjacentVertex : neighbours) {
			if (!visited[adjacentVertex]) {
				transpose(reverseGraph, visited, groups, adjacentVertex);
			}
		}
	}

	public static List<Integer> getNeighbours(int[][] reverseGraph, int key) {
		List<Integer> neighbours = new ArrayList<>();

		for (int vertex = 0; vertex < reverseGraph.length; vertex++) {
			if (reverseGraph[key][vertex] == 1) {
				neighbours.add(vertex);
			}
		}
		return neighbours;
	}

	public static int[][] getReverseGraph(int[][] matrix) {
		int size = matrix.length;
		int[][] reverseGraph = new int[size][size];
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 1) {
					reverseGraph[j][i] = 1;
				}
			}
		}
		return reverseGraph;
	}
}
