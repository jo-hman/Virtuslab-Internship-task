package com.virtuslab.internship.receipt;

import com.virtuslab.internship.product.Product;

import java.math.BigDecimal;

public record ReceiptEntry(
        Product product,
        long quantity,
        BigDecimal totalPrice) {

    public ReceiptEntry(Product product, long quantity) {
        this(product, quantity, product.price().multiply(BigDecimal.valueOf(quantity)));
    }
}
