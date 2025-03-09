// Trie-based approach to count unique substrings of a given string
// --------------------------------------------------------
// Time Complexity: O(N^2)  (N = length of the string, due to inserting all suffixes)
// Space Complexity: O(N^2) (for Trie storage, in the worst case)

public class CountUniqueSubstrings {
    
    // Trie Node Definition
    // - Contains an array of 26 children for lowercase English letters
    // - `isEndOfWord` marks the end of a valid word
    static class Node {
        Node[] children = new Node[26];
        boolean isEndOfWord;

        // Constructor: Initializes all children to null
        Node() {
            for (int i = 0; i < children.length; i++) {
                children[i] = null;
            }
            isEndOfWord = false;
        }
    }

    // Root of the Trie
    private static Node root = new Node();

    // Trie Insertion Function
    // --------------------------------
    // Approach:
    // - Start from the root node.
    // - Traverse each character in the word.
    // - If a node for a character does not exist, create a new node.
    // - Move to the next node.
    // - Mark `isEndOfWord = true` at the last character.
    //
    // Time Complexity: O(L) per word
    // Space Complexity: O(L) per word
    private static void insert(String word) {
        Node curr = root;
        for (int level = 0; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';

            // If node does not exist, create a new node
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            }

            // Move to the child node
            curr = curr.children[index];
        }
        curr.isEndOfWord = true; // Mark end of word
    }

    // Function to count total nodes in the Trie
    // -----------------------------------------
    // Approach:
    // - Perform DFS (Depth-First Search) on the Trie.
    // - Count each node because each represents a unique substring.
    // - Return total count of nodes.
    //
    // Time Complexity: O(N^2) (since Trie has at most N^2 nodes in worst case)
    // Space Complexity: O(N^2) (Trie storage)
    public static int countNodes(Node root) {
        if (root == null) {
            return 0;
        }
        int count = 0;

        // Traverse all children
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                count += countNodes(root.children[i]); // Recursive DFS call
            }
        }
        return count + 1; // Include the current node
    }

    // Main Function
    public static void main(String[] args) {
        // Input string
        String str = "ababa";

        // Insert all suffixes into the Trie
        // ---------------------------------
        // Approach:
        // - Generate all suffixes of `str`
        // - Insert each suffix into the Trie
        // - This ensures we store all unique substrings in the Trie
        //
        // Example for "ababa":
        //   Insert "ababa"
        //   Insert "baba"
        //   Insert "aba"
        //   Insert "ba"
        //   Insert "a"
        for (int i = 0; i < str.length(); i++) {
            String suffix = str.substring(i);
            insert(suffix);
        }

        // Print the count of unique substrings
        // `countNodes(root) - 1` because we exclude the root node (empty string)
        System.out.println(countNodes(root) - 1);
    }
}
