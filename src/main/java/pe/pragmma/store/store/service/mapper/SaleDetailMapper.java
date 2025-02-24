package pe.pragmma.store.store.service.mapper;

import org.springframework.stereotype.Component;
import pe.pragmma.store.store.controller.dto.SaleDetailDto;
import pe.pragmma.store.store.repository.entity.SaleDetailEntity;

import java.util.List;

@Component
public class SaleDetailMapper {

    public static SaleDetailEntity toEntity(SaleDetailDto saleDetailDto){
        SaleDetailEntity saleDetailEntity = new SaleDetailEntity();
        saleDetailEntity.setQuantity(saleDetailDto.getQuantity());
        saleDetailEntity.setObservation(saleDetailDto.getObservation());
        saleDetailEntity.setActive(saleDetailDto.getActive());
        return saleDetailEntity;
    }

    public static SaleDetailDto toDto(SaleDetailEntity saleDetailEntity){
        SaleDetailDto saleDetailDto = new SaleDetailDto();
        saleDetailDto.setId(saleDetailEntity.getId());
        saleDetailDto.setSaleId(saleDetailEntity.getSale().getId());
        saleDetailDto.setProductId(saleDetailEntity.getProduct().getId());
        saleDetailDto.setQuantity(saleDetailEntity.getQuantity());
        saleDetailDto.setObservation(saleDetailEntity.getObservation());
        saleDetailDto.setActive(saleDetailEntity.getActive());
        return saleDetailDto;
    }

    public static List<SaleDetailDto> toListDto(List<SaleDetailEntity> saleDetailEntityList){
        return saleDetailEntityList.stream()
                .map(SaleDetailMapper::toDto)
                .toList();
    }
}
