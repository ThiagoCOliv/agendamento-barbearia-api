package br.com.api.agendamentobarbeariaapi.service.query;

import java.util.List;

import br.com.api.agendamentobarbeariaapi.entities.ClientEntity;

public interface IClientQueryService
{
    ClientEntity findById(final Long id);
    List<ClientEntity> listClients();

    void verifyPhone(final String phone);
    void verifyPhone(final Long id, final String phone);

    void verifyEmail(final String email);
    void verifyEmail(final Long id, final String email);
}