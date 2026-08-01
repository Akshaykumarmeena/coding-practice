Implement a Trie (prefix tree) with the following methods:

void insert(String word) — inserts the string word into the trie
boolean search(String word) — returns true if the exact string word is in the trie (previously inserted)
boolean startsWith(String prefix) — returns true if there is a previously inserted string that has prefix as a prefix
Example:
Trie trie = new Trie();
trie.insert("apple");
trie.search("apple");    // true
trie.search("app");      // false (only "apple" was inserted, not "app" exactly)
trie.startsWith("app");  // true (because "apple" starts with "app")
trie.insert("app");
trie.search("app");      // true (now "app" itself was inserted)


1 <= word.length, prefix.length <= 2000
word and prefix consist only of lowercase English letters
At most 3 * 10^4 calls total will be made to insert, search, and startsWith


  /*
 * Problem: Implement Trie (Prefix Tree)
 * Pattern: Trie
 * Constraints: 1 <= word/prefix.length <= 2000, lowercase English letters
 *   only, up to 3*10^4 total calls
 * Time: O(L) per operation, where L = length of the word/prefix
 * Space: O(total characters across all inserted words), worst case
 * mock-tested: pass, first attempt, clean
 * Key insight: shared prefixes share tree paths — insert walks/creates
 *   nodes character by character; search additionally requires isEndOfWord
 *   at the final node; startsWith only requires the walk to succeed,
 *   regardless of the end-flag
 */


  ```java
import java.util.*;

public class Practice{

   static class TrieNode{
        TrieNode[] children;
        boolean isEndOfWord;

        TrieNode(){
            children = new TrieNode[26];
            isEndOfWord = false;
        }
   }

   static public class Trie{

        TrieNode root;

        public Trie(){
            root = new TrieNode();
        }

        void insert(String word){
            TrieNode curr = root;

            for(char c: word.toCharArray()){

                if(curr.children[c-'a'] == null){
                    curr.children[c-'a'] = new TrieNode();
                }
                curr = curr.children[c-'a'];
            }
            curr.isEndOfWord = true;
        }

        boolean search(String word){

            TrieNode curr = root;

            for(char c: word.toCharArray()){

                if(curr.children[c-'a'] == null) return false;
                curr = curr.children[c-'a'];
            }

            return curr.isEndOfWord;

        }

        boolean startsWith(String prefix){

            TrieNode curr = root;

            for(char c: prefix.toCharArray()){
                if(curr.children[c-'a'] == null) return false;
                curr = curr.children[c-'a'];
            }

            return true;

        }



   }


    public static void main(String[] args){

        Trie trie = new Trie();
        trie.insert("apple");
        trie.search("apple");    // true
        trie.search("app");      // false (only "apple" was inserted, not "app" exactly)
        trie.startsWith("app");  // true (because "apple" starts with "app")
        trie.insert("app");
        trie.search("app");      // true (now "app" itself was inserted)

    }

}

```
