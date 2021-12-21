package com.example.junithelloworld.search;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TextSearch {
    private String expectedText;
    private List<TextMatch> textMatches;
    private Exception exception = null;
    private int numberOfSurroundingCharacters = 100;
    private InputStream searchContext;
    private String searchTitle;

    public TextSearch(String searchTitle, String expectedText, 
    InputStream searchContext) {
        this.searchTitle = searchTitle;
        this.expectedText = expectedText;
        this.searchContext = searchContext;
        textMatches = new ArrayList<>();
    }

    public List<TextMatch> getTextMatches() {
        return textMatches;
    }

    public Exception getError() {
        return exception;
    }

    public boolean hasError() {
        return exception != null;
    }

    public void setNumberOfSurroundingCharacters(int number) {
        numberOfSurroundingCharacters = number;
    }

    public void execute() {
        try {
            search();
        } catch (Exception e) {
            exception = e;
        }
    }
    
    private void search() throws Exception {
        Pattern pattern = Pattern.compile(expectedText);
        BufferedReader searchContextReader = new BufferedReader(
            new InputStreamReader(searchContext));
        textMatches.clear();
        searchContextReader.lines().forEach(line -> 
            addTextMatches(pattern, line));
        searchContextReader.close();
    }

    private void addTextMatches(Pattern pattern, String line) {
        Matcher matcherInLine = pattern.matcher(line);
        while (matcherInLine.find()) {
            String surroundingContext = 
                extractSurroundingContext(matcherInLine, line);
            textMatches.add(new TextMatch(searchTitle, 
                expectedText, surroundingContext));
        }
    }

    private String extractSurroundingContext(Matcher matcherInLine, 
    String line) {
        int start = matcherInLine.start();
        int end = matcherInLine.end();
        int lineLength = line.length();
        start -= numberOfSurroundingCharacters;
        if (start < 0) start = 0;
        end += numberOfSurroundingCharacters;
        if (end > lineLength) end = lineLength;
        return line.substring(start, end);
    }
}