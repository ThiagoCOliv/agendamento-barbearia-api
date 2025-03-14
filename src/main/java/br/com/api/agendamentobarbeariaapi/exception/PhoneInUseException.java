package br.com.api.agendamentobarbeariaapi.exception;

public class PhoneInUseException extends RuntimeException
{
    public PhoneInUseException(String message){ super(message); }
}
