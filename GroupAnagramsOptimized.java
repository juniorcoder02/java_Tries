import java.util.*;

public class GroupAnagramsOptimized {
    public static List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) return new ArrayList<>();

        Map<String, List<String>> map = new HashMap<>();

        for (String word : strs) {
            // Create frequency count for the word
            int[] count = new int[26]; // Since input contains only lowercase letters
            for (char c : word.toCharArray()) {
                count[c - 'a']++; // Increment frequency of the character
            }

            // Convert count array to a string key
            String key = Arrays.toString(count);

            // Group words based on the key
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(word);
        }

        // Return the grouped anagrams
        return new ArrayList<>(map.values());
    }

    public static void main(String[] args) {
        String[] strs1 = {"eat","tea","tan","ate","nat","bat"};
        String[] strs2 = {""};
        String[] strs3 = {"a"};

        System.out.println(groupAnagrams(strs1));
        System.out.println(groupAnagrams(strs2));
        System.out.println(groupAnagrams(strs3));
    }
}
