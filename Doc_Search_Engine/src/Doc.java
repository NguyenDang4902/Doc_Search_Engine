import java.util.ArrayList;
import java.util.List;

public class Doc {
    private String titleText;
    private String bodyText;

    public Doc(String content) {
        String[] split = content.split("\n");
        this.titleText = split[0];
        this.bodyText = split[1];
    }

    public List<Word> getTitle() {
        List<Word> titleList = new ArrayList<>();
        String[] t = titleText.split(" ");
        for (int i = 0; i < titleText.length(); i++) {
            String text = t[i];
            Word w = Word.createWord(text);
            titleList.add(w);
        }
        return titleList;
    }

    public List<Word> getBody() {
        List<Word> bodyList = new ArrayList<>();
        String[] t = bodyText.split(" ");
        for (int i = 0; i < bodyText.length(); i++) {
            String text = t[i];
            Word w = Word.createWord(text);
            bodyList.add(w);
        }
        return bodyList;
    }

    public boolean equals(Object o) {
        Doc doc = (Doc) o;
        int docTitleSize = doc.getTitle().size();
        int docBodySize = doc.getBody().size();
        if (this.getTitle().size() != docTitleSize || this.getBody().size() != docBodySize) {
            return false;
        } else {
            for (int i = 0; i < docTitleSize; i++) {
                if (this.getTitle().get(i) != doc.getTitle().get(i)) {
                    return false;
                }
            }
            for (int i = 0; i < docBodySize; i++) {
                if (this.getBody().get(i) != doc.getBody().get(i)) {
                    return false;
                }
            }
        }
        return true;
    }
}
