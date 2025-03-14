package br.com.api.agendamentobarbeariaapi.exception;

public class ScheduleInUseException extends RuntimeException
{
    public ScheduleInUseException(String message){ super(message); }
}
