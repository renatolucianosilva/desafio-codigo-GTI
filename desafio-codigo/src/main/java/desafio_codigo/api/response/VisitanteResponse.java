package desafio_codigo.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VisitanteResponse {


    private Long idPessoa;

    private String nome;

    private String cpf;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private String senhaOnline;

}