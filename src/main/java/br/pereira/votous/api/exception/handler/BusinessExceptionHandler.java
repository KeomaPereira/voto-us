package br.pereira.votous.api.exception.handler;

import br.pereira.votous.api.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity tratar (BusinessException e) {
        ApiError error = new ApiError(
                "voto_nao_efetivado",
                 e.getMessage(),
                LocalDateTime.now());

        return ResponseEntity.unprocessableEntity()
                .body(error);
    }
}