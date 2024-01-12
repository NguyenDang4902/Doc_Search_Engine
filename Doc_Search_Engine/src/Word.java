import java.io.*;
import java.util.*;

public class Word {
    public static Set<String> stopWords;
    private String word;

    public Word(String word) {
        this.word = word;
    }

    public boolean isKeyword() {
        this.word = word;
        if (word == null || word.isEmpty()) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (Character.isLetter(word.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public String getPrefix() {
        String prefix = "";
        if (!Character.isLetter(word.charAt(0))) {
            prefix += word.charAt(0);
        }
        return prefix;
    }

    public String getSuffix() {
        String suffix = "";
        for (int i = 1; i < word.length(); i++) {
            if (!Character.isLetter(word.charAt(i))) {
                suffix += word.substring(i);
            }
        }
        return suffix;
    }

    public String getText() {
        String text = "";
        if (word.contains(getPrefix())) {
            word=word.replaceAll(getPrefix(), "");
        }
        if (word.contains(getSuffix())) {
            word=word.replaceAll(getSuffix(), "");
        }

        text = word;
        return text;
    }

    public boolean equals(Object o) {
        Word w = (Word) o;
        if (w.getText().equalsIgnoreCase(this.getText())) {
            return true;
        }
        return false;
    }

    public String toString() {
        return word;
    }

    public static Word createWord(String rawText) {

        return new Word(rawText);
    }

    public static boolean loadStopWords(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = br.readLine()) != null) {
                stopWords.add(line.trim());
            }
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }


}
