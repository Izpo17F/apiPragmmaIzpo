package pe.pragmma.store.store.service;


import pe.pragmma.store.store.controller.dto.ProductItem;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductItem> list();
    ProductItem getById(Integer id);
    Optional<ProductItem> update(Integer id, ProductItem productItem);
    Optional<ProductItem> create(ProductItem productItem);
    void delete(Integer id);
}
