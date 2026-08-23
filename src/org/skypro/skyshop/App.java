package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        // 1. Добавляем несколько продуктов
        Product p1 = new Product("Ноутбук", 50000);
        Product p2 = new Product("Мышь", 1500);
        Product p3 = new Product("Клавиатура", 3000);
        Product p4 = new Product("Монитор", 20000);
        Product p5 = new Product("Наушники", 2500);

        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);
        basket.addProduct(p4);
        basket.addProduct(p5);

        Product p6 = new Product("Веб-камера", 4000);
        basket.addProduct(p6);

        basket.printBasket();

        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        System.out.println("Есть ли 'Ноутбук' в корзине? " + basket.containsProductByName("Ноутбук"));

        System.out.println("Есть ли 'Принтер' в корзине? " + basket.containsProductByName("Принтер"));

        basket.clear();

        basket.printBasket();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalPrice());

        System.out.println("Есть ли 'Ноутбук' в пустой корзине? " + basket.containsProductByName("Ноутбук"));
    }
}



