package com.virtuslab.internship.discount;

import com.virtuslab.internship.product.ProductDb;
import com.virtuslab.internship.receipt.Receipt;
import com.virtuslab.internship.receipt.ReceiptEntry;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FifteenPercentDiscountTest {

    @Test
    void shouldApply15PercentDiscountWhenMoreThen3GrainProducts(){
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntryList = new ArrayList<>();
        receiptEntryList.add(new ReceiptEntry(bread, 2));
        receiptEntryList.add(new ReceiptEntry(cereals, 2));

        var receipt = new Receipt(receiptEntryList);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2))
                .add(cereals.price().multiply(BigDecimal.valueOf(2)))
                .multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldNotApply15PercentDiscountWhenLessThen3GrainProducts(){
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        List<ReceiptEntry> receiptEntryList = new ArrayList<>();
        receiptEntryList.add(new ReceiptEntry(bread, 2));

        var receipt = new Receipt(receiptEntryList);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(0, receiptAfterDiscount.discounts().size());
    }

    @Test
    void shouldApply15PercentDiscountWhen3GrainProducts(){
        // Given
        var productDb = new ProductDb();
        var bread = productDb.getProduct("Bread");
        var cereals = productDb.getProduct("Cereals");
        List<ReceiptEntry> receiptEntryList = new ArrayList<>();
        receiptEntryList.add(new ReceiptEntry(bread, 2));
        receiptEntryList.add(new ReceiptEntry(cereals, 1));

        var receipt = new Receipt(receiptEntryList);
        var discount = new FifteenPercentDiscount();
        var expectedTotalPrice = bread.price().multiply(BigDecimal.valueOf(2))
                .add(cereals.price())
                .multiply(BigDecimal.valueOf(0.85));

        // When
        var receiptAfterDiscount = discount.apply(receipt);

        // Then
        assertEquals(expectedTotalPrice, receiptAfterDiscount.totalPrice());
        assertEquals(1, receiptAfterDiscount.discounts().size());
    }
}
