package org.skypro.skyshop.search;

import java.util.Comparator;

public class SearchResultComparator implements Comparator<Searchable> {

    @Override
    public int compare(Searchable first, Searchable second) {
        int byLength = Integer.compare(second.getName().length(), first.getName().length());

        if (byLength != 0) {
            return byLength;
        }

        return first.getName().compareTo(second.getName());
    }
}

