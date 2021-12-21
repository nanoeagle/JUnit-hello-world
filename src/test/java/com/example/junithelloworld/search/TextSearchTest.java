package com.example.junithelloworld.search;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.junit.*;

public class TextSearchTest {
    private static final String A_TITLE = "1";

    private InputStream searchContext;

    @After
    public void closeResources() throws IOException {
        searchContext.close();
    }

    @Test
    public void searchResultsInExpectedMatchesWhenSearchedTextInContext()
    throws IOException {
        createSearchContextFromFixedContent();
        List<TextMatch> expectedMatches = createExpectedMatches();
        TextSearch search = new TextSearch(
            A_TITLE, "searched text", searchContext);
        search.setNumberOfSurroundingCharacters(10);
        search.execute();
        assertTrue(search.getTextMatches().containsAll(expectedMatches));
    }

    private void createSearchContextFromFixedContent() {
        String fixedContent = "rest of the content here" 
            + "9876543210searched text0123456789" 
            + "more rest of the content here";
        searchContext = new ByteArrayInputStream(fixedContent.getBytes());
    }

    private List<TextMatch> createExpectedMatches() {
        List<TextMatch> expectedMatches = new ArrayList<>();
        expectedMatches.add(new TextMatch(A_TITLE, "searched text",
            "9876543210searched text0123456789"));
        return expectedMatches;
    }

    @Test
    public void searchResultsInNoMatchesWhenSearchedTextNotInContext() {
        createSearchContextFromFixedContent();
        TextSearch search = new TextSearch(A_TITLE, 
            "text that doesn't match", searchContext);
        search.execute();
        assertTrue(search.getTextMatches().isEmpty());
    }
    
    @Test
    public void searchHasErrorWhenUnableToReadInstream() {
        searchContext = createInstreamThrowingErrorWhenRead();
        TextSearch search = new TextSearch("", "", searchContext);
        search.execute();
        assertTrue(search.hasError());
    }

    private InputStream createInstreamThrowingErrorWhenRead() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                throw new IOException();
            }
        };
    }

    @Test
    public void searchDoesNotHaveErrorWhenReadingInstreamSucceeds() {
        createSearchContextFromFixedContent();
        TextSearch search = new TextSearch("", "", searchContext);
        search.execute();
        assertFalse(search.hasError());
    }
}