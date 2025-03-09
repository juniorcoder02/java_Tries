// Trie-based solution to check if a word or prefix exists
// --------------------------------------------------------
// Time Complexity:
// - Insert: O(L)  (L = length of the word)
// - Search: O(L)
// - StartsWith: O(L)
// Space Complexity: O(N * L) (N = number of words, L = average length of words)

public class StartsWithProblem {

    // Trie Node Definition
    // - Contains an array of 26 children (for lowercase English letters)
    // - `isEndOfWord` marks if a word ends at this node
    static class Node {
        Node[] children = new Node[26];
        boolean isEndOfWord;

        // Constructor: Initializes children as null
        public Node() {
            for (int i = 0; i < children.length; i++) {
                children[i] = null;
            }
            isEndOfWord = false; // Default: Not the end of a word
        }
    }

    // Root of the Trie
    public static Node root = new Node();

    // Insert Function (Adds a word to the Trie)
    // ------------------------------------------
    // Approach:
    // - Traverse each character of the word.
    // - If a node does not exist, create a new one.
    // - Move to the next node.
    // - Mark the last node as `isEndOfWord = true`.
    //
    // Time Complexity: O(L) (L = length of the word)
    // Space Complexity: O(L) (for new nodes created)
    public static void insert(String word) {
        Node curr = root;

        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';

            // If node does not exist, create a new node
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            // Move to the next node
            curr = curr.children[idx];
        }

        // Mark last node as the end of a valid word
        curr.isEndOfWord = true;
    }

    // Search Function (Checks if a word exists in the Trie)
    // ------------------------------------------------------
    // Approach:
    // - Traverse each character of the key.
    // - If any character node does not exist, return false.
    // - After the last character, check if it is a valid word.
    //
    // Time Complexity: O(L)
    // Space Complexity: O(1)
    public static boolean search(String key) {
        Node curr = root;

        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';

            // If node does not exist, return false
            if (curr.children[idx] == null) {
                return false;
            }

            // Move to the next node
            curr = curr.children[idx];
        }

        // Return true only if it is a complete word
        return curr.isEndOfWord;
    }

    // StartsWith Function (Checks if a prefix exists in the Trie)
    // ------------------------------------------------------------
    // Approach:
    // - Traverse the characters of the prefix.
    // - If any character node does not exist, return false.
    // - If all characters exist, return true.
    //
    // Time Complexity: O(L)
    // Space Complexity: O(1)
    public static boolean startsWith(String prefix) {
        Node curr = root;

        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';

            // If node does not exist, return false
            if (curr.children[idx] == null) {
                return false;
            }

            // Move to the next node
            curr = curr.children[idx];
        }

        return true; // Prefix exists
    }

    // Main Function
    public static void main(String[] args) {
        // Input array of words
        String[] words = { "apple", "app", "mango", "man", "woman" };

        // Test prefixes
        String prefix1 = "app";  // Should return true
        String prefix2 = "moon"; // Should return false

        // Insert words into Trie
        for (String word : words) {
            insert(word);
        }

        // Check if prefixes exist
        System.out.println(startsWith(prefix1)); // true
        System.out.println(startsWith(prefix2)); // false
    }
}
