public class trie {
    trieNode root = new trieNode();

    private class trieNode {
        trieNode[] children;
        boolean eow;    //end of world check

        public trieNode() {
            children = new trieNode[26];    //a to x
            for (int i = 0; i < 26; i++) {
                children[i] = null;
            }
            eow = false;
        }
    }

    // insert string in trie
    public void insert(String word) {   //O(L); L = word.length
        trieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i)-'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new trieNode();
            }
            if (i == word.length()-1) {
                curr.children[idx].eow = true;
            }
            curr = curr.children[idx];
        }
    }

    // search for string in trie
    public boolean search(String word) {   //O(L); L = word.length
        trieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i)-'a';

            if(curr.children[idx] == null) {
                return false;
            }
            if (i == word.length()-1 && !curr.children[idx].eow) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }

    //to search if word exists as a group of strings in trie
    public boolean workBreak(String word) {
        if (word.length() == 0) {
            return true;
        }
        for (int i = 1; i <= word.length(); i++) {
            String firstspart = word.substring(0, i);
            String secpart = word.substring(i);
            if (search(firstspart) && workBreak(secpart)) {
                return true;
            }
        }
        return false;
    }

    //search for prefix exists in any string
    public boolean startsWith(String prefix) {
        trieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i)-'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }

    // counts no. of nodes = no. of unique substrings
    public int countNodes() {
        return countNodes(root);
    }
    private int countNodes(trieNode curr) {
        if (curr == null) {
            return 0;
        }
        
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if(curr.children[i] != null) {
                count += countNodes(curr.children[i]);
            }
        }
        return count+1;
    }

    // returns the longest work having all its substrings
    public String longestWord() {
        return longestWord(root, new StringBuilder(""), "");
    }
    private String longestWord(trieNode curr, StringBuilder temp, String ans) {
        if (curr == null) {
            return ans;
        }
        
        for (int i = 0; i < 26; i++) {
            if (curr.children[i] != null && curr.children[i].eow) {
                temp.append((char)(i+'a'));
                if (temp.length() > ans.length()) {
                    ans = temp.toString();
                }
                ans = longestWord(curr.children[i], temp, ans);

                temp.deleteCharAt(temp.length()-1);
            }
        }
        return ans;
    }
    public static void main(String[] args) {
        trie t = new trie();
        String words[] = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        // String key = "ilikesamsung";
        // String str = "ababa";
        
        for (String i : words) {
            t.insert(i);
        }

        // System.out.println(t.countNodes());

        // System.out.println(t.search("aba"));
        // System.out.println(t.startsWith("bac"));

        System.out.println(t.longestWord());
    }
}
