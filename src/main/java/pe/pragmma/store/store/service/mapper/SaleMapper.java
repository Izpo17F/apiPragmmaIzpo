package pe.pragmma.store.store.service.mapper;

import org.springframework.stereotype.Component;
import pe.pragmma.store.store.controller.dto.SaleDto;
import pe.pragmma.store.store.repository.entity.SaleEntity;

import java.util.List;

@Component
public class SaleMapper {

    public static SaleEntity toEntity(SaleDto saleDto){
        SaleEntity saleEntity = new SaleEntity();
        saleEntity.setIssueDate(saleDto.getIssueDate());
        saleEntity.setObservation(saleDto.getObservation());
        saleEntity.setActive(saleDto.getActive());
        return saleEntity;
    }
    public static SaleDto toDto(SaleEntity saleEntity){
        SaleDto saleDto = new SaleDto();
        saleDto.setId(saleEntity.getId());
        saleDto.setIssueDate(saleEntity.getIssueDate());
        saleDto.setObservation(saleEntity.getObservation());
        saleDto.setActive(saleEntity.getActive());
        return saleDto;
    }
    public static List<SaleDto> toListDto(List<SaleEntity> saleEntityList){
        return saleEntityList.stream()
                .map(SaleMapper::toDto)
                .toList();
    }
}
