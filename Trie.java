import java.util.ArrayList;


public class Trie {

    //alphabet size (#of symbols)
    static final int alpha_size = 26;

    //Trie Node
    static class TrieNode {

        //array of pointers/ letters the node points to
        TrieNode[] children = new TrieNode[alpha_size];

        //isEnd is true if the node represents a letter last in a word
        boolean isEnd;

        TrieNode() {
            isEnd = false;
            for (int i = 0; i < alpha_size; i++) {
                children[i] = null;
            }
        }
    }

    //the root of the TrieTree
    static TrieNode root;

    // constructor for new trie
    public Trie() {
        root = new TrieNode();
    }


    //Insert function for the Trie, if the key is not present in the tree it will insert the key,
    // if the key is present it will mark the last letter of the key as a leaf node in the existing word
    // source: geeksforgeeks

    static void insert(String key) {
        int level;
        int length = key.length();
        int index;

        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (index >= 0) {
                if (pCrawl.children[index] == null) {
                    pCrawl.children[index] = new TrieNode();
                }
                pCrawl = pCrawl.children[index];
            }

        }
        //mark last node as leaf
        pCrawl.isEnd = true;
    }

    //returns true if key is in the trie, else false
    //source: geeksforgeeks

    static boolean search(String key) {
        int level;
        int length = key.length();
        int index;
        TrieNode pCrawl = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (index >= 0) {
                if (pCrawl.children[index] == null) {
                    pCrawl.children[index] = new TrieNode();
                }
                pCrawl = pCrawl.children[index];
            }
        }
        return (pCrawl != null && pCrawl.isEnd);
    }

    //puts the dictionary into a trie structure
    public void insertDictIntoTrie(ArrayList<String> dict) {
        for (int i = 0; i < dict.size() - 1; i++) {
            insert(dict.get(i));
        }
    }


    // Checks the Trie to see if the words from input.txt are misspelled
    public void SpellCheckInputWords (ArrayList<String> wordlist) {

        //loops through list of words from input.txt
        for (int i = 0; i < wordlist.size(); i++) {
            String current = wordlist.get(i);

            //if the word matches an entry in the dictionary write it to the output file
            if (search(current)) {
                ReadandWriteFiles.writeOutput("output.txt", current);
            } else {
                // if the word is misspelled, look for suggestions
                suggestions(current);
            }
        }
    }

    public void suggestions(String word) {
        boolean done = false;

        // letters lowercase to be able to find match
        word = word.toLowerCase();

        char[] wordArr = word.toCharArray();

        //look for matches of the word minus its last letter
        int length = wordArr.length -1;

        while (done == false) {

            // create char array for new shortened word
            char[] newArr = new char[length];


           //fill char array with new shortened word
            for (int i = 0; i < length; i++) {
                newArr[i] = wordArr[i];
            }

            //make the new char array a string
            String newSearch = String.valueOf(newArr);

            //if this new word is found in the dictionary, suggest it
            if (search(newSearch)) {
                ReadandWriteFiles.writeOutput("output.txt", newSearch);
                done = true;
            }
            //if not try subtracting the last letter of the new word

            length--;
        }

    }

}






















