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

    public Searchable searchBest(String search) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = 0;

        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            if (item == null) {
                continue;
            }
            String term = item.getSearchTerm();
            if (term == null) {
                continue;
            }
            int count = countOccurrences(term, search);
            if (count > maxCount) {
                maxCount = count;
                best = item;
            }
        }

        if (best == null) {
            throw new BestResultNotFound(search);
        }
        return best;
    }

    private int countOccurrences(String str, String substring) {
        if (str == null || substring == null || substring.isEmpty()) {
            return 0;
        }
        int count = 0;
        int index = 0;
        int idx;
        while ((idx = str.indexOf(substring, index)) != -1) {
            count++;
            index = idx + substring.length();
        }
        return count;
    }
}