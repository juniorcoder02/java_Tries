// Trie-based approach to find the longest word where all its prefixes exist in the Trie
// --------------------------------------------------------
// Time Complexity: O(N * L)  (N = number of words, L = avg. word length)
// Space Complexity: O(N * L) (for Trie storage)

public class LongestWordWithAllPrefix {
    
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
    public static Node root = new Node();

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
    public static void insert(String word) {
        Node curr = root;
        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';

            // If node does not exist, create a new node
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            // Move to the child node
            curr = curr.children[idx];
        }
        curr.isEndOfWord = true; // Mark end of word
    }

    // Trie Search Function
    // --------------------------------
    // Approach:
    // - Traverse each character of the key.
    // - If any character is missing, return false.
    // - If the last character is found, check `isEndOfWord` status.
    //
    // Time Complexity: O(L)
    // Space Complexity: O(1)
    private static boolean search(String key) {
        Node curr = root;
        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';

            // If the node does not exist, return false
            if (curr.children[idx] == null) {
                return false;
            }

            // Move to the next node
            curr = curr.children[idx];
        }
        return curr.isEndOfWord; // Return true only if it marks the end of a valid word
    }

    // Variable to store the longest valid word
    public static String ans = "";

    // Function to find the longest word where all prefixes exist
    // ----------------------------------------------------------
    // Approach:
    // - Perform DFS (Depth-First Search) on the Trie.
    // - If a node's `isEndOfWord` is true, proceed further.
    // - Keep track of the current word using `StringBuilder temp`.
    // - Update `ans` if a longer valid word is found.
    // - Use backtracking to remove the last character and explore other paths.
    //
    // Time Complexity: O(N * L)
    // Space Complexity: O(L) (for recursive depth)
    public static void longestWord(Node root, StringBuilder temp) {
        if (root == null) {
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (root.children[i] != null && root.children[i].isEndOfWord) {
                char ch = (char) (i + 'a'); // Convert index to character
                temp.append(ch); // Append character to the current word
                
                // Update answer if the current word is longer
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }

                // Recursive call to explore further
                longestWord(root.children[i], temp);

                // Backtrack to explore other possibilities
                temp.deleteCharAt(temp.length() - 1);
            }
        }
    }

    // Main Function
    public static void main(String[] args) {
        // Input word list
        String[] words = { "a", "banana", "app", "appl", "ap", "apply", "apple" };

        // Insert words into the Trie
        for (String word : words) {
            insert(word);
        }

        // Find the longest word where all prefixes exist
        longestWord(root, new StringBuilder(""));

        // Print the result
        System.out.println(ans);
    }
}
