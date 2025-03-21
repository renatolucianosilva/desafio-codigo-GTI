package desafio_codigo.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import desafio_codigo.modell.UnidadePenal;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@Builder
public class CustodiadoResponse {


    @Schema(description = "ID Visitante", example = "2")
    private Long idPessoa;

    @Schema(description = "Nome", example = "Luiz Carlos da Silva Santos")
    private String nome;

    @Schema(description = "CPF", example = "015.036.114-68")
    private String cpf;

    @Schema(description = "Data de Nascimento", example = "12/05/1986")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Schema(description = "Numero de Prontuário", example = "123456")
    private String numeroProntuario;

    @Schema(description = "Vulgo", example = "Zezinho")
    private String vulgo;

    private UnidadePenalResponse unidadePenal;

}