package br.com.api.agendamentobarbeariaapi.controller;

import java.time.OffsetDateTime;
import java.time.YearMonth;
import static java.time.ZoneOffset.UTC;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.api.agendamentobarbeariaapi.controller.request.SaveScheduleRequest;
import br.com.api.agendamentobarbeariaapi.controller.response.SaveScheduleResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.ScheduleAppointmentMonthResponse;
import br.com.api.agendamentobarbeariaapi.entities.ScheduleEntity;
import br.com.api.agendamentobarbeariaapi.mapper.IScheduleMapper;
import br.com.api.agendamentobarbeariaapi.service.IScheduleService;
import br.com.api.agendamentobarbeariaapi.service.query.IScheduleQueryService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("schedules")
@AllArgsConstructor
public class ScheduleController 
{
    private final IScheduleService service;
    private final IScheduleQueryService queryService;
    private final IScheduleMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    SaveScheduleResponse create(@RequestBody @Valid final SaveScheduleRequest request)
    {
        ScheduleEntity schedule = mapper.toEntity(request);
        service.create(schedule);
        return mapper.toSaveResponse(schedule);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void delete(@PathVariable final Long id)
    {
        service.delete(id);
    }

    @GetMapping("{year}/{month}")
    ScheduleAppointmentMonthResponse listMonth(@PathVariable final int year, @PathVariable final int month) {
        YearMonth yearMonth = YearMonth.of(year, month);
        OffsetDateTime startAt = yearMonth.atDay(1).atTime(0,0,0,0).atOffset(UTC);
        OffsetDateTime endAt = yearMonth.atEndOfMonth().atTime(23,59,59,999_999_999).atOffset(UTC);
        List<ScheduleEntity> schedules = queryService.findInMonth(startAt, endAt);
        return mapper.toMonthResponse(year, month, schedules);
    }
}