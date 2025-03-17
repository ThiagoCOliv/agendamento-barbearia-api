package br.com.api.agendamentobarbeariaapi.controller;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.agendamentobarbeariaapi.controller.request.SaveClientRequest;
import br.com.api.agendamentobarbeariaapi.controller.request.UpdateClientRequest;
import br.com.api.agendamentobarbeariaapi.controller.response.ClientDetailResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.ListClientResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.SaveClientResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.UpdateClientResponse;
import br.com.api.agendamentobarbeariaapi.entities.ClientEntity;
import br.com.api.agendamentobarbeariaapi.mapper.IClientMapper;
import br.com.api.agendamentobarbeariaapi.service.IClientService;
import br.com.api.agendamentobarbeariaapi.service.query.IClientQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("clients")
@AllArgsConstructor
public class ClientController
{
    private final IClientService service;
    private final IClientQueryService queryService;
    private final IClientMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveClientResponse create(@RequestBody @Valid final SaveClientRequest request)
    {
        ClientEntity client = mapper.toEntity(request);
        service.create(client);
        return mapper.toSaveResponse(client);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final Long id)
    {
        service.delete(id);
    }

    @PutMapping("{id}")
    UpdateClientResponse update(@PathVariable final Long id, @RequestBody @Valid final UpdateClientRequest request) 
    {
        ClientEntity client = mapper.toEntity(id, request);
        service.update(client);
        return mapper.toUpdateResponse(client);
    }

    @GetMapping("{id}")
    ClientDetailResponse findById(@PathVariable final Long id) 
    {
        ClientEntity client = queryService.findById(id);
        return mapper.toDetailResponse(client);
    }
    
    @GetMapping
    List<ListClientResponse> list() 
    {
        List<ClientEntity> clients = queryService.listClients();
        return mapper.toListResponse(clients);
    }
}