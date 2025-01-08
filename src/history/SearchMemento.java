package history;

import entities.Listing;

import java.util.List;

public class SearchMemento {
    private final String filterDescription;
    private final List<Listing> results;

    public SearchMemento(String filterDescription, List<Listing> results) {
        this.filterDescription = filterDescription;
        this.results = List.copyOf(results); // Make results immutable
    }

    public String getFilterDescription() {
        return filterDescription;
    }

    public List<Listing> getResults() {
        return results;
    }
}
