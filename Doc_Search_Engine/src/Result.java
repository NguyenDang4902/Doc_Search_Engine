import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Result implements Comparable<Result> {
    private Doc d;
    private List<Match> matches = new ArrayList<>();
    private int totalFreq;
    private double avgFirstIndex;

    public Result(Doc d, List<Match> matches) {
        int tmp = 0;
        this.d = d;
        this.matches = matches;
        for (int i = 0; i < matches.size(); i++) {
            tmp += matches.get(i).getFirstIndex();
            totalFreq += matches.get(i).getFreq();
        }
        avgFirstIndex = tmp / matches.size();
    }

    public Doc getDoc() {
        return d;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public int getTotalFrequency() {
        return totalFreq;
    }

    public double getAverageFirstIndex() {
        return 0;
    }

    public String htmlHighlight() {
        String bodyResult = "";
        String titleResult = "";
        List<Word> titleText = new ArrayList<>(getDoc().getTitle());
        List<Word> bodyText = new ArrayList<>(getDoc().getBody());
        for (Match m : matches) {
            for (Word word : titleText) {
                if (m.getWord().equals(word)) {
                    int index = titleText.indexOf(word);
                    String s = word.getPrefix() + "<u>" + word.getText() + "</u>" + word.getSuffix();
                    Word newWord = Word.createWord(s);
                    titleText.set(index,newWord);
                }
            }
            for (Word word : bodyText) {
                if (m.getWord().equals(word)) {
                    int index = bodyText.indexOf(word);
                    String s = word.getPrefix() + "<b>" + word.getText() + "</b>" + word.getSuffix();
                    Word newWord = Word.createWord(s);
                    bodyText.set(index,newWord);
                }
            }
        }
        String title = "";
        for (int i = 0; i < titleText.size();i++) {
            title += titleText.get(i).toString() + " ";
        }
        String body = "";
        for (int i = 0; i < bodyText.size();i++) {
            body += bodyText.get(i).toString() + " ";
        }
        titleResult = "<h3>" + title + "</h3>";
        bodyResult = "<p>" + body + "</p>";

        return titleResult.trim() + bodyResult.trim();
    }

    public int compareTo(Result o) {
        if (this.getMatches().size() > o.getMatches().size()) {
            return 1;
        } else if (this.getMatches().size() == o.getMatches().size()) {
            if (this.getTotalFrequency() != o.getTotalFrequency()) {
                if (this.getTotalFrequency() < o.getTotalFrequency()) {
                    return -1;
                } else {
                    return 1;
                }
            } else {
                if (this.getAverageFirstIndex() != o.getAverageFirstIndex()) {
                    if (this.getAverageFirstIndex() > o.getAverageFirstIndex()) {
                        return 1;
                    } else {
                        return -1;
                    }
                } else {
                    return 0;
                }
            }
        }
        return -1;
    }
}
