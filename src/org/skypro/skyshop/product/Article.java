package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public class Article implements Searchable {
    private final String title;
    private final String text;

    public Article(String title, String text) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException(
                    "Название статьи не может быть пустым, null или состоять только из пробелов"
            );
        }
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException(
                    "Текст статьи не может быть пустым, null или состоять только из пробелов"
            );
        }
        this.title = title;
        this.text = text;
    }

    @Override
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getContentType() {
        return "ARTICLE";
    }

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String toString() {
        return title + "\n" + text;
    }
}