import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Query {
    private List<Word> keywordsList = new ArrayList<>();
    public Query(String searchPhrase) {
        String[] s = searchPhrase.split(" ");
        for (int i = 0; i < s.length; i++) {
            String text = s[i];
            if (Word.createWord(text).isKeyword() == true) {
                keywordsList.add(Word.createWord(text));
            }
        }
    }

    public List<Word> getKeywords() {
        return keywordsList;
    }

    public List<Match> matchAgainst(Doc d) {
        List<Match> list = new ArrayList<>();
        for (Word word : keywordsList) {
            int freq = (int) d.getTitle().stream().filter(word::equals).count();
            freq += d.getBody().stream().filter(word::equals).count();
            if (d.getBody().contains(word) != false) {
                Match m = new Match(d, word, freq, d.getBody().indexOf(word));
                list.add(m);
            } else if(d.getTitle().contains(word) != false) {
                Match m = new Match(d, word, freq, d.getTitle().indexOf(word));
                list.add(m);
            }
        }
        return list;
    }
}
