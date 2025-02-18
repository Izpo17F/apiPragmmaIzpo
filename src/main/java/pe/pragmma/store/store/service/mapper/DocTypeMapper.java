package pe.pragmma.store.store.service.mapper;

import org.springframework.stereotype.Component;
import pe.pragmma.store.store.controller.dto.DocTypeDto;
import pe.pragmma.store.store.repository.entity.DocTypeEntity;

import java.util.List;

@Component
public class DocTypeMapper {
    public static DocTypeEntity toEntity(DocTypeDto docTypeDto){
        DocTypeEntity docTypeEntity = new DocTypeEntity();
        docTypeEntity.setName(docTypeDto.getName());
        docTypeEntity.setDescription(docTypeDto.getDescription());
        return docTypeEntity;
    }
    public static DocTypeDto toDto(DocTypeEntity docTypeEntity){
        DocTypeDto docTypeDto = new DocTypeDto();
        docTypeDto.setId(docTypeEntity.getId());
        docTypeDto.setName(docTypeEntity.getName());
        docTypeDto.setDescription(docTypeEntity.getDescription());
        return docTypeDto;
    }
    public static List<DocTypeDto> toListDto(List<DocTypeEntity> docTypeEntities){
        return docTypeEntities.stream()
                .map(DocTypeMapper::toDto)
                .toList();
    }
}
