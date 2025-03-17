package br.com.api.agendamentobarbeariaapi.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

import br.com.api.agendamentobarbeariaapi.controller.request.SaveScheduleRequest;
import br.com.api.agendamentobarbeariaapi.controller.response.ClientScheduleAppointmentResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.SaveScheduleResponse;
import br.com.api.agendamentobarbeariaapi.controller.response.ScheduleAppointmentMonthResponse;
import br.com.api.agendamentobarbeariaapi.entities.ScheduleEntity;

@Mapper(componentModel = SPRING)
public interface IScheduleMapper 
{
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "client.id", source = "clientId")
    ScheduleEntity toEntity(final SaveScheduleRequest request);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "day", expression = "java(schedule.getStartAt().getDayOfMonth())")
    SaveScheduleResponse toSaveResponse(final ScheduleEntity schedule);

    @Mapping(target = "scheduledAppointments", expression = "java(toClientMonthResponse(schedules))")
    ScheduleAppointmentMonthResponse toMonthResponse(final int year, final int month, final List<ScheduleEntity> schedules);
    
    List<ClientScheduleAppointmentResponse> toClientMonthResponse(final List<ScheduleEntity> schedules);

    @Mapping(target = "clientId", source = "client.id")
    @Mapping(target = "clientName", source = "client.name")
    @Mapping(target = "day", expression = "java(schedule.getStartAt().getDayOfMonth())")
    ClientScheduleAppointmentResponse toClientMonthResponse(final ScheduleEntity schedule);
}