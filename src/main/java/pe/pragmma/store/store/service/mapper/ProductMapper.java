package pe.pragmma.store.store.service.mapper;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;
import pe.pragmma.store.store.controller.dto.ProductItem;
import pe.pragmma.store.store.repository.entity.ProductEntity;

import java.util.List;

@Component
@NoArgsConstructor
public class ProductMapper {

    public static ProductEntity toEntity(ProductItem productItem, ProductEntity productEntity){
        productEntity.setName(productItem.getName());
        productEntity.setDescription(productItem.getDescription());
        productEntity.setPrice(productItem.getPrice());
        productEntity.setStorage(productItem.getStorage());
        return productEntity;
    }
    public static ProductItem toDto(ProductEntity productEntity){
        return new ProductItem(productEntity.getId(), productEntity.getName(),
                productEntity.getDescription(), productEntity.getPrice(),
                productEntity.getStorage(), productEntity.getActive());
    }

    public static List<ProductItem> toDtoList(List<ProductEntity> productEntities){
        return productEntities.stream().map(ProductMapper::toDto).toList();
    }
}
