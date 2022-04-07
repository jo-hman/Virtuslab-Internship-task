package com.virtuslab.internship.api;

import com.virtuslab.internship.application.BasketController;
import com.virtuslab.internship.product.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasketControllerTest {

    @Test
    void createBasketShouldReturnHttpStatusCREATED(){
        // Given
        var basketController = new BasketController();

        // When
        ResponseEntity responseEntity = basketController.createBasket();

        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
    }

    @Test
    void addProductShouldReturnHttpStatusOKWhenBasketWasCreated(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;
        basketController.createBasket();

        // When
        ResponseEntity responseEntity = basketController.addProduct(basketId, new ProductRequest("Steak"));

        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void addProductShouldThrowIndexOutOfBoundsExceptionWhenThereIsNoBasket(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;

        // When Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            basketController.addProduct(basketId, new ProductRequest("Steak"));
        });
    }

    @Test
    void removeProductShouldReturnHttpStatusOKWhenThereIsProductToDelete(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;
        basketController.createBasket();
        basketController.addProduct(0, new ProductRequest("Steak"));

        // When
        ResponseEntity responseEntity = basketController.removeProduct(basketId, new ProductRequest("Steak"));

        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void removeProductShouldReturnHttpStatusNOTFOUNDWhenThereIsNoProductToDelete(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;
        basketController.createBasket();

        // When
        ResponseEntity responseEntity = basketController.removeProduct(basketId, new ProductRequest("Steak"));

        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    void removeProductShouldThrowIndexOutOfBoundsExceptionWhenThereIsNoBasket(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;

        // When Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            basketController.removeProduct(basketId, new ProductRequest("Steak"));
        });
    }

    @Test
    void getProductsShouldReturnHttpStatusOKWhenBasketWasCreated(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;
        basketController.createBasket();
        basketController.addProduct(basketId, new ProductRequest("Bread"));
        basketController.addProduct(basketId, new ProductRequest("Steak"));

        // When
        ResponseEntity responseEntity = basketController.getProducts(basketId);

        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getProductShouldThrowIndexOutOfBoundsExceptionWhenThereIsNoBasket(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;

        // When Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            basketController.getProducts(basketId);
        });
    }

    @Test
    void getReceiptShouldReturnHttpStatusOKWhenBasketWasCreated(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;
        basketController.createBasket();
        basketController.addProduct(basketId, new ProductRequest("Bread"));
        basketController.addProduct(basketId, new ProductRequest("Steak"));

        // When
        ResponseEntity responseEntity = basketController.getReceipt(basketId);

        // Then
        assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
    }

    @Test
    void getReceiptShouldThrowIndexOutOfBoundsExceptionWhenThereIsNoBasket(){
        // Given
        var basketController = new BasketController();
        var basketId = 0;

        // When Then
        assertThrows(IndexOutOfBoundsException.class, () -> {
            basketController.getReceipt(basketId);
        });
    }

}
