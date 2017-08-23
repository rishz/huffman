import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws Exception {
		Scanner scn = new Scanner(System.in);
		String str = scn.nextLine();
		HashMap<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < str.length(); i++) {
			if (map.containsKey(str.charAt(i))) {
				map.put(str.charAt(i), map.get(str.charAt(i)) + 1);
			} else {
				map.put(str.charAt(i), 1);
			}
		}
		PriorityQueue<Character, BinaryTree> heap = new Heap<>(false);
		for (int i = 0; i < map.keySet().size(); i++) {
			heap.add((Character) map.keySet().toArray()[i],
					new BinaryTree((Integer) map.values().toArray()[i], null, null));
		}

		while (heap.size() != 1) {
			BinaryTree temp1 = heap.remove();
			BinaryTree temp2 = heap.remove();
			BinaryTree combined = null;
			combined = new BinaryTree(temp1.root.data + temp2.root.data, temp1.root, temp2.root);
			heap.add("", combined);// empty.
		}
		System.out.println(heap.get());
	}

}