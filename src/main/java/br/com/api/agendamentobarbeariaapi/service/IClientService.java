package br.com.api.agendamentobarbeariaapi.service;

import br.com.api.agendamentobarbeariaapi.entities.ClientEntity;

public interface IClientService 
{
    ClientEntity create(final ClientEntity client);
    ClientEntity update(final ClientEntity client);
    void delete(final Long id);
}