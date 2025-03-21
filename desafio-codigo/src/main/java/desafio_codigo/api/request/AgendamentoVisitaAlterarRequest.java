package desafio_codigo.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AgendamentoVisitaAlterarRequest {

    @Schema(description = "Data", example = "15/06/2025")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private String novaData;

    @JsonFormat(pattern = "HH:mm")
    @Schema(description = "Hora", example = "12:00")
    private String novoHorario;
}
