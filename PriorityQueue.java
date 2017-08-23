public interface PriorityQueue<K extends Comparable<K>, V> {
	int size();

	void display();

	boolean isEmpty();

	void add(K priority, V value);

	V remove()throws Exception;

	V get()throws Exception;
	
	void displayAsATree();

}