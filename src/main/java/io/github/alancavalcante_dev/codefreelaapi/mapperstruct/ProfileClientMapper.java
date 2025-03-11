package io.github.alancavalcante_dev.codefreelaapi.mapperstruct;

import io.github.alancavalcante_dev.codefreelaapi.dto.ProfileClientDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileClientMapper {

    ProfileClient toEntity(ProfileClientDTO profileClientResponseDTO);

    @Mapping(target = "user", source = "user")
    ProfileClientDTO toResponseDTO(ProfileClient profileClient);

}
