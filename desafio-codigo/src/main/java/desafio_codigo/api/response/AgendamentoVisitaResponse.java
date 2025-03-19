package desafio_codigo.api.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AgendamentoVisitaResponse {


    private CustodiadoResponse custodiado;
    private VisitanteResponse visitante;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataHoraAgendamento;
    private StatusResponse status;


}