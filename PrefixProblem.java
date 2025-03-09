// Trie-based solution for finding unique prefixes of words
// --------------------------------------------------------
// Time Complexity: O(N * L)  (N = number of words, L = length of longest word)
// Space Complexity: O(N * L) (for Trie storage)

public class PrefixProblem {

    // Trie Node Definition
    // - Contains an array of 26 children for lowercase English letters
    // - `endOfWord` marks the end of a valid word
    // - `freq` keeps track of how many words share this prefix
    static class Node {
        Node[] children = new Node[26];
        boolean endOfWord;
        int freq; // Frequency of words passing through this node

        // Constructor: Initializes children as null and frequency as 1
        public Node() {
            for (int i = 0; i < children.length; i++) {
                children[i] = null;
            }
            freq = 1; // When a node is first created, its frequency is 1
        }
    }

    // Root of the Trie
    public static Node root = new Node();

    // Trie Insertion Function
    // --------------------------------
    // Approach:
    // - Start from the root node.
    // - Traverse each character in the word.
    // - If a node for a character does not exist, create a new node.
    // - If a node exists, increment its frequency count.
    // - Mark `endOfWord = true` at the last character.
    //
    // Time Complexity: O(L) per word
    // Space Complexity: O(L) per word
    public static void insert(String word) {
        Node curr = root;
        for (int level = 0; level < word.length(); level++) {
            int index = word.charAt(level) - 'a';

            // If node does not exist, create a new node
            if (curr.children[index] == null) {
                curr.children[index] = new Node();
            } else {
                curr.children[index].freq++; // Increase frequency count
            }

            // Move to the next node
            curr = curr.children[index];
        }
        curr.endOfWord = true; // Mark end of word
    }

    // Function to find and print unique prefixes
    // -----------------------------------------
    // Approach:
    // - Perform DFS (Depth-First Search) on the Trie.
    // - Stop when a node's frequency is 1 (it means the prefix is unique).
    // - Traverse through all the children nodes.
    //
    // Time Complexity: O(N * L) (visiting each node once)
    // Space Complexity: O(N * L) (Trie storage)
    public static void findPrefix(Node root, String ans) {
        if (root == null) {
            return;
        }

        // If frequency is 1, print the unique prefix
        if (root.freq == 1) {
            System.out.println(ans);
            return;
        }

        // Traverse all children
        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null) {
                findPrefix(root.children[i], ans + (char) (i + 'a')); // Append character to prefix
            }
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Input array of words
        String[] arr = { "zebra", "dog", "duck", "dove" };

        // Insert words into Trie
        for (String word : arr) {
            insert(word);
        }

        // Mark root's frequency as -1 to avoid stopping at the root
        root.freq = -1;

        // Find and print unique prefixes
        findPrefix(root, "");
    }
}
