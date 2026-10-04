package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.Iterator;

public class ProductBasket {
    private final List<Product> items;

    public ProductBasket() {
        this.items = new LinkedList<>();
    }

    public void addProduct(Product product) {
        items.add(product);
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public int getSpecialCount() {
        int count = 0;
        for (Product p : items) {
            if (p.isSpecial()) {
                count++;
            }
        }
        return count;
    }

    public void printBasket() {
        if (items.isEmpty()) {
            System.out.println("в корзине пусто");
            return;
        }

        for (Product p : items) {
            System.out.println(p);
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
    }

    public boolean containsProductByName(String name) {
        for (Product p : items) {
            if (p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public List<Product> removeProductsByName(String name) {
        List<Product> removed = new LinkedList<>();
        Iterator<Product> iterator = items.iterator();
        while (iterator.hasNext()) {
            Product p = iterator.next();
            if (p.getName().equals(name)) {
                removed.add(p);
                iterator.remove();
            }
        }
        return removed;
    }

    public void clear() {
        items.clear();
    }
}