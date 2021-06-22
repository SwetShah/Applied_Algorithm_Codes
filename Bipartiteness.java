import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Bipartiteness {

	public static int bipartite(List<List<Integer>> edges, int vertices) {

		Set<Integer> black = new HashSet<>();
		Set<Integer> white = new HashSet<>();
		Queue<Integer> queue = new LinkedList<>();
		Map<Integer, Set<Integer>> linkMap = new HashMap<>();
		Set<Integer> verticesSet = new HashSet<>();

		if (vertices == 0 || vertices == 1 || vertices == 2) {
			return 1;
		}
		linkMap = createLinkMap(linkMap, edges, verticesSet);
		for (int i = 0; i < vertices; i++) {
			if (linkMap.get(i) == null) {
				linkMap.put(i, new HashSet<>());
			}
		}

		black.add(0);
		queue.add(0);
		boolean isBlack;
		verticesSet.remove(0);

		while (!queue.isEmpty() || !verticesSet.isEmpty()) {

			if (queue.isEmpty()) {
				int newNode = verticesSet.iterator().next();
				queue.add(newNode);
				isBlack = true;
				black.add(newNode);
			}
			int node = queue.poll();
			verticesSet.remove(node);

			isBlack = black.contains(node);

			for (Integer neighbour : linkMap.get(node)) {

				if (!white.contains(neighbour) && !black.contains(neighbour)) {
					queue.add(neighbour);
				}

				if (isBlack) {
					if (black.contains(neighbour)) {
						return -1;
					}
					white.add(neighbour);
				} else {
					if (white.contains(neighbour)) {
						return -1;
					}
					black.add(neighbour);
				}
			}
		}
		return 1;
	}

	public static Map<Integer, Set<Integer>> createLinkMap(Map<Integer, Set<Integer>> linkMap,
			List<List<Integer>> edges, Set<Integer> verticesSet) {

		// Create link Map
		for (List<Integer> links : edges) {
			int first = links.get(0);
			int second = links.get(1);

			verticesSet.add(first);
			verticesSet.add(second);
			if (linkMap.get(first) == null) {
				linkMap.put(first, new HashSet<>(Arrays.asList(second)));
			} else {
				linkMap.get(first).add(second);
			}
			if (linkMap.get(second) == null) {
				linkMap.put(second, new HashSet<>(Arrays.asList(first)));
			} else {
				linkMap.get(second).add(first);
			}
		}
		return linkMap;
	}
}
