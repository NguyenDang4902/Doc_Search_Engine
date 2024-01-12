public class Match implements Comparable<Match> {
    private Word w;
    private int freq;
    private int firstIndex;

    public Match(Doc d, Word w, int freq, int firstIndex) {
        this.w = w;
        this.freq = freq;
        this.firstIndex = firstIndex;
    }

    public int getFreq() {
        return freq;
    }

    public int getFirstIndex() {
        return firstIndex;
    }

    public Word getWord() {
        return w;
    }

    public int compareTo(Match o) {
        if (this.getFirstIndex() > o.getFirstIndex()) {
            return 1;
        }
        if (this.getFirstIndex() == o.getFirstIndex()) {
            return -1;
        }
        return 0;
    }
}
