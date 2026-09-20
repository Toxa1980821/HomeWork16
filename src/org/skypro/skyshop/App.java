package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import org.skypro.skyshop.search.BestResultNotFound;

import java.util.List;

public class App {
    public static void main(String[] args) {
        // --- Корзина ---
        ProductBasket basket = new ProductBasket();

        Product p1 = new SimpleProduct("Ноутбук", 50000);
        Product p2 = new DiscountedProduct("Мышь", 1500, 20);
        Product p3 = new FixPriceProduct("Клавиатура");
        Product p4 = new SimpleProduct("Монитор", 20000);
        Product p5 = new DiscountedProduct("Наушники", 2500, 10);
        Product p6 = new SimpleProduct("Веб-камера", 4000);

        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);
        basket.addProduct(p4);
        basket.addProduct(p5);
        basket.addProduct(p6);

        // Демонстрация: добавили 6 продуктов — без ошибки, список не ограничен
        basket.printBasket();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Есть ли 'Ноутбук' в корзине? " + basket.containsProductByName("Ноутбук"));
        System.out.println("Есть ли 'Принтер' в корзине? " + basket.containsProductByName("Принтер"));

        // --- Демонстрация removeProductsByName ---
        System.out.println("\n=== УДАЛЕНИЕ ПРОДУКТОВ ===");

        // Сценарий 1: удаляем существующий продукт
        List<Product> removed = basket.removeProductsByName("Мышь");
        System.out.println("Удалённые продукты:");
        for (Product p : removed) {
            System.out.println(p);
        }
        System.out.println("Содержимое корзины после удаления:");
        basket.printBasket();

        // Сценарий 2: удаляем несуществующий продукт
        List<Product> removedEmpty = basket.removeProductsByName("Принтер");
        if (removedEmpty.isEmpty()) {
            System.out.println("\nСписок пуст");
        }
        System.out.println("Содержимое корзины:");
        basket.printBasket();

        basket.clear();
        basket.printBasket();
        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());
        System.out.println("Есть ли 'Ноутбук' в пустой корзине? " + basket.containsProductByName("Ноутбук"));

        // --- Демонстрация проверок данных ---
        System.out.println("\n=== ТЕСТИРОВАНИЕ ПРОВЕРОК ===");

        try {
            new SimpleProduct("   ", 1000);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new DiscountedProduct(null, 1000, 10);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new SimpleProduct("Монитор", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new DiscountedProduct("Планшет", -500, 15);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            new DiscountedProduct("Планшет", 5000, 150);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // --- Поиск ---
        System.out.println("\n=== ТЕСТИРОВАНИЕ ПОИСКА ===");

        SearchEngine engine = new SearchEngine(20);

        engine.add(p1);
        engine.add(p2);
        engine.add(p3);
        engine.add(p4);
        engine.add(p5);
        engine.add(p6);

        Article a1 = new Article(
                "Как выбрать ноутбук",
                "Ноутбук ноутбук ноутбук — выбирай с умом. Ноутбук должен подходить вам."
        );
        Article a2 = new Article(
                "Лучшие мыши для работы",
                "Эргономичные мыши снижают нагрузку на кисть."
        );
        Article a3 = new Article(
                "Фикс-прайс: что это значит",
                "Товары с фиксированной ценой удобны для бюджета."
        );

        engine.add(a1);
        engine.add(a2);
        engine.add(a3);

        testSearch(engine, "ноутбук");
        testSearch(engine, "мышь");

        // --- searchBest ---
        System.out.println("\n=== ТЕСТИРОВАНИЕ searchBest ===");

        try {
            Searchable best = engine.searchBest("ноутбук");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            Searchable best = engine.searchBest("несуществующий_запрос");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void testSearch(SearchEngine engine, String query) {
        System.out.println("\nПоиск по запросу: \"" + query + "\"");
        List<Searchable> results = engine.search(query);
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено");
        } else {
            for (Searchable s : results) {
                System.out.println(s.getStringRepresentation());
            }
        }
    }
}