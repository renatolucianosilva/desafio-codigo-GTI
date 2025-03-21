package desafio_codigo.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import desafio_codigo.config.FormatarCpf;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

@Data
public class AgendamentoVisitaRequest {

    @NotNull(message = "CPF is Not Null")
    @NotEmpty(message = "CPF  is Not Empty")
    @Schema(description = "CPF", example = "015.036.114-68")
    @Pattern(regexp = "^\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}$", message = "CPF deve estar no formato XXX.XXX.XXX-XX ou XXXXXXXXXXX")
    @Length(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres")
    private String visitanteCpf;

    @NotNull(message = "Numero Prontuário is Not Null")
    @NotEmpty(message = "Numero Prontuário is Not Empty")
    @Pattern(regexp = "^\\d+$", message = "Somente Numeros.")
    @Schema(description = "Numero de Prontuário", example = "123456")
    @Length(min = 4, max = 8, message = "Numero de Prontuário deve ter entre 4 e 8 caracteres")
    private String numeroProntuario;


    @Schema(description = "Data", example = "05/04/2025")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String dataAgendamento;

    @JsonFormat(pattern = "HH:mm")
    @Schema(description = "Hora", example = "10:00")
    private String horaAgendamento;


}
