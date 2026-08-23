package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

public class ProductBasket {
    private final Product[] items;

    public ProductBasket() {

        this.items = new Product[5];

    }

    public void addProduct(Product product) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] == null) {
                items[i] = product;
                return;
            }
        }
        System.out.println("Невозможно добавить продукт");
    }

    public int getTotalPrice() {
        int total = 0;
        for (Product p : items) {
            if (p != null) {
                total += p.getPrice();
            }
        }
        return total;
    }

    public void printBasket() {
        boolean hasItems = false;
        for (Product p : items) {
            if (p != null) {
                hasItems = true;
                System.out.println(p.getName() + ": " + p.getPrice());
            }
        }

        if (!hasItems) {
            System.out.println("в корзине пусто");
            return;
        }

        System.out.println("Итого: " + getTotalPrice());
    }

    public boolean containsProductByName(String name) {
        for (Product p : items) {
            if (p != null && p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clear() {
        for (int i = 0; i < items.length; i++) {
            items[i] = null;
        }
    }
}