package br.com.api.agendamentobarbeariaapi.exceptionhandler;

import java.time.OffsetDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.api.agendamentobarbeariaapi.controller.response.ProblemResponse;
import lombok.extern.log4j.Log4j2;

@Log4j2
@ControllerAdvice
public class AgendamentoBarbeariaExceptionHandler extends ResponseEntityExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleUncaught(final Exception ex, final WebRequest request)
    {
        log.error("handleUncaught: ", ex);
        HttpStatus status = INTERNAL_SERVER_ERROR;
        ProblemResponse response = ProblemResponse.builder().status(status.value()).timestamp(OffsetDateTime.now()).message(ex.getMessage()).build();
        return handleExceptionInternal(ex, response, new HttpHeaders(), status, request);
    }
}