package com.virtuslab.internship.api;

import com.virtuslab.internship.application.BasketService;
import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.product.ProductRequest;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BasketServiceTest {

    @Test
    void createBasketShouldMakeItAvailableToAddProductsToBasket(){
        // Given
        var basketService = new BasketService();

        // When
        basketService.createBasket();

        // Then
        basketService.addProduct(0, new ProductRequest("Steak"));
    }

    @Test
    void removeProductShouldReturnTrueWhenProductWasDeleted(){
        // Given
        var basketService = new BasketService();
        basketService.createBasket();
        basketService.addProduct(0, new ProductRequest("Steak"));

        // When Then
        assertTrue(basketService.removeProduct(0, new ProductRequest("Steak")));
        assertEquals(0, basketService.getProducts(0).size());
    }

    @Test
    void removeProductShouldReturnFalseWhenProductWasDeleted(){
        // Given
        var basketService = new BasketService();
        basketService.createBasket();

        // When Then
        assertFalse(basketService.removeProduct(0, new ProductRequest("Steak")));
        assertEquals(0, basketService.getProducts(0).size());
    }

    @Test
    void removeProductShouldThrowIndexOutOfBoundsExceptionWhenThereIsNoBasket(){
        // Given
        var basketService = new BasketService();

        // When Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            basketService.removeProduct(0, new ProductRequest("Steak"));
        });
    }

    @Test
    void getProductsShouldReturnAddedProductsWhenProductsAdded(){
        // Given
        var basketService = new BasketService();
        var productsDb = new ProductDb();
        basketService.createBasket();
        basketService.addProduct(0, new ProductRequest("Steak"));
        basketService.addProduct(0, new ProductRequest("Bread"));

        // When
        var products = basketService.getProducts(0);

        // Then
        assertEquals(2, basketService.getProducts(0).size());
        assertEquals(productsDb.getProduct("Steak"), products.get(0));
        assertEquals(productsDb.getProduct("Bread"), products.get(1));
    }

    @Test
    void getProductsShouldThrowIndexOutOfBoundsExceptionWhenThereIsNoBasket(){
        // Given
        var basketService = new BasketService();

        // When Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            basketService.getProducts(0);
        });
    }

    @Test
    void getReceiptShouldReturnCorrectReceiptWhenThereIsBasket(){
        // Given
        var basketService = new BasketService();
        var receiptGenerator = new ReceiptGenerator();
        basketService.createBasket();
        basketService.addProduct(0, new ProductRequest("Steak"));
        basketService.addProduct(0, new ProductRequest("Bread"));

        var basketToMatch = new Basket();
        var productsDb = new ProductDb();
        basketToMatch.addProduct(productsDb.getProduct("Steak"));
        basketToMatch.addProduct(productsDb.getProduct("Bread"));

        Receipt expectedReceipt = receiptGenerator.generate(basketToMatch);

        // When
        Receipt receipt = basketService.getReceipt(0);

        // Then
        assertEquals(expectedReceipt, receipt);
    }

    @Test
    void getReceiptShouldThrowIndexOutOfBoundsExceptionWhenThereIsNoBasket(){
        var basketService = new BasketService();

        // When Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            basketService.getReceipt(0);
        });
    }
}
