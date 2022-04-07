package com.virtuslab.internship.application;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.product.ProductRequest;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptGenerator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class BasketService {

    private final ProductDb productDb = new ProductDb();
    private final List<Basket> basketsDb = new ArrayList<>(); //simulates Basket database

    public int createBasket() {
        basketsDb.add(new Basket());
        return basketsDb.size() - 1; //return index of a basket, subject to change when there is a real DB
    }

    public void addProduct(Integer basketId, ProductRequest productRequest) throws NoSuchElementException, IndexOutOfBoundsException{
        basketsDb.get(basketId).addProduct(productDb.getProduct(productRequest.productName()));
    }

    public boolean removeProduct(Integer basketId, ProductRequest productRequest) throws IndexOutOfBoundsException{
        return basketsDb.get(basketId)
                .getProducts()
                .remove(productDb.getProduct(productRequest.productName()));
    }

    public List<Product> getProducts(Integer basketId) throws IndexOutOfBoundsException {
        return basketsDb.get(basketId).getProducts();
    }

    public Receipt getReceipt(Integer basketId) throws IndexOutOfBoundsException{
        ReceiptGenerator receiptGenerator = new ReceiptGenerator();
        return receiptGenerator.generate(basketsDb.get(basketId));
    }
}
