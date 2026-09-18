package org.skypro.skyshop.search;

public class SearchEngine {
    private final Searchable[] items;
    private int size = 0;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
    }

    public void add(Searchable item) {
        if (size < items.length) {
            items[size] = item;
            size++;
        }
    }

    public Searchable[] search(String query) {
        Searchable[] results = new Searchable[5];
        int found = 0;

        for (int i = 0; i < size && found < 5; i++) {
            Searchable item = items[i];
            if (item == null) {
                continue;
            }
            String term = item.getSearchTerm();
            if (term != null && term.contains(query)) {
                results[found] = item;
                found++;
            }
        }
        return results;
    }
}

