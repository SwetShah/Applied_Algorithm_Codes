import java.util.HashMap;
import java.util.Map;

public class FamilyForest {

//	public static void main(String[] args) {
//		FamilyForest f = new FamilyForest();
//		String[] people = new String[] { "Ricardo", "Sean", "Maya", "Ishaan", "Chia-Lin" };
//
//		for (String s : people) {
//			f.make_family(s);
//		}
//
//		f.union("Sean", "Ishaan");
//		f.union("Maya", "Ishaan");
//		f.union("Ricardo", "Chia-Lin");
//
//		if (f.find("Ishaan") == f.find("Sean")) {
//			System.out.println("Correct");
//		}
//		if (f.find("Maya") == f.find("Sean")) {
//			System.out.println("Correct");
//		}
//		if (f.find("Ricardo") == f.find("Chia-Lin")) {
//			System.out.println("Correct");
//		}
//		if (f.find("Sean") != f.find("Chia-Lin")) {
//			System.out.println("Correct");
//		}
//	}

	Map<String, Object> map;

	public FamilyForest() {
		map = new HashMap<>();
	}

	public void make_family(String s) {
		map.put(s, -1);
	}

	public String union(String s, String t) {

		String parent = "";
		if (map.get(s) instanceof Integer && map.get(t) instanceof Integer) {
			map.put(s, (Integer) map.get(t) + (Integer) map.get(s));
			map.put(t, s);
			parent = s;
		} else {
			String parent1 = find(s);
			String parent2 = find(t);

			int weight1 = (Integer) map.get(parent1);
			int weight2 = (Integer) map.get(parent2);

			if (weight1 < weight2) {
				map.put(parent1, (Integer) map.get(parent2) + (Integer) map.get(parent1));
				map.put(parent2, parent1);
				parent = parent1;
			} else {
				map.put(parent2, (Integer) map.get(parent1) + (Integer) map.get(parent2));
				map.put(parent1, parent2);
				parent = parent2;
			}
		}
		return parent;
	}

	public String find(String s) {
		if (map.get(s) instanceof Integer) {
			return s;
		} else {
			return find((String) map.get(s));
		}
	}

}
