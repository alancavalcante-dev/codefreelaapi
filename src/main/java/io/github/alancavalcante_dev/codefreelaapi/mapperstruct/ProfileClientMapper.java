package io.github.alancavalcante_dev.codefreelaapi.mapperstruct;

import io.github.alancavalcante_dev.codefreelaapi.dto.ProfileClientInsertRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.ProfileClientResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.ProfileClientUpdateRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileClientMapper {


    ProfileClient toEntity(ProfileClientInsertRequestDTO client);

    ProfileClient toEntityUpdate(ProfileClientUpdateRequestDTO client);

    ProfileClientInsertRequestDTO toRequestDTO(ProfileClient client);

    ProfileClientResponseDTO toResponseDTO(ProfileClient client);

}
