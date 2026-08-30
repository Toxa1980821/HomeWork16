package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        // 1. Добавляем несколько продуктов
        Product p1 = new SimpleProduct("Ноутбук", 50000);
        Product p2 = new DiscountedProduct("Мышь", 1500, 20);
        Product p3 = new FixPriceProduct("Клавиатура");
        Product p4 = new SimpleProduct("Монитор", 20000);
        Product p5 = new DiscountedProduct("Наушники", 2500, 10);

        basket.addProduct(p1);
        basket.addProduct(p2);
        basket.addProduct(p3);
        basket.addProduct(p4);
        basket.addProduct(p5);

        Product p6 = new SimpleProduct("Веб-камера", 4000);
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



