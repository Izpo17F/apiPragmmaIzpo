package pe.pragmma.store.store.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pe.pragmma.store.store.controller.dto.ProductItem;
import pe.pragmma.store.store.exception.EntityNotFoundException;
import pe.pragmma.store.store.repository.ProductRepository;
import pe.pragmma.store.store.repository.entity.ProductEntity;
import pe.pragmma.store.store.service.ProductService;
import pe.pragmma.store.store.service.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public List<ProductItem> list() {
        return ProductMapper.toDtoList(productRepository.findAll());
    }

    public ProductItem getById(Integer id){
        return ProductMapper.toDto(productRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("El producto no existe")
        ));
    }

    public Optional<ProductItem> update(Integer id, ProductItem productItem){
        Optional<ProductEntity> product = productRepository.findById(id);
        if(product.isEmpty()){
            throw new EntityNotFoundException("El producto no existe");
        }
        else if(Boolean.FALSE.equals(productItem.getActive())){
            throw new EntityNotFoundException("El producto con el id " + id + " fue eliminado");
        }
        else {
            ProductEntity productEntity = ProductMapper.toEntity(productItem, product.get());
            return Optional.of(ProductMapper.toDto(productRepository.save(productEntity)));
        }
    }

    public Optional<ProductItem> create(ProductItem productItem){
        ProductEntity productEntity = ProductMapper.toEntity(productItem, new ProductEntity());
        return Optional.of(ProductMapper.toDto(productRepository.save(productEntity)));
    }

    public void delete(Integer id){
        Optional<ProductEntity> product = productRepository.findByIdAndActive(id, true);
        if(product.isEmpty()){
            throw new EntityNotFoundException("El producto con el id " + id + " Ya fue elimiando");
        }
        else {
            ProductEntity productEntity = product.get();
            productEntity.setActive(false);
            productRepository.save(productEntity);
        }

    }
}
