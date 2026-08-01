
Constraints:

1 <= word.length <= 25
word consists of lowercase English letters
2 <= n <= 10^4 where n is the number of calls to addWord and search
At most 500 calls will be made to addWord

Design a data structure that supports adding new words and finding if a string matches any previously added string.

Implement the WordDictionary class:

void addWord(word) — adds word to the data structure
boolean search(word) — returns true if there is a string in the data structure that matches word. word may contain dots '.' where a '.' can match any single letter.
Example:
WordDictionary wd = new WordDictionary();
wd.addWord("bad");
wd.addWord("dad");
wd.addWord("mad");
wd.search("pad"); // false
wd.search("bad"); // true
wd.search(".ad"); // true (the '.' matches 'b', 'd', or 'm' — any of them works)
wd.search("b.."); // true (the '.' matches any letter twice, and "bad" fits: b-a-d)




/*
 * Problem: Design Add and Search Words Data Structure
 * Pattern: Trie + Backtracking/DFS (recursive branching for wildcard '.')
 * Constraints: 1 <= word.length <= 25, up to 10^4 total calls, up to 500
 *   addWord calls, lowercase letters + '.' wildcard in search
 * Time: O(26^d) worst case for search (d = number of dots, since each dot
 *   branches into up to 26 possibilities), O(L) for addWord
 * Space: O(total characters inserted)
 * mock-tested: multiple rounds — off-by-one base case, missing return
 *   propagation in the wildcard branch, and critically, mutating the
 *   shared `node` variable inside the 26-way branching loop (which
 *   corrupted subsequent loop iterations by checking children-of-a-child
 *   instead of children-of-the-original-node)
 * Key insight: exact-letter matching walks a single path (like standard
 *   Trie search); wildcard '.' requires trying ALL 26 children
 *   independently via recursion — pass node.children[i] directly as the
 *   argument to each recursive call, never reassign the loop's own node
 *   variable, or sibling branches get corrupted by whichever branch was
 *   tried first
 */

static public class WordDictionary{
    TrieNode root;

    public WordDictionary(){
        root = new TrieNode();
    }

    void addWord(String word){
        TrieNode curr = root;
        for(char c : word.toCharArray()){
            if(curr.children[c-'a'] == null){
                curr.children[c-'a'] = new TrieNode();
            }
            curr = curr.children[c-'a'];
        }
        curr.isEndOfWord = true;
    }

    boolean search(String word){
        return searchHelper(word, 0, root);
    }

    boolean searchHelper(String word, int index, TrieNode node){
        if(index == word.length()) return node.isEndOfWord;

        char c = word.charAt(index);

        if(c == '.'){
            for(int i = 0; i < 26; i++){
                if(node.children[i] != null){
                    if(searchHelper(word, index + 1, node.children[i])) return true;
                }
            }
            return false;
        } else {
            if(node.children[c-'a'] == null) return false;
            return searchHelper(word, index + 1, node.children[c-'a']);
        }
    }
}
