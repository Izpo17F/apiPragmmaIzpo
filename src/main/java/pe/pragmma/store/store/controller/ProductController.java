package pe.pragmma.store.store.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.pragmma.store.store.controller.dto.ProductItem;
import pe.pragmma.store.store.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductItem>> listProduct(){
        List<ProductItem> items = productService.list();
        return new ResponseEntity<>(items, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductItem> getProductById(Integer id){
        ProductItem item = productService.getById(id);
        return new ResponseEntity<>(item, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<ProductItem> createProduct(@RequestBody ProductItem productItem){
        return new ResponseEntity<>(productService.create(productItem).orElseThrow(), HttpStatus.CREATED);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ProductItem> updateProduct(@PathVariable("id") Integer id, @RequestBody ProductItem productItem){
        return new ResponseEntity<>(productService.update(id, productItem).orElseThrow(), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id){
        productService.delete(id);
        return new ResponseEntity<>("Producto borrado con el id " + id ,HttpStatus.OK);
    }
}
