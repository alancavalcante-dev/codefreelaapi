package io.github.alancavalcante_dev.codefreelaapi.mapperstruct;

import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileClientInsertRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileClientResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.client.ProfileClientUpdateRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileClient;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileClientMapper {


    ProfileClient toEntity(ProfileClientInsertRequestDTO client);

    ProfileClient toEntityUpdate(ProfileClientUpdateRequestDTO client);

    ProfileClientInsertRequestDTO toRequestDTO(ProfileClient client);

    ProfileClientResponseDTO toResponseDTO(ProfileClient client);

}
