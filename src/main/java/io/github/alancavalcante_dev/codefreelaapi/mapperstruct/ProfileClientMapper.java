package io.github.alancavalcante_dev.codefreelaapi.mapperstruct;

import io.github.alancavalcante_dev.codefreelaapi.dto.ProfileClientRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.ProfileClientResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileClientMapper {

    ProfileClient toEntity(ProfileClientRequestDTO client);

    ProfileClientRequestDTO toRequestDTO(ProfileClient client);

    ProfileClientResponseDTO toResponseDTO(ProfileClient client);

}
