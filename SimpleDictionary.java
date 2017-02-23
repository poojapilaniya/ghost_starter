package com.example.pooja.ghost_starter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class SimpleDictionary implements GhostDictionary {
    private ArrayList<String> words;

    public SimpleDictionary(InputStream wordListStream) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(wordListStream));
        words = new ArrayList<>();
        String line = null;
        while((line = in.readLine()) != null) {
            String word = line.trim();
            if (word.length() >= MIN_WORD_LENGTH)
                words.add(line.trim());
        }
    }

    @Override
    public boolean isWord(String word) {
        return words.contains(word);
    }

    @Override
    public String getAnyWordStartingWith(String prefix) {

        if(prefix.equals(""))
        {
            Random r= new Random();
            int temp= r.nextInt(words.size());
            return words.get(temp);
        }
        else
        {
            return bs(0,words.size()-1,prefix);
        }
    }

    private String bs(int start, int end,String key)
    {
        int mid= (start+end)/2;
        if(end>start) {
            String midstr = words.get(mid);
            if (midstr.length() > key.length() && midstr.startsWith(key)) {
                return midstr;
            } else if (midstr.compareTo(key) < 0) {
                return bs(mid + 1, end, key);

            } else if (midstr.compareTo(key) > 0) {
                return bs(start, mid - 1, key);
            }
            return words.get(mid);
        }
        else
            return null;
    }

    @Override
    public String getGoodWordStartingWith(String prefix) {
        String selected = null;
        return selected;
    }
}
