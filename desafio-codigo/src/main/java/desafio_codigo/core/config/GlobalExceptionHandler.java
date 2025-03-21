package desafio_codigo.core.config;

import desafio_codigo.api.response.ErroResponse;
import desafio_codigo.exceptions.BadRequestException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErroResponse> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .reduce("", (acc, error) -> acc + (acc.isEmpty() ? "" : ", ") + error);

        ErroResponse erro = new ErroResponse(HttpStatus.BAD_REQUEST.value(), "Erro de validação: " + errorMessage);
        return ResponseEntity.badRequest().body(erro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErroResponse> handleConstraintViolationException(ConstraintViolationException ex) {
        String errorMessage = ex.getConstraintViolations().stream()
                .findFirst()
                .map(violation -> violation.getMessage())
                .orElse("Erro de validação.");

        ErroResponse erro = new ErroResponse(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return ResponseEntity.badRequest().body(erro);
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResponse> handleGenericException(Exception ex) {
        ErroResponse erro = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Erro interno do servidor");
        ex.printStackTrace();
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erro);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErroResponse> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        String errorMessage = "Erro de integridade de dados: " + ex.getMessage();

        // Você pode personalizar a mensagem de erro com base no tipo de violação
        if (ex.getMessage().contains("unique constraint")) {
            errorMessage = "Violação de chave única: " + ex.getMessage();
        } else if (ex.getMessage().contains("foreign key constraint")) {
            errorMessage = "Violação de chave estrangeira: " + ex.getMessage();
        }

        ErroResponse erro = new ErroResponse(HttpStatus.CONFLICT.value(), errorMessage);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(erro);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ErroResponse> handleResponseStatusException(ResponseStatusException ex) {
        ErroResponse erro = new ErroResponse(ex.getStatusCode().value(), ex.getReason());
        return ResponseEntity.status(ex.getStatusCode()).body(erro);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroResponse> handleBadRequestException(BadRequestException ex) {
        ErroResponse erro = new ErroResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        ErroResponse erro = new ErroResponse(HttpStatus.BAD_REQUEST.value(), "Corpo da requisição inválido ou mal formatado.");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}