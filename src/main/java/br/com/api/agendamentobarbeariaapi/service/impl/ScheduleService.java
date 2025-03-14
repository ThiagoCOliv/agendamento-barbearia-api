package br.com.api.agendamentobarbeariaapi.service.impl;

import org.springframework.stereotype.Service;

import br.com.api.agendamentobarbeariaapi.entities.ScheduleEntity;
import br.com.api.agendamentobarbeariaapi.repository.IScheduleRepository;
import br.com.api.agendamentobarbeariaapi.service.IScheduleService;
import br.com.api.agendamentobarbeariaapi.service.query.IScheduleQueryService;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ScheduleService implements IScheduleService
{
    private final IScheduleRepository repository;
    private final IScheduleQueryService queryService;

    @Override
    public ScheduleEntity create(ScheduleEntity schedule) {
        queryService.verifyIfScheduleExists(schedule.getStartAt(), schedule.getEndAt());
        return repository.save(schedule);
    }

    @Override
    public void delete(Long id) {
        queryService.findById(id);
        repository.deleteById(id);
    }
    
}