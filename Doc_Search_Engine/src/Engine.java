import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Engine {
    private Doc[] docsList;
    private int countDoc;
    private List<Result> searchResult;

    public int loadDocs(String dirname) {
        File[] filesList = (new File(dirname)).listFiles();
        countDoc = filesList.length;
        docsList = new Doc[countDoc];
        int i = 0;
        while (i < countDoc) {
            try {
                BufferedReader br = new BufferedReader(new FileReader(filesList[i]));
                String line1 = br.readLine();
                String line2 = br.readLine();
                String newLine = line1 + "\n" + line2;
                docsList[i] = new Doc(newLine.trim());
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            i++;
        }
        return countDoc;
    }

    public Doc[] getDocs() {
        return docsList;
    }

    public List<Result> search(Query q) {
        searchResult = new ArrayList<>();
        for (Doc d : docsList) {
            List<Match> matches = q.matchAgainst(d);
            if (matches.size() > 0) {
                Result r = new Result(d, matches);
                searchResult.add(r);
            }
        }
        Collections.sort(searchResult);
        return searchResult;
    }

    public String htmlResult(List<Result> results) {
        StringBuilder sb = new StringBuilder();
        for (Result r : results) {
            sb.append(r.htmlHighlight());
        }
        return sb.toString();
    }
}
