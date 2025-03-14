package br.com.api.agendamentobarbeariaapi.service.query;

import java.time.OffsetDateTime;
import java.util.List;

import br.com.api.agendamentobarbeariaapi.entities.ScheduleEntity;

public interface IScheduleQueryService
{
    ScheduleEntity findById(final Long id);
    List<ScheduleEntity> findInMonth(final OffsetDateTime startAt, final OffsetDateTime endAt);
    void verifyIfScheduleExists(final OffsetDateTime startAt, final OffsetDateTime endAt);
}