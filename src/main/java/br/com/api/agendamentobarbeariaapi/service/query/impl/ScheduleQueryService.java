package br.com.api.agendamentobarbeariaapi.service.query.impl;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.api.agendamentobarbeariaapi.entities.ScheduleEntity;
import br.com.api.agendamentobarbeariaapi.exception.NotFoundException;
import br.com.api.agendamentobarbeariaapi.exception.ScheduleInUseException;
import br.com.api.agendamentobarbeariaapi.repository.IScheduleRepository;
import br.com.api.agendamentobarbeariaapi.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleQueryService implements IScheduleQueryService
{
    private final IScheduleRepository repository;

    @Override
    public ScheduleEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Agendamento não foi encontrado"));
    }

    @Override
    public List<ScheduleEntity> findInMonth(OffsetDateTime startAt, OffsetDateTime endAt) {
        return repository.findByStartAtGreaterThanEqualAndEndAtLessThanEqualOrderByStartAtAscEndAtAsc(startAt, endAt);
    }

    @Override
    public void verifyIfScheduleExists(OffsetDateTime startAt, OffsetDateTime endAt) {
        if(repository.existsByStartAtAndEndAt(startAt, endAt))
        {
            throw new ScheduleInUseException("Não é possível agendar o atendimento para este horário");
        }
    }
    
}
