package com.NgocHieu.Buoi22.controller;

import com.NgocHieu.Buoi22.service.NhaSanXuatService;
import com.NgocHieu.Buoi22.viewmodels.BookGetVm;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//package com.NgocHieu.Buoi22.controller;
//
//import com.NgocHieu.Buoi22.viewmodels.ProductGetVm;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@CrossOrigin
//@RestController
//@RequestMapping("/api/products")
//public class ApiController {
//    @Autowired
//    private ProductService productService;
//    @GetMapping
//    public List<Product> getAllProducts() {
//        return productService.getAllProducts().stream().toList();
//    }
//
//        @PostMapping
//    public Product createProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
//        Product product = productService.getProductById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found on :: "
//                        + id));
//        return ResponseEntity.ok().body(product);
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
//                                                 @RequestBody Product productDetails) {
//        Product product = productService.getProductById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found on :: "
//                        + id));
//        product.setName(productDetails.getName());
//        product.setPrice(productDetails.getPrice());
//        product.setThumnail(String.valueOf(productDetails.getNums()));
//        final Product updatedProduct =productService.addProduct(product);
//        return ResponseEntity.ok(updatedProduct);
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
//        Product product = productService.getProductById(id)
//                .orElseThrow(() -> new RuntimeException("Product not found on :: "
//                        + id));
//        productService.deleteProductById(id);
//        return ResponseEntity.ok().build();
//    }
//}
@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ApiController {

    private final NhaSanXuatService cartService;
    @GetMapping("/books")
    public ResponseEntity<List<BookGetVm>> getAllBooks(Integer pageNo,
                                                       Integer pageSize, String sortBy) {
        return ResponseEntity.ok(cartService.getAllNSX(
                pageNo == null ? 0 : pageNo,
                        pageSize == null ? 20 : pageSize,
                        sortBy == null ? "id" : sortBy)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }
    @GetMapping("/books/{id}")
    public ResponseEntity<BookGetVm> cartService(@PathVariable Long id)
    {
        return ResponseEntity.ok(cartService.getLoaiNSX(id)
                .map(BookGetVm::from)
                .orElse(null));
    }
    @DeleteMapping("/books/{id}")
    public ResponseEntity<Void> deleteBookById(@PathVariable Long id) {
        cartService.deleteNSX(id);
        return ResponseEntity.ok().build();
    }
    /*@GetMapping("/books/search")
    public ResponseEntity<List<BookGetVm>> searchBooks(String keyword)
    {
        return ResponseEntity.ok(bookService.searchBook(keyword)
                .stream()
                .map(BookGetVm::from)
                .toList());
    }*/
}