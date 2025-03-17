package desafio_codigo.modell;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class AgendamentoVisita {

    private String id;
    private Custodiado custodiadoId;
    private Visitante visitanteId;
    private LocalDateTime dataHoraAgendamento;
    private Status statusId;

}
