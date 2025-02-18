package pe.pragmma.store.store.service.mapper;

import org.springframework.stereotype.Component;
import pe.pragmma.store.store.controller.dto.RoleDto;
import pe.pragmma.store.store.repository.entity.RoleEntity;

import java.util.List;

@Component
public class RoleMapper {
    public static RoleEntity toEntity(RoleDto roleDto){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName(roleDto.getName());
        roleEntity.setDescription(roleDto.getDescription());
        return roleEntity;
    }
    public static RoleDto toDto(RoleEntity roleEntity){
        RoleDto roleDto = new RoleDto();
        roleDto.setId(roleEntity.getId());
        roleDto.setName(roleEntity.getName());
        roleDto.setDescription(roleEntity.getDescription());
        return roleDto;
    }
    public static List<RoleDto> toListDto(List<RoleEntity> roleEntities){
        return roleEntities.stream()
                .map(RoleMapper::toDto)
                .toList();
    }
}
