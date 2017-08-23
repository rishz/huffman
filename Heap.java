import java.util.ArrayList;

public class Heap<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	private class Node implements Comparable<Node> {
		K priority;
		V value;

		public Node(K priority, V value) {
			this.priority = priority;
			this.value = value;
		}

		public int compareTo(Heap<K, V>.Node arg0) {
			if (isMin) {
				return -1 * this.priority.compareTo(arg0.priority);
			} else {
				return this.priority.compareTo(arg0.priority);
			}
		}

		public String toString() {
			return "{" + this.priority + ", " + this.value + "}";
		}
	}

	private ArrayList<Node> data;
	private boolean isMin;

	public Heap() {
		this(false);
	}

	public Heap(boolean isMin) {
		this.data = new ArrayList<>();
		this.isMin = isMin;
	}

	public Heap(boolean isMin, K[] priority, V[] value) {
		this(isMin);

		for (int i = 0; i < priority.length; i++) {
			Node node = new Node(priority[i], value[i]);
			this.data.add(node);
		}
		for (int i = (this.size() / 2) - 1; i >= 0; i--) {
			this.downHeapify(i);
		}
	}

	public int size() {
		return this.data.size();
	}

	public void display() {
		System.out.println(this);
	}

	public void displayAsATree() {
		String treeDisplay = displayAsATreeHelper(0);
		System.out.println(treeDisplay);
	}

	public String displayAsATreeHelper(int pi) {
		String retval = "";
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;
		if (lci < this.size()) {
			retval += this.data.get(lci) + "=>";
		}

		retval += this.data.get(pi);

		if (rci < this.size()) {
			retval += "<=" + this.data.get(rci);
		}
		retval += "\n";
		if (lci < this.size()) {
			retval += displayAsATreeHelper(lci);
		}
		if (rci < this.size()) {
			retval += displayAsATreeHelper(rci);
		}

		return retval;
	}

	public boolean isEmpty() {
		return this.size() == 0;
	}

	public void add(K priority, V value) {
		Node temp = new Node(priority, value);
		this.data.add(temp);

		this.upHeapify(this.size() - 1);
	}

	private void upHeapify(int ci) {
		if (ci == 0) {
			return;
		}
		int pi = (ci - 1) / 2;

		if (this.data.get(pi).compareTo(this.data.get(ci)) < 0) {
			this.swap(pi, ci);
			this.upHeapify(pi);
		}

	}

	public V remove() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty Heap");
		}
		V retval = this.data.get(0).value;
		this.swap(0, this.size() - 1);
		this.data.remove(this.size() - 1);
		this.downHeapify(0);
		return retval;
	}

	public void downHeapify(int pi) {
		int lci = 2 * pi + 1;
		int rci = 2 * pi + 2;
		int maxIndex = pi;
		if (lci < this.size() && this.data.get(lci).compareTo(this.data.get(maxIndex)) > 0) {
			maxIndex = lci;
		}
		if (rci < this.size() && this.data.get(rci).compareTo(this.data.get(maxIndex)) > 0) {
			maxIndex = rci;
		}
		if (maxIndex != pi) {
			swap(pi, maxIndex);
			this.downHeapify(maxIndex);
		}

	}

	public V get() throws Exception {
		if (this.isEmpty()) {
			throw new Exception("Empty Heap");
		}
		return this.data.get(0).value;
	}

	private void swap(int i, int j) {
		Node temp = this.data.get(i);
		this.data.set(i, this.data.get(j));
		this.data.set(j, temp);
	}

	public String toString() {
		return this.data.toString();
	}

}
