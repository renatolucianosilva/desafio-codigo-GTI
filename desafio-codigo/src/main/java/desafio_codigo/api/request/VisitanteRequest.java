package desafio_codigo.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VisitanteRequest {

    private String nome;
    @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "CPF inválido")
    private String cpf;
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;
    private String senhaOnline;

}
