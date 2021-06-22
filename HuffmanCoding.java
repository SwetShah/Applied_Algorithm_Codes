import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class HuffmanCoding {

	static HuffmanCoding h = new HuffmanCoding();

//	public static void main(String[] args) {
//		Map<Character, String> lookup = new HashMap<>();
//		lookup = (Map<Character, String>)build("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//		encode(lookup, "ABCDEFGHIJKLMNOPQRSTUVWXYZ");
//		decode(lookup, "110111010");
//	}

	public static Object build(String text) {

		Map<Character, Integer> map = new HashMap<>();
		PriorityQueue<Node> queue;
		Map<Character, String> lookup = new HashMap<>();

		Node root;
		map = frequencyCount(map, text);
		queue = sortedQueue(map);
		root = createHuffmanTree(queue);
		generateMapOfCharacterAndCode(root, lookup, "");
		return lookup;
	}

	public static String encode(Object dic, String text) {
		Map<Character, String> lookup = (Map<Character, String>) dic;

		if (text == null || text == "") {
			return "";
		}
		String encodedMessage = "";

		for (Character c : text.toCharArray()) {
			encodedMessage = encodedMessage + lookup.get(c);
		}
		return encodedMessage;
	}

	public static String decode(Object dic, String text) {

		Map<Character, String> lookup = (Map<Character, String>) dic;
		String decodedMessage = "";
		final String[] subString = { "" };
		int i = 0;
		char[] charArray = text.toCharArray();
		while (i < text.length()) {
			subString[0] = subString[0] + charArray[i];
			List<Character> list = lookup.entrySet().stream().filter(e -> e.getValue().equals(subString[0]))
					.map(e -> e.getKey()).collect(Collectors.toList());
			if (list != null && !list.isEmpty()) {
				decodedMessage = decodedMessage + list.get(0);
				list = null;
				subString[0] = "";
			}
			i++;
		}
		return decodedMessage;
	}

	public static Map<Character, Integer> frequencyCount(Map<Character, Integer> map, String text) {
		for (Character c : text.toCharArray()) {
			if (map.get(c) == null) {
				map.put(c, 1);
			} else {
				int count = map.get(c) + 1;
				map.put(c, count);
			}
		}
		return map;
	}

	public static PriorityQueue<Node> sortedQueue(Map<Character, Integer> map) {
		Map<Node, Integer> sortedSet = new TreeMap<>();

		PriorityQueue<Node> queue = new PriorityQueue<>();
		for (Map.Entry<Character, Integer> entry : map.entrySet()) {
			Node n = h.new Node();
			n.c = entry.getKey();
			n.value = entry.getValue();
			sortedSet.put(n, null);
			queue.add(n);
			n = null;
		}

		return queue;
	}

	public static Node createHuffmanTree(PriorityQueue<Node> queue) {

		if (queue.size() > 1) {
			Node left = queue.poll();
			Node right = queue.poll();

			Node parent = h.new Node(left.value + right.value);
			parent.left = left;
			parent.right = right;
			queue.add(parent);
			createHuffmanTree(queue);
		}

		Node root = queue.peek();
		return root;
	}

	private static void generateMapOfCharacterAndCode(Node root, Map<Character, String> lookup, String text) {

		if (root == null) {
			return;
		}
		if (root.left == null && root.right == null) {
			String s = text.length() > 0 ? text : "1";
			lookup.put(root.c, s);
		}
		generateMapOfCharacterAndCode(root.left, lookup, text + "0");
		generateMapOfCharacterAndCode(root.right, lookup, text + "1");
	}

	public class Node implements Comparable<Node> {
		Node left;
		Node right;
		int value;
		char c;

		public Node() {

		}

		public Node(int value) {
			super();
			this.value = value;
		}

		public Node(HuffmanCoding.Node left, HuffmanCoding.Node right, int value, char c) {
			super();
			this.left = left;
			this.right = right;
			this.value = value;
			this.c = c;
		}

		@Override
		public int compareTo(HuffmanCoding.Node n) {

			if (this.value == n.value) {
				return 0;
			} else if (this.value > n.value)
				return 1;
			else
				return -1;
		}

	}

}
