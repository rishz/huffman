
public class BinaryTree {

	public class Node {
		public Node left;
		public Node right;
		public int data;

		Node(int data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
		}

		public String toString() {
			String retval = "";

			if (this.left != null) {
				retval += this.left.data + " => ";
			}
			retval += this.data;
			if (this.right != null) {
				retval += " <= " + this.right.data;
			}

			retval += "\n";
			if (this.left != null) {
				retval += this.left;
			}
			if (this.right != null) {
				retval += this.right;
			}
			return retval;
		}

	}

	public Node root;

	private int size;

	public BinaryTree(Integer freq, Node left, Node right){
		this.root = new Node(freq, left, right);
	}

	public BinaryTree(Integer freq, BinaryTree leftTree, BinaryTree rightTree, boolean isTree){
		this.root = new Node(freq, leftTree.root, rightTree.root);
	}

	public void display() {
		System.out.println(this);
	}

	public String toString() {
		return this.root.toString();
	}

	public int max() {
		return max(this.root);
	}

	private int max(Node node) {
		if (node == null) {
			return Integer.MIN_VALUE;
		}
		int lmax = this.max(node.left);
		int rmax = this.max(node.right);

		return Math.max(node.data, Math.max(lmax, rmax));
	}

	public int min() {
		return min(this.root);
	}

	private int min(Node node) {
		if (node == null) {
			return Integer.MAX_VALUE;
		}
		int lmin = this.min(node.left);
		int rmin = this.min(node.right);

		return Math.min(node.data, Math.min(lmin, rmin));
	}

	public int height() {
		return this.height(this.root);
	}

	private int height(Node node) {
		if (node == null) {
			return -1;
		}
		int lHeight = this.height(node.left);
		int rHeight = this.height(node.right);

		if (lHeight > rHeight) {
			return lHeight + 1;
		} else {
			return rHeight + 1;
		}
	}

	public int size() {
		return this.size(this.root);
	}

	private int size(Node node) {
		if (node == null) {
			return 0;
		}
		int lsize = this.size(node.left);
		int rsize = this.size(node.right);
		return lsize + rsize + 1;
	}

	public void mirror() {
		mirror(this.root);
	}

	private void mirror(Node node) {
		if (node == null) {
			return;
		}
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;

		mirror(node.left);
		mirror(node.right);
	}

	public void preOrder() {
		preOrder(this.root);
		System.out.println("END\n");
	}

	private void preOrder(Node node) {
		if (node == null) {
			return;
		}
		System.out.print(node.data + ", ");
		preOrder(node.left);
		preOrder(node.right);
	}

	public void postOrder() {
		postOrder(this.root);
		System.out.println("END\n");
	}

	private void postOrder(Node node) {
		if (node == null) {
			return;
		}

		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + ", ");

	}

}
