package desafio_codigo.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class VisitanteResponse {

    @Schema(description = "ID Visitante", example = "2")
    private Long idPessoa;

    @Schema(description = "Nome", example = "Luiz Carlos da Silva Santos")
    private String nome;

    @Schema(description = "CPF", example = "015.036.114-68")
    private String cpf;

    @Schema(description = "Data de Nascimento", example = "12/05/1986")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Schema(description = "Senha", example = "3525@85#ty9")
    private String senhaOnline;

}