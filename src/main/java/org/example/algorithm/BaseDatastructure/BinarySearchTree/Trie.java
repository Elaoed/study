package org.example.algorithm.BaseDatastructure.BinarySearchTree;

/**
 * 208. Implement Trie
 */
public class Trie {

    // 原来是用来标识 是否存在这个值的？
    private boolean isEnd;

    private Trie[] alphabet;

    public Trie() {
        alphabet = new Trie[26];
        isEnd = false;
    }

    // 忘了本身就有这个值吗
    public void insert(String word) {
        Trie trie = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';
            if (trie.alphabet[index] == null) {
                trie.alphabet[index] = new Trie();
            }
            trie = trie.alphabet[index];
        }
        trie.isEnd = true;
    }

    public boolean search(String word) {
        Trie trie = searchPrefix(word);
        return trie != null && trie.isEnd;

    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    public Trie searchPrefix(String prefix) {

        // 顺序通过什么方式来调整
        Trie trie = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            trie = trie.alphabet[index];
            if (trie == null) {
                return null;
            }
        }
        return trie;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();
        trie.insert("apple");
        System.out.println(trie.search("apple"));
        System.out.println(trie.search("app"));
    }

}