package com.virtuslab.internship.application;

import com.virtuslab.internship.product.Product;
import com.virtuslab.internship.product.ProductRequest;
import com.virtuslab.internship.receipt.Receipt;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/basket")
public class BasketController {

    private final BasketService basketService = new BasketService();

    @PostMapping("create")
    public ResponseEntity<Integer> createBasket(){
        return new ResponseEntity<>(basketService.createBasket() ,HttpStatus.CREATED);
    }

    @PutMapping("{basketId}/add-product")
    public ResponseEntity addProduct(@PathVariable("basketId") Integer basketId, @RequestBody ProductRequest productRequest){
        basketService.addProduct(basketId, productRequest);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("{basketId}/remove-product")
    public ResponseEntity removeProduct(@PathVariable("basketId") Integer basketId, @RequestBody ProductRequest productRequest){
        if(basketService.removeProduct(basketId, productRequest))
            return new ResponseEntity(HttpStatus.OK);
        else
            return new ResponseEntity(HttpStatus.NOT_FOUND);

    }

    @GetMapping("{basketId}/get-products")
    public ResponseEntity<List<Product>> getProducts(@PathVariable("basketId") Integer basketId){
        return new ResponseEntity<>(basketService.getProducts(basketId), HttpStatus.OK);
    }

    @GetMapping("{basketId}/get-receipt")
    public ResponseEntity<Receipt> getReceipt(@PathVariable("basketId") Integer basketId){
        return new ResponseEntity<>(basketService.getReceipt(basketId), HttpStatus.OK);
    }
}
