package br.com.api.agendamentobarbeariaapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import br.com.api.agendamentobarbeariaapi.controller.request.SaveClientRequest;
import br.com.api.agendamentobarbeariaapi.controller.request.UpdateClientRequest;
import br.com.api.agendamentobarbeariaapi.controller.response.ClientDetailResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.ListClientResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.SaveClientResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.UpdateClientResponse;
import br.com.api.agendamentobarbeariaapi.entities.ClientEntity;

@Mapper(componentModel = SPRING)
public interface IClientMapper
{
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final SaveClientRequest request);

    SaveClientResponse toSaveResponse(final ClientEntity client);

    UpdateClientResponse toUpdateResponse(final ClientEntity client);

    @Mapping(target = "schedules", ignore = true)
    ClientEntity toEntity(final Long id, final UpdateClientRequest request);

    ClientDetailResponse toDetailResponse(final ClientEntity client);

    List<ListClientResponse> toListResponse(final List<ClientEntity> clients);
}