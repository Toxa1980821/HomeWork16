package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductBasket {
    private final Map<String, List<Product>> items;

    public ProductBasket() {
        this.items = new HashMap<>();
    }

    public void addProduct(Product product) {
        items.computeIfAbsent(product.getName(), k -> new ArrayList<>())
                .add(product);
    }

    public int getTotalPrice() {
        return items.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    private long getSpecialCount() {
        return items.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public void printBasket() {
        if (items.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        items.values().stream()
                .flatMap(List::stream)
                .forEach(System.out::println);

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    public boolean containsProductByName(String name) {
        return items.containsKey(name);
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = items.remove(name);
        if (removed == null) {
            return new ArrayList<>();
        }
        return removed;
    }

    public void clear() {
        items.clear();
    }
}
