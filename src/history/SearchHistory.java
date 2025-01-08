package history;

/*
* Originator	GuestInterface	Creates and uses SearchMemento.
* Memento	    SearchMemento	Stores the search filter and results.
* Caretaker 	SearchHistory	Maintains a collection of SearchMemento.
*/

import java.util.ArrayList;
import java.util.List;

public class SearchHistory {
    private final List<SearchMemento> history = new ArrayList<>();

    public void addMemento(SearchMemento memento) {
        history.add(memento);
    }

    public List<SearchMemento> getHistory() {
        return List.copyOf(history);
    }
}

