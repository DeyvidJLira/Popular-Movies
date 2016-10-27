package br.com.deyvidjlira.popularmovies.data.models;

import java.util.List;

/**
 * Created by Deyvid on 25/10/2016.
 */
public class ResponseAPI<T> {
    private int page;
    private List<T> results;
    private int totalPages;
    private int totalResults;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }
}
