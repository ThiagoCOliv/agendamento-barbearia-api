package br.com.api.agendamentobarbeariaapi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.api.agendamentobarbeariaapi.entities.ClientEntity;

@Repository
public interface IClientRepository extends JpaRepository<ClientEntity, Long>
{
    boolean existsByEmail(final String email);
    Optional<ClientEntity> findByEmail(final String email);

    boolean existsByPhone(final String phone);
    Optional<ClientEntity> findByPhone(final String phone);
}