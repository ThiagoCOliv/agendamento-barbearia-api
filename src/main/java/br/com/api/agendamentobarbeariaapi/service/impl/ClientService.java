package br.com.api.agendamentobarbeariaapi.service.impl;

import org.springframework.stereotype.Service;

import br.com.api.agendamentobarbeariaapi.entities.ClientEntity;
import br.com.api.agendamentobarbeariaapi.repository.IClientRepository;
import br.com.api.agendamentobarbeariaapi.service.IClientService;
import br.com.api.agendamentobarbeariaapi.service.query.IClientQueryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientService implements IClientService
{
    private final IClientRepository repository;
    private final IClientQueryService queryService;

    @Override
    public ClientEntity create(ClientEntity client) {
        queryService.verifyEmail(client.getEmail());
        queryService.verifyPhone(client.getPhone());

        return repository.save(client);
    }
    
    @Override
    public ClientEntity update(ClientEntity client) {
        queryService.verifyEmail(client.getId(), client.getEmail());
        queryService.verifyPhone(client.getId(), client.getPhone());

        ClientEntity stored = queryService.findById(client.getId());
        stored.setName(client.getName());
        stored.setEmail(client.getEmail());
        stored.setPhone(client.getPhone());

        return repository.save(stored);
    }
    
    @Override
    public void delete(Long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
}