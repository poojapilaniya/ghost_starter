package com.example.pooja.ghost_starter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Random;

public class TrieNode {
    private HashMap<String, TrieNode> children;
    private boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
    }

    public void add(String s) {
        int l;
        TrieNode currentword = this;
         for(int i=0;i<s.length();i++){
             String key = String.valueOf(s.charAt(i));

           /*  if(){

             }else(){

             }*/
         }


    }

    public boolean isWord(String s) {
        //traverse from current node until word mismatch
        int i;
        TrieNode currentNode = this;
        for (i = 0; i < s.length(); i++) {
            String key = String.valueOf(s.charAt(i));
            if (children.containsKey(key)) {
                currentNode = currentNode.children.get(key);
            } else
                return false;

        }
    }
    public String getAnyWordStartingWith(String s) {
        TrieNode currentNode =this;
        Set KeyList;
        String Key;
        String word = "";
        if(!s.isEmpty()){
            for(int i=0;i<s.length();i++){
                 String key = String.valueOf(s.charAt(i));
                 word += key;
            if (children.containsKey(key)) {
                currentNode = currentNode.children.get(key);
            } else
                return null;
            }
        }
        while (!currentNode.isWord) {
            String key;
            Set<String> keyList;
            keyList = currentNode.children.keySet();
            switch (key = keyList.toArray()[new Random().nextInt(keyList.size())].toString())
            word += key;
            currentNode = currentNode.children.get(key);
        }


        return word;
    }
        // traverse until isword == true
        //check first word ,if present then traverse next else rejected
        // check character by character to full word then traverse



    public String getGoodWordStartingWith(String s)
    {
        return null;
    }
}
