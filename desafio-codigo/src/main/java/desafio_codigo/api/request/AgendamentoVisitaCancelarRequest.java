package desafio_codigo.api.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendamentoVisitaCancelarRequest {

    private Long idAgendamento;



}
