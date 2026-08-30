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

    public int getSpecialCount() {
        int count = 0;
        for (Product p : items) {
            if (p != null && p.isSpecial()) {
                count++;
            }
        }
        return count;

    }

    public void printBasket() {
        boolean hasItems = false;
        for (Product p : items) {
            if (p != null) {
                hasItems = true;
                System.out.println(p);
            }
        }

        if (!hasItems) {
            System.out.println("в корзине пусто");
            return;
        }

        System.out.println("Итого: " + getTotalPrice());
        System.out.println("Специальных товаров: " + getSpecialCount());
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

