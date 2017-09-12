package com.playarea.datastructures.trie;

import java.util.Arrays;

/**
 * Created by Chandu on 9/11/2017.
 */
public class Trie {

    private TrieNode root;


    public Trie(String[] words) {
        this.root = new TrieNode();
        Arrays.stream(words).forEach(w->root.addWord(w));
    }


}
