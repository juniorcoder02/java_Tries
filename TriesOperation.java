//  Trie Data Structure Implementation in Java 
// ---------------------------------------------
//  Operations: Insert & Search
//  Time Complexity: O(L) per operation (L = length of the word)
//  Space Complexity: O(N * L) (N = number of words, L = avg. word length)

public class TriesOperation {

    //  Trie Node Definition 
    // - Each node contains an array of 26 children (for lowercase English letters 'a' to 'z').
    // - A boolean flag `isEndOfWord` marks the end of a word.
    static class Node {
        Node[] children = new Node[26];  // Array to store references to child nodes
        boolean isEndOfWord = false;     // Flag to mark end of word

        // Constructor: Initializes all children to `null`
        Node() {
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
        }
    }

    //  Root of the Trie (Always Empty)
    public static Node root = new Node();

    //  INSERTION OPERATION 🚀
    // ------------------------
    //  Approach:
    // - Start from the root node.
    // - Traverse each character in the word.
    // - If a node for a character does not exist, create a new node.
    // - Move to the next node.
    // - After the last character, mark `isEndOfWord = true`.
    //
    //  Time Complexity: O(L)  (L = length of the word)
    //  Space Complexity: O(L) (for new nodes if word is unique)
    public static void insert(String word) {
        Node curr = root;  // Start from the root node

        for (int level = 0; level < word.length(); level++) {
            int idx = word.charAt(level) - 'a';  // Convert character to index (0-25)

            // If the node for this character does not exist, create a new node
            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            // Move to the child node
            curr = curr.children[idx];
        }

        // Mark the last node as the end of a valid word
        curr.isEndOfWord = true;
    }

    //  SEARCH OPERATION 
    // ----------------------
    //  Approach:
    // - Start from the root node.
    // - Traverse each character of the key.
    // - If any character is missing, return false.
    // - After reaching the last character, check `isEndOfWord`.
    //
    //  Time Complexity: O(L)  (L = length of the word)
    //  Space Complexity: O(1) (no extra space used)
    public static boolean search(String key) {
        Node curr = root;  // Start from the root node

        for (int level = 0; level < key.length(); level++) {
            int idx = key.charAt(level) - 'a';  // Convert character to index (0-25)

            // If the node does not exist, the word is not present
            if (curr.children[idx] == null) {
                return false;
            }

            // Move to the next node
            curr = curr.children[idx];
        }

        // Return true only if the last node is marked as the end of a valid word
        return curr.isEndOfWord;
    }

    //  MAIN FUNCTION 
    public static void main(String[] args) {
        //  List of words to insert into the Trie
        String words[] = { "the", "a", "there", "their", "any", "thee" };

        // Insert words into Trie
        for (String word : words) {
            insert(word);
        }

        //  Search in Trie
        System.out.println(search("thee"));  //  true (word exists)
        System.out.println(search("thor"));  //  false (word does not exist)
    }
}
