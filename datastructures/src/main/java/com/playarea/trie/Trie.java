package com.playarea.trie;

import org.apache.commons.lang3.BooleanUtils;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.Predicate;

/**
 * Created by Chandu on 9/11/2017.
 */
public class Trie {

    private TrieNode root;

    public Trie() {
    }

    public Trie(String[] words) {
        root = new TrieNode();
        Arrays.stream(words).parallel().forEach(word -> root.addWord(word));
    }


    public Trie(List<String> words) {
        root = new TrieNode();
        words.parallelStream().forEach(word -> root.addWord(word));
    }

    public TrieNode getRoot() {
        return root;
    }

    public List<String> fetch(String prefix, final boolean caseSensitive) {
        prefix = prefix.toLowerCase();
        TrieNode lastNode = root;

        StringBuilder prefixActual = new StringBuilder();
        for (int i = 0; i < prefix.length(); i++) {
            lastNode = lastNode.getChildNode(prefix.charAt(i));
            if (Objects.isNull(lastNode)) {
                return null;
            }

            prefixActual.append(lastNode.getDisplayCharacter());
        }
        suggestions(prefixActual.toString(), lastNode);
        suggestWords.forEach(System.out::println);
        return suggestWords;
    }

    CopyOnWriteArrayList<String> suggestWords = new CopyOnWriteArrayList<>();


    private void suggestions(final String prefix, TrieNode parent) {
        if (parent.isTerminated())
            suggestWords.add(prefix);

        if (parent.getChildren().size() == 0)
            return;

        for (Map.Entry<Character, TrieNode> entry : parent.getChildren().entrySet()) {
            suggestions(prefix + entry.getValue().getDisplayCharacter(), entry.getValue());

        }
    }


}
