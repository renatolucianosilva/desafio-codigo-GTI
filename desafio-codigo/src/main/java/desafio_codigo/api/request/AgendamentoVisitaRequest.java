package desafio_codigo.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoVisitaRequest {

    private Long idCustodiado;
    private Long idVisitante;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime dataHoraAgendamento;


}
