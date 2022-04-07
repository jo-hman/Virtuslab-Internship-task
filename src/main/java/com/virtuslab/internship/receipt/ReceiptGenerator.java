package com.virtuslab.internship.receipt;

import com.virtuslab.internship.basket.Basket;
import com.virtuslab.internship.discount.FifteenPercentDiscount;
import com.virtuslab.internship.discount.TenPercentDiscount;
import com.virtuslab.internship.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptGenerator {

    public Receipt generate(Basket basket) {
        List<ReceiptEntry> receiptEntries = new ArrayList<>();
        TenPercentDiscount tenPercentDiscount = new TenPercentDiscount();
        FifteenPercentDiscount fifteenPercentDiscount = new FifteenPercentDiscount();

        var products = basket.getProducts();

        for(Product product : products.stream().distinct().collect(Collectors.toList())){
            var quantity = products.stream()
                    .filter(product1 -> product1 == product)
                    .count();

            receiptEntries.add(new ReceiptEntry(product, quantity));
        }

        var receipt = new Receipt(receiptEntries);
        receipt = fifteenPercentDiscount.apply(receipt);
        receipt = tenPercentDiscount.apply(receipt);

        return receipt;
    }
}
