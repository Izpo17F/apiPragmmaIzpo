package pe.pragmma.store.store.service.mapper;

import org.springframework.stereotype.Component;
import pe.pragmma.store.store.controller.dto.UserDto;
import pe.pragmma.store.store.repository.entity.UserEntity;

import java.util.List;

@Component
public class UserMapper {

    //Este metodo no mapea la password dado que la vamos a hashear en el userService
    public static UserEntity toEntity(UserDto userDto){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDto.getUsername());
        userEntity.setFirstName(userDto.getFirstName());
        userEntity.setLastName(userDto.getLastName());
        userEntity.setDocNumber(userDto.getDocNumber());
        return userEntity;
    }
    public static UserDto toDto(UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setUsername(userEntity.getUsername());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setPassword(userEntity.getPassword());
        userDto.setLastName(userEntity.getLastName());
        userDto.setDocTypeId(userEntity.getDocType().getId());
        userDto.setDocNumber(userEntity.getDocNumber());
        userDto.setRoleId(userEntity.getRole().getId());
        return userDto;
    }

    public static List<UserDto> toListDto(List<UserEntity> userEntities){
        return userEntities.stream()
                .map(UserMapper::toDto)
                .toList();

    }
}
