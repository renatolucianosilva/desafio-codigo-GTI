package desafio_codigo.modell;

import desafio_codigo.api.request.AgendamentoVisitaRequest;
import desafio_codigo.exceptions.BadRequestException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@Entity
@Table(name="agendamento_visita")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AgendamentoVisita {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_agendamento")
    private Long idAgendamento;

    @ManyToOne
    @JoinColumn(name = "id_custodiado")
    private Custodiado custodiado;

    @ManyToOne
    @JoinColumn(name = "visitante_id")
    private Visitante visitante;

    @Column(name = "data_hora_agendamento")
    private LocalDateTime dataHoraAgendamento;

    @ManyToOne
    @JoinColumn(name="status_id")
    private Status status;




    public AgendamentoVisita cancelarVisita(Status status) {

        if(this.status.getDescricao().equals("CANCELADO"))
            throw new BadRequestException("Agendamento com Status de Cancelado");
        this.status = status;


        return this;
    }

    public AgendamentoVisita alteraDataHoraVisita(LocalDateTime dataHoraAgendamento) {
        this.dataHoraAgendamento = dataHoraAgendamento;
        return this;
    }



}
