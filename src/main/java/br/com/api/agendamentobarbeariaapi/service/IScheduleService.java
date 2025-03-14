package br.com.api.agendamentobarbeariaapi.service;

import br.com.api.agendamentobarbeariaapi.entities.ScheduleEntity;

public interface IScheduleService 
{
    ScheduleEntity create(final ScheduleEntity schedule);
    void delete(final Long id);
}