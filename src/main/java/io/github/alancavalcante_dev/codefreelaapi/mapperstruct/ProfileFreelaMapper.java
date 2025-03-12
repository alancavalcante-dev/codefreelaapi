package io.github.alancavalcante_dev.codefreelaapi.mapperstruct;

import io.github.alancavalcante_dev.codefreelaapi.dto.freela.ProfileFreelaInsertRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.freela.ProfileFreelaResponseDTO;
import io.github.alancavalcante_dev.codefreelaapi.dto.freela.ProfileFreelaUpdateRequestDTO;
import io.github.alancavalcante_dev.codefreelaapi.model.ProfileFreela;
import org.mapstruct.Mapper;



@Mapper(componentModel = "spring")
public interface ProfileFreelaMapper {


    ProfileFreela toEntity(ProfileFreelaInsertRequestDTO client);

    ProfileFreela toEntityUpdate(ProfileFreelaUpdateRequestDTO client);

    ProfileFreelaInsertRequestDTO toRequestDTO(ProfileFreela client);

    ProfileFreelaResponseDTO toResponseDTO(ProfileFreela client);

}