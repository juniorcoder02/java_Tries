import java.util.*;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    boolean isWord = false;  // Marks if the node represents a complete word
    String word = "";  // Stores the complete word at this node
}

public class LongestWordDictionary {
    
    // Function to insert a word into the Trie
    private static void insert(TrieNode root, String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new TrieNode()); // Create a new node if not present
            node = node.children.get(c); // Move to the next node
        }
        node.isWord = true; // Mark end of a valid word
        node.word = word;  // Store the complete word for easy access
    }

    public static String longestWord(String[] words) {
        TrieNode root = new TrieNode();
        
        // Step 1: Insert all words into the Trie
        for (String word : words) {
            insert(root, word);
        }

        // Step 2: Perform BFS to find the longest valid word
        Queue<TrieNode> queue = new LinkedList<>();
        queue.add(root);
        String result = "";

        while (!queue.isEmpty()) {
            TrieNode node = queue.poll();

            // Process children in lexicographical order to get smallest lexicographic result
            List<Character> sortedKeys = new ArrayList<>(node.children.keySet());
            Collections.sort(sortedKeys);

            for (char c : sortedKeys) {
                TrieNode child = node.children.get(c);
                
                // We can only move forward if the current node represents a valid word
                if (child.isWord) {
                    queue.add(child); // Add valid nodes to BFS queue
                    result = child.word; // The last word stored will be the longest valid word
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String[] words1 = {"w","wo","wor","worl","world"};
        String[] words2 = {"a","banana","app","appl","ap","apply","apple"};

        System.out.println(longestWord(words1)); // Output: "world"
        System.out.println(longestWord(words2)); // Output: "apple"
    }
}
