// Trie-based solution for the Word Break Problem
// ---------------------------------------------
// Time Complexity:
// - Insert: O(L) (L = length of the word)
// - Search: O(L)
// - Word Break: O(2^N) in worst case, can be optimized using DP
// Space Complexity: O(N * L) (Trie storage)

public class WordBreakProblem {
    // Trie Node Definition
    static class Node {
        Node[] children = new Node[26]; // Array for 26 lowercase English letters
        boolean isEndOfWord = false; // Marks if a word ends here

        public Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    // Root of the Trie
    public static Node root = new Node();

    // Insert Function (Adds a word to the Trie)
    public static void insert(String word) {
        Node curr = root;
        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }
            curr = curr.children[idx];
        }
        curr.isEndOfWord = true; // Mark last node as the end of a word
    }

    // Search Function (Checks if a word exists in the Trie)
    public static boolean search(String key) {
        Node curr = root;
        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return curr.isEndOfWord;
    }

    // Word Break Function (Checks if a string can be segmented into dictionary words)
    public static boolean wordBreak(String key) {
        if (key.length() == 0) {
            return true;
        }
        for (int i = 1; i <= key.length(); i++) {
            if (search(key.substring(0, i)) && wordBreak(key.substring(i))) {
                return true;
            }
        }
        return false;
    }

    // Main Function
    public static void main(String[] args) {
        // Dictionary of words
        String[] words = { "i", "like", "sam", "samsung", "mobile", "ice" };
        String key = "ilikesamsung";

        // Step 1: Insert words into Trie
        for (String word : words) {
            insert(word);
        }
        
        // Step 2: Check if the key can be segmented using dictionary words
        System.out.println(wordBreak(key)); // Expected Output: true
    }
}
