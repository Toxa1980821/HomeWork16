package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> items;

    public SearchEngine(int capacity) {
        this.items = new ArrayList<>();
    }

    public void add(Searchable item) {
        items.add(item);
    }

    public TreeMap<String, Searchable> search(String query) {
        TreeMap<String, Searchable> results = new TreeMap<>();

        for (Searchable item : items) {
            if (item == null) {
                continue;
            }
            String term = item.getSearchTerm();
            if (term != null && term.contains(query)) {
                results.put(item.getName(), item);   // ключ — имя → дубликаты схлопнутся
            }
        }
        return results;
    }

    public Searchable searchBest(String search) throws BestResultNotFound {
        Searchable best = null;
        int maxCount = 0;

        for (Searchable item : items) {
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


