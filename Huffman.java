import java.util.*;
import java.util.Map.Entry;

public class Huffman {
    private Huffman() {
    }

    private static class HuffmanNode {
        char ch;
        int frequency;
        HuffmanNode left;
        HuffmanNode right;

        HuffmanNode(char ch, int frequency, HuffmanNode left, HuffmanNode right) {
            this.ch = ch;
            this.frequency = frequency;
            this.left = left;
            this.right = right;
        }
    }

    private static class HuffManComparator implements Comparator<HuffmanNode> {
        public int compare(HuffmanNode node1, HuffmanNode node2) {
            return node1.frequency - node2.frequency;
        }
    }

    public static void compress(String sentence) throws NullPointerException {
        if (sentence == null) {
            throw new NullPointerException("String is Null");
        }
    }

    private static HashMap<Character, Integer> getCharFreq(String sentence) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            if (!map.containsKey(ch)) {
                map.put(ch, 1);
            } else {
                int val = map.get(ch);
                map.put(ch, ++val);
            }
        }

        return map;
    }

    private static HuffmanNode buildTree(HashMap<Character, Integer> map) {
        Queue<HuffmanNode> pq = createNodeQueue(map);
        while (pq.size() > 1) {
            HuffmanNode temp1 = pq.remove();
            HuffmanNode temp2 = pq.remove();
            pq.add(new HuffmanNode('\0', temp1.frequency + temp2.frequency, temp1, temp2));
        }
        return pq.remove();
    }

    private static Queue<HuffmanNode> createNodeQueue(HashMap<Character, Integer> map) {
        // buz---buz
        Queue<HuffmanNode> pq = new PriorityQueue<>(0, new HuffManComparator());
        for (Entry<Character, Integer> entry : map.entrySet()) {
            pq.add(new HuffmanNode(entry.getKey(), entry.getValue(), null, null));
        }
        return pq;
    }

    private static HashMap<Character, String> generateCode(HuffmanNode node, Set<Character> chars) {
        HashMap<Character, String> map = new HashMap<>();
        generateCodeHelper("", node, map);
        return map;
    }

    private static void generateCodeHelper(String s, HuffmanNode node, HashMap<Character, String> map) {
        if (node.left == null && node.right == null) {
            map.put(node.ch, s);
            return;
        }
        generateCodeHelper(s + "0", node.left, map);
        generateCodeHelper(s + "1", node.left, map);
    }
}
