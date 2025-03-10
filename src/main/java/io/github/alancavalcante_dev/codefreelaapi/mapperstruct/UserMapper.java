package io.github.alancavalcante_dev.codefreelaapi.mapperstruct;


import io.github.alancavalcante_dev.codefreelaapi.dto.UserRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.UserResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserRequestDTO toRequestDTO(User user);

    UserResponseDTO toResponseDTO(User user);

    User toEntity(UserRequestDTO userDTO);

}