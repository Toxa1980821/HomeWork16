package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.product.Article;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;
import java.util.Arrays;

public class App {
    public static void main(String[] args) {
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

        basket.printBasket();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Есть ли 'Ноутбук' в корзине? " + basket.containsProductByName("Ноутбук"));
        System.out.println("Есть ли 'Принтер' в корзине? " + basket.containsProductByName("Принтер"));

        basket.clear();
        basket.printBasket();
        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());
        System.out.println("Есть ли 'Ноутбук' в пустой корзине? " + basket.containsProductByName("Ноутбук"));

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
                "При выборе ноутбука важно смотреть на процессор и видеокарту."
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
        testSearch(engine, "фикс");
        testSearch(engine, "видеокарта");
        testSearch(engine, "ничего");
    }

    private static void testSearch(SearchEngine engine, String query) {
        System.out.println("\nПоиск по запросу: \"" + query + "\"");
        Searchable[] results = engine.search(query);
        boolean foundAny = false;
        for (Searchable s : results) {
            if (s != null) {
                System.out.println(s.getStringRepresentation());
                foundAny = true;
            }
        }
        if (!foundAny) {
            System.out.println("Ничего не найдено");
        }
    }
}



