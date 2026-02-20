package utils;



public class SearchData {

    private String keyword;
    private int expectedMinResults;

    public SearchData(String keyword, int expectedMinResults) {
        this.keyword = keyword;
        this.expectedMinResults = expectedMinResults;
    }

    public String getKeyword() { return keyword; }
    public int getExpectedMinResults() { return expectedMinResults; }
}

