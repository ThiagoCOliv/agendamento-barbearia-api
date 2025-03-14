package br.com.api.agendamentobarbeariaapi.service.query.impl;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.api.agendamentobarbeariaapi.entities.ClientEntity;
import br.com.api.agendamentobarbeariaapi.exception.NotFoundException;
import br.com.api.agendamentobarbeariaapi.exception.PhoneInUseException;
import br.com.api.agendamentobarbeariaapi.repository.IClientRepository;
import br.com.api.agendamentobarbeariaapi.service.query.IClientQueryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ClientQueryService implements IClientQueryService
{
    private final IClientRepository repository;

    @Override
    public ClientEntity findById(Long id) 
    {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Cliente não foi encontrado"));
    }

    @Override
    public List<ClientEntity> listClients() {
        return repository.findAll();
    }

    @Override
    public void verifyPhone(String phone) {
        if(repository.existsByPhone(phone))
        {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyPhone(Long id, String phone) {
        Optional<ClientEntity> optional = repository.findByPhone(phone);

        if(optional.isPresent() && !Objects.equals(optional.get().getPhone(), phone))
        {
            throw new PhoneInUseException("O telefone " + phone + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(String email) {
        if(repository.existsByEmail(email))
        {
            throw new PhoneInUseException("O email " + email + " já está em uso");
        }
    }

    @Override
    public void verifyEmail(Long id, String email) {
        Optional<ClientEntity> optional = repository.findByEmail(email);

        if(optional.isPresent() && !Objects.equals(optional.get().getEmail(), email))
        {
            throw new PhoneInUseException("O email " + email + " já está em uso");
        }
    }
    
}