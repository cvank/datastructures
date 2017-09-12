package com.playarea.datastructures.trie;


import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Chandu on 9/11/2017.
 */
public class TrieNode {

    private Map<Character, TrieNode> children;
    private boolean isTerminated = false;

    private char character;

    public TrieNode() {
    }

    public TrieNode(Map<Character, TrieNode> children) {
        this.children = children;
    }

    public TrieNode(char character) {
        this.character = character;
    }

    public char getCharacter() {
        return character;
    }

    public Map<Character, TrieNode> getChildren() {
        return children;
    }


    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }


    private void addWord(String s) {
        
        
        

        char firstChar = s.charAt(0);

        TrieNode child = getChildren(firstChar);
        if (Objects.isNull(child)) {
            child = new TrieNode(firstChar);
            children.put(firstChar, child);
        }

        if (s.length() > 1)
            child.addWord(s.subSequence(1));
        else
            child.isTerminated(true);

    }

}

